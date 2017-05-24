package com.starfire.domain;

import java.util.Date;

/**
 *用户密码修改表 
 */
public class TUserPassword {
	private long passwordId;//密码修改记录id
	private long userId;//用户id
	private Date createTime;//修改时间
	private String passwordNew;//新密码
	private String passwordOld;//旧密码
	public long getPasswordId() {
		return passwordId;
	}
	public void setPasswordId(long passwordId) {
		this.passwordId = passwordId;
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
	public String getPasswordNew() {
		return passwordNew;
	}
	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}
	public String getPasswordOld() {
		return passwordOld;
	}
	public void setPasswordOld(String passwordOld) {
		this.passwordOld = passwordOld;
	}
	public TUserPassword(long passwordId, long userId, Date createTime, String passwordNew, String passwordOld) {
		super();
		this.passwordId = passwordId;
		this.userId = userId;
		this.createTime = createTime;
		this.passwordNew = passwordNew;
		this.passwordOld = passwordOld;
	}
	public TUserPassword() {
		super();
	}
	@Override
	public String toString() {
		return "TUserPassword [passwordId=" + passwordId + ", userId=" + userId + ", createTime=" + createTime
				+ ", passwordNew=" + passwordNew + ", passwordOld=" + passwordOld + "]";
	}
	
	
}
