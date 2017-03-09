package com.cloudDisk.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cloudDisk.dao.BaseDao;
import com.cloudDisk.page.PageResult;
import com.cloudDisk.utils.QueryHelper;

//   HibernateDaoSupport 中依赖sessionFactory属性 需要在spring配置文件中进行依赖注入
public class BaseDaoImpl<T> extends HibernateDaoSupport  implements BaseDao<T> {
	//------------------ 定义clazz对象是用来封装查询结果的------------------------
	Class<T> clazz; 
	public BaseDaoImpl(){
		//获取类型参数 
		 ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
		 Type[] genericType = type.getActualTypeArguments();
		 clazz=(Class)genericType[0];
	}

	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	public void delete(Serializable id) {
		getHibernateTemplate().delete(findById(id));
		
	}

	public T findById(Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}

	public List<T> findObjects() {
		Session session=getSession();
		Query query = session.createQuery("FROM"+" "+clazz.getSimpleName());
		// 事实上下面的这个语句不会返回null 就算查找不到元素也会返回一个长度为0 的list集合
		return query.list();
	}

	public List<T> findObjects(QueryHelper helper) {
				
		Session session=getSession();
		if(session==null){
			System.out.println("session为空");
		}
		Query query = session.createQuery(helper.getHql());
		List<Object> parameter = helper.getParameter();
		if(parameter!=null){
			for(int i=0;i<parameter.size();i++){
				query.setParameter(i, parameter.get(i));
			}
		}
		return query.list();
	}

	
	/**
	 * 分页查询
	 * currentPage当前页号
	 * pageSize 页大小
	 * @return 分页bean
	 */
	public PageResult getPageResult(QueryHelper queryHelper, int currentPage,
			int pageSize) {

		
		Query query = getSession().createQuery(queryHelper.getHql());
		List<Object> parameter = queryHelper.getParameter();
		
		if(parameter!=null){
			for(int i=0;i<parameter.size();i++){
				query.setParameter(i, parameter.get(i));
			}
		}
		if(currentPage<1){
			// 如果当前页小于1 进行越界处理
			currentPage=1;
		}
		query.setFirstResult((currentPage-1)*pageSize);
		query.setMaxResults(pageSize);
		
		//获取总记录数
		Query queryCount = getSession().createQuery(queryHelper.getCountHql());
		if(parameter!=null){
			for(int i=0;i<parameter.size();i++){
				queryCount.setParameter(i, parameter.get(i));
			}
		}
		long totalCount=(Long) queryCount.uniqueResult();
		List items=query.list();
		return new PageResult(totalCount, currentPage, pageSize, items);
	}

}
