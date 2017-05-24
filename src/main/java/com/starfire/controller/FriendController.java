package com.starfire.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.starfire.domain.TFriend;
import com.starfire.domain.TUser;
import com.starfire.dto.ChatMessageResult;
import com.starfire.dto.ChatRecord;
import com.starfire.dto.WebSocketMessage;
import com.starfire.service.TFriendService;
import com.starfire.session.SessionContext;
import com.starfire.websocket.WebSocket;

/**
 *好友controller 
 */
@Controller
@RequestMapping("/friend")
public class FriendController {
	private SessionContext sessionContext = SessionContext.getSessionContext();
	@Autowired
	private TFriendService tFriendService;
	
	
	/**
	 * 向某个好友发送聊天消息
	 */
	@PostMapping("/chatMessage")
	@ResponseBody
	public boolean chatMessage(HttpSession session,
			@RequestParam("userId")Long userId,@RequestParam("message")String message){
		TUser tUser = ((TUser)session.getAttribute("tUser"));
		//存入数据库
		boolean flag = tFriendService.sendChatMessage(tUser.getUserId(), userId, message);
		//判断接收用户是否在线,  在线：发送websocket消息
		if(sessionContext.isOnline(userId)){
			//创建websocket消息
			ChatMessageResult chatMessageResult = new ChatMessageResult(tUser,message);
			WebSocketMessage<ChatMessageResult> webSocketMessage = new WebSocketMessage<>(2,chatMessageResult);
			//发送
			WebSocket.topSendMessage(webSocketMessage, userId);
		}
		return flag;//只要成功数据库了，就算成功了
	}
	
	/**
	 * 查询与某个好友的聊天记录
	 */
	@RequestMapping("/chatRecord")
	public String chatRecord(@RequestParam("userId")Long userId,HttpSession session,Model model){
		TUser tUser = ((TUser)session.getAttribute("tUser"));
		//查询聊天记录
		ChatRecord chatRecord = tFriendService.queryChatRecord(tUser.getUserId(), userId);
		model.addAttribute("chatRecord", chatRecord);
		return "common/chatRecord";
	}
	
	/**
	 *修改与某个好友聊天记录状态为已读 
	 *userIdA 是 自己 userIdB是对方（）
	 */
	@PostMapping("/changeChatState")
	@ResponseBody
	public boolean changeState(@RequestParam("userId")Long userId,HttpSession session){
		TUser tUser = ((TUser)session.getAttribute("tUser"));
		return tFriendService.updateFriendStateToOne(userId,tUser.getUserId());
	}
	
	/**
	 * 获取好友列表
	 */
	@RequestMapping("/list")
	public String list(HttpSession session,Model model){
		TUser tUser = ((TUser)session.getAttribute("tUser"));
		//查询出好友列表
		List<TFriend> friendList = tFriendService.queryAllByUserId(tUser.getUserId());
		//判断用户是否在线
		for (TFriend tFriend : friendList) {
			tFriend.setOnline(sessionContext.isOnline(tFriend.getUserIdA()));
		}
		
		model.addAttribute("friendList", friendList);
		return "common/friendList";
	}
	
	
	/**
	 * 发起好友申请
	 */
	@PostMapping("/apply")
	@ResponseBody
	public boolean apply(@RequestParam("userIdB")Long userIdB,HttpSession session){
		Long userIdA = ((TUser)session.getAttribute("tUser")).getUserId();
		//当不是好友，且不再好友申请中  发起好友申请请求
		if(!tFriendService.verifyIsFriend(userIdA, userIdB) && !tFriendService.verifyIsFriendApply(userIdA, userIdB)){
			boolean flag = tFriendService.addFriendApply(userIdA, userIdB);//添加申请记录
			//判断接收用户是否在线,  在线：发送websocket消息
			if(sessionContext.isOnline(userIdB)){
				//刷新消息列表
				WebSocketMessage<ChatMessageResult> webSocketMessage = new WebSocketMessage<>(3,null);
				//发送
				WebSocket.topSendMessage(webSocketMessage, userIdB);
			}
			
			return flag;
		}
		return false;
	}

}
