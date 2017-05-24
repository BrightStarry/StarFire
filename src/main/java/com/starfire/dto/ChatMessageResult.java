package com.starfire.dto;

import com.starfire.domain.TUser;

/**
 *聊天消息实体类    type:2
 *作为websocketmessage的成员变量传输
 */
public class ChatMessageResult {
	private TUser tUser;//发送者信息
	private String message;//消息内容
	public TUser gettUser() {
		return tUser;
	}
	public void settUser(TUser tUser) {
		this.tUser = tUser;
	}
	public String getMessage() {
		return message;
	}
	public void setMessge(String messge) {
		this.message = messge;
	}
	public ChatMessageResult(TUser tUser, String message) {
		super();
		this.tUser = tUser;
		this.message = message;
	}
	public ChatMessageResult() {
		super();
	}
	@Override
	public String toString() {
		return "ChatMessageResult [tUser=" + tUser + ", message=" + message + "]";
	}
	
	
	
}
