package com.cloudDisk.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloudDisk.dao.FileDao;
import com.cloudDisk.dao.UserDao;
import com.cloudDisk.entity.User;
import com.cloudDisk.entity.Userfile;
import com.cloudDisk.service.FileService;
import com.cloudDisk.service.UserService;

@Service("fileService")
public class FileServiceImpl extends BaseServiceImpl<Userfile> implements FileService{

	// 注入DAO； 这个DAO已经在spring文件中进行了配置 需要添加set方法完成依赖注入
	 // 根据名称查找
	private FileDao fileDao;
	@Resource
	public void setFileDao(FileDao fileDao) {
		super.setBaseDao(fileDao);
		this.fileDao = fileDao;
	}

}
