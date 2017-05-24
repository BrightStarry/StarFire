package com.starfire.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.starfire.domain.TUser;

/**
 *用户基本信息dao 
 */
@Repository
public interface TUserDao {
	
	/**
	 * 查询所有用户id
	 */
	Long[] queryAllUserId();
	
	/**
	 * 模糊搜索 根据 name
	 */
	List<TUser> queryByLike(String name);
	
	/**
	 * 批量查询用户信息
	 */
	List<TUser> queryByUserIdList(List<Long> userIdList);
	
	/**
	 *校验手机 邮箱 昵称 是否重复 
	 */
	Integer checkAccount(@Param("phone")Long phone,@Param("mail")String mail,@Param("name")String name);
	
	/**
	 * 注册
	 */
	Integer register(@Param("phone")Long phone,@Param("password")String password,@Param("headImage")String headImage);
	
	/**
	 * 登录
	 */
	TUser login(@Param("phone")Long phone,@Param("password")String password);
	
	/**
	 * 查询用户信息 根据 id name phone
	 */
	TUser queryOneByIdNamePhoneOr(@Param("userId")Long userId,@Param("name")String name,@Param("phone")Long phone);
	
	/**
	 * 修改信息 根据id
	 */
	Integer updateById(TUser tUser);
}
