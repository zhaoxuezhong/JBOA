package com.zxz.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * SysDepartment entity. @author MyEclipse Persistence Tools
 */

public class SysDepartment implements java.io.Serializable {

	// Fields

	private long id;
	private String name;
	private Set bizClaimVouyearStatisticses = new HashSet(0);
	private Set sysEmployees = new HashSet(0);
	private Set bizClaimVoucherStatisticses = new HashSet(0);

	// Constructors

	/** default constructor */
	public SysDepartment() {
	}

	/** minimal constructor */
	public SysDepartment(String name) {
		this.name = name;
	}

	/** full constructor */
	public SysDepartment(String name, Set bizClaimVouyearStatisticses, Set sysEmployees,
			Set bizClaimVoucherStatisticses) {
		this.name = name;
		this.bizClaimVouyearStatisticses = bizClaimVouyearStatisticses;
		this.sysEmployees = sysEmployees;
		this.bizClaimVoucherStatisticses = bizClaimVoucherStatisticses;
	}

	// Property accessors

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getBizClaimVouyearStatisticses() {
		return this.bizClaimVouyearStatisticses;
	}

	public void setBizClaimVouyearStatisticses(Set bizClaimVouyearStatisticses) {
		this.bizClaimVouyearStatisticses = bizClaimVouyearStatisticses;
	}

	public Set getSysEmployees() {
		return this.sysEmployees;
	}

	public void setSysEmployees(Set sysEmployees) {
		this.sysEmployees = sysEmployees;
	}

	public Set getBizClaimVoucherStatisticses() {
		return this.bizClaimVoucherStatisticses;
	}

	public void setBizClaimVoucherStatisticses(Set bizClaimVoucherStatisticses) {
		this.bizClaimVoucherStatisticses = bizClaimVoucherStatisticses;
	}

}