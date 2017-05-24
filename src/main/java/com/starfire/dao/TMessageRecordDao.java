package com.starfire.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.starfire.domain.TMessageRecord;

/**
 *消息推送记录dao 
 */
@Repository
public interface TMessageRecordDao {
	
	/**
	 *批量增加
	 */
	Integer addMessageRecordList(@Param("userIds")Long[] userIds,@Param("content")String content);
	
	/**
	 * 修改状态 为已读
	 */
	Integer updateState(Long messageId);
	
	/**
	 * 查询某个用户所有的被推送记录 一个月内
 	 */
	List<TMessageRecord> queryByUserId(Long userId);
	
	/**
	 * 查询推送记录详情
	 */
	TMessageRecord queryDetailById(Long messageId);
	
	/**
	 * 增加记录
	 */
	Integer addMessageRecord(@Param("userId")Long userId,@Param("content")String content);
}
