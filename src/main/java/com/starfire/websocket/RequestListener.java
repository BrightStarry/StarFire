package com.starfire.websocket;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 *请求监听器
 *因为 
 *3.websocket中继承Configurator的配置类，中重写的modifyHandshake方法，中request中HttpSession为空。
 *这个问题，需要这样解决
 */
@WebListener
public class RequestListener implements ServletRequestListener{
	
	
	
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
	}
	
	/**
	 * 请求初始化方法
	 */
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		//将所有请求都带上httpSession
		((HttpServletRequest)sre.getServletRequest()).getSession();
	}

}
