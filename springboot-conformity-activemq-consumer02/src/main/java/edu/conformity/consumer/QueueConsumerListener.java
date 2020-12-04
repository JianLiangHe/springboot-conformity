package edu.conformity.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Queue模式的消费者
 * @author hejianliang
 *
 */
@Component
public class QueueConsumerListener {

	private final Logger LOG = LoggerFactory.getLogger(QueueConsumerListener.class);
	
	@JmsListener(destination = "${spring.activemq.queue-name}", containerFactory = "queueListener")
	public void readActiveQueue(String message) {
		LOG.info("消费者02接收队列消息:[" + message + "]");
	}
	
}
