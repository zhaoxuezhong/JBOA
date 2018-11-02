package com.zxz.entity;

import java.util.Date;

/**
 * BizLeave entity. @author MyEclipse Persistence Tools
 */

public class BizLeave implements java.io.Serializable {

	// Fields

	private long id;
	private SysEmployee sysEmployeeByNextDealSn;
	private SysEmployee sysEmployeeByEmployeeSn;
	private Date starttime;
	private Date endtime;
	private double leaveday;
	private String reason;
	private String status;
	private String leavetype;
	private String approveOpinion;
	private Date createtime;
	private Date modifytime;

	// Constructors

	/** default constructor */
	public BizLeave() {
	}

	/** minimal constructor */
	public BizLeave(SysEmployee sysEmployeeByEmployeeSn, Date starttime, Date endtime, double leaveday, String reason) {
		this.sysEmployeeByEmployeeSn = sysEmployeeByEmployeeSn;
		this.starttime = starttime;
		this.endtime = endtime;
		this.leaveday = leaveday;
		this.reason = reason;
	}

	/** full constructor */
	public BizLeave(SysEmployee sysEmployeeByNextDealSn, SysEmployee sysEmployeeByEmployeeSn, Date starttime,
			Date endtime, double leaveday, String reason, String status, String leavetype, String approveOpinion,
			Date createtime, Date modifytime) {
		this.sysEmployeeByNextDealSn = sysEmployeeByNextDealSn;
		this.sysEmployeeByEmployeeSn = sysEmployeeByEmployeeSn;
		this.starttime = starttime;
		this.endtime = endtime;
		this.leaveday = leaveday;
		this.reason = reason;
		this.status = status;
		this.leavetype = leavetype;
		this.approveOpinion = approveOpinion;
		this.createtime = createtime;
		this.modifytime = modifytime;
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

	public SysEmployee getSysEmployeeByEmployeeSn() {
		return this.sysEmployeeByEmployeeSn;
	}

	public void setSysEmployeeByEmployeeSn(SysEmployee sysEmployeeByEmployeeSn) {
		this.sysEmployeeByEmployeeSn = sysEmployeeByEmployeeSn;
	}

	public Date getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public double getLeaveday() {
		return this.leaveday;
	}

	public void setLeaveday(double leaveday) {
		this.leaveday = leaveday;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLeavetype() {
		return this.leavetype;
	}

	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}

	public String getApproveOpinion() {
		return this.approveOpinion;
	}

	public void setApproveOpinion(String approveOpinion) {
		this.approveOpinion = approveOpinion;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getModifytime() {
		return this.modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

}