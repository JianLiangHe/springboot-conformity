package edu.conformity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.conformity.service.ProducerService;

/**
 * 生产者
 * @author hejianliang
 *
 */
@RestController
@RequestMapping("/producer/")
public class ProducerController {

	@Autowired
	private ProducerService producerService;
	
	@RequestMapping(value = "sendQueue", method = RequestMethod.POST)
	public String sendQueue(
			@RequestParam(value = "message", required = true) String message) {
		boolean flag = producerService.sendQueue(message);
		return flag ? "success" : "error";
	}
	
	@RequestMapping(value = "sendTopic", method = RequestMethod.POST)
	public String sendTopic(
			@RequestParam(value = "message", required = true) String message) {
		boolean flag = producerService.sendTopic(message);
		return flag ? "success" : "error";
	}
	
}
