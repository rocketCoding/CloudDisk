package com.cloudDisk.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.cloudDisk.dao.UserDao;
import com.cloudDisk.entity.User;
import com.cloudDisk.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

	// 注入DAO； 这个DAO已经在spring文件中进行了配置 需要添加set方法完成依赖注入
	 // 根据名称查找
	private UserDao userDao;
	// 这里还需要加上@Resource注解
	@Resource
	public void setUserDao(UserDao userDao) {
		// 先给父类一个具体的dao 让父类的方法能够执行成功	
		super.setBaseDao(userDao);
		this.userDao = userDao;
	}
	
}
