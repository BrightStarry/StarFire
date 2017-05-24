package com.starfire.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starfire.dao.TFriendApplyDao;
import com.starfire.dao.TMessageRecordDao;
import com.starfire.domain.TFriendApply;
import com.starfire.domain.TMessageRecord;
import com.starfire.dto.ChatMessageResult;
import com.starfire.dto.Message;
import com.starfire.dto.MessageList;
import com.starfire.dto.WebSocketMessage;
import com.starfire.service.TFriendService;
import com.starfire.service.TMessageService;
import com.starfire.session.SessionContext;
import com.starfire.websocket.WebSocket;

/**
 *消息推送服务实现类 
 */
@Service
public class TMessageServiceImpl implements TMessageService{
//	private static Logger LOGGER = LoggerFactory.getLogger(TMessageServiceImpl.class);
	private SessionContext sessionContext = SessionContext.getSessionContext();
	@Autowired
	private TMessageRecordDao tMessageRecordDao;
	@Autowired
	private TFriendApplyDao tFriendApplyDao;
	@Autowired
	private TFriendService tFriendService;
	
	
	
	
	
	/**
	 * 查询某用户消息列表   一个月内
	 */
	@Override
	public MessageList queryUserMessageList(Long userId) {
		// 未读消息数量统计
		int unread = 0;
		//查询推送消息记录
		List<TMessageRecord> tMessageRecords = tMessageRecordDao.queryByUserId(userId);
		//查询好友申请记录
		List<TFriendApply> tFriendApplys = tFriendApplyDao.queryFriendApplyByUserIdB(userId);
		//创建消息列表List
		List<Message> messages = new ArrayList<Message>();
		
		Message message = null;
		//放入推送消息记录
		for (TMessageRecord tMessageRecordTemp : tMessageRecords) {
			message = new Message(2,tMessageRecordTemp);
			messages.add(message);
			if(tMessageRecordTemp.getState() == -1)//如果未读，累加器+1
				unread++;
		}
		//放入好友申请记录
		for (TFriendApply tFriendApplyTemp : tFriendApplys) {
			message = new Message(1,tFriendApplyTemp);
			messages.add(message);
			if(tFriendApplyTemp.getState() == -1)//如果未读，累加器+1
				unread++;
		}
		
		//根据日期排序  重写comparetor方法
		Collections.sort(messages, Message.dateComparator);
		//构建返回实体
		MessageList messageList = new MessageList(messages,unread);
		return messageList;
	}
	
	/**
	 * 给所有用户推送消息
	 */
	@Override
	public boolean sendMessageForAll(String content) {
		return false;
	}
	
	/**
	 * 给某个用户推送消息
	 */
	@Override
	public boolean sendMessageForUserId(Long userId,String content){
		Integer flag = tMessageRecordDao.addMessageRecord(userId, content);
		if(sessionContext.isOnline(userId)){
			//刷新消息列表
			WebSocketMessage<ChatMessageResult> webSocketMessage = new WebSocketMessage<>(3,null);
			//发送
			WebSocket.topSendMessage(webSocketMessage, userId);
		}
		return flag != null && flag == 1;
	}
	
	/**
	 * 查询好友申请记录详情
	 */
	@Override
	public TFriendApply queryFriendApplyDetail(Long applyId) {
		//因为一次只能查出一个用户的详情，所以查询两次，然后赋值
		TFriendApply applyA = tFriendApplyDao.queryDetailById(applyId, "A");
		TFriendApply applyB = tFriendApplyDao.queryDetailById(applyId, "B");
		applyA.settUserB(applyB.gettUserB());
		return applyA;
	}
	
	/**
	 * 查询网站推送记录详情
	 */
	@Override
	public TMessageRecord queryMessageRecordDetail(Long messageId) {
		return tMessageRecordDao.queryDetailById(messageId);
	}
	
	
	/**
	 * 修改 消息 读取状态 
	 */
	@Transactional
	@Override
	public boolean updateMesageState(Integer type,Long messageId, Integer state) {
		Integer flag = null;
		if(type == 1){//修改好友申请状态
			flag = tFriendApplyDao.updateByApplyId(messageId, state);
			//如果是申请同意  在好友表中建立二人关系  --其实可以用触发器。
			if(state == 2){
				TFriendApply apply = tFriendApplyDao.queryDetailById(messageId, "A");//只是我i饿了查询出userIdA和userIdB
				tFriendService.addFriend(apply.getUserIdA(), apply.getUserIdB());
			}
		}
		if(type == 2){//修改网站推送消息读取状态  -默认从未读设置成已读
			flag = tMessageRecordDao.updateState(messageId);
		}
		
		return flag != null && flag == 1;
	}
	
	
	
	
}
