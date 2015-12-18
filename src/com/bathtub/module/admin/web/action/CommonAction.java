package com.bathtub.module.admin.web.action;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.bathtub.core.base.action.BaseAction;
import com.bathtub.core.utils.CodeUtil;
import com.bathtub.core.utils.Constants;
import com.bathtub.module.admin.entity.User;
import com.bathtub.module.admin.service.UserService;

public class CommonAction extends BaseAction
{
	@Autowired()
	@Qualifier("userService")
	private UserService userService;

	private static final long serialVersionUID = 6026790061476395281L;

	// �û���¼�û���������
	private String loginId;

	private String password;

	private User user;

	/**
	 * ��½����
	 */
	public String userLogin() throws IOException
	{
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(loginId, CodeUtil.encode(password, CodeUtil.MD5));
		token.setRememberMe(true);
		try
		{
			currentUser.login(token);
		}
		catch (AuthenticationException e)
		{
			this.getSession().setAttribute(Constants.LOGIN_MESSAGE, e.getLocalizedMessage());
			this.getSession().setAttribute(Constants.LOGIN_FAIL, "true");
			return INPUT;
		}

		if (currentUser.isAuthenticated())
		{
			user = getLoginUser();
			this.getSession().setAttribute(Constants.LOGIN_MESSAGE, "��½�ɹ�");
		}
		else
		{
			this.getSession().setAttribute(Constants.LOGIN_MESSAGE, Constants.LOGIN_ERROR);
			this.getSession().setAttribute(Constants.LOGIN_FAIL, "true");
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * ��վ�˳���¼
	 */
	public String logout()
	{
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated())
		{
			subject.logout(); // session�����٣���SessionListener����session���٣�����Ȩ�޻���
		}
		return SUCCESS;
	}

	public String getLoginId()
	{
		return loginId;
	}

	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

}
