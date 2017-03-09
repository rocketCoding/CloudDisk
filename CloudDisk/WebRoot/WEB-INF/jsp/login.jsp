<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>

	<head>
		<meta charset="utf-8">
		<title>登录</title>
		<link rel="stylesheet" type="text/css"   href="${pageContext.request.contextPath }/css/bootstrap.min.css">
		<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<script type="text/javascript">
		        var flag1=false; // 全局变量，记录用户名效验结果
		        var flag2=false; // 全局变量，记录email效验结果
		        var flag3=false; // 全局变量，记录密码效验结果
		        var flag4=false; // 全局变量，记录确认密码效验结果
		         /* AJAX 注册表单判断 */
		        //校验总函数 传入元素id
		    	function doVerify(index){
		    	
		    	    if(index=="all"){
		    	         // 检查当前能否提交表单
		    	         var flag=flag1 && flag2 && flag3 && flag4;
		    	         if(flag==true){
 			    	         document.forms[1].submit();
		    	         }else{
		    	            alert("注册信息不完整！");
		    	         }
		    	    }
		    	
		    		//1、获取需要效验的字段的值
		    		var content = $("#"+index).val();
		    		// 去除两端空格
		    		content=content.trim();
		    		if(content != ""){
			    		switch (index) {
						case "userName":
							// 执行用户名效验
						    verifyUserName(content);
							break;
	                    case "userEmail":
	                        // 用户邮箱效验
	                        flag2=verifyEmail(content);
	                        break;
	                    case "userPwd":
	                        // 用户密码效验
	                        flag3=verifyPwd(content);
	                        break;
	                    case "confirmPwd":
	                        // 确认密码效验
	                        flag4=verifyPwdConfirm(content);
	                        break;      
						}
		    		
		    		}
		    	}
		    
		    	// 效验用户名 （判空  重名判断AJAX）
		    	function verifyUserName(content){
		    	    $("font:eq(0)").empty();
		    	    if(content!=null&&content!=""){
		    	        	$.ajax({
			    				url:"${pageContext.request.contextPath }/user/user_verifyUserName.action",
			    				data: {"user.userName": content},
			    				type: "post",
			    				async: false,//非异步
			    				success: function(msg){
			    					if(msg != "true"){
			    						$("#userName").focus();
			    						$("font:eq(0)").prepend("用户名已存在");
			    					    flag1=false;
			    					} else {
			    						// 验证通过
			    						flag1=true;
			    					}
			    				}
			    			});
		    	    }
		    	    
		    	}
		     	// 效验邮箱(判空  邮箱格式是否合法)
		    	function verifyEmail(content){
		    	     // 首先要清空上次的校验信息！
		    	     $("font:eq(1)").empty();
			    	if(content!=null&&content!=""){
			    	   var emailPat=/^(.+)@(.+)$/;
	                   var matchArray=content.match(emailPat);
	                   if(matchArray==null){
	                      $("font:eq(1)").prepend("邮箱格式错误");
	                      return false;
	                   }else{
	                      return true;
	                   }
			    	}else{
			    	   $("font:eq(1)").prepend("邮箱格式错误");
			    	    return false;
			    	}
		    	}
		    	
		    	
		    	// 效验密码(判空)
		    	function verifyPwd(content){
		    	   $("font:eq(2)").empty();
		    	  if(content!=null&&content!=""){
		    	     return true;
		    	  }else{
		    	     $("font:eq(2)").prepend("密码不能为空");
  		    	     return false;
		    	  }
		    	}
		    	
		    	
		    	// 效验确认密码（判断是否和上次的密码一致）
		    	function verifyPwdConfirm(content){
		    	  $("font:eq(3)").empty();
		    	   if(content!=null&&content!=""){
		    	     var password = $("#userPwd").val();
		    	     if(password==content){
 			    	     return true;
		    	     }else{
		    	        $("font:eq(3)").prepend("两次输入的密码不一致");
						return false;		    	     
		    	     }
		    	  }else{
		    	     $("font:eq(3)").prepend("两次输入的密码不一致");
  		    	     return false;
		    	  }
		    	}
		      
		    
		   
		</script>
	</head>

	<body>
		<div class="container" style="padding-top: 120px;" align="center">
			<div class="jumbotron" style="background-color:#FFFFFF;">
				<h1 style="color: #1C86EE;">CLOUD-DISK</h1>
				<p>云存储，更放心！ </p>
				<div>
					<ul id="myTab" class="nav nav-tabs" style="width: 443px;">
						<li class="active" style="width: 220px;">
							<a href="#home" data-toggle="tab">
								登录
							</a>
						</li>
						<li>
							<a href="#register" data-toggle="tab" style="width: 220px;">注册</a>
						</li>

					</ul>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane fade in active" id="home">
                            <!------- 登陆表单开始 ------------>
							<div class="login_form">

								<form class="form-horizontal"  style="width: 460px; padding-top: 20px"
								      action="${pageContext.request.contextPath }/user/user_login.action" method="post">
								

									<div class="form-group">
										<div class="col-md-5" style="width: 450px;margin-left: 14px;">
											<input type="text" class="form-control" placeholder="用户名/email" name="emailOrName" />
										</div>
									</div>

									<div class="form-group">
										<div class="col-md-5" style="width: 450px;margin-left: 14px;">
											<input type="password" class="form-control" placeholder="密码" name="user.userPwd" />
										</div>
									</div>

									<div class="form-group" style="margin: 0 auto;">
										<div class="col-md-5" style="width: 450px;">
											<button type="button" class="btn btn-primary btn-lg " style="width: 200px;" onclick="this.form.submit()">登录</button>
										</div>
									</div>

								</form>
							</div>
							 <!------- 登陆表单结束 ------------>
						</div>
						<div class="tab-pane fade" id="register">
						     <!------- 注册表单开始 ------------>
							<div class="register_form">

								<form class="form-horizontal" style="width: 460px; padding-top: 20px;"
								      action="${pageContext.request.contextPath }/user/user_register.action" method="post">
								

									<div class="form-group">
										<div class="col-md-5" style="width: 450px;margin-left: 14px;">
											<input type="text" class="form-control" placeholder="用户名" name="user.userName"  id="userName" onchange="doVerify('userName')" />
										</div>
										<!-- 用于显示ajax信息  -->
										<font color="red" style="float: right;" name="info_userName">  </font>
									</div>

									<div class="form-group">
										<div class="col-md-5" style="width: 450px;margin-left: 14px;">
											<input type="text" class="form-control" placeholder="邮箱" name="user.userEmail"  id="userEmail" onchange="doVerify('userEmail')" />
										</div>
										<!-- 用于显示ajax信息  -->
										<font color="red" style="float: right;">  </font>
									</div>

									<div class="form-group">
										<div class="col-md-5" style="width: 450px;margin-left: 14px;">
											<input type="password" class="form-control" placeholder="密码" name="user.userPwd"  id="userPwd" onchange="doVerify('userPwd')"  />
										</div>
										<!-- 用于显示ajax信息  -->
										<font color="red" style="float: right;">  </font>
									</div>
									<div class="form-group">
										<div class="col-md-5" style="width: 450px;margin-left: 14px;">
											<input type="password" class="form-control" placeholder="确认密码" id="confirmPwd" onchange="doVerify('confirmPwd')" />
										</div>
										<!-- 用于显示ajax信息  -->
										<font color="red" style="float: right;">  </font>
									</div>
									<div class="form-group" style="margin: 0 auto;">
										<div class="col-md-5" style="width: 450px;">
											<button type="button" class="btn btn-success" style="width: 200px;" onclick="doVerify('all')">立即注册</button>
										</div>
									</div>
								</form>
							</div>
							  <!------- 注册表单结束 ------------>
						</div>
					</div>
				</div>

			</div>

			<!--底部-->

		</div>

	</body>

</html>