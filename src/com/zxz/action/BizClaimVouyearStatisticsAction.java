package com.zxz.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zxz.entity.BizClaimVoucher;
import com.zxz.entity.BizClaimVouyearStatistics;
import com.zxz.entity.SysEmployee;
import com.zxz.entity.condition.ClaimVoucherStatisticsCondition;
import com.zxz.entity.page.PageInfo;
import com.zxz.service.BizClaimVouyearStatisticsService;
import com.zxz.service.impl.BizClaimVouyearStatisticsServiceImpl;
import com.zxz.util.Constants;


/**
 * @author zhaoxuezhong
 *
 */
public class BizClaimVouyearStatisticsAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = -7125309684976868207L;
	private BizClaimVouyearStatisticsService bcvyss=new BizClaimVouyearStatisticsServiceImpl();
	private ClaimVoucherStatisticsCondition condition=new ClaimVoucherStatisticsCondition();
	private boolean group;
	
	public String searchBizClaimVouyearStatistics()throws Exception{
		SysEmployee employee = (SysEmployee) session.get("employee");
		String position=employee.getSysPosition().getNameCn();
		if(position.equals(Constants.POSITION_FM)){
			condition.setDept(employee.getSysDepartment().getId());
		}
		PageInfo<BizClaimVouyearStatistics> pageInfo=bcvyss.findClaimVouyearStatistics(group,condition, condition.getPageIndex(), condition.getPageSize());
		session.put("compYear", pageInfo);
		if(position.equals(Constants.POSITION_FM)){
			return "dept";
		}
		else if(position.equals(Constants.POSITION_GM)){
			if(group){
				return SUCCESS;
			}
			return "detail";
		}
		return ERROR;
	}
	
	public String getBizClaimVouyearStatisticsByYear()throws Exception{
		try {
			PageInfo<BizClaimVoucher> claimVoucherList=bcvyss.findClaimVouyearByYear(condition, condition.getPageIndex(), condition.getPageSize());
			session.put("claimVoucherList", claimVoucherList);
			SysEmployee employee = (SysEmployee) session.get("employee");
			String position=employee.getSysPosition().getNameCn();
			if(position.equals(Constants.POSITION_FM)){
				return "dept";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}
	

	private Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> arg0) {
		session=arg0;
	}

	public BizClaimVouyearStatisticsService getBcvyss() {
		return bcvyss;
	}

	public void setBcvyss(BizClaimVouyearStatisticsService bcvyss) {
		this.bcvyss = bcvyss;
	}

	public ClaimVoucherStatisticsCondition getCondition() {
		return condition;
	}

	public void setCondition(ClaimVoucherStatisticsCondition condition) {
		this.condition = condition;
	}

	public boolean isGroup() {
		return group;
	}

	public void setGroup(boolean group) {
		this.group = group;
	}

	public Map<String, Object> getSession() {
		return session;
	}

}
