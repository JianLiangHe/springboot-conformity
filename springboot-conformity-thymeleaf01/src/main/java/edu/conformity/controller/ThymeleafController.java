package edu.conformity.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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
	
	@RequestMapping(value = "inner", method = RequestMethod.GET)
	public String inner(HttpServletRequest request, Model model) {
		request.setAttribute("requestMessage", "springboot-request");
		request.getSession().setAttribute("sessionMessage", "springboot-session");
		request.getServletContext().setAttribute("applicationMessage", "springboot-application");
		model.addAttribute("url", "www.baidu.com");
		request.setAttribute("url2",
	            "<span style='color:red'>www.baidu.com</span>");
		return "show_inner";
	}
	
	@RequestMapping(value = "show4", method = RequestMethod.GET)
	public String show4(Model model) {
		model.addAttribute("age", 17);
		model.addAttribute("name", "lucy");
		model.addAttribute("name2", "Jack");
		model.addAttribute("hobbies", 2);
		return "show4";
	}
	
	@RequestMapping(value = "show5", method = RequestMethod.GET)
	public String show5(Model model) {
		String[] names = {"Jack", "Jackey", "Lucy", "Lili", "Bilibili", "Dilidili", "Cilicili"};
		model.addAttribute("names", names);
		return "show5";
	}
	
}
