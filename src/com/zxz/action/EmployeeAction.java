package com.zxz.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zxz.entity.SysDepartment;
import com.zxz.entity.SysEmployee;
import com.zxz.entity.SysPosition;
import com.zxz.service.EmployeeService;
import com.zxz.service.impl.EmployeeServiceImpl;

/**
 * @author zhaoxuezhong
 *
 */
public class EmployeeAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = -8594445737442553858L;
	private EmployeeService es=new EmployeeServiceImpl();
	private SysEmployee employee;
	private String random;
	
	public String login()throws Exception{
		if(!session.get("random").equals(random)){
			session.put("message", "验证码输入错误！");
			return LOGIN;
		}
		SysEmployee reEmp=es.login(employee);
		if(reEmp!=null){
			SysPosition position=new SysPosition();
			position.setId(reEmp.getSysPosition().getId());
			position.setNameCn(reEmp.getSysPosition().getNameCn());
			SysDepartment dept=new SysDepartment();
			dept.setId(reEmp.getSysDepartment().getId());
			dept.setName(reEmp.getSysDepartment().getName());
			reEmp.setSysDepartment(dept);
			reEmp.setSysPosition(position);
			session.put("employee", reEmp);
			return SUCCESS;			
		}
		return ERROR;
	}
	
	public String out()throws Exception{
		session.remove("employee");
		return SUCCESS;
	}
	
	private Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> arg0) {
		session=arg0;
	}
	public EmployeeService getEs() {
		return es;
	}
	public void setEs(EmployeeService es) {
		this.es = es;
	}
	public SysEmployee getEmployee() {
		return employee;
	}
	public void setEmployee(SysEmployee employee) {
		this.employee = employee;
	}
	public Map<String, Object> getSession() {
		return session;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}
}
