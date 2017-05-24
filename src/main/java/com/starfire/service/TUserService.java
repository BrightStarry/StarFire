package com.starfire.service;

import java.util.List;

import com.starfire.domain.TUser;
import com.starfire.domain.TUserDetail;
import com.starfire.dto.CommonResult;

/**
 *用户基本信息service 
 */
public interface TUserService {
	/**
	 * 模糊查询 根据name
	 */
	List<TUser> queryUserByLike(String name);
	/**
	 *验证账户 某字段 
	 */
	boolean checkAccount(long phone,String name,String mail);
	
	/**
	 * 注册
	 */
	CommonResult<TUser> register(long phone ,String password);
	
	/**
	 * 登录
	 */
	CommonResult<TUser> login(long phone ,String password);
	
	/**
	 * 修改用户基本信息
	 */
	TUser updateUserInfoById(TUser tUser);
	
	/**
	 * 查询用户所有信息
	 */
	TUser queryUserInfoById(Long userId);
	
	/**
	 * 增加登录记录
	 */
	boolean addLoginRecord(Long userId,String ip);
	

	/**
	 * 查询用户详细信息
	 */
	TUserDetail queryUserDetailByUserId(Long userId);
	
	/**
	 * 增加用户详细信息
	 * 就增加一条新的记录(只有id的)
	 */
	boolean addUserDetail(Long userId);
	
	/**
	 * 修改用户详细信息
	 */
	boolean editUserDetailById(TUserDetail tUserDetail);
	
	/**
	 * 批量查询用户信息
	 */
	List<TUser> queryByUserIdList(List<Long> userIdList);
}
