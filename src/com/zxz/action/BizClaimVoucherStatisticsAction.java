package com.zxz.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zxz.entity.BizClaimVoucher;
import com.zxz.entity.BizClaimVoucherStatistics;
import com.zxz.entity.SysEmployee;
import com.zxz.entity.condition.ClaimVoucherStatisticsCondition;
import com.zxz.entity.page.PageInfo;
import com.zxz.service.BizClaimVoucherStatisticsService;
import com.zxz.service.impl.BizClaimVoucherStatisticsServiceImpl;
import com.zxz.util.Constants;


/**
 * @author zhaoxuezhong
 *
 */
public class BizClaimVoucherStatisticsAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = -7125309684976868207L;
	private BizClaimVoucherStatisticsService bcvss=new BizClaimVoucherStatisticsServiceImpl();
	private ClaimVoucherStatisticsCondition condition=new ClaimVoucherStatisticsCondition();
	private boolean group;
	
	public String searchBizClaimVoucherStatistics()throws Exception{
		SysEmployee employee = (SysEmployee) session.get("employee");
		String position=employee.getSysPosition().getNameCn();
		if(position.equals(Constants.POSITION_FM)){
			condition.setDept(employee.getSysDepartment().getId());
		}
		PageInfo<BizClaimVoucherStatistics> pageInfo=bcvss.findClaimVoucherStatistics(group,condition, condition.getPageIndex(), condition.getPageSize());
		session.put("compMonth", pageInfo);
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
	
	public String getBizClaimVoucherStatisticsByMonth()throws Exception{
		try {
			PageInfo<BizClaimVoucher> claimVoucherList=bcvss.findClaimVoucherByMonth(condition, condition.getPageIndex(), condition.getPageSize());
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

	public BizClaimVoucherStatisticsService getBcvss() {
		return bcvss;
	}

	public void setBcvss(BizClaimVoucherStatisticsService bcvss) {
		this.bcvss = bcvss;
	}

	public ClaimVoucherStatisticsCondition getCondition() {
		return condition;
	}

	public void setCondition(ClaimVoucherStatisticsCondition condition) {
		this.condition = condition;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public boolean isGroup() {
		return group;
	}

	public void setGroup(boolean group) {
		this.group = group;
	}
	
}
