package edu.conformity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.conformity.service.MailService;

@RestController
@RequestMapping("/mail/")
public class MailController {

	@Autowired
	private MailService mailService;
	
	@RequestMapping(value = "sendMail", method = RequestMethod.GET)
	public void sendMail() {
		String to = "844131681@qq.com";
		String subject = "Springboot mail test";
		String content = "Springboot mail test content";
		
		mailService.sendSimpleMail(to, subject, content);
	}
	
}
