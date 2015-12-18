package com.bathtub.module.admin.service;

import com.bathtub.core.base.service.BaseService;
import com.bathtub.module.admin.entity.User;

public interface UserService extends BaseService 
{

	/**
	 * 根据用户ID查找用户
	 * 
	 * @param loginId
	 * @return
	 * @return User
	 * @throws
	 */
	public User findUserByLoginId(String loginId);
	
	/**
	 * 根据用户ID查找用户
	 * 
	 * @param loginId
	 * @return
	 * @return User
	 * @throws
	 */
	public User findUserByUserName(String loginId);
	
	/**
	 * 验证用户
	 * 
	 * @param loginId
	 * @param password
	 * @return Boolean
	 */
	public User checkPassword(String loginId, String password);
}
