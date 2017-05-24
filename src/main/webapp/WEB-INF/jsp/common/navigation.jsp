<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- 引入jstl -->
<%@include file="tag.jsp" %>
<!-- 导航栏 -->
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header col-lg-1">
            <a class="navbar-brand"
               href="${pageContext.request.contextPath }/indexBody"
               target="indexBody"><b> &nbsp;&nbsp;&nbsp;星火<br/>StarFire
            </b></a>
        </div>

        <div class="col-lg-4">
            <p class="navbar-text">一个为了毕业设计而生的网站</p>
        </div>
        <!-- 登录 -->
        <c:if test="${!empty tUser}">
            <!-- 用户搜索 -->
            <div class="col-lg-4">
                <form class="navbar-form navbar-left" role="search">
                    <!-- autocomplete="off" 表示关闭浏览器的自动提示功能，防止遮挡 -->
                    <input type="text" class="form-control" placeholder="输入昵称"
                           data-provide="typeahead" autocomplete="off" id="searchUserInput">
                    <input type="hidden" id="searchUserIdInput">
                    <button type="button" class="btn btn-default" id="searchUserBtn">进入主页</button>
                </form>
            </div>
        </c:if>

        <ul class="nav navbar-nav navbar-right">
            <!-- 登录 -->
            <c:if test="${!empty tUser}">
                <li><a href="#" class="dropdown-toggle" data-toggle="dropdown"><span
                        class="glyphicon glyphicon-home"> 欢迎你，${!empty tUser.name ? tUser.name : tUser.phone }</span>
                </a>
                    <ul class="dropdown-menu">
                        <li><a
                                href="${pageContext.request.contextPath }/userIndex/${tUser.userId}"
                                target="indexBody">我的首页</a></li>
                        <li><a onclick="index.user.headImageUploadModal();" href="#">上传头像</a></li>
                        <li><a onclick="index.user.userInfoModal();" href="#">修改基本信息</a></li>
                        <li><a onclick="index.user.userInfoDetailModal();" href="#">修改详细信息</a></li>
                        <li class="divider"></li>
                        <li><a onclick="index.user.userExit()">退出登录</a></li>
                    </ul>
                </li>
                <li><a href="#" class="dropdown-toggle" data-toggle="dropdown" ><span
                        class="glyphicon glyphicon-envelope">消息 </span> <span
                        class="badge" id="unreadNum"></span></a>
                    <ul id="messageUl" class="dropdown-menu">
                    </ul>
                </li>
                <li><a href="#" class="dropdown-toggle" data-toggle="dropdown"><span
                        class="glyphicon glyphicon-star">功能 </span> </a>
                    <ul class="dropdown-menu">
                        <li><a onclick="index.fun.faceAgeTestModal()" href="#">人脸年龄测试</a></li>
                        <li><a onclick="index.fun.aiChatModal()" href="#">AI CHAT</a></li>
                    </ul>
                </li>
                <li><a href="#" class="dropdown-toggle" data-toggle="dropdown"><span
                        class="glyphicon glyphicon-user">好友 </span> <span
                        class="badge" id="friendMessageNum"></span></a>
                    <ul id="friendUl" class="dropdown-menu">
                    </ul>
                </li>

            </c:if>
            <!-- 未登录 -->
            <c:if test="${empty tUser}">
                <li><a href="#" onclick="index.login.openLoginModal()"><span
                        class="glyphicon glyphicon-log-in"></span> 登录</a></li>
                <li><a href="#" onclick="index.register.openRegisterModal()"><span
                        class="glyphicon glyphicon-user"></span> 注册</a></li>
            </c:if>
        </ul>

    </div>
