package edu.conformity.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * topic模式的消费者
 * @author hejianliang
 *
 */
@Component
public class TopicConsumerListener {

	private final Logger LOG = LoggerFactory.getLogger(TopicConsumerListener.class);
	
	@JmsListener(destination = "${spring.activemq.topic-name}", containerFactory = "topicListener")
	public void readActiveTopic(String message) {
		LOG.info("消费者接收订阅消息:[" + message + "]");
	}
	
}
