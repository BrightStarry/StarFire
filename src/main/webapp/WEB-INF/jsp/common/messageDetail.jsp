<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入jstl -->
<%@include file="tag.jsp"%>
<c:if test="${message.type == 2 }">
	<strong>
		星火：${message.tMessageRecord.content} 
	</strong>
</c:if>
<c:if test="${message.type == 1 }">
	<strong >
		<c:if test="${!empty message.tFriendApply.tUserA.name }">
			${message.tFriendApply.tUserA.name  }
		</c:if> 
		<c:if test="${empty message.tFriendApply.tUserA.name }">
			${message.tFriendApply.tUserA.phone }  (昵称未设置)
		</c:if> 
		&nbsp;&nbsp;&nbsp;请求添加你为好友。是否同意？
	</strong>
	<c:if test="${message.tFriendApply.state == -1 ||  message.tFriendApply.state == 1}">
		<div class="btn-group btn-group-sm ">
			<button type="button" class="btn btn-success" id="applyYes">同意</button>
			<button type="button" class="btn btn-danger" id="applyNo">拒绝</button>
		</div>
	</c:if>
	<c:if test="${message.tFriendApply.state == 2}">
		<button type="button" class="btn btn-success" disabled>已同意</button>
	</c:if>
	<c:if test="${message.tFriendApply.state == -2}">
		<button type="button" class="btn btn-danger" disabled>已拒绝</button>
	</c:if>
</c:if>
