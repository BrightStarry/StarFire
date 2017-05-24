package com.starfire.domain;

import java.util.Date;

/**
 *弹幕记录表 
 */
public class TBarrage {
	private long barrageId;//弹幕id
	private long userId;//用户id
	private Date createTime;//发送id
	private String content;//发送内容
	public long getBarrageId() {
		return barrageId;
	}
	public void setBarrageId(long barrageId) {
		this.barrageId = barrageId;
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
	public TBarrage(long barrageId, long userId, Date createTime, String content) {
		super();
		this.barrageId = barrageId;
		this.userId = userId;
		this.createTime = createTime;
		this.content = content;
	}
	
	
	public TBarrage(long userId, String content) {
		super();
		this.userId = userId;
		this.content = content;
	}
	public TBarrage() {
		super();
	}
	@Override
	public String toString() {
		return "TBarrage [barrageId=" + barrageId + ", userId=" + userId + ", createTime=" + createTime + ", content="
				+ content + "]";
	}
	
	
	
}
