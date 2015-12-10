package com.bathtub.core.base.aop;

import org.aspectj.lang.JoinPoint;

import com.bathtub.core.exception.AopException;

/**  
 * �������к�ִ�еĲ����ӿ�
 * 
 */
public interface AfterAdvice
{
	/**
	 * �������к�ִ�еĲ���
	 * 
	 * @param jp �е�
	 * @param retVal ��������ֵ
	 * @return 
	 * @throws com.jshx.core.exception.AopException  
	 */
	public void doAfter(JoinPoint jp, Object retVal) throws AopException;

}
