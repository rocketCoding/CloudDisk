<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div>
	<nav class="navbar navbar-inverse " role=" navigation ">
		<div class="container-fluid ">
			<div class="navbar-header ">
				<a class="navbar-brand " href="# "> <font color="#ffffff">网盘
				</font>
				</a>
			</div>
			<div>
				<ul class="nav navbar-nav ">
					<li id="main"><a href="#" onclick="jumpPage('main')">内容清单</a>
					</li>
					<li id="file_upload"><a href="#"
						onclick="jumpPage('file_upload')">上传文件</a></li>

					<li id="user_info"><a href="#" onclick="jumpPage('user_info')">用户中心</a>
					</li>

				</ul>
			</div>
			<div>
				<form class="navbar-form navbar-left " role="search ">
					<div class="form-group ">
						<input type="text" class="form-control " name="userFile.fileName"
							placeholder="Search ">
					</div>
					<button type="button" class="btn btn-default " onclick="doSearch()">搜索</button>
				</form>
				<div class="navbar-btn navbar-right">
					<img src="${pageContext.request.contextPath }/img/user.png" /> <font
						style="color: #ffffff; margin-right: 13px;">
						${userInfo.userName } </font> <img
						src="${pageContext.request.contextPath }/img/out.png" /> <a
						href="${pageContext.request.contextPath}/user/user_logOut.action"
						style="color: #ffffff; margin-right: 20px;">注销 </a>
				</div>
			</div>
		</div>
	</nav>

</div>
<script type="text/javascript">
	/*  在整个文档加载完毕之后就调用下面的这个函数，该函数根据url 来判断当前让那个li标签处于点击状态 */
/* 	$(document).ready(function() {
		// 先让所有的标签全部处于未点击的状态【这种方式只在jsp页面使用动态包含的时候有效果 】
		$("ul li").removeAttr("class");
		var test = window.location.href;
		if (test.indexOf("toUpdateUserInfoUI") > 0) {
			$("#user_info").attr("class", "active");
		} else if (test.indexOf("netdisk_list") > 0) {
			$("#main").attr("class", "active");
		} else if (test.indexOf("toFileUploadUI") > 0) {
			$("#file_upload").attr("class", "active");
		}

	});
 */
	window.onload = function() {
		$("ul li").removeAttr("class");
		var test = window.location.href;
		if (test.indexOf("toUpdateUserInfoUI") > 0) {
			$("#user_info").attr("class", "active");
		} else if (test.indexOf("netdisk_list") > 0) {
			$("#main").attr("class", "active");
		} else if (test.indexOf("toFileUploadUI") > 0) {
			$("#file_upload").attr("class", "active");
		}
	}

	// 控制导航栏各个标签的链接
	function jumpPage(target) {
		switch (target) {
		case 'main':
			window.location.href = "${pageContext.request.contextPath}/netdisk/netdisk_list.action";
			break;
		case 'file_upload':
			window.location.href = "${pageContext.request.contextPath}/netdisk/netdisk_toFileUploadUI.action";
			break;
		case 'user_info':
			window.location.href = "${pageContext.request.contextPath}/user/user_toUpdateUserInfoUI.action";
			break;
		default:
			break;
		}
	}

	// 执行搜索
	function doSearch() {
		var list_url = "${pageContext.request.contextPath }/netdisk/netdisk_list.action?pageNo=1";
		//重置页号
		$("#pageNo").val(1);
		document.forms[0].action = list_url;
		document.forms[0].submit();
	}
</script>


