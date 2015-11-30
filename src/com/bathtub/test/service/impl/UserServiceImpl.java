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
package com.bathtub.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bathtub.test.dao.UserDao;
import com.bathtub.test.entity.User;
import com.bathtub.test.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserDao userDao;

	public boolean login(String name, String password)
	{
		User user = userDao.getUserByName(name);

		if (user != null)
		{
			if (password.equals(user.getPwd()))
			{
				return true;
			}
		}
		return false;
	}

}
