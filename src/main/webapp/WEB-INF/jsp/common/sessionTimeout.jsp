<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="head.jsp"%>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="jumbotron" style="color:white;">
				<h1>
					Sorry，你的访问被拒绝!
				</h1>
				<p>
					这位同学，你好，首先告诉你一个很不好的消息，那就是你的访问被拒绝了。引发这个问题的原因很简单，不是你没有登录，却试图访问登录后才有权限访问的地址，就是你的登录超时了。所以等下，你将会被刷新界面。goodbye!
				</p>
<!-- 				<p> -->
<!-- 					 <a class="btn btn-primary btn-large" href="#">Learn more</a> -->
<!-- 				</p> -->
			</div>
		</div>
	</div>
</div>
<%@include file="jquery.jsp"%>
<script>
	var goodbye = function(){
		parent.location.reload();
	};
	$(function(){
		setTimeout(goodbye, 5000);
	});
</script>