package com.starfire.service;


import com.starfire.domain.TFriendApply;
import com.starfire.domain.TMessageRecord;
import com.starfire.dto.MessageList;

/**
 *网站消息推送 服务接口  
 */
public interface TMessageService {
	
	/**
	 * 查询某用户一个月来的所有消息记录
	 */
	MessageList queryUserMessageList(Long userId);
	
	/**
	 * 给所有用户推送消息
	 */
	boolean sendMessageForAll(String content);
	
	/**
	 * 给某一用户发送消息
	 */
	boolean sendMessageForUserId(Long userId,String content);
	/**
	 * 查询好友申请消息详情
	 */
	TFriendApply queryFriendApplyDetail(Long applyId);
	
	/**
	 * 查询网站推送记录详情
	 */
	TMessageRecord queryMessageRecordDetail(Long messageId);
	
	/**
	 * 修改 消息 读取状态
	 */
	boolean updateMesageState(Integer type,Long messageId,Integer state);
	
}
