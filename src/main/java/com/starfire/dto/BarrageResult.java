package com.starfire.dto;

import java.io.Serializable;

/**
 *弹幕返回对象
 *当收到websocket的消息时，获取发送对象的一些信息，返回给所有连接的用户
 */
public class BarrageResult implements Serializable{
	private static final long serialVersionUID = 1L;
	private String message;//消息
	private String name;//用户昵称
	private String headImage;//用户头像路径
	private Long userId;//用户id 用来拼接用户首页链接。其他用户可以点击弹幕进入该用户首页
	private int state;//状态，是否游客==
	private static String TYPE = "BarrageResult";
	
	
	
	public static String getTYPE() {
		return TYPE;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public BarrageResult() {
		super();
	}
	public BarrageResult(String message, Long userId,String name, String headImage,int state ) {
		super();
		this.message = message;
		this.name = name;
		this.headImage = headImage;
		this.userId = userId;
		this.state = state;
	}
	@Override
	public String toString() {
		return "BarrageResult [message=" + message + ", name=" + name + ", headImage=" + headImage + ", userId="
				+ userId + ", state=" + state + "]";
	}
	public BarrageResult(String message, String headImage, int state) {
		super();
		this.message = message;
		this.headImage = headImage;
		this.state = state;
	}
	
	
	
	
}
