package edu.conformity.service;

import javax.jms.Destination;
import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时队列消息服务
 * @author hejianliang
 *
 */
@Component
@EnableScheduling
public class ScheduledProducerService {

	private final Logger LOG = LoggerFactory.getLogger(ProducerService.class);
	
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	@Autowired
	private Queue queue;
	
	/**
	 * 定时推送的消息
	 */
	private String scheduledMessage = "{data:[]}";
	
	/**
	 * 发送定时队列消息
	 * @param message
	 * @return
	 */
	@Scheduled(fixedDelay = 10000) //该注解修改的方法不能有参数
	public void sendQueueByScheduled() {
		try {
			this.sendMessage(this.queue, this.scheduledMessage);
			LOG.info("发送定时队列消息：[" + this.scheduledMessage +"]，成功");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("发送定时队列消息：[" + this.scheduledMessage +"]，失败。" + e.getMessage());
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
