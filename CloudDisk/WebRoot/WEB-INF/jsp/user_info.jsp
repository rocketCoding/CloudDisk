<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>

<head>
<meta charset="utf-8">
<title>用户中心</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>

<body>
	<div>
		<!-- 顶部导航 -->
		<jsp:include page="header.jsp"></jsp:include>

	</div>

	<!--  用户信息div开始  -->
	<div class="tab-pane" id="user_info" style="width: 800px;margin-left: 25px">
		<div class="panel panel-default" >
			<div class="panel-heading">用户个人信息</div>
			<div style="padding: 50px 50px 10px;">
				<form class="bs-example bs-example-form" role="form" action="${pageContext.request.contextPath }/user/user_updateUser.action"
				method="post" enctype="multipart/form-data">
					<div class="input-group">
						<span class="input-group-addon">邮箱</span>
						<input type="text" class="form-control" placeholder="邮箱" 
						value="${userInfo.userEmail}" name="user.userEmail">
					</div>
					<br>
					<div class="input-group">
					   <span class="input-group-addon">用户名(不可更改)</span>
						<input type="text" class="form-control" placeholder="用户名" 
							value="${userInfo.userName }" readonly="true" name="user.userName">
					</div>
					<br>
					<div class="input-group">
					   <span class="input-group-addon">密码</span>
						<input type="text" class="form-control" placeholder="密码" 
						value="${userInfo.userPwd }" name="user.userPwd">
					</div>
					<div class="input-group">
					
					<button type="submit"
							class="btn btn-success" style="margin-top: 25px;">保存修改</button>
				    </div>
				</form>
			</div>
			
		</div>
	</div>
	<!--  用户信息div结束 -->

</body>

</html>