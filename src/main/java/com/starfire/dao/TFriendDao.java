package com.starfire.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.starfire.domain.TFriend;

/**
 *好友 dao 
 */
@Repository
public interface TFriendDao {
	/**
	 *增加好友关系 
	 *每次增加 增加两条  即 A：a B:b A:b B:a 方便查询(在service层体现)
	 */
	Integer addOne(@Param("userIdA")Long userIdA,@Param("userIdB")Long userIdB);
	
	/**
	 * 验证是否好友 
	 */
	Integer verifyIsFriend(@Param("userIdA")Long userIdA,@Param("userIdB")Long userIdB);
	
	/**
	 * 删除好友关系
	 */
	Integer deleteFriend(@Param("userIdA")Long userIdA,@Param("userIdB")Long userIdB);
	
	/**
	 * 查询所有好友列表  根据id  userId = userIdB
	 * 返回好友列表，包含好友的基本信息
	 */
	List<TFriend> queryAllByUserId(Long userId);
	
	/**
	 * 修改 状态   -1未读  1已读   userIdA为消息发起人    userIdB为接收者
	 */
	Integer updateState(@Param("userIdA")Long userIdA,@Param("userIdB")Long userIdB,@Param("state")Integer state);
}
