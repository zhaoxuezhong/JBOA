package com.zxz.service;

import com.zxz.entity.BizClaimVoucher;
import com.zxz.entity.BizClaimVoucherStatistics;
import com.zxz.entity.condition.ClaimVoucherStatisticsCondition;
import com.zxz.entity.page.PageInfo;

/**
 * @author zhaoxuezhong
 *
 */
public interface BizClaimVoucherStatisticsService {

	public BizClaimVoucherStatistics findClaimVoucherStatistics(Long id);
	
	public PageInfo<BizClaimVoucherStatistics> findClaimVoucherStatistics
	(boolean group,ClaimVoucherStatisticsCondition condition, Integer firstResult,Integer maxResult);
	
	public PageInfo<BizClaimVoucher> findClaimVoucherByMonth(ClaimVoucherStatisticsCondition condition, Integer firstResult,Integer maxResult);

}
