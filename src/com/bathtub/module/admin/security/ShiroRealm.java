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
	 * ��֤��ǰ��¼��Subject
	 */
	@Override
	@Transactional(readOnly = true)
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
	{
		// ʵ�������authcToken�Ǵ�LoginController����currentUser.login(token)��������
		// ����token�����ö���һ����
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
				// �˴�����ȶ�,�ȶԵ��߼�Shiro����,����ֻ�践��һ����������ص���ȷ����֤��Ϣ
				// ˵���˾��ǵ�һ���������¼�û���,�ڶ���������Ϸ��ĵ�¼����
				// ����һ��,�����ĵ�¼ҳ���Ͼ�ֻ������ָ�����û����������ͨ����֤
				AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user, password, this.getName());
				return authcInfo;
			}
			else
			{
				throw new AuthenticationException("�û������������");
			}
		}
		throw new AuthenticationException("δ�����û���");
	}

	/**
	 * Ϊ��ǰ��¼��Subject�����ɫ��Ȩ��
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		// ��ȡ��ǰ��¼���û���,�ȼ���(String)principals.fromRealm(this.getName()).iterator().next()
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
	 * ��һЩ���ݷŵ�ShiroSession��,�Ա��������ط�ʹ��
	 * @see ����Controller,ʹ��ʱֱ����HttpSession.getAttribute(key)�Ϳ���ȡ��
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
