package edu.conformity.test;

/**
 * lambda表达式创建线程
 * @author hejianliang
 *
 */
public class LambdaTest5 {

	public static void main(String[] args) {
		Thread t = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(2 + ":" + i);
			}
		});
		
		t.start();
	}
	
}
