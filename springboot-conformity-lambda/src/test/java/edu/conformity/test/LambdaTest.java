package edu.conformity.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.conformity.lambdainter.NoReturnMultiParam;
import edu.conformity.lambdainter.NoReturnNoParam;
import edu.conformity.lambdainter.NoReturnOneParam;
import edu.conformity.lambdainter.ReturnMultiParam;
import edu.conformity.lambdainter.ReturnNoParam;
import edu.conformity.lambdainter.ReturnOneParam;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LambdaTest.class)
public class LambdaTest {

	//@Test
	public void noReturnNoParam() {
		NoReturnNoParam noReturnNoParam = () -> {
			System.out.println("NoReturnNoParam");
		};
		noReturnNoParam.method();
	}
	
	//@Test
	public void noReturnOneParam() {
		NoReturnOneParam noReturnOneParam = (int a) -> {
			this.noReturnNoParam();
			System.out.println("noReturnOneParam a:" + a);
		};
		noReturnOneParam.method(6);
	}
	
	//@Test
	public void noReturnMultiParam() {
		NoReturnMultiParam noReturnMultiParam = (int a, int b) -> {
			System.out.println("noReturnMultiParam a:" + a + ", b:"+b);
		};
		noReturnMultiParam.method(2, 3);
	}
	
	//@Test
	public void returnNoParam() {
		ReturnNoParam returnNoParam = () -> {
			System.out.println("returnNoParam");
			return 8;
		};
		int result = returnNoParam.method();
		System.out.println("result:" + 8);
	}
	
	//@Test
	public void returnOneParam() {
		ReturnOneParam returnOneParam = (int a) -> {
			System.out.println("returnOneParam param:" + a);
			return a + 1;
		};
		int result = returnOneParam.method(5);
		System.out.println("result:" + result);
	}
	
	@Test
	public void returnMultiParam() {
		ReturnMultiParam returnMultiParam = (int a, int b) -> {
			System.out.println("returnMultiParam a:" + a + ", b:" + b);
			return a + b;
		};
		int result = returnMultiParam.method(5, 5);
		System.out.println("result: " + result);
	}
	
}
