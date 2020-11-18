package edu.conformity.service.impl;

import org.springframework.stereotype.Service;

import edu.conformity.entity.User;
import edu.conformity.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public User getUser(String loginName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkUser(String loginName, String password) {
		return true;
	}

}
