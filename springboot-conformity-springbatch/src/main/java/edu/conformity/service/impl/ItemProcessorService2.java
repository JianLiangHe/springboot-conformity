package edu.conformity.service.impl;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

@Service
public class ItemProcessorService2 implements ItemProcessor<String, String> {

	@Override
	public String process(String item) throws Exception {
		return item + " hejianliang";
	}
	
}
