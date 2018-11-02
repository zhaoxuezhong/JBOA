package com.zxz.service;

import com.zxz.entity.BizLeave;
import com.zxz.entity.condition.Condition;
import com.zxz.entity.page.PageInfo;

/**
 * @author zhaoxuezhong
 *
 */
public interface BizLeaveService {

	PageInfo<BizLeave> findBizLeave(Condition condition, Integer firstResult,Integer maxResult);
	
	long save(BizLeave bl);
	
	BizLeave findBizLeave(long id);
}
