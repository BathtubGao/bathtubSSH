package com.bathtub.core.base.aop;

import org.aspectj.lang.JoinPoint;

import com.bathtub.core.exception.AopException;

/**
 * �쳣����ӿ�
 */
public interface ExceptionAdvice
{

	/**
	 * �쳣����
	 */
	public void doThrowing(JoinPoint jp, Throwable ex) throws AopException;

}
