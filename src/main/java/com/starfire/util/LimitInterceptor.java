package com.starfire.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.starfire.exception.SessionTimeoutException;
/**
 *权限拦截器 
 */
public class LimitInterceptor implements HandlerInterceptor{
	
	private String[] passUrls;
	
	
	public void setPassUrls(String[] passUrls) {
		this.passUrls = passUrls;
	}

	/**
	 * 进入之后
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exception)
			throws Exception {
		
	}
	
	/**
	 * 处理完成后，未到达下一目标
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav)
			throws Exception {
		
	}
	
	/**
	 * 进入之前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		if(request.getSession().getAttribute("tUser") == null){
//			String requestUrl = request.getRequestURL().toString();
			String requestUrl = request.getServletPath();
			if("/".equals(requestUrl))
				return true;
			for (String tempStr : passUrls) {
				if(requestUrl.contains(tempStr)){
					return true;
				}
			}
			throw new SessionTimeoutException("session超时或校验不通过！");
		}
		return true;
	}

}
