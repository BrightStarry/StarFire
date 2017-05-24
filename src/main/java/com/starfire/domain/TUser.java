package com.starfire.domain;

import java.util.Date;

/**
 *用户基本信息表 
 */
public class TUser {
	private long userId;//用户id
	private String name;//名字
	private String headImage;//头像 路径
	private String intro;//简介
	private long phone;//电话
	private String mail;//邮箱
	private String password;//密码
	private String sex;//性别
	private Date creteTime;//注册时间
	private int state;//状态 ， 1：有效  -1：无效  2:有效且订购了短信服务
	private Date lastTime;//最后登录时间
	
	private TUserDetail tUserDetail;//用户详细信息
	
	public TUserDetail gettUserDetail() {
		return tUserDetail;
	}
	public void settUserDetail(TUserDetail tUserDetail) {
		this.tUserDetail = tUserDetail;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getCreteTime() {
		return creteTime;
	}
	public void setCreteTime(Date creteTime) {
		this.creteTime = creteTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public TUser() {
		super();
	}
	public TUser(long userId, String name, String headImage, String intro, long phone, String mail, String password,
			String sex, Date creteTime, int state, Date lastTime) {
		super();
		this.userId = userId;
		this.name = name;
		this.headImage = headImage;
		this.intro = intro;
		this.phone = phone;
		this.mail = mail;
		this.password = password;
		this.sex = sex;
		this.creteTime = creteTime;
		this.state = state;
		this.lastTime = lastTime;
	}
	@Override
	public String toString() {
		return "TUser [userId=" + userId + ", name=" + name + ", headImage=" + headImage + ", intro=" + intro
				+ ", phone=" + phone + ", mail=" + mail + ", password=" + password + ", sex=" + sex + ", creteTime="
				+ creteTime + ", state=" + state + ", lastTime=" + lastTime + "]";
	}
	
	
}
