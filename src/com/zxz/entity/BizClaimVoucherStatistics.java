package com.zxz.entity;

import java.util.Date;

/**
 * BizClaimVoucherStatistics entity. @author MyEclipse Persistence Tools
 */

public class BizClaimVoucherStatistics implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 6226584235240608559L;
	private long id;
	private SysDepartment sysDepartment;
	private double totalCount;
	private Integer year;
	private Integer month;
	private Date modifyTime;

	// Constructors

	/** default constructor */
	public BizClaimVoucherStatistics() {
	}

	public BizClaimVoucherStatistics(double totalCount, Integer year, Integer month) {
		super();
		this.totalCount = totalCount;
		this.year = year;
		this.month = month;
	}

	/** full constructor */
	public BizClaimVoucherStatistics(SysDepartment sysDepartment, double totalCount, Integer year, Integer month,
			Date modifyTime) {
		this.sysDepartment = sysDepartment;
		this.totalCount = totalCount;
		this.year = year;
		this.month = month;
		this.modifyTime = modifyTime;
	}

	// Property accessors

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SysDepartment getSysDepartment() {
		return this.sysDepartment;
	}

	public void setSysDepartment(SysDepartment sysDepartment) {
		this.sysDepartment = sysDepartment;
	}

	public double getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(double totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}