package edu.conformity.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.conformity.pojo.Book;

@Controller
@RequestMapping("/thymeleaf/")
public class ThymeleafController {

	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(Model model) {
		model.addAttribute("uid", "123456");
		model.addAttribute("name", "Jackey");
		model.addAttribute("age", "<span style='color: pink;'>18</span>");
		return "show";
	}
	
	@RequestMapping(value = "show2", method = RequestMethod.GET)
	public String show2(Model model) {
		Book book = new Book("123", "Java思想编程", "书内容。。。");
		model.addAttribute("book", book);
		return "show2";
	}
	
	@RequestMapping(value = "show3", method = RequestMethod.GET)
	public String set(Model model) {
		Set<String> allNames = new HashSet<String>();
		String[] ids = {"1", "2", "3", "4", "5"};
		for (String str : ids) {
			allNames.add("Book - " + str);
		}
		
		model.addAttribute("names", allNames);
		model.addAttribute("ids", ids);
		model.addAttribute("mydate", new Date());
		
		return "show3";
	}
	
}
