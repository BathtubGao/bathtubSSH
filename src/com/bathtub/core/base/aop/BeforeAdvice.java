package com.bathtub.core.base.aop;

import org.aspectj.lang.JoinPoint;

import com.bathtub.core.exception.AopException;

/**
 * �������к�ִ��ǰ�Ĳ����ӿ�
 */
public interface BeforeAdvice
{

	/**
	 * �������к�ִ��ǰ�Ĳ���
	 */
	public void doBefore(JoinPoint jp) throws AopException;

}
