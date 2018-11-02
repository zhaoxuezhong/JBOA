package com.zxz.service.impl;

import com.zxz.dao.Dao;
import com.zxz.dao.impl.DaoImpl;
import com.zxz.entity.BizClaimVoucher;
import com.zxz.entity.BizClaimVoucherStatistics;
import com.zxz.entity.condition.ClaimVoucherStatisticsCondition;
import com.zxz.entity.page.PageInfo;
import com.zxz.service.BizClaimVoucherStatisticsService;

public class BizClaimVoucherStatisticsServiceImpl implements BizClaimVoucherStatisticsService {
	private Dao dao=new DaoImpl();
		
	@Override
	public BizClaimVoucherStatistics findClaimVoucherStatistics(Long id) {
		return (BizClaimVoucherStatistics) dao.get(BizClaimVoucherStatistics.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<BizClaimVoucherStatistics> findClaimVoucherStatistics(boolean group,ClaimVoucherStatisticsCondition condition,
			Integer firstResult, Integer maxResult) {
		PageInfo<BizClaimVoucherStatistics> pageInfo=new PageInfo<BizClaimVoucherStatistics>();
		try {
			StringBuilder sb=new StringBuilder("from BizClaimVoucherStatistics bcvs where 1=1");
			if(condition==null){
				condition=new ClaimVoucherStatisticsCondition();
			}
			else{
				if(condition.getDept()!=0){
					sb.append(" and bcvs.sysDepartment.id=:dept ");
				}
				if(condition.getYear()!=null){
					sb.append(" and bcvs.year=:year ");
				}
				if(condition.getMonth()!=null){
					sb.append(" and bcvs.month=:month ");
				}
				if(condition.getStartMonth()!=null&&condition.getStartMonth()!=0){
					sb.append(" and bcvs.month >=:startMonth ");
				}
				if(condition.getEndMonth()!=null&&condition.getEndMonth()!=0){
					sb.append(" and bcvs.month <=:endMonth ");
				}
				if(condition.getStartYear()!=null&&condition.getStartYear()!=0){
					sb.append(" and bcvs.year >=:startYear ");
				}
				if(condition.getEndYear()!=null&&condition.getEndYear()!=0){
					sb.append(" and bcvs.year <=:endYear ");
				}
			}
			if(group){
				sb.append(" group by bcvs.year,bcvs.month ");
			}
			Integer totalCount=dao.getCount(sb.toString(), condition,"bcvs.id");
			pageInfo.setTotalCount(totalCount);
			if(totalCount!=null&&totalCount!=0){
				sb.append(" order by bcvs.year desc,bcvs.month desc");
				if(group){
					sb.insert(0, "select new BizClaimVoucherStatistics(sum(bcvs.totalCount),bcvs.year,bcvs.month) ");
				}
				pageInfo.setPageSize(maxResult);
				pageInfo.setPageIndex(firstResult);
				pageInfo.setList(dao.findByObject(sb.toString(), condition, (firstResult-1)*maxResult, maxResult));
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageInfo;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<BizClaimVoucher> findClaimVoucherByMonth(ClaimVoucherStatisticsCondition condition,
			Integer firstResult, Integer maxResult) {
		PageInfo<BizClaimVoucher> pageInfo=new PageInfo<BizClaimVoucher>();
		try {
			StringBuilder sb=new StringBuilder("from BizClaimVoucher bcv where 1=1");
			if(condition==null){
				condition=new ClaimVoucherStatisticsCondition();
			}
			else{
				if(condition.getDept()!=0){
					sb.append(" and bcv.sysEmployeeByCreateSn.sysDepartment.id=:dept ");
				}
				if(condition.getYear()!=null){
					sb.append(" and extract(year from bcv.modifyTime)=:year ");
				}
				if(condition.getMonth()!=null){
					sb.append(" and extract(month from bcv.modifyTime)=:month ");
				}
			}
			sb.append(" and bcv.status='已付款' order by bcv.status,bcv.modifyTime desc");
			Integer totalCount=dao.getCount(sb.toString(), condition,"bcv.id");
			pageInfo.setTotalCount(totalCount);
			if(totalCount!=null&&totalCount!=0){
				pageInfo.setPageSize(maxResult);
				pageInfo.setPageIndex(firstResult);
				pageInfo.setList(dao.findByObject(sb.toString(), condition, (firstResult-1)*maxResult, maxResult));
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageInfo;
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
}
