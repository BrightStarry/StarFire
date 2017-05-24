package com.starfire.api;


import org.junit.Test;

import com.google.gson.Gson;
import com.starfire.domain.TUser;
import com.starfire.dto.ChatMessageResult;
import com.starfire.dto.WebSocketMessage;

/**
 *聊天机器人测试 
 */
public class AIChatAPITest {
//	@Test
//	public void testA() throws Exception{
//		AIChatAPI.openChat("你好啊");
//	}
	
	@Test
	public void testB(){
		TUser tUser = new TUser();
		tUser.setUserId(1111111111L);
		tUser.setName("sd");
		ChatMessageResult c = new ChatMessageResult();
		c.setMessge("dfffff");
		c.settUser(tUser);
		WebSocketMessage<ChatMessageResult> a = new WebSocketMessage<>();
		a.setData(c);
		Gson gson = new Gson();
		String str = gson.toJson(a);
		
		System.out.println(str);
	}
}
