package edu.conformity.service;

public interface MailService {

	/**
	 * 发送简单邮件
	 * @param to
	 * @param subject
	 * @param content
	 */
	void sendSimpleMail(String to, String subject, String content);
	
}
