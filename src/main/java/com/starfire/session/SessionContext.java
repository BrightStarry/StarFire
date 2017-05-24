package com.starfire.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import com.starfire.domain.TUser;

/**
 *sessionContext 单例   
 * 
 * 管理 登录的  session 
 * 
 * key:userId
 */
public class SessionContext {
	//自身实例
	private static SessionContext sessionContext;
	//线程安全的map
	private Map<Long, HttpSession> sessionMap;
	
	//私有化构造方法，在new 的时候会创建map的实例
	private SessionContext(){
		sessionMap = new ConcurrentHashMap<Long, HttpSession>();
	}
	
	/**
	 * 获取自身实例的方法  只有其唯一实例为null时，才创建
	 */
	public static SessionContext getSessionContext(){
		if(sessionContext == null){
			sessionContext = new SessionContext();
		}
		return sessionContext;
	}
	
	/**
	 * 增加session
	 */
	public void addSession(HttpSession session){
		if(session != null && session.getAttribute("tUser") != null){
			sessionMap.put(((TUser)session.getAttribute("tUser")).getUserId(),session);
		}
	}
	
	/**
	 * 删除session
	 */
	public void deleteSession(HttpSession session){
		if(session != null){
			sessionMap.remove(session);
			if(session.getAttribute("tUser") != null){//顺便删除登录信息
				session.removeAttribute("tUser");
			}
		}
	}
	
	/**
	 * 查询 用户是否在线
	 */
	public boolean isOnline(Long userId){
		if(userId != null){
			return sessionMap.containsKey(userId);
		}
		return false;
	}
	
	/**
	 * 获取在线用户数
	 */
	public int getSessionSize(){
		return sessionMap.size();
	}
	
	
	
}
