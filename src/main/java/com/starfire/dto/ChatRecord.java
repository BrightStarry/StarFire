package com.starfire.dto;

import java.util.List;

import com.starfire.domain.TChatRecord;

/**
 *聊天记录 
 */
public class ChatRecord {
	
	private Long userIdA;
	private Long userIdB;
	private String nameA;
	private String nameB;
	private List<TChatRecord> tChatRecordList;
	public Long getUserIdA() {
		return userIdA;
	}
	public void setUserIdA(Long userIdA) {
		this.userIdA = userIdA;
	}
	public Long getUserIdB() {
		return userIdB;
	}
	public void setUserIdB(Long userIdB) {
		this.userIdB = userIdB;
	}
	public String getNameA() {
		return nameA;
	}
	public void setNameA(String nameA) {
		this.nameA = nameA;
	}
	public String getNameB() {
		return nameB;
	}
	public void setNameB(String nameB) {
		this.nameB = nameB;
	}
	public List<TChatRecord> gettChatRecordList() {
		return tChatRecordList;
	}
	public void settChatRecordList(List<TChatRecord> tChatRecordList) {
		this.tChatRecordList = tChatRecordList;
	}
	public ChatRecord() {
		super();
	}
	public ChatRecord(Long userIdA, Long userIdB, String nameA, String nameB, List<TChatRecord> tChatRecordList) {
		super();
		this.userIdA = userIdA;
		this.userIdB = userIdB;
		this.nameA = nameA;
		this.nameB = nameB;
		this.tChatRecordList = tChatRecordList;
	}
	
	
}
