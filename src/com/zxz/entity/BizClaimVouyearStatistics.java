package com.zxz.entity;

import java.util.Date;

/**
 * BizClaimVouyearStatistics entity. @author MyEclipse Persistence Tools
 */

public class BizClaimVouyearStatistics implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 949804619667680807L;
	private long id;
	private SysDepartment sysDepartment;
	private double totalCount;
	private Integer year;
	private Date modifyTime;

	// Constructors

	/** default constructor */
	public BizClaimVouyearStatistics() {
	}

	public BizClaimVouyearStatistics(double totalCount, Integer year) {
		super();
		this.totalCount = totalCount;
		this.year = year;
	}

	/** full constructor */
	public BizClaimVouyearStatistics(SysDepartment sysDepartment, double totalCount, Integer year, Date modifyTime) {
		this.sysDepartment = sysDepartment;
		this.totalCount = totalCount;
		this.year = year;
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

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}