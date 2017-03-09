package com.cloudDisk.page;

import java.util.ArrayList;
import java.util.List;

// 存放分页结果的实体

public class PageResult {
	// 总数据个数
	private long totalCount;
	// 页号
	private int pageNo; 
	// 总页数
	private int totalPageCount; // 可以通过总记录数和页大小来计算得出
	// 每一页存放多少数据
	private int pageSize;
	// 分页数据
	private List items;

	// 计算总页数
	public PageResult(long totalCount, int pageNo, int pageSize, List items) {
		this.items = items == null ? new ArrayList() : items;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		if (totalCount != 0) {
			// 计算总页数
			int tem = (int) totalCount / pageSize;
			this.totalPageCount = (totalCount % pageSize == 0) ? tem: (tem + 1);
			this.pageNo = pageNo;
		} else {
			this.pageNo = 0;
		}
	}

	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items;
	}
	
	
	
}
