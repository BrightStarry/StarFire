package com.starfire.domain;

import java.util.Date;

/**
 *用户详细信息 
 */
public class TUserDetail {
	private Long userId;//用户id
	private Date smsCreateTime;//短信服务开始时间
	private Date smsEndTime;//短信服务结束时间
	private Double money;//消费总金额
	private Date birthdate;//出生日期
	private Integer personality;//性格 1-5 内向到外向
	private Integer height;//身高 cm
	private Integer weight;//体重 斤
	private String color;//颜色
	private String province;//省
	private String city;//市
	private String district;//区
	
	private String relation;//关系 用来在页面显示 用户主页 与 进入该用户主页的用户 的关系  1本人  2申请好友 3好友 4申请好友中
	
	
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getSmsCreateTime() {
		return smsCreateTime;
	}
	public void setSmsCreateTime(Date smsCreateTime) {
		this.smsCreateTime = smsCreateTime;
	}
	public Date getSmsEndTime() {
		return smsEndTime;
	}
	public void setSmsEndTime(Date smsEndTime) {
		this.smsEndTime = smsEndTime;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public Integer getPersonality() {
		return personality;
	}
	public void setPersonality(Integer personality) {
		this.personality = personality;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public TUserDetail(Long userId, Date smsCreateTime, Date smsEndTime, Double money, Date birthdate,
			Integer personality, Integer height, Integer weight, String color, String province, String city,
			String district) {
		super();
		this.userId = userId;
		this.smsCreateTime = smsCreateTime;
		this.smsEndTime = smsEndTime;
		this.money = money;
		this.birthdate = birthdate;
		this.personality = personality;
		this.height = height;
		this.weight = weight;
		this.color = color;
		this.province = province;
		this.city = city;
		this.district = district;
	}
	public TUserDetail() {
		super();
	}
	@Override
	public String toString() {
		return "TUserDetail [userId=" + userId + ", smsCreateTime=" + smsCreateTime + ", smsEndTime=" + smsEndTime
				+ ", money=" + money + ", birthdate=" + birthdate + ", personality=" + personality + ", height="
				+ height + ", weight=" + weight + ", color=" + color + ", province=" + province + ", city=" + city
				+ ", district=" + district + "]";
	}
	
	
	
	
	
	
}
