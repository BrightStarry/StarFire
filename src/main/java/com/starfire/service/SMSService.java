package com.starfire.service;


/**
 *发送短信服务接口 
 */
public interface SMSService {
	/**
	 * 发送验证码
	 * @param phone
	 * @return
	 */
	boolean sendCheckCode(long phone);
	
	/**
	 * 发送通知短信
	 * @param phone
	 * @param userId1
	 * @param userId2
	 * @return
	 */
	boolean sendInform(long phone,long userId1,long userId2);
	
	/**
	 * 校验手机验证码
	 */
	boolean verifyCheckCode(long phone,String checkCode);

}
