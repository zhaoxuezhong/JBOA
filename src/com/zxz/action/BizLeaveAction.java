package com.zxz.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zxz.entity.BizLeave;
import com.zxz.entity.SysEmployee;
import com.zxz.entity.condition.Condition;
import com.zxz.entity.page.PageInfo;
import com.zxz.service.BizLeaveService;
import com.zxz.service.EmployeeService;
import com.zxz.service.impl.BizLeaveServiceImpl;
import com.zxz.service.impl.EmployeeServiceImpl;

/**
 * @author zhaoxuezhong
 *
 */
public class BizLeaveAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = -269355471304584902L;
	private BizLeaveService bls=new BizLeaveServiceImpl();
	private EmployeeService es = new EmployeeServiceImpl();
	private Condition condition=new Condition();
	private BizLeave leave=new BizLeave();
	private String type;
	private Map<String,String> leaveTypeMap=new HashMap<String, String>();
	
	public String searchBizLeave()throws Exception{
		PageInfo<BizLeave> pageInfo=bls.findBizLeave(condition, condition.getPageIndex(), condition.getPageSize());
		session.put("leavePageInfo", pageInfo);
		return SUCCESS;
	}
	
	public String getBizLeaveById()throws Exception{
		try {
			leave=bls.findBizLeave(leave.getId());
			if (type == null || type.equals("") || type.equals("show")) {
				return "showDetail";
			} else if (type.equals("check")) {
				return "check";
			}
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String saveBizLeave() throws Exception{
		leave.setCreatetime(new Date());
		leave.setStatus("待审批");
		long result=bls.save(leave);
		System.out.println(result);
		return SUCCESS;
	}
	
	public String checkLeave() throws Exception{
		BizLeave bl=bls.findBizLeave(leave.getId());
		bl.setStatus(leave.getStatus());
		bl.setModifytime(new Date());
		bl.setApproveOpinion(leave.getApproveOpinion());
		return SUCCESS;
	}
	
	public String toSaveBizLeave() throws Exception{
		SysEmployee employee = (SysEmployee) session.get("employee");
		SysEmployee manager = es.findSysEmployee(employee.getSysDepartment().getId(), 2L).get(0);
		session.put("manager", manager);
		leaveTypeMap.put("事假", "事假");
		leaveTypeMap.put("年假", "年假");
		leaveTypeMap.put("婚假", "婚假");
		leaveTypeMap.put("病假", "病假");		
		return SUCCESS;
	}
	
	private Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> arg0) {
		session=arg0;
	}
	public BizLeaveService getBls() {
		return bls;
	}
	public void setBls(BizLeaveService bls) {
		this.bls = bls;
	}
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	public Map<String, Object> getSession() {
		return session;
	}

	public BizLeave getLeave() {
		return leave;
	}

	public void setLeave(BizLeave leave) {
		this.leave = leave;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String,String> getLeaveTypeMap() {
		return leaveTypeMap;
	}

	public void setLeaveTypeMap(Map<String,String> leaveTypeMap) {
		this.leaveTypeMap = leaveTypeMap;
	}

	public EmployeeService getEs() {
		return es;
	}

	public void setEs(EmployeeService es) {
		this.es = es;
	}
	
}
