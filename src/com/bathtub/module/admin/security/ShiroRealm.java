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
	 * ��֤��ǰ��¼��Subject
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
	{
		// ʵ�������authcToken�Ǵ�LoginController����currentUser.login(token)��������
		// ����token�����ö���һ����
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String userName = token.getUsername();
		String password = new String(token.getPassword());
		if (userName != null && !"".equals(userName))
		{
			//�˴�����ȶ�,�ȶԵ��߼�Shiro����,����ֻ�践��һ����������ص���ȷ����֤��Ϣ  
	        //˵���˾��ǵ�һ���������¼�û���,�ڶ���������Ϸ��ĵ�¼����
	        //����һ��,�����ĵ�¼ҳ���Ͼ�ֻ������ָ�����û����������ͨ����֤  
			if ("bathtub".equals(token.getUsername()) && "99999".equals(password))
			{
				AuthenticationInfo authcInfo = new SimpleAuthenticationInfo("bathtub", password, this.getName());
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
		String currentUsername = (String) super.getAvailablePrincipal(principals);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		if (null != currentUsername && "bathtub".equals(currentUsername))
		{
			// ���һ����ɫ,�������������ϵ����,����֤�����û�ӵ��admin��ɫ
			info.addRole("admin");
			// ���Ȩ��
			info.addStringPermission("admin:manage");
			System.out.println("��Ϊ�û�[mike]������[admin]��ɫ��[admin:manage]Ȩ��");
			return info;
		}

		return null;
	}
}
