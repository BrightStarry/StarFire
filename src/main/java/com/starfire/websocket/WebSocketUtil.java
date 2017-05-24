package com.starfire.websocket;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Service;

import com.starfire.domain.TUser;





/**
 * session工具 主要作用是储存、增加、删除、查找 websocket服务中的session
 */
@Service
public class WebSocketUtil {

	private static Map<String, WebSocket> webSockets = new ConcurrentHashMap <String, WebSocket>();
	
	/**
	 * 保存一个WebSocket到WebSockets 根据Session的id为key
	 */
	public static void addWebSocket(WebSocket webSocket) {
		webSockets.put(webSocket.getSession().getId(), webSocket);
	}
	
	/**
	 * 获取一个WebSocket 根据userId
	 */
	public static WebSocket getWebSocketByUserId(Long userId){
		Iterator<Entry<String, WebSocket>> iterator = webSockets.entrySet().iterator();
		WebSocket webSocket = null;
		while(iterator.hasNext()){
			webSocket = iterator.next().getValue();
			TUser tUser = (TUser)webSocket.getHttpSession().getAttribute("tUser");
			if(tUser != null && tUser.getUserId() == userId){
				if(webSocket.getSession().isOpen()){
					return webSocket;
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取一个WebSocket 根据session 的id
	 */
	public static WebSocket getWebSocketBySessionId(Session Session) {
		return webSockets.get(Session.getId());
	}

	/**
	 * 删除一个WebSocket
	 */
	public static void removeWebSocket(HttpSession httpSession) {
		webSockets.remove(httpSession.getId());
	}
	

	/**
	 * 判断是否存在连接
	 */
	public static boolean isConnect(HttpSession httpSession) {
		return webSockets.containsKey(httpSession.getId());
	}

	/**
	 * 获取连接数量，也就是总在线人数
	 */
	public static int getSize() {
		return webSockets.size();
	}

	/**
	 * 获取所有WebSocket
	 */
	public static Set<Entry<String,WebSocket>> getAllWebSocket(){
		return webSockets.entrySet();
	}

}
