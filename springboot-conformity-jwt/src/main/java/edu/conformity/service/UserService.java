package edu.conformity.service;

import edu.conformity.entity.User;

/**
 * 用户业务层
 * @author Administrator
 *
 */
public interface UserService {

	/**
	 * 查询用户信息
	 * @param loginName
	 * @return
	 */
	User getUser(String loginName);
	
	/**
	 * 验证用户信息
	 * @param loginName
	 * @param password
	 * @return
	 */
	boolean checkUser(String loginName, String password);
	
}
