<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入jstl -->
<%@include file="tag.jsp"%>
<%-- <c:if test="${fn:length(chatRecord.tChatRecordList) == 0 }">
	<p class="text-center">----------暂时没有聊天记录----------</p>
</c:if> --%>
<c:forEach var="tChatRecord" items="${chatRecord.tChatRecordList}">
	<c:if test="${tChatRecord.userIdA == chatRecord.userIdA }">
		<p class="text-left col-lg-offset-1 col-lg-11 small list-inline text-primary">you:${tChatRecord.content}</p>
	</c:if>
	<c:if test="${tChatRecord.userIdA == chatRecord.userIdB }">
		<p class="text-right col-lg-11 small list-inline">${tChatRecord.content}:${chatRecord.nameB}</p>
	</c:if>
</c:forEach>