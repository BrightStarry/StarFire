package com.starfire.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.starfire.service.APIService;

@Controller
@RequestMapping("/util")
public class UtilController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilController.class);
	@Autowired
	private APIService apiService;
	
	
	/**
	 * 人脸年龄测试
	 * @param file
	 * @param session
	 * @return
	 */
	@PostMapping("/faceAgeTest")
	@ResponseBody
	public String FaceAgeTest(@RequestParam("faceImage")MultipartFile file,HttpSession session){
		try {
			return apiService.getFaceAge(file.getInputStream());
		} catch (IOException e) {
			LOGGER.warn("人脸识别test，file获取inputstream失败");
			return null;
		}
	}
	
	/**
	 * 机器人聊天
	 */
	@PostMapping("/aiChat")
	@ResponseBody
	public String aiChat(@RequestParam("message")String message){
		return apiService.openAIChat(message);
	}

}
