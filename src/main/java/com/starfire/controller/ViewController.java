package com.starfire.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.starfire.domain.TUser;
import com.starfire.service.TFriendService;
import com.starfire.service.TUserService;
import com.starfire.util.ImageCheckCodeUtil;

/**
 * 视图控制器
 */
@Controller
public class ViewController {
	
	@Autowired
	private TFriendService tFriendService;
	@Autowired
	private TUserService tUserService;
	
	
	/**
	 *返回首页 的body 页面 
	 */
	@RequestMapping("/indexBody")
	public String indexBody(){
		return "indexBody";
	}
	

	/**
	 *进入主页的方法
	 */
	@GetMapping(value="/")
	public String index(HttpServletRequest request,HttpSession session){
		String ip = request.getHeader("x-real-ip");
		if(ip != null && !ip.equals("")){
			session.setAttribute("x-real-ip", ip);
		}
		return "index";
	}
	
	
	/**
	 * 进入用户主页
	 */
	@RequestMapping("/userIndex/{userId}")
	public String userIndex(@PathVariable("userId")long userId,Model model,HttpSession session){
		//获取用户所有信息 放入model 
		TUser tUser = tUserService.queryUserInfoById(userId);
		model.addAttribute("userData", tUser);
		//判断 用户主页用户 与 进入该用户主页的用户的关系
		String relation = "申请好友";//保存关系 的string 默认为 陌生人
		Long loginUserId = ((TUser)session.getAttribute("tUser")).getUserId();//登录用户的id
		//比较是否本人
		if(loginUserId.equals(userId))
			relation = "本人";
		//比较是否是好友
		if(tFriendService.verifyIsFriend(loginUserId,userId))
			relation = "好友";
		//比较是否申请中
		if(tFriendService.verifyIsFriendApply(loginUserId, userId))
			relation = "好友申请中";
		//将 relation 放入 model
		tUser.gettUserDetail().setRelation(relation);
		return "userIndex";
	}
	
	/**
	 * 生成图片验证码
	 */
	@GetMapping("/imageCheckCodeGenerator/{random}")
	public void imageCheckCodeGenerator(HttpSession session, HttpServletResponse response) {
		ImageCheckCodeUtil imageCheckCodeUtil = new ImageCheckCodeUtil();
		// 将验证码存入session
		session.setAttribute("imageCheckCode", imageCheckCodeUtil.getCode());
		// 设置响应设置
		response.setContentType("image/png");
		// 禁止图像缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 返回写入图片
		try {
			imageCheckCodeUtil.write(response.getOutputStream());
		} catch (IOException e) {
			//TODO
		}
	}
	
	
}
