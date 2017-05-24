package com.starfire.domain;

import java.util.Date;

/**
 *友情赞助记录表 
 */
public class TSponsor {
	private long sponsorId;//赞助记录id
	private long userId;//用户id
	private double money;//赞助金额
	private String content;//赞助宣言
	private Date createTime;//赞助时间
	public long getSponsorId() {
		return sponsorId;
	}
	public void setSponsorId(long sponsorId) {
		this.sponsorId = sponsorId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
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
	public TSponsor(long sponsorId, long userId, double money, String content, Date createTime) {
		super();
		this.sponsorId = sponsorId;
		this.userId = userId;
		this.money = money;
		this.content = content;
		this.createTime = createTime;
	}
	public TSponsor() {
		super();
	}
	@Override
	public String toString() {
		return "TSponsor [sponsorId=" + sponsorId + ", userId=" + userId + ", money=" + money + ", content=" + content
				+ ", createTime=" + createTime + "]";
	}
	
	
}