</nav>
<c:if test="${empty SessionScope.tUser}">
    <!-- 注册模拟框 -->
    <div id="registerModal" class="modal fade">
        <div class="container">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" style="color: white;">
                        <a class="close" data-dismiss="modal">×</a>
                        <h3 class="modal-title text-center">
                            <span class="glyphicon glyphicon-phone"></span><b>注册</b>
                        </h3>
                    </div>

                    <div class="modal-body">
                        <form id="registerForm">
                            <div class="row">
                                <div class="col-lg-4 col-lg-offset-4">
                                    <div class="form-inline">
                                        <input type="text" id="registerPhone" name="phone"
                                               placeholder="请输入手机号码" class="form-control"/> <span
                                            class="glyphicon glyphicon-ok hidden"
                                            id="registerPhoneChecked"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-4 col-lg-offset-4">
                                    <div class="form-inline">
                                        <input type="text" id="imageCheckCode" name="phone"
                                               placeholder="请输入图片验证码" class="form-control"/> <span
                                            class="glyphicon glyphicon-ok hidden"
                                            id="imageCheckCodeChecked"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-4 col-lg-offset-4">
                                    <img
                                            src="${pageContext.request.contextPath }/imageCheckCodeGenerator/1"
                                            onclick="this.src='${pageContext.request.contextPath }/imageCheckCodeGenerator/' + Math.random()"/>
                                    <button type="button" class="btn  btn-success " id="sendCode"
                                            data-loading-text="发送中..." disabled='disabled'
                                            style="margin-left: 40px;">发送验证码
                                    </button>
                                    <span id="sendCodeMessage" class="glyphicon"></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-4 col-lg-offset-4">
                                    <div class="form-inline">
                                        <input type="text" placeholder="请输入验证码" class="form-control"
                                               id="checkCode"/> <span
                                            class="glyphicon glyphicon-ok hidden" id="checkCodeChecked"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-4 col-lg-offset-4">
                                    <div class="form-inline">
                                        <input type="password" placeholder="请输入密码"
                                               class="form-control" id="password"/> <span
                                            class="glyphicon glyphicon-ok hidden" id="passwordChecked"></span>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div class="row">
                            <div class="col-lg-5 col-lg-offset-2 ">
                                <span class="glyphicon"></span>
                                <button type="button" class="btn btn-info" id="registerBtn">
                                    <span class="glyphicon glyphicon-phone"></span> 注册
                                </button>
                                <span id="registerBtnMessage" class="glyphicon"></span>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /注册模拟框 -->

    <!-- 登录模拟框 -->
    <div id="loginModal" class="modal fade">
        <div class="container">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" style="color: white;">
                        <a class="close" data-dismiss="modal">×</a>
                        <h3 class="modal-title text-center">
                            <span class="glyphicon glyphicon-phone"></span><b>登录</b>
                        </h3>
                    </div>
                    <form id="loginForm" action="">
                        <div class="modal-body">
                            <div class="row">
                                <div class="text-center col-lg-6 col-lg-offset-3">
                                    <input type="text" id="loginPhone" name="phone"
                                           placeholder="请输入手机号码" class="form-control"/> <span
                                        class="glyphicon glyphicon-ok hidden" id="loginPhoneChecked"></span>
                                </div>
                            </div>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="text-center col-lg-6 col-lg-offset-3">
                                    <input type="password" placeholder="请输入密码" class="form-control"
                                           id="loginPassword"/>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <div class="row">
                                <div class="col-xs-5 col-xs-offset-2 ">
                                    <span class="glyphicon"></span>
                                    <button type="button" class="btn btn-success" id="loginBtn">
                                        <span class="glyphicon glyphicon-phone"></span> 登录
                                    </button>
                                    <span id="loginBtnMessage" class="glyphicon"></span>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</c:if>


