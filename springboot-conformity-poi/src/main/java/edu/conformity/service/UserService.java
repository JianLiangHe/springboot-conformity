package edu.conformity.service;

import java.util.List;

import edu.conformity.pojo.User;

public interface UserService {

	List<User> findAll();
	
	void save(List<User> userList);
	
}
