package com.zxz.entity.condition;

import java.util.Date;

/**
 * @author zhaoxuezhong
 *
 */
public class ClaimVoucherStatisticsCondition {

	private long dept;
	private Integer year;
	private Integer month;
	private Integer startMonth;
	private Integer endMonth;
	private Integer startYear;
	private Integer endYear;
	private Integer pageIndex=1;
	private Integer pageSize=5;
	
	public ClaimVoucherStatisticsCondition(){
		super();
	}

	public ClaimVoucherStatisticsCondition(long dept, Integer year, Integer month, Integer startMonth, Integer endMonth,
			Integer startYear, Integer endYear, Integer pageIndex, Integer pageSize) {
		super();
		this.dept = dept;
		this.year = year;
		this.month = month;
		this.startMonth = startMonth;
		this.endMonth = endMonth;
		this.startYear = startYear;
		this.endYear = endYear;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

	public long getDept() {
		return dept;
	}

	public void setDept(long dept) {
		this.dept = dept;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(Integer startMonth) {
		this.startMonth = startMonth;
	}

	public Integer getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(Integer endMonth) {
		this.endMonth = endMonth;
	}

	public Integer getStartYear() {
		return startYear;
	}

	public void setStartYear(Integer startYear) {
		this.startYear = startYear;
	}

	public Integer getEndYear() {
		return endYear;
	}

	public void setEndYear(Integer endYear) {
		this.endYear = endYear;
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