<!-- 基本信息修改modal 登录后显示-->
<c:if test="${!empty tUser}">
    <div id="userInfoModal" class="modal fade">
        <div class="container">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" style="color: white;">
                        <a class="close" data-dismiss="modal">×</a>
                        <h3 class="modal-title text-center">
                            <span class="glyphicon glyphicon-user"></span><b>基本信息</b>
                        </h3>
                    </div>
                    <div class="modal-body">
                        <form id="userInfoForm">
                            <div class="row">
                                <div class="col-lg-6 col-lg-offset-3">
                                    <input type="text" id="name" name="name" placeholder="请输入你的昵称"
                                           class="form-control"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-6 col-lg-offset-3">
                                    <input type="text" id="intro" name="intro"
                                           placeholder="请输入你的一句话简介" class="form-control"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-6 col-lg-offset-3">
                                    <input type="text" id="mail" name="mail"
                                           placeholder="请输入你的邮箱地址" class="form-control"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-6 col-lg-offset-3">
                                    <div style="color: white;">
                                        性别： <input type="radio" id="man" value="男" name="sex"/><label
                                            for="man">男</label> <input type="radio" id="woman" value="女"
                                                                       name="sex"/><label for="woman">女</label>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div class="row">
                            <div class="col-lg-5 col-lg-offset-2 " style="color: white;">
                                <span class="glyphicon"></span>
                                <button type="button" class="btn btn-success" id="userInfoBtn">
                                    <span class="glyphicon glyphicon-pencil"></span> 提交
                                </button>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>


    <!-- 头像上传modal 登录后显示-->
    <div id="headImageUploadModal" class="modal fade" tabindex="-1"
         role="dialog" aria-labelledby="myModalLabel" style="color: white;">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h3 class="modal-title text-center">
                        <span class="glyphicon glyphicon-picture"></span><b>头像上传</b>
                    </h3>
                </div>
                <div class="modal-body">
                    <form id="headImageForm">
                        <input type="file" name="headImage" id="imageFile" multiple
                               class="file-loading"/>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- 详细信息 修改modal 登录后显示-->
    <div id="userDetailModal" class="modal fade" tabindex="-1"
         role="dialog" aria-labelledby="myModalLabel" style="color: white;">
        <div class="container">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" style="color: white;">
                        <a class="close" data-dismiss="modal">×</a>
                        <h3 class="modal-title text-center">
                            <span class="glyphicon glyphicon-user"></span><b>详细信息</b>
                        </h3>
                    </div>
                    <div class="modal-body">
                        <form id="userDetailForm">
                            <div class="row col-lg-6 col-lg-offset-3">
                                <label>出生年月：</label>
                                <div class="input-group date form_date" style="color: white;"
                                     data-date="" data-date-format="yyyy-mm-dd"
                                     data-link-field="birthdate" data-link-format="yyyy-mm-dd">
                                    <input class="form-control" size="16" type="text"
                                           placeholder="选择你的出生日期" name="birthdate"> <span
                                        class="input-group-addon"><span
                                        class="glyphicon glyphicon-remove"></span></span> <span
                                        class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="row col-lg-6 col-lg-offset-3">
                                <label>性格：</label> <select class="form-control"
                                                           name="personality">
                                <option value="1">十分内向</option>
                                <option value="2">稍微内向</option>
                                <option value="3">一般</option>
                                <option value="4">稍微外向</option>
                                <option value="5">十分外向</option>
                            </select>
                            </div>
                            <div class="row col-lg-6 col-lg-offset-3">
                                <label>身高(cm)：</label> <select class="form-control"
                                                               name="height">
                            </select>
                            </div>
                            <div class="row col-lg-6 col-lg-offset-3">
                                <label>体重(公斤)：</label> <select class="form-control"
                                                               name="weight">
                            </select>
                            </div>
                            <div class="row col-lg-6 col-lg-offset-3">
                                <label>喜欢的颜色：</label> <select class="form-control" name="color">
                            </select>
                            </div>
                            <div class="row col-lg-6 col-lg-offset-3">
                                <label>故乡：</label>
                                <div id="hometown">
                                    <select class="form-control" data-province="" name="province"></select><br/>
                                    <!-- 省 -->
                                    <select class="form-control" data-city="" name="city"></select><br/>
                                    <!-- 市 -->
                                    <select class="form-control" data-district="" name="district"></select><br/>
                                    <!-- 区 -->
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div class="row">
                            <div class="col-lg-5 col-lg-offset-2 " style="color: white;">
                                <span class="glyphicon"></span>
                                <button type="button" class="btn btn-success" id="userDetailBtn">
                                    <span class="glyphicon glyphicon-pencil"></span> 提交
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 人脸年龄测试 登陆后显示 -->
    <div id="faceAgeTestModal" class="modal fade" tabindex="-1"
         role="dialog" aria-labelledby="myModalLabel" style="color: white;">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h3 class="modal-title text-center" >
                        <span class="glyphicon glyphicon-picture"></span><b>人脸年龄测试</b>
                    </h3>
                </div>
                <div class="modal-body">
                    <form id="faceAgeTestForm">
                        <input type="file" name="faceImage" id="faceImage" multiple
                               class="file-loading"/>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- 机器人聊天 登陆后显示 -->
    <div id="aiChatModal" class="modal fade" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" style="color: white;">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h3 class="modal-title text-center" >
                        <span class="glyphicon glyphicon-cloud"></span><b>AI CHAT</b>
                    </h3>
                </div>
                <div class="modal-body">
                    <div id="aiChatDiv" class="jumbotron pre-scrollable"></div>
                </div>
                <div class="modal-footer">
                    <div class="row">
                        <div class="input-group col-lg-6 col-lg-offset-3">
                            <input type="text" class="form-control" id="aiChatMessage">
                            <span class="input-group-btn">
								<button class="btn btn-default" type="button" id="aiChatBtn">发送</button>
							</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 消息详情 登陆后显示 -->
    <div id="messageDetailModal" class="modal fade" style="color: white;">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h3 class="modal-title text-center" >
                        <span class="glyphicon glyphicon-envelope"></span><b>消息详情</b>
                    </h3>
                </div>
                <div class="modal-body">
                    <div class="jumbotron text-left">
                        <div class="col-lg-10 col-lg-offset-1  text-center" id="messageDetailDiv">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 好友聊天框 登陆后显示 -->
    <div id="chatModal" class="modal fade" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" style="color: white;">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h3 class="modal-title text-center" id="myModalLabel">
                        <span class="glyphicon glyphicon-cloud"></span><b>好友聊天：<span id="chatUserId"></span></b>
                    </h3>
                </div>
                <div class="modal-body">
                    <div id="chatDiv" class="jumbotron pre-scrollable"></div>
                </div>
                <div class="modal-footer">
                    <div class="row">
                        <div class="input-group col-lg-6 col-lg-offset-3">
                            <input type="text" class="form-control" id="chatMessage">
                            <span class="input-group-btn">
								<button class="btn btn-default" type="button" id="chatBtn">发送</button>
							</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
