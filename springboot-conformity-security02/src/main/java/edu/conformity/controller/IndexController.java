package edu.conformity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/index/")
public class IndexController {

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "hello spring security!";
	}
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String text() {
		return "test不拦截";
	}
	
}
