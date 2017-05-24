package com.starfire.service.impl;


import com.starfire.api.SMSAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfire.service.RedisService;
import com.starfire.service.SMSService;

/**
 * 验证码发送service
 * @author 97038
 *
 */
@Service
public class SMSServiceImpl implements SMSService{
	private Logger logger = LoggerFactory.getLogger(SMSServiceImpl.class);
	@Autowired
	private RedisService redisService;
	
	/**
	 * 发送验证码
	 */
	@Override
	public boolean sendCheckCode(long phone) {
		try {
			String checkCode = String.valueOf((int)(Math.random()*(9999-1000+1))+1000);
			String[] params = new String[1];
			params[0] = checkCode;
			SMSAPI.send(phone, 2, params);
			System.out.println(checkCode);
			boolean flag = true;
			if(flag){
				redisService.set("checkCode"+phone , checkCode);
			}
			return flag;
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return false;
		}
	}
	
	/**
	 * 发送通知
	 */
	@Override
	public boolean sendInform(long phone, long userId1, long userId2) {
		return false;
	}
	
	/**
	 * 校验手机验证码
	 */
	@Override
	public boolean verifyCheckCode(long phone, String checkCode) {
		String realCheckCode = (String) redisService.get("checkCode" + phone);
		if(realCheckCode != null && !realCheckCode.equals("") && realCheckCode.equals(checkCode)){
			return true;
		}
		return false;
	}

}
