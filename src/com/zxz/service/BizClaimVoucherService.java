package com.zxz.service;

import com.zxz.entity.BizClaimVoucher;
import com.zxz.entity.condition.Condition;
import com.zxz.entity.page.PageInfo;

/**
 * @author zhaoxuezhong
 *
 */
public interface BizClaimVoucherService {

	public Long saveClaimVoucher(BizClaimVoucher bcv);
	
	public BizClaimVoucher findClaimVoucher(Long id);
	
	public PageInfo<BizClaimVoucher> findClaimVoucher(Condition condition, Integer firstResult,Integer maxResult);
	
	public void deleteClaimVoucher(BizClaimVoucher bcv);
	
	public void updateClaimVoucher(BizClaimVoucher bcv);
	
}
