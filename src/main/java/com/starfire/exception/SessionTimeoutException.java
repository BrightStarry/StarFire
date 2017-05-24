package com.starfire.exception;
/**
 * session超时异常
 */
@SuppressWarnings("serial")
public class SessionTimeoutException extends RuntimeException{
	public SessionTimeoutException(String message,Throwable cause){
		super(message,cause);
	}
	public SessionTimeoutException(String message){
		super(message);
	}
}
