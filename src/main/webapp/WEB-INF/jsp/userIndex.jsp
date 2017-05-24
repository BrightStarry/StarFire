<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/tag.jsp"%>
<%@include file="common/head.jsp"%>
<div class="container" style="color: white;">
	<div class="jumbotron">
		<div class="row">
			<div class="col-lg-1">
				<img src="${pageContext.servletContext.contextPath }/resources/headImage/${userData.headImage}"
					class="img-circle" style="width: 200px; height: 200px;">
			</div>
			<div class="col-lg-2 col-lg-offset-2 ">
				<h2>${userData.name}</h2>
			</div>
			<div class="col-lg-8 col-lg-offset-2 text-justify lead">
				<h3>
					<em><strong>${userData.intro}</strong></em>
				</h3>
			</div>
			<div class="col-lg-1 pull-right ">
				<c:if test='${userData.tUserDetail.relation != "申请好友"}'>
					<button type="button" class="btn btn-danger" id="relationBtn" disabled>${userData.tUserDetail.relation}</button>
				</c:if>
				<c:if test='${userData.tUserDetail.relation == "申请好友"}'>
					<button type="button" class="btn btn-danger" id="relationBtn" >${userData.tUserDetail.relation}</button>
				</c:if>
			</div>
		</div>
	</div>
</div>
<div class="container" style="color: white;">
	<div class="jumbotron">
		<div class="container text-center" style="padding: 100px 50px 10px;">
			<button type="button" class="btn btn-default col-lg-2"
				title="${userData.sex}" data-toggle="popover" data-placement="left">性别</button>
			<button type="button" class="btn btn-primary  col-lg-2"
				title="${userData.tUserDetail.height}cm" data-toggle="popover"
				data-placement="top">身高</button>
			<button type="button" class="btn btn-success  col-lg-2"
				title="${userData.tUserDetail.weight}斤" data-toggle="popover"
				data-placement="bottom">体重</button>
			<button type="button" class="btn btn-warning  col-lg-2"
				title="${userData.tUserDetail.color}" data-toggle="popover"
				data-placement="top">喜欢的颜色</button>
			<button type="button" class="btn btn-info  col-lg-2"
				title="${userData.tUserDetail.province}${userData.tUserDetail.city }${userData.tUserDetail.district}"
				data-toggle="popover" data-placement="bottom">故乡</button>
			<button type="button" class="btn btn-success  col-lg-2"
				title="${userData.phone}" data-toggle="popover"
				data-placement="right">手机</button>
		</div>
	</div>
</div>
<input type="hidden" value="${userData.userId }" id="userId">

<%@include file="common/jquery.jsp"%>


<script type="text/javascript">
	$(function() {
		//提示框初始化方法
		$("[data-toggle='popover']").popover({
			trigger : 'click hover focus'
		});
		
		//----------------------好友功能--------------------------
		var relation = '${userData.tUserDetail.relation}';//登录用户与主页用户的关系  申请好友 本人 好友  好友申请中
		var relationBtn = $('#relationBtn');//按钮
		//除了 申请好友  状态  其它的都把按钮禁用
		if(relation != '申请好友'){
			relationBtn.attr('disabled','true');
		}
		//定义点击 申请好友按钮的方法
		relationBtn.click(function(){
			$.post('${pageContext.request.contextPath }/friend/apply',{userIdB:$('#userId').val()},function(result){
				if(result){
					//申请成功
					relationBtn.attr('disabled','true');//禁用按钮
					relationBtn.text('好友申请中');//改变文字
					util.openMessenger('申请成功，请等待ta的回复!','success');//提示
				}else{
					util.openMessenger('申请失败!','error');//提示
				}
			});	
		});
		
		
			
		
		

	});
</script>