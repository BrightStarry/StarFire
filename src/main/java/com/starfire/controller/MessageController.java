package com.starfire.controller;


import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.starfire.domain.TFriendApply;
import com.starfire.domain.TMessageRecord;
import com.starfire.domain.TUser;
import com.starfire.dto.ChatMessageResult;
import com.starfire.dto.Message;
import com.starfire.dto.MessageList;
import com.starfire.dto.WebSocketMessage;
import com.starfire.service.TMessageService;
import com.starfire.service.TUserService;
import com.starfire.session.SessionContext;
import com.starfire.websocket.WebSocket;

/**
 *消息controller 
 */
@Controller
@RequestMapping("/message")
public class MessageController {
	private SessionContext sessionContext = SessionContext.getSessionContext();
	@Autowired
	private TMessageService tMessageService;
	@Autowired
	private TUserService tUserService;
	
	
	/**
	 * 修改消息状态 
	 */
	@PostMapping("/change/state")
	@ResponseBody
	public boolean changeState(@RequestParam("type")Integer type,
			@RequestParam("messageId")Long messageId,
			@RequestParam(value="state",required=false)Integer state,
			@RequestParam(value="userIdA",required=false)Long userIdA,
			@RequestParam(value="userIdB",required=false)Long userIdB){

		state = state == null ? 1  : state;//如果没有指定state，设定它为1(已读)
		boolean flag = tMessageService.updateMesageState(type, messageId, state);
		//如果是 点击了 同意或拒绝好友请求
		if(userIdA != null && userIdA != 0 && (state == 2 || state == -2)){
			//判断接收用户是否在线,  在线：发送websocket消息
			if(sessionContext.isOnline(userIdA)){
				TUser tUser = tUserService.queryUserInfoById(userIdB);
				if(state == 2){//如果同意了
					//刷新好友列表
					WebSocketMessage<ChatMessageResult> webSocketMessage = new WebSocketMessage<>(4,null);
					//发送
					WebSocket.topSendMessage(webSocketMessage, userIdA);
					//发送消息通知
					tMessageService.sendMessageForUserId(userIdA,"你好，你发送给用户：" + (StringUtils.isNotEmpty(tUser.getName()) ? tUser.getName() : tUser.getPhone()) + " 的好友申请，已经被他同意。" );
				}else{
					//拒绝
					tMessageService.sendMessageForUserId(userIdA,"你好，你发送给用户：" + (StringUtils.isNotEmpty(tUser.getName()) ? tUser.getName() : tUser.getPhone()) + " 的好友申请，已经被他拒绝。" );
				}
			}
		}
		return flag;
	}
	
	/**
	 * 获取消息列表   暂且试试直接返页面
	 * 暂时全当能进入这个方法的都是已经登录的用户，也就是当作session中的tUser不为空，
	 * 等下直接用拦截器拦截所有未登录用户的请求就可以了。或者判断下是否登录，然后确认是否发起这个请求
	 */
	@RequestMapping("/list")
	public String list(HttpSession session,Model model){
		TUser tUser =((TUser)session.getAttribute("tUser"));
		//查询出用户所有的消息放入session
		MessageList messageList = tMessageService.queryUserMessageList(tUser.getUserId());
		model.addAttribute("messageList", messageList);
		return "common/messageList";
	}
	
	/**
	 * 返回 消息详情页面
	 */
	@RequestMapping("/detail")
	public String detail(@RequestParam("messageId")Long messageId,@RequestParam("type")Integer type,Model model){
		Message message = new Message();
		message.setType(type);
		switch (type) {
			case 1:
				TFriendApply apply = tMessageService.queryFriendApplyDetail(messageId);
				message.settFriendApply(apply);
				break;
			case 2:
				TMessageRecord messageRecord = tMessageService.queryMessageRecordDetail(messageId);
				message.settMessageRecord(messageRecord);
				break;
		}
		model.addAttribute("message", message);
		return "common/messageDetail";
	}

}
