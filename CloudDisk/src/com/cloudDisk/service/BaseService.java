package com.cloudDisk.service;

import java.io.Serializable;
import java.util.List;

import com.cloudDisk.page.PageResult;
import com.cloudDisk.utils.QueryHelper;

public interface BaseService<T> {
	        //保存
			public void save(T entity);
			//更新
			public void update(T entity);
			//删除
			public void delete(Serializable id);
			//根据id查找 使用Serializable 接口是为了通用性。因为id可能有不同种类型
			public T findById(Serializable id);
			//查找全部
			public List<T> findObjects();
			//条件查找
			public List<T> findObjects(QueryHelper helper);
			//分页条件查找
			public PageResult getPageResult(QueryHelper queryHelper, int currentPage,
					int pageSize);
}
