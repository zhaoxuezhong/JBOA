package com.zxz.entity.condition;

import java.util.Date;

/**
 * @author zhaoxuezhong
 *
 */
public class Condition {

	private String createSn;
	private String nextDealSn;
	private String status;
	private Date startTime;
	private Date endTime;
	private Integer pageIndex=1;
	private Integer pageSize=5;
	
	public Condition(){
		super();
	}
	public Condition(String createSn, String nextDealSn, String status, Date startTime,
			Date endTime, Integer pageIndex, Integer pageSize) {
		super();
		this.createSn = createSn;
		this.nextDealSn = nextDealSn;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}
	public String getCreateSn() {
		return createSn;
	}

	public void setCreateSn(String createSn) {
		this.createSn = createSn;
	}

	public String getNextDealSn() {
		return nextDealSn;
	}

	public void setNextDealSn(String nextDealSn) {
		this.nextDealSn = nextDealSn;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
