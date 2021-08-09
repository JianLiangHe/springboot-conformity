package edu.conformity.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

@Service
public class ItemProcessorService implements ItemProcessor<String, String> {

	public static String[] message;
	
	/**
	 * 在此处进行数据处理操作，如进行计算、逻辑处理、格式转换等，本例将数据变成全大写数据
	 */
	@Override
	public String process(String item) throws Exception {
		// 存储处理过的数据，可供下一个step使用
		List<String> list = new ArrayList<String>();
		
		if (message != null) {
			for (int i = 0; i < message.length; i++) {
				list.add(message[i]);
			}
		}
		
		list.add(item.toUpperCase());
		message = list.toArray(new String[list.size()]);
		return item.toUpperCase();
	}

}
