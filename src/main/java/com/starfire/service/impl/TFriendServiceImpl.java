package com.starfire.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.starfire.dao.TChatRecordDao;
import com.starfire.dao.TFriendApplyDao;
import com.starfire.dao.TFriendDao;
import com.starfire.dao.TUserDao;
import com.starfire.domain.TChatRecord;
import com.starfire.domain.TFriend;
import com.starfire.domain.TUser;
import com.starfire.dto.ChatRecord;
import com.starfire.exception.FriendServiceException;
import com.starfire.service.TFriendService;

/**
 * 好友 服务 实现类
 */
@Service
public class TFriendServiceImpl implements TFriendService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TFriendServiceImpl.class);
	@Autowired
	private TFriendDao tFriendDao;
	@Autowired
	private TFriendApplyDao tFriendApplyDao;
	@Autowired
	private TChatRecordDao tChatRecordDao;
	@Autowired
	private TUserDao tUserDao;

	/**
	 * 查询与好友的聊天记录 userIdA 登录用户 userIdB 好友 七天内
	 * 
	 * 之前这个方法中的 addAll方法一直报错，也就是返回的是false。但是我的两个list明明都已经不为空了，不过size是0而已。
	 * 然后看了下源码，才发现addAll这个方法，返回的false。不过是因为传入的参数list的size为0。。。而不是是否合并成功，
	 * 这样的话，就直接不判断好了。
	 */
	@Override
	public ChatRecord queryChatRecord(Long userIdA, Long userIdB) {
		// 查询出 登录用户 向 好友 发送的消息
		List<TChatRecord> listA = tChatRecordDao.queryChatReocrdByPairUserId(userIdA, userIdB);
		// 查询出 好友 向 登录用户发起的消息
		List<TChatRecord> listB = tChatRecordDao.queryChatReocrdByPairUserId(userIdB, userIdA);
		// 确保list A 和 B 都不为空
		listA = listA != null && listA.size() > 0 ? listA : new ArrayList<TChatRecord>();
		listB = listB != null && listB.size() > 0 ? listB : new ArrayList<TChatRecord>();
		// 查询两个用户name
		TUser tUserA = tUserDao.queryOneByIdNamePhoneOr(userIdA, null, null);
		TUser tUserB = tUserDao.queryOneByIdNamePhoneOr(userIdB, null, null);

		listA.addAll(listB);
		// 排序 按照消息发送时间
		Collections.sort(listA, TChatRecord.dateComparator);
		// 创建 聊天记录
		ChatRecord chatRecord = new ChatRecord(userIdA, userIdB,tUserA.getName() ,tUserB.getName(),listA);
		return chatRecord;

	}

	/**
	 * 增加好友 该事务传播行为表示，如果该方法被其他service调用，会合并成一个事务，没有，则自己单独一个事务
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean addFriend(Long userIdA, Long userIdB) {
		try {
			Integer flag1 = tFriendDao.addOne(userIdA, userIdB);
			Integer flag2 = tFriendDao.addOne(userIdB, userIdA);
			if (flag1 == null || flag2 == null || flag1 != 1 || flag2 != 1) {
				throw new FriendServiceException("增加好友失败！");
			}
			return true;
		} catch (FriendServiceException e) {
			LOGGER.warn("增加好友失败! 增加多条记录时，某条记录Mybatis返回值为0或null!" + e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.warn("增加好友失败!内部错误 " + e.getMessage());
			throw e;
		}
	}

	/**
	 * 验证是否好友
	 */
	@Override
	public boolean verifyIsFriend(Long userIdA, Long userIdB) {
		Integer flag = tFriendDao.verifyIsFriend(userIdA, userIdB);
		return flag != null && flag == 1 ? true : false;
	}

	/**
	 * 删除好友
	 */
	@Transactional
	@Override
	public boolean deleteFriend(Long userIdA, Long userIdB) {
		try {
			Integer flag1 = tFriendDao.deleteFriend(userIdA, userIdB);
			Integer flag2 = tFriendDao.deleteFriend(userIdB, userIdA);
			if (flag1 == null || flag2 == null || flag1 != 1 || flag2 != 1) {
				throw new FriendServiceException("删除好友失败！");
			}
			return true;
		} catch (FriendServiceException e) {
			LOGGER.warn("删除好友失败! 删除多条记录时，某条记录Mybatis返回值为0或null!" + e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.warn("增加好友失败!内部错误 " + e.getMessage());
			throw e;
		}
	}

	/**
	 * 查询所有好友 无意中看见的，list返回，不返回null，只返回size为0的，可以减少下一层的判断。防止异常。
	 */
	@Override
	public List<TFriend> queryAllByUserId(Long userId) {
		// 先查询出所有好友userId
		List<TFriend> tFriendList = tFriendDao.queryAllByUserId(userId);
		return tFriendList != null && tFriendList.size() > 0 ? tFriendList : new ArrayList<TFriend>();
	}

	/**
	 * 增加好友申请记录
	 */
	@Override
	public boolean addFriendApply(Long userIdA, Long userIdB) {
		Integer flag = tFriendApplyDao.addOne(userIdA, userIdB);
		return flag != null && flag == 1;
	}

	/**
	 * 修改好友申请记录状态
	 */
	@Override
	public boolean updateFriendApplyState(Long userIdA, Long userIdB, int state) {
		Integer flag = tFriendApplyDao.updateByPairId(userIdA, userIdB, state);
		return flag != null && flag == 1;
	}

	/**
	 * 验证是否在好友申请中
	 */
	@Override
	public boolean verifyIsFriendApply(Long userIdA, Long userIdB) {
		Integer flag = tFriendApplyDao.verifyIsFriendApply(userIdA, userIdB);
		return flag != null && flag == 1;
	}
	
	
	/**
	 * 发送好友聊天消息
	 */
	@Transactional
	@Override
	public boolean sendChatMessage(Long userIdA,Long userIdB,String message) {
		//将 消息存入数据库
		TChatRecord tChatRecord = new TChatRecord(userIdA,userIdB,message);
		Integer a = tChatRecordDao.addChatRecord(tChatRecord);
		//修改 与该好友的聊天状态为 未读-1
		Integer b = tFriendDao.updateState(userIdA, userIdB, -1);
		return  a != null && a == 1 && b != null && b == 1 ;
	}
	
	/**
	 * 修改与某用户的聊天状态 为1已读
	 */
	@Override
	public boolean updateFriendStateToOne(Long userIdA, Long userIdB) {
		Integer flag = tFriendDao.updateState(userIdA, userIdB, 1);
		return flag != null && flag == 1;
	}

}
