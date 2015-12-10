package com.bathtub.test.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bathtub.core.base.dao.impl.BaseDaoImpl;
import com.bathtub.test.dao.UserDao;
import com.bathtub.test.entity.User;

@Component("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao
{

	public User getUserByName(String name)
	{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		//paraMap.put("username", name);
		List<User> userList = this.findListByHqlId("findUserByMap", paraMap);
		if (userList.size() > 0)
		{
			return userList.get(0);
		}
		else
		{
			return null;
		}
	}

	@Transactional
	public void save(User user)
	{
		user.setId(null);
		this.saveOrUpdateObject(user);
	}

}
