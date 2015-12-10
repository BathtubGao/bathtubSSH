package com.bathtub.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bathtub.core.base.service.impl.BaseServiceImpl;
import com.bathtub.test.dao.UserDao;
import com.bathtub.test.entity.User;
import com.bathtub.test.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService
{
	@Autowired() 
	@Qualifier("userDao")
	private UserDao userDao;
	
	public boolean login(String name, String password)
	{
		User user = userDao.getUserByName(name);
		if (null != user && user.getPwd().equals(password))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Transactional
	public void save(User user)
	{
		userDao.save(user);
	}

}
