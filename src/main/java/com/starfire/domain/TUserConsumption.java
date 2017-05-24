package com.starfire.domain;

import java.util.Date;

/**
 *用户消费记录 
 */
public class TUserConsumption {
	private long consumptionId;//消费id
	private long userId;//用户id
	private int state;//状态 状态， 0：其他 1：赞助 2：短信服务
	private Date createTime;//消费时间
	private String detail;//详情
	public long getConsumptionId() {
		return consumptionId;
	}
	public void setConsumptionId(long consumptionId) {
		this.consumptionId = consumptionId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public TUserConsumption(long consumptionId, long userId, int state, Date createTime, String detail) {
		super();
		this.consumptionId = consumptionId;
		this.userId = userId;
		this.state = state;
		this.createTime = createTime;
		this.detail = detail;
	}
	public TUserConsumption() {
		super();
	}
	@Override
	public String toString() {
		return "TUserConsumption [consumptionId=" + consumptionId + ", userId=" + userId + ", state=" + state
				+ ", createTime=" + createTime + ", detail=" + detail + "]";
	}
	
	
	
}
