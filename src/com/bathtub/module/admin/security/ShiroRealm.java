package com.bathtub.module.admin.security;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.transaction.annotation.Transactional;

import com.bathtub.core.utils.Constants;
import com.bathtub.core.utils.SpringContextHolder;
import com.bathtub.module.admin.entity.User;
import com.bathtub.module.admin.service.UserService;

public class ShiroRealm extends AuthorizingRealm
{
	private UserService userService;

	public void initService(){
		userService = (UserService)SpringContextHolder.getBean("userService");
	}
	
	
	/**
	 * 验证当前登录的Subject
	 */
	@Override
	@Transactional(readOnly = true)
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
	{
		// 实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
		// 两个token的引用都是一样的
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String userName = token.getUsername();
		String password = new String(token.getPassword());
		if (userName != null && !"".equals(userName))
		{
			User user = userService.checkPassword(userName, password);

			if (user != null)
			{
				user.setLogTime(new Date());
				this.setSession(Constants.CURR_USER, user);
				this.setSession(Constants.LOGIN_USER_ID, user.getId());
				// 此处无需比对,比对的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息
				// 说白了就是第一个参数填登录用户名,第二个参数填合法的登录密码
				// 这样一来,在随后的登录页面上就只有这里指定的用户和密码才能通过验证
				AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user, password, this.getName());
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
		User user = (User) super.getAvailablePrincipal(principals);
		if (user != null)
		{

		}

		return null;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 */
	private void setSession(Object key, Object value)
	{
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser)
		{
			Session session = currentUser.getSession();
			if (null != session)
			{
				session.setAttribute(key, value);
			}
		}
	}
}
