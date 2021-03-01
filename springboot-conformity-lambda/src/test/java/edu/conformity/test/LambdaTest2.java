package edu.conformity.test;

import edu.conformity.lambdainter.NoReturnMultiParam;
import edu.conformity.lambdainter.NoReturnOneParam;
import edu.conformity.lambdainter.ReturnOneParam;

public class LambdaTest2 {

	public static void main(String[] args) {
		// 1.简化参数类型，可以不写参数类型，但是必须所有参数都不写
		NoReturnMultiParam lambda1 = (a, b) -> {
			System.out.println("简化参数类型");
		};
		lambda1.method(1, 2);
		
		// 2.简化参数小括号，如果只有一个参数则可以省略参数小括号
		NoReturnOneParam lambda2 = a -> {
			System.out.println("简化参数小括号");
		};
		lambda2.method(2);
		
		// 3.简化方法大括号，如果方法只有一条浴巾，则可以省略方法大括号
		NoReturnOneParam lambda3 = a -> System.out.println("省略方法大括号");
		lambda3.method(2);
		
		// 4.如果方法体只有一条语句，并且是return语句，则可以省略方法大括号
		ReturnOneParam lambda4 = a -> a + a;
		int result = lambda4.method(5);
		System.out.println("lambda4 result: " + result);
	}
	
}
