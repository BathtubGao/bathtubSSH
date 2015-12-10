package com.bathtub.core.base.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;


/**
 *  ���ݷ��ʽӿ�DAO��Data Access Object��,�ýӿ�Ϊ���е����ݿ���ʲ����ṩͨ�÷���
 */

@SuppressWarnings("rawtypes")
public interface BaseDao
{
	
	/**
	 * ���Session
	 * 
	 * @return Session   
	 */
	public Session getSession();
	
	/**
	 * ����sqlid ���봫��Ĳ�����ѯ���ݿ⣬�����б����
	 * 
	 * @param sqlId
	 * @param paraMap
	 * @return List
	 */
	List findListBySqlId(String sqlId, Map<?, ?> paraMap);
	
	
	/**
	 * ����hqlid ����ѯ���ݿ⣬�����б����
	 * 
	 * @param hqlId
	 * @param paraMap
	 * @return List
	 */
	List findListByHqlId(String hqlId, Map<?, ?> paraMap);
	
	/**
	 * ����ʵ����
	 * 
	 * @param object
	 * @return void
	 */
	void saveObject(Object object);
	
	/**
	 * �������¶���
	 * 
	 * @param object
	 * @return void   
	 */
	public void saveOrUpdateObject(Object object);
	
}
