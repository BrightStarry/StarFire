package com.starfire.dto;


/**
 *注册状态返回 
 */
@Deprecated
public class RegisterResult {
/*	SUCCESS(1,"注册成功"),
	INNER_ERROR(0,"服务器内部错误"),
	MOBILE_REPEAT(-1,"手机号码重复"),
	PARAM_NULL(-2,"参数为空"),
	CHECK_CODE_ERROR(-3,"验证码错误");*/
	
	private int state;//注册状态
	private String stateInfo;//状态信息
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
//	public RegisterResult(RegisterStateEnum stateEnum) {
//		super();
//		this.state = stateEnum.getState();
//		this.stateInfo = stateEnum.getStateInfo();
//	}
	public RegisterResult() {
		super();
	}
	
	
	
}
