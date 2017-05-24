package com.starfire.domain;

import java.util.Date;

/**
 *好友申请记录表 
 */
public class TFriendApply {
	private long applyId;//申请记录id
	private long userIdA;//请求发起人
	private long userIdB;//请求接收人
	private Date createTime;//申请时间
	private int state;//消息状态 消息状态，-1：未读  1：已读  2 ：接受  -2：拒绝
	
	private TUser tUserB;//被请求用户
	private TUser tUserA;//请求发起者
	
	
	
	
	public TUser gettUserB() {
		return tUserB;
	}
	public void settUserB(TUser tUserB) {
		this.tUserB = tUserB;
	}
	public TUser gettUserA() {
		return tUserA;
	}
	public void settUserA(TUser tUserA) {
		this.tUserA = tUserA;
	}
	public long getApplyId() {
		return applyId;
	}
	public void setApplyId(long applyId) {
		this.applyId = applyId;
	}
	public long getUserIdA() {
		return userIdA;
	}
	public void setUserIdA(long userIdA) {
		this.userIdA = userIdA;
	}
	public long getUserIdB() {
		return userIdB;
	}
	public void setUserIdB(long userIdB) {
		this.userIdB = userIdB;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public TFriendApply(long applyId, long userIdA, long userIdB, Date createTime, int state) {
		super();
		this.applyId = applyId;
		this.userIdA = userIdA;
		this.userIdB = userIdB;
		this.createTime = createTime;
		this.state = state;
	}
	public TFriendApply() {
		super();
	}
	@Override
	public String toString() {
		return "TFriendApply [applyId=" + applyId + ", userIdA=" + userIdA + ", userIdB=" + userIdB + ", createTime="
				+ createTime + ", state=" + state + "]";
	}
	
	
}
