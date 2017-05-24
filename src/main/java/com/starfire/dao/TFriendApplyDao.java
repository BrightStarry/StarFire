package com.starfire.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.starfire.domain.TFriendApply;

/**
 *好友申请 dao 
 */
@Repository
public interface TFriendApplyDao {
	
	/**
	 * 增加好友申请
	 */
	Integer addOne(@Param("userIdA")Long userIdA,@Param("userIdB")Long userIdB);
	
	/**
	 * 修改好友申请状态  根据两个用户id
	 */
	Integer updateByPairId(@Param("userIdA")Long userIdA,@Param("userIdB")Long userIdB,@Param("state")Integer state);
	
	/**
	 * 修改好友申请状态 ，根据申请id
	 */
	Integer updateByApplyId(@Param("applyId")Long applyId,@Param("state")Integer state);
	
	/**
	 * 验证是否申请中 即状态 -1(未读) or 1(已读) 
	 */
	Integer verifyIsFriendApply(@Param("userIdA")Long userIdA,@Param("userIdB")Long userIdB);
	
	/**
	 * 查询某用户一个月内所有的被添加好友记录
	 */
	List<TFriendApply> queryFriendApplyByUserIdB(Long userIdB);
	
	/**
	 * 查询好友申请记录详情  包括   X用户信息 type=A 或 B
	 */
	TFriendApply queryDetailById(@Param("applyId")Long applyId,@Param("type")String type);

}
