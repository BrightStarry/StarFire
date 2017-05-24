package com.starfire.controller;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.starfire.domain.TUser;
import com.starfire.domain.TUserDetail;
import com.starfire.dto.CommonResult;
import com.starfire.service.SMSService;
import com.starfire.service.TUserService;
import com.starfire.session.SessionContext;

/**
 * 用户
 */
@RestController
@RequestMapping("/user")
public class UserController {
	private static SessionContext sessionContext = SessionContext.getSessionContext();
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private TUserService tUserService;
	@Autowired
	private SMSService smsService;
	
	
	
	/**
	 * 模糊查询 根据name
	 */
	@PostMapping("/search/name/like")
	@ResponseBody
	public List<TUser> searchNameLike(@RequestParam("query")String query){
		List<TUser> list = tUserService.queryUserByLike(query);
		return list;
		
	}
	
	
	
	
	/**
	 * 上传或修改用户头像
	 */
	@PostMapping("/upload/headImage")
	public boolean uploadHeadImage(@RequestParam("headImage")MultipartFile file,HttpSession session){
		TUser tUser = null;
		try {
			//项目图片路径
//			String path = session.getServletContext().getRealPath("/WEB-INF/resources/headImage/");
			//Eclipse
//			String path = "F:\\ideaworkspace\\StarFire\\src\\main\\webapp\\WEB-INF\\resources\\headImage\\";
			//IDEA
			String path = "C:\\apache-tomcat-8.0.41\\webapps\\ROOT\\WEB-INF\\resources\\headImage\\";
//			//Linux
//			String path = File.separator + "zx" + File.separator + "tomcat" + File.separator + "webapps"
//					+ File.separator + "ROOT" + File.separator + "WEB-INF" + File.separator
//					+ "resources" + File.separator + "headImage" + File.separator;
			//文件后缀名
			String type = file.getOriginalFilename().substring(
					file.getOriginalFilename().indexOf(".")).toLowerCase();
			//最终路径，用userId作文件名
			tUser = (TUser)session.getAttribute("tUser");
			path += tUser.getUserId() + type;
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path));
			//修改用户headImage
			tUser.setHeadImage(tUser.getUserId() + type);
			tUserService.updateUserInfoById(tUser);
			//将更新后的用户信息存入session
			session.setAttribute("tUser", tUser);
			return true;
		} catch (Exception e) {
			logger.warn("用户：" + 
					tUser.getUserId() + "上传头像失败！" + e.getMessage());
			return false;
		}
	}
	
	
	
	/**
	 * 修改用户基本信息
	 * 记住，每次涉及到更新，要更新session中的数据
	 */
	@PostMapping("/edit/userInfo")
	public boolean editUserInfo(TUser tUser,HttpSession session){
		tUser.setUserId(((TUser)session.getAttribute("tUser")).getUserId());//设置好id
		TUser editedTUser  = tUserService.updateUserInfoById(tUser);
		if(editedTUser != null){
			session.setAttribute("tUser", editedTUser);
			return true;
		}
		return false;
	}
	
	/**
	 * 修改用户详细信息
	 */
	@PostMapping("/edit/userDetail")
	public boolean editUserDetail(TUserDetail tUserDetail,HttpSession session){
		if(session.getAttribute("tUser") != null){
			tUserDetail.setUserId(((TUser)session.getAttribute("tUser")).getUserId());
		}
		return tUserService.editUserDetailById(tUserDetail);
	}
	
	/**
	 * 查询用户详细信息
	 */
	@PostMapping("/query/userDetail")
	@ResponseBody
	public TUserDetail queryUserDetail(HttpSession session){
		if(session.getAttribute("tUser") != null){
			return tUserService.queryUserDetailByUserId(((TUser)session.getAttribute("tUser")).getUserId());
		}
		return null;
	}
	
	
	/**
	 * 获取登录的用户的基本信息
	 */
	@PostMapping("/query/userInfo")
	public TUser info(HttpSession session){
		return session.getAttribute("tUser") != null ? (TUser)session.getAttribute("tUser") : null;
	}
	
	
	/**
	 * 登录            验证 手机 密码
	 * @return CommonResult<TUser>
	 */
	@PostMapping("/login")
	@ResponseBody
	public CommonResult<TUser> login(@RequestParam(name="phone",required=false)long phone,
			@RequestParam(name = "password",required=false)String password,
			@RequestHeader(name="x-real-ip",required=false)String ip,
			HttpSession session){
		
		CommonResult<TUser> commonResult = tUserService.login(phone, password);
		loginCommonFun(commonResult, session, ip);
		return commonResult;
	}

	/**
	 * 注册方法 AJAX
	 * @return 注册返回对象 {注册状态 and 状态信息}
	 */
	@PostMapping("/register")
	public CommonResult<TUser> register(@RequestParam(name="phone",required=false) long phone, 
			@RequestParam(name = "password",required=false) String password,
			HttpSession session,@RequestHeader(name="x-real-ip",required=false)String ip) {
		CommonResult<TUser> commonResult = tUserService.register(phone, password);
		loginCommonFun(commonResult, session, ip);
		return commonResult;
	}

	
	
	/**
	 * 验证图片验证码
	 */
	@PostMapping("/verifyImageCheckCode/{checkCode}")
	public boolean verifyImageCheckCode(@PathVariable("checkCode")String checkCode,HttpSession session){
		//如果传过来的验证码不为空，且等于之前存在sessin中的验证码，返回true
		if(checkCode != null && !checkCode.equals("") && session.getAttribute("imageCheckCode")!= null
				&& checkCode.toLowerCase().equals(session.getAttribute("imageCheckCode"))){
			return true;
		}
		return false;
	}
	
	/**
	 * 验证手机号码是否已经注册
	 * false 注册 true 未注册
	 */
	@PostMapping("/checkPhone/{phone}")
	public boolean checkPhone(@PathVariable("phone")long phone){
		return tUserService.checkAccount(phone, null, null);
	}
	
	/**
	 * 发送手机验证码
	 */
	@PostMapping("/sendCode")
	public boolean sendCode(@RequestParam("imageCheckCode")String checkCode,@RequestParam("phone")long phone,HttpSession session){
		//如果传过来的验证码不为空，且等于之前存在sessin中的验证码，返回true
		if(checkCode != null && !checkCode.equals("") && session.getAttribute("imageCheckCode")!= null
				&& checkCode.toLowerCase().equals(session.getAttribute("imageCheckCode"))){
			session.removeAttribute("imageCheckCode");//如果验证通过，这个验证码就无效i了
			return smsService.sendCheckCode(phone);
		}
		return false;
	}
	
	/**
	 * 验证 手机验证码
	 */
	@PostMapping("/verifyCheckCode")
	public boolean verifyCheckCode(@RequestParam("phone")long phone,@RequestParam("checkCode")String checkCode){
		return smsService.verifyCheckCode(phone, checkCode);
	}
	
	/**
	 * 注销
	 */
	@PostMapping("/exit")
	public boolean exit(HttpSession session){
		if(session.getAttribute("tUser") != null ){
			sessionContext.deleteSession(session);//在这个方法中会将session中的tUser删除
		}
		return true; 
	}
	
	/**
	 *将 获取的日期参数转换格式 
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	/**
	 * 存储用户登录记录
	 */
	public void addIp(HttpSession session,String ip,CommonResult<TUser> commonResult){
		//如果为空，尝试从session中取其第一次访问的ip，根据natapp官网说，一般第一次会记录其ip
		//2017年5月15日 21:36:35 知识果然都是连贯的。现在知道了。。这个不过是nginx的反向代理设置而已
		ip = ip == null || ip.equals("") ? (String) session.getAttribute("x-real-ip") : ip;
		ip = ip == null ? "" : ip;
		//存入数据库
		tUserService.addLoginRecord(commonResult.getData().getUserId(), ip);
	}
	
	/**
	 * 用户登录 校验成功后执行的方法
	 */
	public void loginCommonFun(CommonResult<TUser> commonResult,HttpSession session,String ip){
		if(commonResult.isState() == true){
			session.setAttribute("tUser", commonResult.getData());//将用户信息放入session
			sessionContext.addSession(session);//添加session到sessionContext
			addIp(session, ip, commonResult);//增加登录记录
		}
	}
	
	

}
