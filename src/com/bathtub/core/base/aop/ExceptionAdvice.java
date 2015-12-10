package com.bathtub.core.base.aop;

import org.aspectj.lang.JoinPoint;

import com.bathtub.core.exception.AopException;

/**
 * 异常处理接口
 */
public interface ExceptionAdvice
{

	/**
	 * 异常处理
	 */
	public void doThrowing(JoinPoint jp, Throwable ex) throws AopException;

}
