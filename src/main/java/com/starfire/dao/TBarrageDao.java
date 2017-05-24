package com.starfire.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 弹幕dao 
 */
@Repository
public interface TBarrageDao {
	/**
	 * 存储弹幕信息
	 */
	Integer  addBarrage(@Param("userId")Long userId,@Param("content")String content);
}
