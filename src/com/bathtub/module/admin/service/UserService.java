package com.bathtub.module.admin.service;

import com.bathtub.core.base.service.BaseService;
import com.bathtub.module.admin.entity.User;

public interface UserService extends BaseService 
{

	/**
	 * �����û�ID�����û�
	 * 
	 * @param loginId
	 * @return
	 * @return User
	 * @throws
	 */
	public User findUserByLoginId(String loginId);
	
	/**
	 * �����û�ID�����û�
	 * 
	 * @param loginId
	 * @return
	 * @return User
	 * @throws
	 */
	public User findUserByUserName(String loginId);
	
	/**
	 * ��֤�û�
	 * 
	 * @param loginId
	 * @param password
	 * @return Boolean
	 */
	public User checkPassword(String loginId, String password);
}
