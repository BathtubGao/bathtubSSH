package com.bathtub.core.base.interceptor;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.bathtub.core.base.entity.BaseModel;

/**
 * 自动监听保存、删除、修改方法，添加相应数据
 * @author john.zhang
 */
public class HibernateInterceptor extends EmptyInterceptor
{

	private static final long serialVersionUID = 2504913633378515244L;

	/**
	 * 保存entity时加入创建时间和创建者id
	 * @param entity
	 * @param id
	 * @param state
	 * @param propertyNames
	 * @param types
	 */
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types)
	{
		try
		{
			// 保存数据记录时直接添加默认字段信息：BaseModel为XML配置的实体类；BaseEntity为注释配置的实体类
			if (entity instanceof BaseModel)
			{
				// 如果ServletActionContext报空指针，PropertyUtils.setProperty必须放在后面，否则执行两条语句
				for (int i = 0; i < propertyNames.length; i++)
				{
					if ("createTime".equals(propertyNames[i]))
						state[i] = new Date();
				}
			}

		}
		catch (Exception e)
		{
		}

		return true;
	}

	/**
	 * 更新entity时加入最近更新时间和最近更新者id
	 * @param entity
	 * @param id
	 * @param currentState
	 * @param previousState
	 * @param propertyNames
	 * @param types
	 */
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types)
	{
		try
		{
			if (entity instanceof BaseModel)
			{
				// 同时更新currentState， 这样才能起到更新的效果
				for (int i = 0; i < propertyNames.length; i++)
				{
					if ("updateTime".equals(propertyNames[i]))
					{
						currentState[i] = new Date();
					}
				}
			}
		}
		catch (Exception e)
		{
		}
		return true;
	}

}
