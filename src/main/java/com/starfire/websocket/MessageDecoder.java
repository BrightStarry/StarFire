package com.starfire.websocket;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.starfire.dto.WebSocketMessage;
/**
 *消息解码器 
 *将从客户端传过来的消息解码
 */
public class MessageDecoder<T> implements Decoder.Text<WebSocketMessage<String>>{
	
	private Logger logger = LoggerFactory.getLogger(MessageDecoder.class);
	
	

	@Override
	public void destroy() {
	}

	@Override
	public void init(EndpointConfig arg0) {
	}
	
	/**
	 * 解码方法
	 */
	
	@Override
	@SuppressWarnings("unchecked")
	public WebSocketMessage<String> decode(String jsonMessage) throws DecodeException {
//		//将字符串解析成json对象
//		JsonObject jsonObject = Json.createReader(new StringReader(jsonMessage)).readObject();
//		//将json对象转换成WebSocketMessage
//		WebSocketMessage<String> webSocketMessage = new WebSocketMessage<>();
//		webSocketMessage.setType(jsonObject.getInt("type"));
//		webSocketMessage.setData(jsonObject.getString("message"));
//		return webSocketMessage;
		//上面的是使用java原生的json，这个是使用fastJson
		try {
			WebSocketMessage<String> webSocketMessage = JSON.parseObject(jsonMessage,WebSocketMessage.class);
			return webSocketMessage;
		} catch (Exception e) {
			logger.warn("webSocket编码器 编码 失败！" + e.getMessage());
			return null;
		}
	}
	
	
	/**
	 * 解码前执行的方法
	 */
	@Override
	public boolean willDecode(String jsonMessage) {
		return true;
	}

}
