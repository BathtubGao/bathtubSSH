package com.bathtub.core.base.aop;

import org.aspectj.lang.JoinPoint;

import com.bathtub.core.exception.AopException;

/**
 * 方法运行后执行前的操作接口
 */
public interface BeforeAdvice
{

	/**
	 * 方法运行后执行前的操作
	 */
	public void doBefore(JoinPoint jp) throws AopException;

}
