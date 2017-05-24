<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入jstl -->
<%@include file="tag.jsp"%>
<c:if test="${fn:length(messageList.messages) == 0}">
	<li><a href="#">暂时没有消息</a></li>
</c:if>
<c:forEach var="message" items="${messageList.messages}">
	<c:if test="${message.type == 1 }">
		<!-- 好友消息 -->
		<li><a
			<c:if test="${message.tFriendApply.state != -1 }">
				style="color:Gray;"
			</c:if>
			onclick="index.message.messageDetailModal(${message.type},${message.tFriendApply.applyId },${message.tFriendApply.state},${message.tFriendApply.userIdA },${message.tFriendApply.userIdB })"
			href="#">好友申请请求：
			<c:if test="${!empty message.tFriendApply.tUserA.name}">
				${message.tFriendApply.tUserA.name}
			</c:if>
			<c:if test="${empty message.tFriendApply.tUserA.name}">
				${message.tFriendApply.tUserA.phone}
			</c:if>
			</a>
		</li>
	</c:if>
	<c:if test="${message.type == 2 }">
		<!-- 网站推送消息 -->
		<li><a
			<c:if test="${message.tMessageRecord.state != -1 }">
				style="color:Gray;"
			</c:if>
			onclick="index.message.messageDetailModal(${message.type},${message.tMessageRecord.messageId },${message.tMessageRecord.state },this)"
			href="#"> <c:if
					test="${fn:length(message.tMessageRecord.content) > 5 }">
					星火：${fn:substring(message.tMessageRecord.content,0,5) }...
				</c:if> <c:if test="${fn:length(message.tMessageRecord.content) <= 5 }">
					星火：${message.tMessageRecord.content }
				</c:if>
		</a></li>
	</c:if>
</c:forEach>
<script type="text/javascript">
$(function() {
	var unreadNum = ${messageList.unread};
	if(unreadNum > 0){
		$('#unreadNum').text(unreadNum);
	}else{
		$('#unreadNum').text('');
	}
	
});
</script>
