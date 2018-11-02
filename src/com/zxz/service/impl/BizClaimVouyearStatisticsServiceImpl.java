package com.zxz.service.impl;

import com.zxz.dao.Dao;
import com.zxz.dao.impl.DaoImpl;
import com.zxz.entity.BizClaimVoucher;
import com.zxz.entity.BizClaimVouyearStatistics;
import com.zxz.entity.condition.ClaimVoucherStatisticsCondition;
import com.zxz.entity.page.PageInfo;
import com.zxz.service.BizClaimVouyearStatisticsService;

public class BizClaimVouyearStatisticsServiceImpl implements BizClaimVouyearStatisticsService {
	private Dao dao=new DaoImpl();

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<BizClaimVouyearStatistics> findClaimVouyearStatistics(boolean group,ClaimVoucherStatisticsCondition condition,
			Integer firstResult, Integer maxResult) {
		PageInfo<BizClaimVouyearStatistics> pageInfo=new PageInfo<BizClaimVouyearStatistics>();
		try {
			StringBuilder sb=new StringBuilder("from BizClaimVouyearStatistics bcvs where 1=1");
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
				if(condition.getStartYear()!=null&&condition.getStartYear()!=0){
					sb.append(" and bcvs.year >=:startYear ");
				}
				if(condition.getEndYear()!=null&&condition.getEndYear()!=0){
					sb.append(" and bcvs.year <=:endYear ");
				}
			}
			if(group){
				sb.append(" group by bcvs.year ");
			}
			Integer totalCount=dao.getCount(sb.toString(), condition,"bcvs.id");
			pageInfo.setTotalCount(totalCount);
			if(totalCount!=null&&totalCount!=0){
				sb.append(" order by bcvs.year desc");
				if(group){
					sb.insert(0, "select new BizClaimVouyearStatistics(sum(bcvs.totalCount),bcvs.year) ");
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
	public PageInfo<BizClaimVoucher> findClaimVouyearByYear(ClaimVoucherStatisticsCondition condition,
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
