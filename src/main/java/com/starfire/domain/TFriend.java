package com.starfire.domain;
/**
 *好友关系表 
 */
public class TFriend {
	private Long friendId;//朋友关系id
	private Long userIdA;//用户id1
	private Long userIdB;//用户id2
	private Integer state;//状态  是否有消息未读    -1：未读   1：已读
	private Boolean isOnline;//用户是否在线
	
	private TUser tUserA;// 当一个用户查询好友列表时，tUserA就是好友的信息。
	
	
	
	public Boolean getIsOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	public TUser gettUserA() {
		return tUserA;
	}
	public void settUserA(TUser tUserA) {
		this.tUserA = tUserA;
	}
	public Long getFriendId() {
		return friendId;
	}
	public void setFriendId(Long friendId) {
		this.friendId = friendId;
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public TFriend() {
		super();
	}
	@Override
	public String toString() {
		return "TFriend [friendId=" + friendId + ", userIdA=" + userIdA + ", userIdB=" + userIdB + ", state=" + state
				+ "]";
	}
	
	
	

	
}
