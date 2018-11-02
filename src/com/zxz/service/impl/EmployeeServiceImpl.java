package com.zxz.service.impl;

import java.io.Serializable;
import java.util.List;

import com.zxz.dao.Dao;
import com.zxz.dao.impl.DaoImpl;
import com.zxz.entity.SysEmployee;
import com.zxz.service.EmployeeService;

/**
 * @author zhaoxuezhong
 *
 */
@SuppressWarnings("unchecked")
public class EmployeeServiceImpl implements EmployeeService {
	private Dao dao=new DaoImpl();
	
	@Override
	public SysEmployee login(SysEmployee emp) {
		String hql="from SysEmployee where sn=:sn and password=:password ";
		List<SysEmployee> empList=dao.findByObject(hql,emp);
		return empList.size()>0?empList.get(0):null;
	}

	@Override
	public List<SysEmployee> findSysEmployee(Long dept,Long position) {
		String hql="from SysEmployee s where s.sysDepartment.id="+dept+" and s.sysPosition.id="+position;
		return dao.findByObject(hql, new SysEmployee());
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	@Override
	public SysEmployee get(Serializable sn) {
		return (SysEmployee) dao.get(SysEmployee.class, sn);
	}
}
