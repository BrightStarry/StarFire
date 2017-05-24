package com.starfire.service;

import java.util.List;

import com.starfire.domain.TFriend;
import com.starfire.dto.ChatRecord;

/**
 *好友 服务 接口 
 */
public interface TFriendService {
	
	/**
	 * 向某个好友发送聊天消息
	 */
	boolean sendChatMessage(Long userIdA,Long userIdB,String message);
	
	/**
	 * 查询与好友的聊天记录
	 * 七天内
	 */
	ChatRecord queryChatRecord(Long userIdA,Long userIdB);
	
	
	/**
	 * 添加好友
	 */
	boolean addFriend(Long userIdA,Long userIdB);
	
	/**
	 * 验证是否好友
	 */
	boolean verifyIsFriend(Long userIdA,Long userIdB);
	
	/**
	 * 删除好友关系
	 */
	boolean deleteFriend(Long userIdA,Long userIdB);
	
	/**
	 * 查询所有好友列表  
	 * 返回 好友基本信息 list
	 */
	List<TFriend> queryAllByUserId(Long userId);
	
	/**
	 * 增加好友申请记录
	 */
	boolean addFriendApply(Long userIdA,Long userIdB);
	
	/**
	 * 修改好友申请记录状态
	 */
	boolean updateFriendApplyState(Long userIdA,Long userIdB,int state);
	
	/**
	 * 验证是否在申请中
	 */
	boolean verifyIsFriendApply(Long userIdA,Long userIdB);
	
	/**
	 * 修改 与某用户间的消息状态为已读
	 */
	boolean updateFriendStateToOne(Long userIdA,Long userIdB);
}
