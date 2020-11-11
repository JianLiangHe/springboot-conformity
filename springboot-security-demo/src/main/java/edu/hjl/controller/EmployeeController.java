package edu.hjl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee/")
public class EmployeeController {

	@RequestMapping(value = "greeting", method = RequestMethod.GET)
	public String greeting() {
		return "hello world";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "login success";
	}
	
}
