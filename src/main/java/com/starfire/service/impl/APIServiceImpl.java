package com.starfire.service.impl;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.starfire.api.AIChatAPI;
import com.starfire.api.FaceAgeAPI;
import com.starfire.service.APIService;
import com.starfire.util.SignUtil;
/**
 * api 服务类
 *
 */
@Service
public class APIServiceImpl implements APIService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(APIServiceImpl.class);
	
	/**
	 * 获取年龄
	 */
	@Override
	public String getFaceAge(InputStream is) {
		try {
			String base64 = SignUtil.getBase64(is);
			String age = FaceAgeAPI.getFaceAge(base64);
			return age;
		} catch (Exception e) {
			LOGGER.warn("人脸识别失败！" + e.getMessage());
		}
		return null;
	}
	
	/**
	 * 机器人聊天
	 */
	@Override
	public String openAIChat(String message) {
		try {
			return AIChatAPI.openChat(message);
		} catch (Exception e) {
			LOGGER.warn("智能聊天失败！" + e.getMessage());
		}
		return null;
	}

}
