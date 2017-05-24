<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入jstl -->
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>星火--首页</title>
<%@include file="common/head.jsp"%>
<style>
html, body {
	margin: 0px 0px;
	width: 100%;
	height: 100%;
}

iframe {
	margin: 0px 0px;
	width: 100%;
	height: 90%;
}
</style>

</head>
<body class="bb-js">
	<%@include file="common/navigation.jsp" %>



	<iframe src="${pageContext.request.contextPath }/indexBody" name="indexBody" frameborder="no"></iframe>
	
<%-- 	<%@include file="indexBody.jsp" %> --%>
	



	<!-- JS dependencies -->
	<%@include file="common/jquery.jsp"%>
	<%@include file="common/end.jsp"%>
	<script src="${pageContext.servletContext.contextPath }/resources/js/index.js" type="text/javascript"></script>


</body>
<script type="text/javascript">
	$(function() {
		//执行初始化方法
		index.init();
	});
</script>
</html>