package edu.conformity.controller;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生产者
 * @author hejianliang
 *
 */
@RestController
@RequestMapping("/producer/")
public class ProducerController {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	@Autowired
	private Queue queue;
	
	@Autowired
	private Topic topic;
	
	@RequestMapping(value = "sendQueue", method = RequestMethod.POST)
	public String sendQueue(
			@RequestParam(value = "message", required = true) String message) {
		this.sendMessage(this.queue, message);
		return "success";
	}
	
	@RequestMapping(value = "sendTopic", method = RequestMethod.POST)
	public String sendTopic(
			@RequestParam(value = "message", required = true) String message) {
		this.sendMessage(this.topic, message);
		return "success";
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
