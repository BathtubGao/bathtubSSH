package com.bathtub.core.base.aop;

import org.aspectj.lang.JoinPoint;

import com.bathtub.core.exception.AopException;

/**  
 * 方法运行后执行的操作接口
 * 
 */
public interface AfterAdvice
{
	/**
	 * 方法运行后执行的操作
	 * 
	 * @param jp 切点
	 * @param retVal 方法返回值
	 * @return 
	 * @throws com.jshx.core.exception.AopException  
	 */
	public void doAfter(JoinPoint jp, Object retVal) throws AopException;

}
