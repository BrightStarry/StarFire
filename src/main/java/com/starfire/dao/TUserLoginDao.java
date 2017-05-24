package com.starfire.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *用户登录记录dao 
 */
@Repository
public interface TUserLoginDao {
	/**
	 * 增加用户登录记录
	 */
	Integer addOne(@Param("userId")Long userId,@Param("ip")String ip);
}
