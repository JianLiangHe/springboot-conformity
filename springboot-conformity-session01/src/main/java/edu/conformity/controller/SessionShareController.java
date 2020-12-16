package edu.conformity.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping(value = "/sessionShare/")
public class SessionShareController {

	@Value("${server.port}")
	private Integer port;
	
	@ApiOperation("set")
	@RequestMapping(value = "set", method = RequestMethod.POST)
	public String set(HttpSession session) {
		session.setAttribute("user", "Jackey");
		return String.valueOf(port);
	}
	
	@ApiOperation("get")
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public String get(HttpSession session) {
		return "user: " + session.getAttribute("user") + ", port: " + port;
	}
	
}
