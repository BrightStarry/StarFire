package com.starfire.domain;

import java.util.Date;

/**
 *用户消息接收记录表  
 *显示在登录用户页面上的信息列表o 
 */
public class TUserMessage {
	private long messageId;//消息id
	private long userId;//用户id
	private Date createTime;//消息时间
	private String content;//消息内容
	private int state;// 消息状态， -1:未读  1：已读
	public long getMessageId() {
		return messageId;
	}
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public TUserMessage(long messageId, long userId, Date createTime, String content, int state) {
		super();
		this.messageId = messageId;
		this.userId = userId;
		this.createTime = createTime;
		this.content = content;
		this.state = state;
	}
	public TUserMessage() {
		super();
	}
	@Override
	public String toString() {
		return "TUserMessage [messageId=" + messageId + ", userId=" + userId + ", createTime=" + createTime
				+ ", content=" + content + ", state=" + state + "]";
	}
	
	
	
}
