package com.starfire.dto;

import java.util.List;

/**
 *消息列表 实体类 
 */
public class MessageList {
	
	private List<Message> messages;//消息列表
	private Integer unread;//未读消息数目
	
	
	
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public Integer getUnread() {
		return unread;
	}
	public void setUnread(Integer unread) {
		this.unread = unread;
	}
	public MessageList(List<Message> messages, Integer unread) {
		super();
		this.messages = messages;
		this.unread = unread;
	}
	public MessageList() {
		super();
	}
	
	

}
