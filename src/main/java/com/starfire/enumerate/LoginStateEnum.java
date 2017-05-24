package com.starfire.enumerate;
/**
 *登录状态枚举 
 */
public enum LoginStateEnum  implements CommonStateEnum{
	INTERNAL_ERROR("INTERNAL_ERROR","服务器内部错误"),
	PHONE_NUll("PHONE_NUll","手机号码为空"),
	PASSWORD_NULL("PASSWORD_NULL","密码为空"),
	ACCOUNT_UNMATCHED("ACCOUNT_UNMATCHED","帐号密码不匹配");
	
	private String errorCode;//异常代码
	private String errorMessage;//异常信息
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
	LoginStateEnum() {
	}
	private LoginStateEnum(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	
	

}
