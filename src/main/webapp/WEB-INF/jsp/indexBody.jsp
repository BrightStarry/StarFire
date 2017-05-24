<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="common/tag.jsp"%>
	<%@include file="common/head.jsp"%>    
    
    


	<!-- 未登录 -->
	<c:if test="${empty tUser}">
		<div class="list-group">
			<a href="#" class="list-group-item active">
				<h4 class="list-group-item-heading" >欢迎您来到星火</h4>
			</a> <a href="#" class="list-group-item">
				<h4 class="list-group-item-heading">手机号码快速注册</h4>
				<p class="list-group-item-text">您可以通过手机接收我发送的验证码，快速地进行网站帐号注册。</p>
			</a> <a href="#" class="list-group-item">
				<h4 class="list-group-item-heading">帐号登录</h4>
				<p class="list-group-item-text">如果您已经注册了星火的帐号，可以点击右上角的登录按钮登录。</p>
			</a>
		</div>
	</c:if>
	<div class="list-group">
		<a href="#" class="list-group-item active">
			<h4 class="list-group-item-heading">网站基本功能</h4>
		</a> <a href="#" class="list-group-item">
			<h4 class="list-group-item-heading">好友聊天</h4>
			<p class="list-group-item-text">您可以通过网站向其他用户发起聊天。</p>
		</a> <a href="#" class="list-group-item">
			<h4 class="list-group-item-heading">弹幕发送</h4>
			<p class="list-group-item-text">无论你是否登录，都可以通过底部消息框发送（点击发送或回车）弹幕。</p>
		</a>
	</div>
	<div class="list-group">
		<a href="#" class="list-group-item active">
			<h4 class="list-group-item-heading">更多功能</h4>
		</a> <a href="#" class="list-group-item">
			<h4 class="list-group-item-heading">人工智能</h4>
			<p class="list-group-item-text">注册完您的帐号后，您可以和网站中有趣的智能机器人进行聊天。</p>
		</a> <a href="#" class="list-group-item">
			<h4 class="list-group-item-heading" href="#" >年龄测试</h4>
			<p class="list-group-item-text">您可以上传自己或他人的照片，本站会自动测试出照片中人物的大致年龄。</p>
		</a>
	</div>
	
	<%@include file="common/jquery.jsp"%>
	
