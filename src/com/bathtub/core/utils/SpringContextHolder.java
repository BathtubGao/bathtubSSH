package com.bathtub.core.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * �Ծ�̬��������Spring ApplicationContext, �����κδ����κεط��κ�ʱ����ȡ��ApplicaitonContext.
 * 
 */
public class SpringContextHolder implements ApplicationContextAware
{
	private static ApplicationContext applicationContext;
	
	/**
	 * ʵ��ApplicationContextAware�ӿڵ�contextע�뺯��, ������뾲̬����.
	 * 
	 * @param applicationContext   ��ǰSpring������ApplicationContext
	 * 
	 */
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextHolder.applicationContext = applicationContext;
	}
	
	/**
	 * ȡ�ô洢�ھ�̬�����е�ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}
	
	/**
	 * �ж�ApplicationContext�Ƿ�ע��
	 * 
	 */
	private static void checkApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicaitonContextδע��,����applicationContext.xml�ж���SpringContextHolder");
		}
	}

	/**
	 * ����Bean�����Ʋ���Bean
	 * 
	 * @param beanName bean����
	 */
	@SuppressWarnings("unchecked")
	public static <T>T getBean(String beanName){
		checkApplicationContext();
		return (T)applicationContext.getBean(beanName);
	}
	
	/**
	 * ����Bean��������Bean
	 * @param clazz   Bean�����
	 */
	@SuppressWarnings("unchecked")
	public static <T>T getBean(Class<T> clazz){
		checkApplicationContext();
		return (T)applicationContext.getBeansOfType(clazz);
	}
	
	/**
	 * ����ȫ����bean
	 */
	public static Map<String,Object> getAllBeans(){
		String[] beanNames = applicationContext.getBeanDefinitionNames();
		Map<String,Object> beanMap = new HashMap<String,Object>();
		for(String beanName : beanNames){
			if(beanName.indexOf("ProxyTemplate")!=-1)
				continue;
			Object bean = applicationContext.getBean(beanName);
			beanMap.put(beanName, bean);
		}
		return beanMap;
	}
}
