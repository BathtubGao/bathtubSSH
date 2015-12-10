package com.bathtub.core.base.aop;

import org.aspectj.lang.ProceedingJoinPoint;

import com.bathtub.core.exception.AopException;

/**
 * 包围通知接口
 */
public interface AroundAdvice
{

	/**
	 * 实现包围通知
	 */
	public Object doAround(ProceedingJoinPoint pjp) throws AopException;

}