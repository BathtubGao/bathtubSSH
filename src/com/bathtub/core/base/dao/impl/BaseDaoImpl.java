package com.bathtub.core.base.dao.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.bathtub.core.base.dao.BaseDao;
import com.bathtub.core.base.entity.BaseModel;
import com.bathtub.core.utils.StringUtil;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.scope.SessionScope;
import com.ibatis.sqlmap.engine.scope.StatementScope;

@SuppressWarnings(value = { "deprecation", "rawtypes" })
public class BaseDaoImpl implements BaseDao
{
	protected final Logger logger;

	public BaseDaoImpl()
	{
		this.logger = LoggerFactory.getLogger(getClass());
	}

	@Autowired()
	@Qualifier("sqlMapClient")
	private SqlMapClient sqlMapClient;

	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	public void saveOrUpdateObject(Object object)
	{
		getSession().saveOrUpdate(object);
	}

	public void saveObject(Object object)
	{
		if (object instanceof BaseModel)
		{
			if (StringUtil.isNotNullAndNotEmpty(((BaseModel) object).getId()))
			{
				getSession().update(object);
			}
			else
			{
				getSession().save(object);
			}
		}
		else
		{
			getSession().saveOrUpdate(object);
		}
	}

	public List findListByHqlId(final String hqlId, Map<?, ?> paraMap)
	{
		List restList = null;
		try
		{
			String hsql = getSqlStatementById(hqlId, paraMap);
			if (hsql != null)
			{
				Query query = getSession().createQuery(hsql);
				query.setCacheable(true);
				setQueryParameters(query, paraMap);
				restList = query.list();
			}
			else
			{
				System.out.println("未在 SqlMap配置文件中配置, ID为" + hqlId + "hql 语句");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return restList;
	}

	public List findListBySqlId(final String sqlId, Map<?, ?> paraMap)
	{
		List list = null;
		try
		{
			String hql = getSqlStatementById(sqlId, paraMap);
			if (StringUtils.isNotBlank(hql))
			{
				Query query = getSession().createSQLQuery(hql);
				// query.setCacheable(SysPropertiesUtil.getBoolean("use_query_cache",
				// false));
				setQueryParameters(query, paraMap);
				list = query.list();
			}
			else
			{
				System.out.println("未在 SqlMap配置文件中配置, ID为" + sqlId + "hql 语句");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据sql-map配置的sql语句id查找sql语句
	 * @param sqlId
	 * @param paramObject
	 * @return
	 */
	protected String getSqlStatementById(String sqlId, Object paramObject)
	{
		if (sqlMapClient == null)
		{
			logger.error("IBATIS sqlMapClient 未设置!");
			throw new RuntimeException("IBATIS sqlMapClient 未设置!");
		}
		String sql = null;
		ExtendedSqlMapClient extendedSqlMapClient = (ExtendedSqlMapClient) sqlMapClient;
		MappedStatement mappedStatement = extendedSqlMapClient.getMappedStatement(sqlId);
		if (mappedStatement != null)
		{
			SessionScope sessionScope = new SessionScope();
			sessionScope.incrementRequestStackDepth();
			StatementScope statementScope = new StatementScope(sessionScope);
			statementScope.setStatement(mappedStatement);
			sql = mappedStatement.getSql().getSql(statementScope, paramObject);
		}
		return sql;
	}

	/**
	 * 设置查询条件
	 * @param query
	 * @param paraMap
	 */
	protected void setQueryParameters(Query query, Map paraMap)
	{
		if (query != null && paraMap != null && !paraMap.isEmpty())
		{
			List namedParms = Arrays.asList(query.getNamedParameters());
			Iterator iter = paraMap.keySet().iterator();
			while (iter.hasNext())
			{
				String paraName = (String) iter.next();
				if (namedParms.contains(paraName))
				{
					Object value = paraMap.get(paraName);
					if (value instanceof List)
					{
						query.setParameterList(paraName, (List) value);
					}
					else
					{
						query.setParameter(paraName, value);
					}
				}
			}
		}
	}
}
