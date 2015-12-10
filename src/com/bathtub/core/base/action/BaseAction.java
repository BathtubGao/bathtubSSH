package com.bathtub.core.base.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport
{

	private static final long serialVersionUID = -8278362090314253360L;

	protected static final String RELOAD = "reload";

	protected static final String VIEW = "view";

	protected static final String EDIT = "edit";

	protected static final String LIST = "list";

	protected static final String JSON_ERROR = "Json Converted Error";
	
	
	public BaseAction() {
	}

	/**
	 * ��ȡHTTP����
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * ��ȡSession����
	 * 
	 * @return
	 */
	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * ��ȡHTTPӦ��
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * ����key��ȡ��Session�б���Ķ���
	 * 
	 * @param key
	 * @return
	 */
	public Object getSessionAttribute(String key) {
		return this.getSession().getAttribute(key);

	}

	/**
	 * ����key��ȡHTTP�����б���Ķ���
	 * 
	 * @param key
	 * @return
	 */
	public Object getRequestAttribute(String key) {
		return this.getRequest().getAttribute(key);
	}

	/**
	 * �����󱣴���HTTP������
	 * 
	 * @param key
	 * @param object
	 */
	public void setRequestAttribute(String key, Object object) {
		this.getRequest().setAttribute(key, object);
	}

	/**
	 * �����󱣴���Session��
	 * 
	 * @param key
	 * @param object
	 */
	public void setSessionAttribute(String key, Object object) {
		this.getSession().setAttribute(key, object);
	}

	/**
	 * ����key��ȡ����Ĳ���ֵ
	 * 
	 * @param key
	 * @return
	 */
	public String getRequestParameter(String key) {
		return this.getRequest().getParameter(key);
	}
}
