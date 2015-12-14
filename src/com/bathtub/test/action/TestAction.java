/**************************************************************************/
/*                                                                        */
/* Copyright (c) 2012-2013 Jiangsu Hongxin System Integration Co., Ltd.   */
/*                                                                        */
/* PROPRIETARY RIGHTS of Jiangsu Hongxin are involved in the 　　　　　　 */
/* subject matter of this material.  All manufacturing, reproduction, use,*/
/* and sales rights pertaining to this subject matter are governed by the */
/* license agreement. The recipient of this software implicitly accepts   */
/* the terms of the license.                                              */
/* 本软件文档资料是江苏鸿信公司的资产,任何人士阅读和使用本资料必须获得    */
/* 相应的书面授权,承担保密责任和接受相应的法律约束.                       */
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
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中
			currentUser.login(token);
			
			//验证是否登录成功  
			if (currentUser.isAuthenticated())
			{
				this.getSession().setAttribute("LOGIN_MESSAGE", username + "登陆成功");
			}
		}
		catch (AuthenticationException e)
		{
			this.setRequestAttribute("LOGIN_MESSAGE", "登陆失败");
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
		this.setRequestAttribute("message", "保存成功！");
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
