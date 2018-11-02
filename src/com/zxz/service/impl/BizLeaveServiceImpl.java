package com.zxz.service.impl;

import com.zxz.dao.Dao;
import com.zxz.dao.impl.DaoImpl;
import com.zxz.entity.BizLeave;
import com.zxz.entity.condition.Condition;
import com.zxz.entity.page.PageInfo;
import com.zxz.service.BizLeaveService;

/**
 * @author zhaoxuezhong
 *
 */
public class BizLeaveServiceImpl implements BizLeaveService {

	private Dao dao=new DaoImpl();
	
	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<BizLeave> findBizLeave(Condition condition, Integer firstResult,Integer maxResult) {
		PageInfo<BizLeave> pageInfo=new PageInfo<BizLeave>();
		StringBuilder sb=new StringBuilder("from BizLeave where 1=1");
		if(condition==null){
			condition=new Condition();
		}
		if(condition.getStartTime()!=null){
			sb.append(" and starttime >:startTime");
		}
		if(condition.getEndTime()!=null){
			sb.append(" and endtime <:endTime ");
		}
		sb.append(" order by createtime desc,status");
		Integer totalCount=dao.findByObject(sb.toString(), condition).size();
		pageInfo.setTotalCount(totalCount);
		pageInfo.setPageSize(maxResult);
		pageInfo.setPageIndex(firstResult);
		if(totalCount!=null&&totalCount>0){
			pageInfo.setList(dao.findByObject(sb.toString(), condition, (firstResult-1)*maxResult, maxResult));
		}
		return pageInfo;
	}

	@Override
	public long save(BizLeave bl) {
		return dao.save(bl);
	}

	@Override
	public BizLeave findBizLeave(long id) {
		return (BizLeave) dao.get(BizLeave.class, id);
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
}
