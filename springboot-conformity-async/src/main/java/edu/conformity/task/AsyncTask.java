package edu.conformity.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 异步任务类
 * @author hejianliang
 *
 */
@Component
public class AsyncTask {

	@Async
	public void task1() throws InterruptedException {
		long currentTimeMillsStart = System.currentTimeMillis();
		Thread.sleep(1000);
		long currentTimeMillsEnd = System.currentTimeMillis();
		System.out.println("===task1任务耗时：" + (currentTimeMillsEnd - currentTimeMillsStart) + "ms");
	}
	
	@Async
	public void task2() throws InterruptedException {
		long currentTimeMillsStart = System.currentTimeMillis();
		Thread.sleep(1000);
		long currentTimeMillsEnd = System.currentTimeMillis();
		System.out.println("===task2任务耗时：" + (currentTimeMillsEnd - currentTimeMillsStart) + "ms");
	}
	
	@Async
	public void task3() throws InterruptedException {
		long currentTimeMillsStart = System.currentTimeMillis();
		Thread.sleep(1000);
		long currentTimeMillsEnd = System.currentTimeMillis();
		System.out.println("===task3任务耗时：" + (currentTimeMillsEnd - currentTimeMillsStart) + "ms");
	}
	
}
