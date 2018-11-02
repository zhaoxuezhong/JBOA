package com.zxz.service.impl;

import com.zxz.dao.Dao;
import com.zxz.dao.impl.DaoImpl;
import com.zxz.entity.BizClaimVoucher;
import com.zxz.entity.condition.Condition;
import com.zxz.entity.page.PageInfo;
import com.zxz.service.BizClaimVoucherService;

/**
 * @author zhaoxuezhong
 *
 */
public class BizClaimVoucherServiceImpl implements BizClaimVoucherService {
	private Dao dao=new DaoImpl();
	
	@Override
	public Long saveClaimVoucher(BizClaimVoucher bcv) {
		return dao.save(bcv);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<BizClaimVoucher> findClaimVoucher(Condition condition, Integer firstResult, Integer maxResult) {
		PageInfo<BizClaimVoucher> pageInfo=new PageInfo<BizClaimVoucher>();
		try {
			StringBuilder sb=new StringBuilder("from BizClaimVoucher bcv where 1=1");
			if(condition==null){
				condition=new Condition();
			}
			else{
				if(condition.getCreateSn()!=null){
					sb.append(" and bcv.sysEmployeeByCreateSn.sn=:createSn ");
				}
				if(condition.getNextDealSn()!=null){
					sb.append(" and bcv.sysEmployeeByNextDealSn.sn=:nextDealSn ");
				}
				if(condition.getStatus()!=null&&!condition.getStatus().equals("")){
					sb.append(" and bcv.status=:status");
				}
				if(condition.getStartTime()!=null){
					sb.append(" and bcv.createTime >=:startTime ");
				}
				if(condition.getEndTime()!=null){
					sb.append(" and bcv.createTime <=:endTime ");
				}
			}
			sb.append(" order by bcv.status,bcv.createTime desc");
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

	@Override
	public BizClaimVoucher findClaimVoucher(Long id) {
		return (BizClaimVoucher) dao.get(BizClaimVoucher.class, id);
	}

	@Override
	public void deleteClaimVoucher(BizClaimVoucher bcv) {
		dao.delete(bcv);
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	@Override
	public void updateClaimVoucher(BizClaimVoucher bcv) {
		this.dao.update(bcv);
	}

}
