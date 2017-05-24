package com.starfire.exception;
/**
 * 好友服务异常
 */
@SuppressWarnings("serial")
public class FriendServiceException extends RuntimeException{
	public FriendServiceException(String message,Throwable cause){
		super(message,cause);
	}
	public FriendServiceException(String message){
		super(message);
	}
}
