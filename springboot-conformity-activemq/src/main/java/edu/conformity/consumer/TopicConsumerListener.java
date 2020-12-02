package edu.conformity.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * topic模式的消费者
 * @author hejianliang
 *
 */
@Component
public class TopicConsumerListener {

	@JmsListener(destination = "${spring.activemq.topic-name}", containerFactory = "topicListener")
	public void readActiveTopic(String message) {
		System.out.println("topic接收到：" + message);
	}
	
}
