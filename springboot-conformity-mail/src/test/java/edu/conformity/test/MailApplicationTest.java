package edu.conformity.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.conformity.service.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MailApplicationTest.class)
public class MailApplicationTest {

	@Resource
	private MailService mailService;
	
	@Test
	public void sendMail() {
		String to = "844131681@qq.com";
		String subject = "Springboot mail test";
		String content = "Springboot mail test content";
		
		mailService.sendSimpleMail(to, subject, content);
	}
	
}
