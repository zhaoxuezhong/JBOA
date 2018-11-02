package com.zxz.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BizClaimVoucher entity. @author MyEclipse Persistence Tools
 */

public class BizClaimVoucher implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -2395265547935492285L;
	private long id;
	private SysEmployee sysEmployeeByNextDealSn;
	private SysEmployee sysEmployeeByCreateSn;
	private Date createTime;
	private String event;
	private double totalAccount;
	private String status;
	private Date modifyTime;
	private Set bizCheckResults = new HashSet(0);
	private Set bizClaimVoucherDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public BizClaimVoucher() {
	}

	/** minimal constructor */
	public BizClaimVoucher(SysEmployee sysEmployeeByCreateSn, Date createTime, String event, double totalAccount,
			String status) {
		this.sysEmployeeByCreateSn = sysEmployeeByCreateSn;
		this.createTime = createTime;
		this.event = event;
		this.totalAccount = totalAccount;
		this.status = status;
	}

	/** full constructor */
	public BizClaimVoucher(SysEmployee sysEmployeeByNextDealSn, SysEmployee sysEmployeeByCreateSn, Date createTime,
			String event, double totalAccount, String status, Date modifyTime, Set bizCheckResults,
			Set bizClaimVoucherDetails) {
		this.sysEmployeeByNextDealSn = sysEmployeeByNextDealSn;
		this.sysEmployeeByCreateSn = sysEmployeeByCreateSn;
		this.createTime = createTime;
		this.event = event;
		this.totalAccount = totalAccount;
		this.status = status;
		this.modifyTime = modifyTime;
		this.bizCheckResults = bizCheckResults;
		this.bizClaimVoucherDetails = bizClaimVoucherDetails;
	}

	// Property accessors

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SysEmployee getSysEmployeeByNextDealSn() {
		return this.sysEmployeeByNextDealSn;
	}

	public void setSysEmployeeByNextDealSn(SysEmployee sysEmployeeByNextDealSn) {
		this.sysEmployeeByNextDealSn = sysEmployeeByNextDealSn;
	}

	public SysEmployee getSysEmployeeByCreateSn() {
		return this.sysEmployeeByCreateSn;
	}

	public void setSysEmployeeByCreateSn(SysEmployee sysEmployeeByCreateSn) {
		this.sysEmployeeByCreateSn = sysEmployeeByCreateSn;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public double getTotalAccount() {
		return this.totalAccount;
	}

	public void setTotalAccount(double totalAccount) {
		this.totalAccount = totalAccount;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Set getBizCheckResults() {
		return this.bizCheckResults;
	}

	public void setBizCheckResults(Set bizCheckResults) {
		this.bizCheckResults = bizCheckResults;
	}

	public Set getBizClaimVoucherDetails() {
		return this.bizClaimVoucherDetails;
	}

	public void setBizClaimVoucherDetails(Set bizClaimVoucherDetails) {
		this.bizClaimVoucherDetails = bizClaimVoucherDetails;
	}

}