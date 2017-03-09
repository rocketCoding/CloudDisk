package com.cloudDisk.utils;

import java.util.ArrayList;
import java.util.List;

// 查询工具类
// 作用：返回已经填充完毕的带占位符的查询语句以及查询参数
//      封装查询语句，和查询条件；方便dao层进行操作
public class QueryHelper {

	private String whereClause="";
	private String fromClause="";
	private String orderByClause ="";
	
	//这个集合用于存放查询参数 并暴露接口给外界取用；
    private List<Object> parameter;
    
    //定义两个常量指定排序顺序	    
    public static String ORDER_BY_DESC="DESC"; 
    public static String ORDER_BY_ASC="ASC"; 
    
    // 一个查询一定会有一条 from 子句
    /**
     * 
     * @param clazz 要查询的目标对象
     * @param alias 查询别名
     */
    public QueryHelper(Class clazz,String alias){
    	
    	fromClause="FROM "+clazz.getSimpleName()+" "+alias;
    	
    }
    
    
    /**
     * 设置where子句
     * @param condition 查询条件； 举例：name like ? 
     * @param params 查询参数；举例："王%"
     */
    public void setWhereClause(String condition,Object... params) {
    	if(whereClause.length()>1){
    		whereClause+=" "+"AND"+" "+condition;
    	}else{
    		whereClause+="WHERE"+" "+condition;
    	}
    	
    	if(params!=null&&params.length>0){
    		if(parameter==null){
	             //	参数集合为空的时候再new			
					parameter=new ArrayList<Object>();
				}
			for(Object obj:params){
				parameter.add(obj);
			}
    	}
		
	}


 /**
  * 设置查询排序
  * @param orderBy 查询顺序：可选已经定义好的常量值；
  * @param columnIndex 要根据排序的字段
  */
	public void setOrderByClause(String orderBy,String columnIndex) {
		
		if(orderByClause.length()>1){
				orderByClause+=", "+columnIndex+" "+orderBy;
		}else{
		    // 第一次追加
			orderByClause+="ORDER BY"+" "+columnIndex+" "+orderBy;
		}
		
	}

	
	/**
	 * 为用户提供已经组织好的Hql查询语句
	 * @return
	 */
    public String getHql(){
    	return fromClause+"  "+whereClause+" "+orderByClause;
    }
    
    
    /**
     * @return 组装完毕的查询数量语句
     */
    public String getCountHql(){
    	return "SELECT COUNT(*) "+fromClause+"  "+whereClause;
    }
	
	 /**
	  * 为用户封装了查询参数
	  * @return 查询参数集
	  */
    public List<Object> getParameter(){
    	return parameter;
    }

    
}
