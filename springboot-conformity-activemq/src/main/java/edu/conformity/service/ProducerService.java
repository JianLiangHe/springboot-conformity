package edu.conformity.service;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProducerService {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	@Autowired
	private Queue queue;
	
	@Autowired
	private Topic topic;
	
	/**
	 * 发送队列消息
	 * @param message
	 * @return
	 */
	public boolean sendQueue(String message) {
		boolean flag = false;
		
		try {
			this.sendMessage(this.queue, message);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return flag;
		}
	}
	
	/**
	 * 发布订阅消息
	 * @param message
	 * @return
	 */
	public boolean sendTopic(String message) {
		boolean flag = false;
		
		try {
			this.sendMessage(this.topic, message);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return flag;
		}
	}
	
	/**
	 * 发送消息
	 * @param destination 是发送到的队列
	 * @param message 是待发送的消息
	 */
	private void sendMessage(Destination destination, final String message) {
		jmsMessagingTemplate.convertAndSend(destination, message);
	}
	
}
