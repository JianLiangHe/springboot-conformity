package edu.conformity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.conformity.service.MethodService;

@RestController
@RequestMapping("/v1/user/")
public class UserController {

	@Autowired
	private MethodService methodService;

	@RequestMapping(value = "dba", method = RequestMethod.GET)
	public String dba() {
		return "hello dba";
	}

	@RequestMapping(value = "admin", method =RequestMethod.GET)
	public String admin() {
		return "hello admin";
	}
	
	@RequestMapping(value = "user", method =RequestMethod.GET)
	public String user() {
		return "hello user";
	}
	
	
}
