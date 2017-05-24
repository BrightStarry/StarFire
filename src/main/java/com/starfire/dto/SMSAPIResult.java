package com.starfire.dto;

/**
 *短信发送返回结果对象
 */
@Deprecated
public class SMSAPIResult {
	private Integer code;//状态码
	private Integer msg;//sendId
	private Integer obj;//验证码
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Integer getMsg() {
		return msg;
	}
	public void setMsg(Integer msg) {
		this.msg = msg;
	}
	public Integer getObj() {
		return obj;
	}
	public void setObj(Integer obj) {
		this.obj = obj;
	}
	public SMSAPIResult() {
		super();
	}
	public SMSAPIResult(Integer code, Integer msg, Integer obj) {
		super();
		this.code = code;
		this.msg = msg;
		this.obj = obj;
	}
	@Override
	public String toString() {
		return "SMSAPIResult [code=" + code + ", msg=" + msg + ", obj=" + obj + "]";
	}
	
	
	
}
