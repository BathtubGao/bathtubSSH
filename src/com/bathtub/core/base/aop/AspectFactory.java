package com.bathtub.core.base.aop;

import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bathtub.core.exception.AopException;
import com.bathtub.core.utils.SpringContextHolder;

/**  
 * adviceMap����������Լ����Ӧ��AOP����ʵ�ֵ�ӳ��<br>
 * �ڵ���AOP��ʱ����Ҫ����adviceMap�����õ���Ϣ���Ҿ���Ľ�����Aspect��ִ��
 */
public class AspectFactory
{
protected Logger logger = LoggerFactory.getLogger(AspectFactory.class);
	
	private String getBeanName(String methodInfo){
		if(this.adviceMap==null)
			return null;
		Iterator<String> keyIt = this.adviceMap.keySet().iterator();
		while(keyIt.hasNext()){
			String regex = keyIt.next();
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(methodInfo);
			if(m.matches()){
				return adviceMap.get(regex);
			}
		}
		return null;
	}
		
	/** ������Լ����Ӧ��AOP����ʵ�����ӳ�� */
	private Map<String, String> adviceMap;

	/**
	 * �������к�ִ�еĲ���
	 * 
	 * @param jp �е�
	 * @param retVal ��������ֵ
	 * @return 
	 */
	public void doAfter(JoinPoint jp, Object retVal) throws Throwable {
		if(adviceMap==null)
			return;
		String className = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName();
		String beanName = getBeanName(className+"."+methodName);
		if(beanName==null)
			return;
		Object aspect = SpringContextHolder.getBean(beanName);		
		if(aspect==null)
			throw new AopException("AOP���ô���Bean"+beanName+"δ���ã�");
		if(!(aspect instanceof AfterAdvice))
			return ;
		AfterAdvice advice = (AfterAdvice)aspect;
		advice.doAfter(jp, retVal);
	}

	/**
	 * ʵ�ְ�Χ֪ͨ
	 * 
	 * @param pjp �е�
	 * @return   
	 */
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		if(adviceMap==null){
			Object retVal = null;
	        try{
	        	retVal = pjp.proceed();
	        }catch(Throwable cause){
	        	throw new AopException(cause.getLocalizedMessage(),cause);
	        }
	        return retVal;
		}
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		String beanName = getBeanName(className+"."+methodName);
		if(beanName==null){
			Object retVal = null;
	        try{
	        	retVal = pjp.proceed();
	        }catch(Throwable cause){
	        	throw cause;
	        }
	        return retVal;
		}
		Object aspect = SpringContextHolder.getBean(beanName);		
		if(aspect==null)
			throw new AopException("AOP���ô���Bean"+beanName+"δ���ã�");
		if(!(aspect instanceof AroundAdvice)){
			Object retVal = pjp.proceed();
	        return retVal;
		}
		AroundAdvice advice = (AroundAdvice)aspect;
		return advice.doAround(pjp);
	}

	/**
	 * �������к�ִ��ǰ�Ĳ���
	 * 
	 * @param jp �е�
	 * @return void   
	 */
	public void doBefore(JoinPoint jp) throws AopException{
		if(adviceMap==null)
			return;
		String className = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName();
		String beanName = getBeanName(className+"."+methodName);
		if(beanName==null)
			return;
		
		Object aspect = SpringContextHolder.getBean(beanName);	
		if(aspect==null)
			throw new AopException("AOP���ô���Bean"+beanName+"δ���ã�");
		if(!(aspect instanceof BeforeAdvice))
			return;
		BeforeAdvice advice = (BeforeAdvice)aspect;
		advice.doBefore(jp);
	}

	/**
	 * �쳣����
	 * 
	 * @param jp
	 * @param ex
	 * @return void  
	 * @throws com.jshx.core.exception.AopException
	 */
	public void doThrowing(JoinPoint jp, Throwable ex) throws AopException,Throwable{
		String className = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName();
		String beanName = getBeanName(className+"."+methodName);
		
		if(beanName==null){
			//Ĭ�ϵ��쳣����
			throw ex;
		}else{
			Object aspect = SpringContextHolder.getBean(beanName);
			if(aspect==null)
				throw new AopException("AOP���ô���Bean"+beanName+"δ���ã�");
			if(!(aspect instanceof ExceptionAdvice)){
				throw ex;
			}
			ExceptionAdvice advice = (ExceptionAdvice)SpringContextHolder.getBean(beanName);
			advice.doThrowing(jp, ex);
		}		
	}

	/**
	 * @return the adviceMap
	 */
	public Map<String, String> getAdviceMap() {
		return adviceMap;
	}

	/**
	 * @param adviceMap the adviceMap to set
	 */
	public void setAdviceMap(Map<String, String> adviceMap) {
		this.adviceMap = adviceMap;
	}
}
