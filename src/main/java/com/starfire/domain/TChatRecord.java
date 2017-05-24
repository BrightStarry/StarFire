package com.starfire.domain;

import java.util.Comparator;
import java.util.Date;


/**
 * 聊天记录表
 */
public class TChatRecord {
	private Long chatId;// 聊天记录id
	private Long userIdA;// 用户id 消息发起人
	private Long userIdB;// 用户id 消息接收人
	private String content;// 消息内容
	private Date createTime;// 发送时间

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public TChatRecord() {
		super();
	}
	

	public TChatRecord(Long userIdA, Long userIdB, String content) {
		super();
		this.userIdA = userIdA;
		this.userIdB = userIdB;
		this.content = content;
	}

	@Override
	public String toString() {
		return "TChatRecord [chatId=" + chatId + ", userIdA=" + userIdA + ", userIdB=" + userIdB + ", content="
				+ content + ", createTime=" + createTime + "]";
	}

	// 按照日期排序
	public static Comparator<TChatRecord> dateComparator = new Comparator<TChatRecord>() {
		@Override
		public int compare(TChatRecord arg0, TChatRecord arg1) {
			//要弄成倒序显示在对话框。所以颠倒下
			return arg1.getCreateTime().compareTo(arg0.getCreateTime());
		}
	};

}
