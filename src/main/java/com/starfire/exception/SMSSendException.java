package com.starfire.exception;
/**
 * 短信发送异常
 */
@SuppressWarnings("serial")
public class SMSSendException extends RuntimeException{
	public SMSSendException(String message,Throwable cause){
		super(message,cause);
	}
	public SMSSendException(String message){
		super(message);
	}
}
