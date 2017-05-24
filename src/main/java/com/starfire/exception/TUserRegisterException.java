package com.starfire.exception;
/**
 * 用户注册异常 
 */
@SuppressWarnings("serial")
public class TUserRegisterException extends RuntimeException{
	public TUserRegisterException(String message,Throwable cause){
		super(message,cause);
	}
	public TUserRegisterException(String message){
		super(message);
	}
}
