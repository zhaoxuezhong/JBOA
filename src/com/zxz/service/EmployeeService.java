package com.zxz.service;

import java.io.Serializable;
import java.util.List;

import com.zxz.entity.SysEmployee;

/**
 * @author zhaoxuezhong
 *
 */
public interface EmployeeService {

	public SysEmployee login(SysEmployee emp);
	
	public List<SysEmployee> findSysEmployee(Long dept,Long position);
	
	public SysEmployee get(Serializable sn);
	
}
