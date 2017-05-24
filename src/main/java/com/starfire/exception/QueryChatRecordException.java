package com.starfire.exception;
/**
 * 聊天记录获取异常
 */
@SuppressWarnings("serial")
public class QueryChatRecordException extends RuntimeException{
	public QueryChatRecordException(String message,Throwable cause){
		super(message,cause);
	}
	public QueryChatRecordException(String message){
		super(message);
	}
}
