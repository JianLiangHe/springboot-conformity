package edu.conformity.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import edu.conformity.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	private static final Logger LOG = LoggerFactory.getLogger(MailServiceImpl.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String from;
	
	@Override
	public void sendSimpleMail(String to, String subject, String content) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(from); // 发件人
		msg.setTo(to); // 收件人
		msg.setSubject(subject); // 主题
		msg.setText(content); // 内容
		
		try {
			mailSender.send(msg);
			LOG.info("邮件已经发送。");
		} catch (Exception e) {
			LOG.error("邮件发送异常。");
			e.printStackTrace();
		}
	}

}
