/**************************************************************************/
/*                                                                        */
/* Copyright (c) 2012-2013 Jiangsu Hongxin System Integration Co., Ltd.   */
/*                                                                        */
/* PROPRIETARY RIGHTS of Jiangsu Hongxin are involved in the ������������ */
/* subject matter of this material.  All manufacturing, reproduction, use,*/
/* and sales rights pertaining to this subject matter are governed by the */
/* license agreement. The recipient of this software implicitly accepts   */
/* the terms of the license.                                              */
/* ������ĵ������ǽ��պ��Ź�˾���ʲ�,�κ���ʿ�Ķ���ʹ�ñ����ϱ�����    */
/* ��Ӧ��������Ȩ,�е��������κͽ�����Ӧ�ķ���Լ��.                       */
/*                                                                        */
/**************************************************************************/
package com.bathtub.test.action;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.bathtub.core.base.action.BaseAction;
import com.bathtub.test.entity.User;
import com.bathtub.test.service.UserService;

public class TestAction extends BaseAction
{
	@Autowired
	private UserService userService;

	private String username;

	private String password;

	public String userLogin() throws Exception
	{
		
		try
		{
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
			token.setRememberMe(true);
			// �ڵ�����login������,SecurityManager���յ�AuthenticationToken,�����䷢�͸������õ�Realmִ�б������֤���
			// ÿ��Realm�����ڱ�Ҫʱ���ύ��AuthenticationTokens������Ӧ
			// ������һ���ڵ���login(token)����ʱ,�����ߵ�MyRealm.doGetAuthenticationInfo()������
			currentUser.login(token);
			
			//��֤�Ƿ��¼�ɹ�  
			if (currentUser.isAuthenticated())
			{
				this.getSession().setAttribute("LOGIN_MESSAGE", username + "��½�ɹ�");
			}
		}
		catch (AuthenticationException e)
		{
			this.setRequestAttribute("LOGIN_MESSAGE", "��½ʧ��");
			return ERROR;
		}

		
		return SUCCESS;
	}

	public String save()
	{
		User user = new User();
		user.setUsername(username);
		user.setPwd(password);
		userService.save(user);
		this.setRequestAttribute("message", "����ɹ���");
		return SUCCESS;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

}
