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
	 * 获取HTTP请求
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获取Session对象
	 * 
	 * @return
	 */
	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * 获取HTTP应答
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 根据key获取在Session中保存的对象
	 * 
	 * @param key
	 * @return
	 */
	public Object getSessionAttribute(String key) {
		return this.getSession().getAttribute(key);

	}

	/**
	 * 根据key获取HTTP请求中保存的对象
	 * 
	 * @param key
	 * @return
	 */
	public Object getRequestAttribute(String key) {
		return this.getRequest().getAttribute(key);
	}

	/**
	 * 将对象保存在HTTP请求中
	 * 
	 * @param key
	 * @param object
	 */
	public void setRequestAttribute(String key, Object object) {
		this.getRequest().setAttribute(key, object);
	}

	/**
	 * 将对象保存在Session中
	 * 
	 * @param key
	 * @param object
	 */
	public void setSessionAttribute(String key, Object object) {
		this.getSession().setAttribute(key, object);
	}

	/**
	 * 根据key获取请求的参数值
	 * 
	 * @param key
	 * @return
	 */
	public String getRequestParameter(String key) {
		return this.getRequest().getParameter(key);
	}
}
