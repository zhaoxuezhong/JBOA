package com.zxz.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zxz.dao.Dao;

/**
 * @author zhaoxuezhong
 *
 */
@SuppressWarnings({ "deprecation", "rawtypes" ,"unchecked"})
public class DaoImpl extends HibernateDaoSupport implements Dao {

	@Override
	public Long save(Object obj) {
		return (Long)getHibernateTemplate().save(obj);
	}
	@Override
	public void update(Object obj) {
		getHibernateTemplate().update(obj);
	}

	@Override
	public void delete(Object obj) {
		getHibernateTemplate().delete(obj);
	}
	
	@Override
	public List findByObject(String hql, Object obj) {
		return findByObject(hql, obj, -1, -1);
	}
	@Override
	public List findByMap(String hql, Map map) {
		return findByMap(hql, map, -1, -1);
	}

	@Override
	public List findByObject(final String hql, final Object obj, final Integer firstResult,
			final Integer maxResult) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query=session.createQuery(hql).setProperties(obj);
				if(firstResult>=0&&maxResult>0){
					query.setFirstResult(firstResult).setMaxResults(maxResult);
				}
				return query.list();
			}
		});
	}

	@Override
	public List findByMap(final String hql, final Map map, final Integer firstResult,
			final Integer maxResult) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query=session.createQuery(hql).setProperties(map);
				if(firstResult>=0&&maxResult>0){
					query.setFirstResult(firstResult).setMaxResults(maxResult);
				}
				return query.list();
			}
		});
	}
	@Override
	public Object get(Class clazz, Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}
	@Override
	public Integer getCount(String hql,Object obj,String id) {
		List<Long> count=findByObject("select count("+id+") "+hql, obj);
		if(count!=null&&count.size()>0){
			if(count.size()>1){
				return count.size();
			}
			return count.get(0).intValue();
		}else{
			return null;
		}
	}
}
