package edu.conformity.service;

import java.util.List;

import edu.conformity.entity.User;

/**
 * User业务层
 * @author Administrator
 *
 */
public interface UserService {

	/**
	 * 查询所有user
	 * @return
	 */
	List<User> findAll();
	
}
