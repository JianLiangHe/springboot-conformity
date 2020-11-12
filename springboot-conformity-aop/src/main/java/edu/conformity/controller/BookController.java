package edu.conformity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/book/")
public class BookController {

	@RequestMapping(value = "saveJavaBook", method = RequestMethod.POST)
	public void saveJavaBook() {
		System.out.println("新增Book：Java编程思想");
	}
	
	@RequestMapping(value = "saveJSBook", method = RequestMethod.POST)
	public void saveJSBook() {
		System.out.println("新增Book：JS编程思想");
	}
	
	@RequestMapping(value = "saveYellowBook", method = RequestMethod.POST)
	public void saveYellowBook() {
		System.out.println("新增Book：Yellow Book" + (1/0));
	}
	
}
