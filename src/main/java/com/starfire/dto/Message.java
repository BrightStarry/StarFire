package com.starfire.dto;

import java.util.Comparator;
import java.util.Date;

import com.starfire.domain.TFriendApply;
import com.starfire.domain.TMessageRecord;

/**
 *消息 实体来 
 */
public class Message {
	private Integer type;//消息类型 1:好友申请消息  2：网站推送消息
	private TFriendApply tFriendApply;//好友申请消息
	private TMessageRecord tMessageRecord;//网站推送消息
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public TFriendApply gettFriendApply() {
		return tFriendApply;
	}
	public void settFriendApply(TFriendApply tFriendApply) {
		this.tFriendApply = tFriendApply;
	}
	public TMessageRecord gettMessageRecord() {
		return tMessageRecord;
	}
	public void settMessageRecord(TMessageRecord tMessageRecord) {
		this.tMessageRecord = tMessageRecord;
	}
	
	
	public Message() {
		super();
	}
	public Message(Integer type, TMessageRecord tMessageRecord) {
		super();
		this.type = type;
		this.tMessageRecord = tMessageRecord;
	}
	public Message(Integer type, TFriendApply tFriendApply) {
		super();
		this.type = type;
		this.tFriendApply = tFriendApply;
	}
	//按照日期排序
	public static Comparator<Message> dateComparator = new Comparator<Message>() {
		@Override
		public int compare(Message arg0, Message arg1) {
			Date createTimeA = arg0.getType() == 1 ? 
					arg0.gettFriendApply().getCreateTime() : arg0.gettMessageRecord().getCreateTime();
			Date createTimeB = arg1.getType() == 1 ? 
					arg1.gettFriendApply().getCreateTime() : arg1.gettMessageRecord().getCreateTime();
			return createTimeA.compareTo(createTimeB);
		}
	};
	
	
}
