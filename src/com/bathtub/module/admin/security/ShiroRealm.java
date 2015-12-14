package com.bathtub.module.admin.security;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class ShiroRealm extends AuthorizingRealm
{

	/**
	 * 验证当前登录的Subject
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
	{
		// 实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
		// 两个token的引用都是一样的
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String userName = token.getUsername();
		String password = new String(token.getPassword());
		if (userName != null && !"".equals(userName))
		{
			//此处无需比对,比对的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息  
	        //说白了就是第一个参数填登录用户名,第二个参数填合法的登录密码
	        //这样一来,在随后的登录页面上就只有这里指定的用户和密码才能通过验证  
			if ("bathtub".equals(token.getUsername()) && "99999".equals(password))
			{
				AuthenticationInfo authcInfo = new SimpleAuthenticationInfo("bathtub", password, this.getName());
				return authcInfo;
			}
			else
			{
				throw new AuthenticationException("用户名或密码错误");
			}
		}
		throw new AuthenticationException("未输入用户名");
	}

	/**
	 * 为当前登录的Subject授予角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		// 获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
		String currentUsername = (String) super.getAvailablePrincipal(principals);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		if (null != currentUsername && "bathtub".equals(currentUsername))
		{
			// 添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
			info.addRole("admin");
			// 添加权限
			info.addStringPermission("admin:manage");
			System.out.println("已为用户[mike]赋予了[admin]角色和[admin:manage]权限");
			return info;
		}

		return null;
	}
}
