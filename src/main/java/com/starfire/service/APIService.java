package com.starfire.service;

import java.io.InputStream;

/**
 *api 服务接口 
 */
public interface APIService {
	
	/**
	 * 获取人脸年龄
	 * @param is
	 * @return
	 */
	String getFaceAge(InputStream is);
	
	/**
	 * 机器人聊天 
	 */
	String openAIChat(String message);
}
