package com.cloudDisk.service.impl;

import java.io.Serializable;
import java.util.List;

import com.cloudDisk.dao.BaseDao;
import com.cloudDisk.page.PageResult;
import com.cloudDisk.service.BaseService;
import com.cloudDisk.utils.QueryHelper;

// Service 公共实现类
public class BaseServiceImpl<T> implements BaseService<T> {
	// service虽然抽取成一个基类 但是其中每个方法的具体实现都要由相应的dao来完成 ；
	// 为了达到抽象的效果我们必须在父类进行接收具体的dao类型

	BaseDao<T> baseDao;

	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	public void save(T entity) {
		baseDao.save(entity);
	}

	public void update(T entity) {
		baseDao.update(entity);
	}

	public void delete(Serializable id) {
		baseDao.delete(id);
	}

	public T findById(Serializable id) {
		return baseDao.findById(id);
	}

	public List<T> findObjects() {
		return baseDao.findObjects();
	}

	public List<T> findObjects(QueryHelper helper) {
		
		return baseDao.findObjects(helper);
	}

	public PageResult getPageResult(QueryHelper queryHelper, int currentPage,
			int pageSize) {
		return baseDao.getPageResult(queryHelper, currentPage, pageSize);
	}

}
