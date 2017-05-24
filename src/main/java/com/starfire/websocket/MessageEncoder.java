package com.starfire.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.starfire.dto.WebSocketMessage;

/**
 *消息编码器
 *当使用websocket从服务端向客户端传输自定义的Object时，需要编码器
 *反之，需要解码器。 
 * @param <T>
 *
 */
public class MessageEncoder<T> implements Encoder.Text<WebSocketMessage<T>>{
	
	private Logger logger = LoggerFactory.getLogger(MessageEncoder.class);
	private Gson gson = new Gson();
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void init(EndpointConfig config) {
		
	}
	
	//编码方法  也就是把对象转换成json格式的字符串
	@Override
	public String encode(WebSocketMessage<T> webSocketMessage) throws EncodeException {
		try {
			String jsonMessage= gson.toJson(webSocketMessage);
			return jsonMessage;
		} catch (Exception e) {
			logger.warn("webSocket编码器 编码 失败！" + e.getMessage());
			return null;
		}
	}

}
