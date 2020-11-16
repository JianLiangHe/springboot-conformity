package edu.conformity.controller;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.conformity.task.AsyncTask;
import edu.conformity.task.AsyncTask2;

/**
 * 异步任务controller
 * @author hejianliang
 *
 */
@RestController
@RequestMapping("/asyncTask/")
public class AsyncTaskController {

	@Autowired
	private AsyncTask asyncTask;
	
	@Autowired
	private AsyncTask2 asyncTask2;
	
	@RequestMapping(value = "doTask", method = RequestMethod.GET)
	public String doTask() throws InterruptedException {
		long currentTimeMillsStart = System.currentTimeMillis();
		asyncTask.task1();
		asyncTask.task2();
		asyncTask.task3();
		long currentTimeMillsEnd = System.currentTimeMillis();
		return "task任务总耗时：" + (currentTimeMillsEnd - currentTimeMillsStart) + "ms";
	}
	
	@RequestMapping(value = "doTask2", method = RequestMethod.GET)
	public String doTask2() throws InterruptedException {
		long currentTimeMillsStart = System.currentTimeMillis();
		Future<String> task1 = asyncTask2.task1();
		Future<String> task2 = asyncTask2.task2();
		Future<String> task3 = asyncTask2.task3();
		
		for (;;) {
			if (task1.isDone() && task2.isDone() && task3.isDone()) {
				break;
			}
			Thread.sleep(1000);
		}
		
		long currentTimeMillsEnd = System.currentTimeMillis();
		return "task任务总耗时：" + (currentTimeMillsEnd - currentTimeMillsStart) + "ms";
	}
	
}
