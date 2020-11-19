package edu.conformity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.conformity.entity.User;
import edu.conformity.service.UserService;

@RestController
@RequestMapping("/user/")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "findAll", method = RequestMethod.GET)
	public List<User> findAll() {
		return userService.findAll();
	}
	
}
