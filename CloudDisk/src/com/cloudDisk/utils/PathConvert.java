package com.cloudDisk.utils;

import org.apache.struts2.ServletActionContext;

public  class PathConvert {
	/**
	 * 绝对路径转为相对路径
	 * @param absolutePath
	 * @return
	 * 
	 */
  public static String convertpath(String absolutePath){
	  String fileName=absolutePath.substring(absolutePath.lastIndexOf("\\"));// 得到“/文件名” \lession4.pptx

	  absolutePath=absolutePath.substring(0,absolutePath.lastIndexOf("\\"));

	  String user=absolutePath.substring(absolutePath.lastIndexOf("\\")); // 得到“/用户名” \zhangjian

	  return "\\upload"+user+fileName ;
  }


}
