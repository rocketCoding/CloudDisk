package com.cloudDisk.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cloudDisk.entity.User;
import com.cloudDisk.service.UserService;
import com.cloudDisk.service.impl.UserServiceImpl;
import com.cloudDisk.utils.QueryHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	
	
	
	@Resource
	UserService userService; // 注解方式注入userService;
	
	/**
	 * 去往登录页面
	 */
	public String toLoginUI(){
		return "login";
	}
	
	
	// 去往用户信息更新界面
	public String toUpdateUserInfoUI(){
		
		// 重新更新一遍session中的内容
		return "userInfoUI";
	}
	
	// 将用户新修改的信息保存到数据库
  public String	updateUser(){
	  
	  if(user!=null){
		  System.out.println(user.getUserId()); // 为“”
		  System.out.println(user.getUserEmail());
		  System.out.println(user.getUserPwd());
		  System.out.println(user.getUserName()); // 为null
		  userService.update(user);
	  }
	  return "input"; // 地址栏问题 (需要让地址栏改变)
  }
	
	
	
	/**
	 * 登陆数据验证
	 * @return
	 */
	public String login(){ 
		 emailOrName=emailOrName.trim();
	    if(emailOrName!=null&&!emailOrName.equals("")){
	    	  QueryHelper helper=new QueryHelper(User.class, "u");
			  String reg = "\\w+[\\w]*@[\\w]+\\.[\\w]+$";
				// 第一步判断当前的字符串是email还是普通用户名
		      if(emailOrName.matches(reg)){
					// 使用邮箱名登陆
					helper.setWhereClause("userEmail=?", emailOrName);
				}else{
					// 使用用户名登陆
					helper.setWhereClause("userName=?", emailOrName);
				}
			
				// 第二步根据信息去数据库查找有关记录
		      if(user!=null&& !user.getUserPwd().trim().equals("")){
		    	      helper.setWhereClause("userPwd=?", user.getUserPwd().trim());
					  List<User> users=null;
					  users = userService.findObjects(helper);
					   if(users!=null&&users.size()>0){
							// 登录成功 去往网盘主界面 第三步 将用户信息加入到session中
							ActionContext.getContext().getSession().put("userInfo", users.get(0));
							return "success";
					   }
	            }
	    }
		return "input";
	}

	
	/**
	 * 退出登录 返回登录界面
	 * @return
	 */
	public String logOut(){
		// 清除session数据
		ActionContext.getContext().getSession().remove("userInfo");
		return "input";
	}
	
	
	
	/**
	 * 注册逻辑
	 * @return
	 */
	public String register(){
		if(user!=null){
				// 注册无误 往数据库中插入一条数据  注册的各项验证操作已经通过前台js代码完成
				userService.save(user);
		}
        // 返回登陆界面 使用新注册账号登陆
		return "input";
	}
   	
	
	/**
	 * 检测用户名是否重复
	 */
	public void verifyUserName(){	
		String result="false";
		// 验证新注册的账号是否重名
		if(user!=null&&user.getUserName()!=null){
			QueryHelper helper=new QueryHelper(User.class, "u");
			String name=user.getUserName();
			helper.setWhereClause("userName=?", name);
			
			if(userService==null){
				System.out.println("not null");
			}	
			List<User> users = userService.findObjects(helper);
		
			if(users==null||users.size()==0){
				// 用户名不重复
				result="true";
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html");
			try {
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(result.getBytes());
	            outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
  // 利用struts的参数自动封装来接收表单的参数
	private User user;
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
  
// 从前端获取到的用户名或者email字符串；
	String emailOrName;

	public String getEmailOrName() {
		return emailOrName;
	}
	public void setEmailOrName(String emailOrName) {
		this.emailOrName = emailOrName;
	}

}
