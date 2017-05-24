package com.starfire.domain;

import java.util.Date;

/**
 *邮箱发送记录 
 */
public class TMail {
	private long mailId;//邮箱发送id
	private long userId;//用户id
	private String mail;//邮箱
	private int state;//状态 1：成功 ； 0：失败
	private String content;//发送内容
	private Date createTime;//发送时间
	public long getMailId() {
		return mailId;
	}
	public void setMailId(long mailId) {
		this.mailId = mailId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public TMail(long mailId, long userId, String mail, int state, String content, Date createTime) {
		super();
		this.mailId = mailId;
		this.userId = userId;
		this.mail = mail;
		this.state = state;
		this.content = content;
		this.createTime = createTime;
	}
	public TMail() {
		super();
	}
	@Override
	public String toString() {
		return "TMail [mailId=" + mailId + ", userId=" + userId + ", mail=" + mail + ", state=" + state + ", content="
				+ content + ", createTime=" + createTime + "]";
	}
	
	
}
