package edu.conformity.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;

@Service
public class ItemWriterService implements ItemWriter<String> {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void write(List<? extends String> items) throws Exception {
		for (String message : items) {
			logger.info("Writing data: " + message);
		}
	}

}
