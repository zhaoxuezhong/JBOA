package com.zxz.entity.page;

import java.util.List;

/**
 * @author zhaoxuezhong
 *
 */
public class PageInfo<T> {

	private Integer pageIndex;
	private Integer pageSize;
	private Integer totalCount;
	private Integer pageTotalCount;
	private List<T> list;
	public PageInfo() {
		super();
	}
	public PageInfo(Integer pageIndex, Integer pgaeSize, Integer totalCount,List<T> list) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pgaeSize;
		this.totalCount = totalCount;
		this.list = list;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		if(pageIndex==null||pageIndex<1){
			pageIndex=1;
		}
		if(pageIndex>getPageTotalCount()){
			pageIndex=getPageTotalCount();
		}
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		if(pageSize==null||pageSize<1){
			pageIndex=1;
		}		
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		if(totalCount==null||totalCount<0){
			totalCount=0;
		}
		this.totalCount = totalCount;
	}
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Integer getPageTotalCount() {
		if(totalCount==0){
			return 0;
		}		
		return totalCount%pageSize==0?(totalCount/pageSize):(totalCount/pageSize+1);
	}
	public void setPageTotalCount(Integer pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}
}
