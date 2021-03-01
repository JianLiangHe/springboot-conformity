package edu.conformity.test;

import edu.conformity.lambdainter.ReturnOneParam;

/**
 * lambda表达式引用法
 * @author hejianliang
 *
 */
public class LambdaTest3 {

	public static void main(String[] args) {
		ReturnOneParam lambda1 = a -> doubleNum(a);
		System.out.println(lambda1.method(3));
		
		// 引用了已经实现的doubleNum方法
		ReturnOneParam lambda2 = LambdaTest3::doubleNum;
		System.out.println(lambda2.method(5));
		
		LambdaTest3 lam = new LambdaTest3();
		
		// 引用了已经实现的addTwo方法
		ReturnOneParam lambda4 = lam::addTwo;
		System.out.println(lambda4.method(6));
	}
	
	/**
	 * 要求
	 * 1.参数数量和类型要与接口中定义的一致
	 * 2.返回值类型要与接口中定义的一致
	 * @param a
	 * @return
	 */
	public static int doubleNum(int a) {
		return a * 2;
	}
	
	public int addTwo(int a) {
		return a + 2;
	}
	
}
