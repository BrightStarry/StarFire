package com.starfire.domain;

import java.util.Date;

/**
 *用户登录记录 
 */
public class TUserLogin {
	private long loginId;//登录记录id
	private long userId;//用户id
	private Date createTime;//登录时间
	private String ip;//登录ip
	public long getLoginId() {
		return loginId;
	}
	public void setLoginId(long loginId) {
		this.loginId = loginId;
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public TUserLogin(long loginId, long userId, Date createTime, String ip) {
		super();
		this.loginId = loginId;
		this.userId = userId;
		this.createTime = createTime;
		this.ip = ip;
	}
	public TUserLogin() {
		super();
	}
	@Override
	public String toString() {
		return "TUserLogin [loginId=" + loginId + ", userId=" + userId + ", createTime=" + createTime + ", ip=" + ip
				+ "]";
	}
	
	
}
