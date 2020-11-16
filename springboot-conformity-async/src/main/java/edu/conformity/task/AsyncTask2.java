package edu.conformity.task;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 * 异步任务类
 * @author hejianliang
 *
 */
@Component
public class AsyncTask2 {

	@Async
	public Future<String> task1() throws InterruptedException {
		long currentTimeMillsStart = System.currentTimeMillis();
		Thread.sleep(1000);
		long currentTimeMillsEnd = System.currentTimeMillis();
		System.out.println("===task1任务耗时：" + (currentTimeMillsEnd - currentTimeMillsStart) + "ms");
		return new AsyncResult<String>("task1执行完毕");
	}
	
	@Async
	public Future<String> task2() throws InterruptedException {
		long currentTimeMillsStart = System.currentTimeMillis();
		Thread.sleep(1000);
		long currentTimeMillsEnd = System.currentTimeMillis();
		System.out.println("===task2任务耗时：" + (currentTimeMillsEnd - currentTimeMillsStart) + "ms");
		return new AsyncResult<String>("task2执行完毕");
	}
	
	@Async
	public Future<String> task3() throws InterruptedException {
		long currentTimeMillsStart = System.currentTimeMillis();
		Thread.sleep(1000);
		long currentTimeMillsEnd = System.currentTimeMillis();
		System.out.println("===task3任务耗时：" + (currentTimeMillsEnd - currentTimeMillsStart) + "ms");
		return new AsyncResult<String>("task3执行完毕");
	}
	
}
