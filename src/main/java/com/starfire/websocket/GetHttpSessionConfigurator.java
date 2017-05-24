package com.starfire.websocket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

/**
 * 获取httpsession的配置器
 */
public class GetHttpSessionConfigurator extends Configurator {
	
	
	
	//修改握手
	//这个方法本来是空的，重写一遍，取出httpSession放到userProperties中去
	@Override
	public void modifyHandshake(ServerEndpointConfig sec, 
									HandshakeRequest request, 
										HandshakeResponse response) {
		
		HttpSession httpSession =(HttpSession) request.getHttpSession();
		sec.getUserProperties().put(HttpSession.class.getName(),httpSession);
	}

}
