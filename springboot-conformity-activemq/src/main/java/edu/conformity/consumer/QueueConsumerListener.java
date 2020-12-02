package edu.conformity.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Queue模式的消费者
 * @author hejianliang
 *
 */
@Component
public class QueueConsumerListener {

	@JmsListener(destination = "${spring.active.queue-name}", containerFactory = "queueListener")
	public void readActiveQueue(String message) {
		System.out.println("queue接收到：" + message);
	}
	
}
