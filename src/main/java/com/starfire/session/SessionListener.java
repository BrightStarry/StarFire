package com.starfire.session;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 *session 监听器 
 */
public class SessionListener implements HttpSessionListener{
	
	private static SessionContext sessionContext = SessionContext.getSessionContext();
	
	/**
	 * session创建
	 */
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		
	}
	
	/**
	 * session销毁
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		sessionContext.deleteSession(event.getSession());//销毁
	}

}
