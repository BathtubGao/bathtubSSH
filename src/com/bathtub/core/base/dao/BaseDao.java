package com.bathtub.core.base.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;


/**
 *  数据访问接口DAO（Data Access Object）,该接口为所有的数据库访问操作提供通用方法
 */

@SuppressWarnings("rawtypes")
public interface BaseDao
{
	
	/**
	 * 获得Session
	 * 
	 * @return Session   
	 */
	public Session getSession();
	
	/**
	 * 根据sqlid ，与传入的参数查询数据库，返回列表对象
	 * 
	 * @param sqlId
	 * @param paraMap
	 * @return List
	 */
	List findListBySqlId(String sqlId, Map<?, ?> paraMap);
	
	
	/**
	 * 根据hqlid ，查询数据库，返回列表对象
	 * 
	 * @param hqlId
	 * @param paraMap
	 * @return List
	 */
	List findListByHqlId(String hqlId, Map<?, ?> paraMap);
	
	/**
	 * 保存实体类
	 * 
	 * @param object
	 * @return void
	 */
	void saveObject(Object object);
	
	/**
	 * 保存或更新对象
	 * 
	 * @param object
	 * @return void   
	 */
	public void saveOrUpdateObject(Object object);
	
}
