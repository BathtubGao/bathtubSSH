package com.bathtub.core.base.interceptor;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.bathtub.core.base.entity.BaseModel;

/**
 * �Զ��������桢ɾ�����޸ķ����������Ӧ����
 * @author john.zhang
 */
public class HibernateInterceptor extends EmptyInterceptor
{

	private static final long serialVersionUID = 2504913633378515244L;

	/**
	 * ����entityʱ���봴��ʱ��ʹ�����id
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
			// �������ݼ�¼ʱֱ�����Ĭ���ֶ���Ϣ��BaseModelΪXML���õ�ʵ���ࣻBaseEntityΪע�����õ�ʵ����
			if (entity instanceof BaseModel)
			{
				// ���ServletActionContext����ָ�룬PropertyUtils.setProperty������ں��棬����ִ���������
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
	 * ����entityʱ�����������ʱ������������id
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
				// ͬʱ����currentState�� ���������𵽸��µ�Ч��
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
