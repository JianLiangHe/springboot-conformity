package edu.conformity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testEndpoints/")
public class TestEndpoints {

	@RequestMapping(value = "getProduct/{id}", method = RequestMethod.GET)
	public String getProduct(@PathVariable String id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		return "product id: " + id;
	}
	
	@RequestMapping(value = "getOrder/{id}", method = RequestMethod.GET)
	public String getOrder(@PathVariable String id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		return "order id: " + id;
	}
	
}
