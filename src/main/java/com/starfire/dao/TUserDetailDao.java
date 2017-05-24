package com.starfire.dao;

import org.springframework.stereotype.Repository;

import com.starfire.domain.TUserDetail;
/**
 *用户详细信息 dao 
 */
@Repository
public interface TUserDetailDao {
	
	/**
	 *查询用户详细信息 
	 */
	TUserDetail queryByUserId(Long userId);
	
	/**
	 * 增加用户详细信息记录
	 * 就一条空的记录
	 */
	Integer addOne(Long userId);
	
	/**
	 * 修改用户详细信息
	 */
	Integer updateByUserId(TUserDetail tUserDetail);
}
