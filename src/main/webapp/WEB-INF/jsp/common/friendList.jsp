<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入jstl -->
<%@include file="tag.jsp"%>
<c:if test="${fn:length(friendList) == 0}">
	<li><a href="#">没有好友</a></li>
</c:if>
<c:forEach var="tFriend" items="${friendList}">
	<li>
		<a onclick="index.friend.chatModal(${tFriend.tUserA.userId})" href="#" 
			<c:if test="${!tFriend.isOnline}">
				style="color:Gray;"
			</c:if>
		>
			<span id="chatUserName">
				<c:if test="${!empty tFriend.tUserA.name }">
					${tFriend.tUserA.name}
				</c:if>
				<c:if test="${empty tFriend.tUserA.name }">
					${tFriend.tUserA.phone}
				</c:if>
			</span>
			<c:if test="${tFriend.state == -1}">
				<span class="badge" name="chatState" id="chatState${tFriend.tUserA.userId}">!</span>
			</c:if>
		</a>
			
	</li>
</c:forEach>
<script type="text/javascript">
$(function() {
	//如果找到了提醒消息符号，在导航栏上也显示
	if($('span[name=chatState]').text() == '!'){
		$('#friendMessageNum').text('!');
	}else{
		$('#friendMessageNum').text('');
	}
	
});
</script>