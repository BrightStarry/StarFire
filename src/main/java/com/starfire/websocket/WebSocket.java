package com.starfire.websocket;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.starfire.service.TBarrageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.starfire.domain.TUser;
import com.starfire.dto.BarrageResult;
import com.starfire.dto.WebSocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 弹幕聊天后端 使用websocket
 */
// 一个是URI，还一个是配置器的类，用来提前取出httpSession
@ServerEndpoint(value = "/webSocket", 
	encoders = {MessageEncoder.class }, 
	decoders={MessageDecoder.class},
	configurator = GetHttpSessionConfigurator.class)
@Component
public class WebSocket{
	private static Logger LOGGER = LoggerFactory.getLogger(WebSocket.class);
	private HttpSession httpSession;
	private Session session;

	/**
	 * 建立连接时的操作 一个是websocket的session，一个是用来获取httpsession的config
	 */
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {

		//获取httpsession
		HttpSession httpSession = getHttpSession(config);
		this.httpSession = httpSession;
		this.session = session;
		
		// 保存this
		WebSocketUtil.addWebSocket(this);
	}

	/**
	 * 收到消息时的操作 将消息发给所有连接的用户
	 */
	@OnMessage
	public void onMessage(WebSocketMessage<String> webSocketMessage, Session session) throws Exception {
		//如果是弹幕类型
		if(webSocketMessage.getType() == 1){
			//执行弹幕类型的方法
			barrageType(webSocketMessage, session);
		}
	}

	/**
	 * 关闭连接时的操作
	 */
	@OnClose
	public void onClose(Session session) {
		
	}

	/**
	 * 发生异常
	 */
	@OnError
	public void onError(Throwable error) {
		LOGGER.warn("websocket 异常" + error.getMessage());
	}

	/**
	 * 通过EndpointConfig 获取httpSession
	 */
	private HttpSession getHttpSession(EndpointConfig config) {
		return (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
	}
	/**
	 * 弹幕类型消息执行的方法
	 */
	private void barrageType(WebSocketMessage<String> webSocketMessage, Session session){
		HttpSession httpSession = WebSocketUtil.getWebSocketBySessionId(session).getHttpSession();//获取httpsession
		String message = webSocketMessage.getData();//消息信息
		BarrageResult barrageResult = null;
		long userId = 0L;
		if(httpSession.getAttribute("tUser") != null){
			//已登录
			TUser tUser = (TUser)httpSession.getAttribute("tUser");
			userId = tUser.getUserId();//用户id
			//如果未定义昵称，取手机号码后四位
			String name = tUser.getName() != null ? 
					tUser.getName() : 
					"未命名用户" + String.valueOf(tUser.getPhone()).substring(7);
			//头像 未设置使用默认的noLogin头像
			String headImage = tUser.getHeadImage();
			int state = 1;//状态 是否游客
			barrageResult = new BarrageResult(message,userId,name,headImage,state);
		}else{
			//未登录
			String name = "游客100" + session.getId();
			String headImage = "noLogin.jpg";
			int state = -1;
			barrageResult = new BarrageResult(message,null,name,headImage,state);
		}
		//包装
		WebSocketMessage<BarrageResult> webSocketMessageResult = new WebSocketMessage<>(1,barrageResult);
		//发送全部
		sendBarrage(webSocketMessageResult);
		webSocket.tBarrageService.addBarrage(userId,message);

	}
	/**
	 * 向所有用户发送弹幕消息
	 */
	private static void sendBarrage(WebSocketMessage<BarrageResult> webSocketMessage)  {
		// 遍历WebSockets，向所有连接的用户发送消息。 迭代器遍历才可以删除 foreach的循环无法操作元素
		Iterator<Map.Entry<String, WebSocket>> iterator = WebSocketUtil.getAllWebSocket().iterator();
		while(iterator.hasNext()){
			Map.Entry<String, WebSocket> entry = iterator.next();
			WebSocket webSocketTemp = entry.getValue();
			try{
				//发送异步消息
				webSocketTemp.getSession().getAsyncRemote().sendObject(webSocketMessage);
			}catch(Exception e){
				LOGGER.warn(e.getMessage() + "向某个session发送弹幕失败，可能因为session异常关闭，没有在WebSockets中删除.");
				iterator.remove();
			}
		}
	}
	
	/**
	 * 管理员向所有用户发送弹幕消息
	 */
	public static void topSendBarrage(String message){
		//消息实体创建
		BarrageResult barrageResult = new BarrageResult(message,"noLogin.jpg",970389);
		WebSocketMessage<BarrageResult> webSocketMessage = new WebSocketMessage<>(1,barrageResult);
		//向所有用户发送消息
		sendBarrage(webSocketMessage);
	}
	
	/**
	 * 管理员向 指定 用户发送 消息
	 */
	public static void topSendMessage(WebSocketMessage<?> webSocketMessage,Long userId){
		WebSocket webSocket = WebSocketUtil.getWebSocketByUserId(userId);
		if(webSocket != null){
			webSocket.getSession().getAsyncRemote().sendObject(webSocketMessage);
		}
		
	}
	
	//GETER/SETER
	public HttpSession getHttpSession() {
		return httpSession;
	}
	public Session getSession() {
		return session;
	}


	@Autowired
	private TBarrageService tBarrageService;
	/**
	 * 将消息保存到数据库
	 * 如下的处理是因为spring注入总是为null
	 */
	private static WebSocket webSocket;
	public static void setWebSocket(WebSocket webSocket){
		WebSocket.webSocket = webSocket;
	}
	@PostConstruct
	public void init(){
		webSocket = this;
		webSocket.tBarrageService = this.tBarrageService;
	}
	
}
