package com.starfire.domain;

import java.util.Date;

/**
 *网站消息推送记录 
 */
public class TMessageRecord {
	private Long messageId;//消息id
	private Long userId;//用户id
	private Date createTime;//推送时间
	private String content;//推送内容
	private Integer state;//状态 -1:未读  1：已读
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public TMessageRecord(Long messageId, Long userId, Date createTime, String content, Integer state) {
		super();
		this.messageId = messageId;
		this.userId = userId;
		this.createTime = createTime;
		this.content = content;
		this.state = state;
	}
	public TMessageRecord() {
		super();
	}
	@Override
	public String toString() {
		return "TMessageRecord [messageId=" + messageId + ", userId=" + userId + ", createTime=" + createTime
				+ ", content=" + content + ", state=" + state + "]";
	}
	
	
}
