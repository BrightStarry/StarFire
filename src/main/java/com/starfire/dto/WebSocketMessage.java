package com.starfire.dto;
/**
 * websocket 消息传输 实体类
 * 
 * 发送过来的消息  只会是 type + string
 * 发送出去的消息 是type + 其他类型（例如BarrageResult）
 */
public class WebSocketMessage<T> {
	
	private int type;//消息类型  1：弹幕  2:好友消息  3：刷新消息列表  4：刷新好友列表  
	private T data;//消息实体
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public WebSocketMessage(int type, T data) {
		super();
		this.type = type;
		this.data = data;
	}
	public WebSocketMessage() {
	}
	
	

}
