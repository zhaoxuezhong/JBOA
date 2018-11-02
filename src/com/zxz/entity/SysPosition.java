package com.zxz.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * SysPosition entity. @author MyEclipse Persistence Tools
 */

public class SysPosition implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -7404397201528911226L;
	private long id;
	private String nameCn;
	private String nameEn;
	private Set sysEmployees = new HashSet(0);

	// Constructors

	/** default constructor */
	public SysPosition() {
	}

	public SysPosition(long id) {
		super();
		this.id = id;
	}

	/** minimal constructor */
	public SysPosition(String nameCn, String nameEn) {
		this.nameCn = nameCn;
		this.nameEn = nameEn;
	}

	/** full constructor */
	public SysPosition(String nameCn, String nameEn, Set sysEmployees) {
		this.nameCn = nameCn;
		this.nameEn = nameEn;
		this.sysEmployees = sysEmployees;
	}

	// Property accessors

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNameCn() {
		return this.nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getNameEn() {
		return this.nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public Set getSysEmployees() {
		return this.sysEmployees;
	}

	public void setSysEmployees(Set sysEmployees) {
		this.sysEmployees = sysEmployees;
	}

}