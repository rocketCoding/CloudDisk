<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>

	<head>
		<meta charset="utf-8">
		<title>文件上传</title>
		<link rel="stylesheet" type="text/css"   href="${pageContext.request.contextPath }/css/bootstrap.min.css">
		<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	</head>

	<body>
		<div>
				<jsp:include page="header.jsp"></jsp:include>
		</div>

		<!-- 上传文件div开始 -->
		<div class="tab-pane" id="upload_file" style="position: absolute;width: 700px;margin-left: 20px;">
		
			<div class="panel panel-default "  >
		        <div class="panel-heading ">
					<h2 class="panel-title ">
					设置文件信息
				     </h2>
				</div>
				
				<div style="padding: 50px 50px 10px;">
				    <form name="frm_test" action="${pageContext.request.contextPath }/netdisk/netdisk_fileUpload.action" method="post" enctype="multipart/form-data" style="margin: 8px">
				    <div class="input-group" style="margin-top: 10px;margin-bottom: 20px">
						<span class="input-group-addon">文件名称</span>
						<input type="text" class="form-control" placeholder="文件名称" 
						 name="userFile.fileName">
					</div>
					<div class="input-group" style="margin-bottom: 20px">
						<span class="input-group-addon">文件选择</span>
						<input type="file" class="form-control" name="userfile"> 
					</div>
					<div class="input-group" style="margin-bottom: 20px">
						<span class="input-group-addon">文件备注</span>
						<input type="text" class="form-control" placeholder="文件备注" name="userFile.fileRemarks"> 
					</div>
					
					<div class="input-group">
					    <button type="button" class="btn btn-danger" onclick="this.form.submit()">确认上传</button>
					</div>
				    </form>
				</div>
			
			</div>
		</div>

		<!-- 上传文件div结束  -->

	</body>

</html>