package edu.conformity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/")
public class DemoController {

	@RequestMapping(value = "hello", method = RequestMethod.POST)
	public String hello(
			@RequestParam(value = "name", required = true) String name) {
		return "hello " + name;
	}
	
}
