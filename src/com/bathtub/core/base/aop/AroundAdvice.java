package com.bathtub.core.base.aop;

import org.aspectj.lang.ProceedingJoinPoint;

import com.bathtub.core.exception.AopException;

/**
 * ��Χ֪ͨ�ӿ�
 */
public interface AroundAdvice
{

	/**
	 * ʵ�ְ�Χ֪ͨ
	 */
	public Object doAround(ProceedingJoinPoint pjp) throws AopException;

}