package com.zxz.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zxz.entity.BizCheckResult;
import com.zxz.entity.BizClaimVoucher;
import com.zxz.entity.BizClaimVoucherDetail;
import com.zxz.entity.SysEmployee;
import com.zxz.entity.condition.Condition;
import com.zxz.entity.page.PageInfo;
import com.zxz.service.BizClaimVoucherService;
import com.zxz.service.EmployeeService;
import com.zxz.service.impl.BizClaimVoucherServiceImpl;
import com.zxz.service.impl.EmployeeServiceImpl;

/**
 * @author zhaoxuezhong
 *
 */
public class BizClaimVoucherAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -410579421489724774L;
	private BizClaimVoucherService bcvs = new BizClaimVoucherServiceImpl();
	private EmployeeService es = new EmployeeServiceImpl();
	private BizClaimVoucher claimVoucher = new BizClaimVoucher();
	private BizClaimVoucherDetail claimVoucherDetail = new BizClaimVoucherDetail();
	private List<BizClaimVoucherDetail> detailList = new ArrayList<BizClaimVoucherDetail>();
	private Condition condition = new Condition();
	private String type;
	private BizCheckResult checkResult;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String saveBizClaimVoucher() throws Exception {
		claimVoucher.setCreateTime(new Date());
		SysEmployee employee = (SysEmployee) session.get("employee");
		if (claimVoucher.getStatus().equals("新创建")) {
			claimVoucher.setSysEmployeeByNextDealSn(employee);
		} else {
			SysEmployee manage = es
					.findSysEmployee(employee.getSysDepartment().getId(), 2L).get(0);
			claimVoucher.setSysEmployeeByNextDealSn(manage);
		}
		claimVoucher.setSysEmployeeByCreateSn(employee);
		claimVoucher.setTotalAccount(claimVoucher.getTotalAccount() + claimVoucherDetail.getAccount());
		detailList.add(claimVoucherDetail);
		for (BizClaimVoucherDetail bcvd : detailList) {
			bcvd.setBizClaimVoucher(claimVoucher);
		}
		claimVoucher.setBizClaimVoucherDetails(new HashSet(detailList));
		Long result = bcvs.saveClaimVoucher(claimVoucher);
		System.out.println(result);
		return SUCCESS;
	}

	public String searchBizClaimVoucher() throws Exception {
		SysEmployee employee = (SysEmployee) session.get("employee");
		if (employee.getSysPosition().getId() == 1) {
			condition.setCreateSn(employee.getSn());
		} else {
			condition.setNextDealSn(employee.getSn());
		}
		PageInfo<BizClaimVoucher> pageInfo = bcvs.findClaimVoucher(condition, condition.getPageIndex(),
				condition.getPageSize());
		session.put("pageInfo", pageInfo);
		return SUCCESS;
	}

	public String searchBizClaimVoucherById() {
		try {
			claimVoucher = bcvs.findClaimVoucher(claimVoucher.getId());
			if (type == null || type.equals("") || type.equals("show")) {
				return "showDetail";
			} else if (type.equals("check")) {
				return "check";
			}
			return "update";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String updateBizClaimVoucher() throws Exception {
		try {
			BizClaimVoucher bcv = bcvs.findClaimVoucher(claimVoucher.getId());
			bcv.setModifyTime(new Date());
			bcv.setStatus(claimVoucher.getStatus());
			SysEmployee employee = (SysEmployee) session.get("employee");
			if(claimVoucher.getStatus().equals("已提交")){
				List<SysEmployee> manages = es.findSysEmployee(employee.getSysDepartment().getId(), 2L);
				if(manages!=null&&manages.size()>0){
					bcv.setSysEmployeeByNextDealSn(manages.get(0));
				}
				else{
					return ERROR;
				}
			}
			if(claimVoucherDetail.getAccount()!=0){
				bcv.setTotalAccount(claimVoucher.getTotalAccount() + claimVoucherDetail.getAccount());
				detailList.add(claimVoucherDetail);
			}
			for (BizClaimVoucherDetail bcvd : detailList) {
				bcvd.setBizClaimVoucher(bcv);
			}
			bcv.setBizClaimVoucherDetails(new HashSet(detailList));
			bcvs.updateClaimVoucher(bcv);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String deleteBizClaimVoucher() throws Exception {
		try {
			bcvs.deleteClaimVoucher(bcvs.findClaimVoucher(claimVoucher.getId()));
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String saveBizCheckResult() throws Exception {
		try {
			BizClaimVoucher bcv = bcvs.findClaimVoucher(checkResult.getBizClaimVoucher().getId());
			String status = "已提交";
			SysEmployee nextEmp = null;
			SysEmployee currEmployee = (SysEmployee) session.get("employee");
			long position = currEmployee.getSysPosition().getId();
			if(position==2||position==3){
				if (checkResult == null || checkResult.getResult().equals("打回")) {
					status = "已打回";
					nextEmp = bcv.getSysEmployeeByCreateSn();                                                                              
				} else if (checkResult.getResult().equals("拒绝")) {
					status = "已终止";
				} else if (checkResult.getResult().equals("通过")) {
					if(position==3||bcv.getTotalAccount() < 5000){
						status = "已审批";
						// 财务职位id=4;
						nextEmp = es.findSysEmployee(4L, 4L).get(0);
					}
					else{
						status = "待审批";
						// 总经理职位id=3;//部门id为当前登陆用户所在部门（employee.getSysDepartment().getId()），这里先手动赋值3测试
						nextEmp = es.findSysEmployee(3L, 3L).get(0);
					}
				}
			}
			else if(position==4){
				if (checkResult.getResult().equals("通过")) {
					status = "已付款";
				}
			}

			bcv.setStatus(status);
			bcv.setSysEmployeeByNextDealSn(nextEmp);
			bcv.setModifyTime(new Date());
			Set checkResults = new HashSet();
			checkResult.setCheckTime(new Date());
			checkResult.setSysEmployee(currEmployee);
			checkResults.add(checkResult);
			bcv.setBizCheckResults(checkResults);
			bcvs.updateClaimVoucher(bcv);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public BizClaimVoucherService getBcvs() {
		return bcvs;
	}

	public void setBcvs(BizClaimVoucherService bcvs) {
		this.bcvs = bcvs;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public BizClaimVoucher getClaimVoucher() {
		return claimVoucher;
	}

	public void setClaimVoucher(BizClaimVoucher claimVoucher) {
		this.claimVoucher = claimVoucher;
	}

	public BizClaimVoucherDetail getClaimVoucherDetail() {
		return claimVoucherDetail;
	}

	public void setClaimVoucherDetail(BizClaimVoucherDetail claimVoucherDetail) {
		this.claimVoucherDetail = claimVoucherDetail;
	}

	public List<BizClaimVoucherDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<BizClaimVoucherDetail> detailList) {
		this.detailList = detailList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BizCheckResult getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(BizCheckResult checkResult) {
		this.checkResult = checkResult;
	}

	public EmployeeService getEs() {
		return es;
	}

	public void setEs(EmployeeService es) {
		this.es = es;
	}

}
