package com.zxz.service;

import com.zxz.entity.BizClaimVoucher;
import com.zxz.entity.BizClaimVouyearStatistics;
import com.zxz.entity.condition.ClaimVoucherStatisticsCondition;
import com.zxz.entity.page.PageInfo;

/**
 * @author zhaoxuezhong
 *
 */
public interface BizClaimVouyearStatisticsService {

	public PageInfo<BizClaimVouyearStatistics> findClaimVouyearStatistics
	(boolean group,ClaimVoucherStatisticsCondition condition, Integer firstResult,Integer maxResult);
	
	public PageInfo<BizClaimVoucher> findClaimVouyearByYear(ClaimVoucherStatisticsCondition condition, Integer firstResult,Integer maxResult);

}
