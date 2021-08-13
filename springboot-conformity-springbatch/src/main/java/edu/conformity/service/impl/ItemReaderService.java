package edu.conformity.service.impl;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Service;

/**
 * 
 * @author hejianliang
 *
 */
@Service
public class ItemReaderService implements ItemReader {
    
	//在此处进行数据读取操作，如从数据库查询、从文件中读取、从变量中读取等，本例从变量中读取；
    private String[] message = {
    		"模拟消息列表数据---1",
    		"模拟消息列表数据---2",
    		"模拟消息列表数据---3",
    		"模拟消息列表数据---4",
    		"模拟消息列表数据---5",
    		"模拟消息列表数据---6",
    		"模拟消息列表数据---7",
    		"模拟消息列表数据---8"
    };
    
    private int count = 0;

    public String read() throws Exception {
        if (count < message.length) {
            return message[count++];
        } else {
        	count = 0;
        }
        return null;
    }
    
}
