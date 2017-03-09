package com.cloudDisk.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



// 本过滤器的目的是为了防止用户绕过登录页面直接访问网盘主页面
public class LoginFilter implements Filter {

	public void destroy() {
		
		
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		// 拿到当前请求的地址
		String uri = request.getRequestURI();
		if(uri.contains("user/user_toLoginUI")){
			chain.doFilter(request, response);
		} else if(!uri.contains("user/user_login")){
			// 如果不是登录的请求需要判断当前的session中是否存在内容
			if(request.getSession().getAttribute("userInfo")!=null){
				// session 回话中存在内容 已经登录
				chain.doFilter(request, response);
			}else{
				//没有登录，跳转到登录页面
				response.sendRedirect(request.getContextPath() + "/user/user_toLoginUI.action");
			}
			
		}else{
			//当前正在执行登录操作 放行
			chain.doFilter(request, response);
		}
		
	}

	public void init(FilterConfig config) throws ServletException {
	}

	

}
