/**
 * 
 */
package com.zxz.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoxuezhong
 *
 */
public interface Dao {

	Long save(Object obj);
	void update(Object obj);
	void delete(Object obj);
	Object get(Class clazz,Serializable id);
	Integer getCount(String hql,Object obj,String id);
	
	List findByObject(String hql,Object obj);
	List findByMap(String hql,Map map);
	
	List findByObject(String hql,Object obj,Integer firstResult,Integer maxResult);	
	List findByMap(String hql,Map map,Integer firstResult,Integer maxResult);
	
}