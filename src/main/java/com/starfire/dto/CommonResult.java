package com.starfire.dto;

import com.starfire.enumerate.CommonStateEnum;

/**
 *通用返回类 
 */
public class CommonResult<T> {
	private boolean state;//状态 是否成功
	private String errorCode;//异常代码
	private String errorMessage;//异常消息
	private T data;//返回实体
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public CommonResult(boolean state, String errorCode, String errorMessage, T data) {
		super();
		this.state = state;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.data = data;
	}
	public CommonResult() {
		super();
	}
	public CommonResult(boolean state, T data) {
		super();
		this.state = state;
		this.data = data;
	}
	
	public CommonResult(boolean state,CommonStateEnum commonStateEnum){
		this.state = state;
		this.errorCode = commonStateEnum.getErrorCode();
		this.errorMessage = commonStateEnum.getErrorMessage();
	}
	
	
}
