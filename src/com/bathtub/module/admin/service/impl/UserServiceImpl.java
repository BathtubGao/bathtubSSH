package com.bathtub.module.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bathtub.core.base.service.impl.BaseServiceImpl;
import com.bathtub.module.admin.dao.UserDAO;
import com.bathtub.module.admin.entity.User;
import com.bathtub.module.admin.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService
{

	@Autowired() 
	@Qualifier("userDAOIpml")
	private UserDAO userDAO;
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NESTED)
	public User findUserByLoginId(String loginId)
	{
		// 逻辑 删除用户后，存在多个同loginId的用户，因此不能通过只找loginId的方法获得user
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("loginId", loginId);
		List<User> users = userDAO.findListByHqlId("queryUsers", paraMap);
		if (null != users && users.size() == 1)
		{
			User user = users.get(0);
			return user;
		}
		else
		{
			return null;
		}
	}

	@Transactional(propagation = Propagation.NESTED)
	public User checkPassword(String loginId, String password)
	{
		User user = this.findUserByLoginId(loginId);
		if (null != user && user.getPassword().equals(password))
			return user;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NESTED)
	public User findUserByUserName(String name)
	{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("name", name);
		List<User> users = userDAO.findListByHqlId("queryUsers", paraMap);
		if (null != users && users.size() == 1)
		{
			User user = users.get(0);
			return user;
		}
		else
		{
			return null;
		}
	}

}
