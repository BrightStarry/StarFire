package com.starfire.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.starfire.websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starfire.dao.TUserDao;
import com.starfire.dao.TUserDetailDao;
import com.starfire.dao.TUserLoginDao;
import com.starfire.domain.TUser;
import com.starfire.domain.TUserDetail;
import com.starfire.dto.CommonResult;
import com.starfire.enumerate.LoginStateEnum;
import com.starfire.enumerate.RegisterStateEnum;
import com.starfire.exception.TUserRegisterException;
import com.starfire.service.TUserService;
/**
 *用户  service impl 
 */
@Service
public class TUserServiceImpl implements TUserService{
	private Logger logger = LoggerFactory.getLogger(TUserServiceImpl.class);
	@Autowired
	private TUserDao tUserDao;
	@Autowired
	private TUserDetailDao tUserDetailDao;
	@Autowired
	private TUserLoginDao tUserLoginDao;
	
	/**
	 * 查询用户详细信息
	 */
	@Override
	public TUserDetail queryUserDetailByUserId(Long userId) {
		return tUserDetailDao.queryByUserId(userId);
	}
	
	/**
	 * 增加一条记录
	 */
	@Override
	public boolean addUserDetail(Long userId) {
		Integer flag = tUserDetailDao.addOne(userId);
		return flag != null && flag == 1;
	}
	
	/**
	 * 修改用户详细信息
	 */
	@Override
	public boolean editUserDetailById(TUserDetail tUserDetail) {
		Integer flag = tUserDetailDao.updateByUserId(tUserDetail);
		return flag != null && flag == 1;
	}
	
	/**
	 * 增加登录记录
	 */
	@Override
	public boolean addLoginRecord(Long userId, String ip) {
		Integer flag = tUserLoginDao.addOne(userId, ip);
		return flag != null && flag == 1;
	}
	
	/**
	 * 验证 phone 或 name  或 mail
	 */
	public boolean checkAccount(long phone,String name,String mail) {
		Integer flag = tUserDao.checkAccount(phone, mail, name);
		return flag ==null || flag != 1;
	}
	
	/**
	 * 注册
	 */
	@Transactional
	public CommonResult<TUser> register(long phone, String password) {
		try {
			//手机为空
			if(phone == 0 ){
				return new CommonResult<TUser>(false,RegisterStateEnum.PHONE_NUll);
			}
			//密码为空
			if(password == null || password.equals("")){
				return new CommonResult<TUser>(false,RegisterStateEnum.PASSWORD_NULL);
			}
			//增加用户基本信息记录
			Integer flag = tUserDao.register(phone, password,"noLogin.jpg");
			//如果返回的为null 或者 不为1
			if(flag != null && flag == 1){
				TUser tUser = tUserDao.login(phone, password);
				//增加用户详细信息记录
				tUserDetailDao.addOne(tUser.getUserId());
				WebSocket.topSendBarrage(phone + "注册成功，大家欢迎！");
				return new CommonResult<TUser>(true,tUser);
			}else{
				throw new TUserRegisterException("dao层返回0");
//				return new CommonResult<TUser>(false,RegisterStateEnum.INTERNAL_ERROR);
			}
		} catch (Exception e) {
			logger.warn("注册时 服务器内部错误！" + e.getMessage());
			throw e;
		}
		
	}
	
	/**
	 * 登录
	 */
	@Override
	public CommonResult<TUser> login(long phone, String password) {
		try {
			//手机为空
			if(phone == 0){
				return new CommonResult<TUser>(false,LoginStateEnum.PHONE_NUll);
			}
			//密码为空
			if(password == null || password.equals("")){
				return new CommonResult<TUser>(false,LoginStateEnum.PASSWORD_NULL);
			}
			TUser tUser = tUserDao.login(phone, password);
			//帐号密码不匹配
			if(tUser == null){
				return new CommonResult<TUser>(false,LoginStateEnum.ACCOUNT_UNMATCHED);
			}
			WebSocket.topSendBarrage(phone + "登录成功，大家欢迎！");
			return new CommonResult<TUser>(true,tUser);
		} catch (Exception e) {
			logger.warn("登录 service失败！服务器内部错误 " + e.getMessage());
			return new CommonResult<TUser>(false,LoginStateEnum.INTERNAL_ERROR);
		}
	}
	
	
	/**
	 * 修改用户基本信息
	 */
	@Override
	public TUser updateUserInfoById(TUser tUser) {
		try {
			tUserDao.updateById(tUser);
			return tUserDao.queryOneByIdNamePhoneOr(tUser.getUserId(), null, null);
		} catch (Exception e) {
			logger.warn("修改用户基本信息失败！用户id:" + tUser.getUserId() +"  /" + e.getMessage() );
			return null;
		}
	}
	
	/**
	 *查询用户详细信息  都放在tUser里 
	 */
	@Override
	public TUser queryUserInfoById(Long userId) {
		TUser tUser = tUserDao.queryOneByIdNamePhoneOr(userId, null, null);
		TUserDetail tUserDetail = tUserDetailDao.queryByUserId(userId);
		tUser.settUserDetail(tUserDetail);
		return tUser;
	}
	
	
	/**
	 * 批量查询用户信息
	 */
	@Override
	public List<TUser> queryByUserIdList(List<Long> userIdList) {
		List<TUser> tUserList = tUserDao.queryByUserIdList(userIdList);
		return tUserList;
	}
	
	/**
	 * 模糊查询 根据name
	 */
	@Override
	public List<TUser> queryUserByLike(String name) {
		List<TUser> tUserList = tUserDao.queryByLike(name);
		//如果不为空，直接返回，如果为空，返回一个size为0的list
		if(tUserList != null)
			return tUserList;
		return new ArrayList<TUser>();
	}
	
	
	
	

}
