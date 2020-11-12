package edu.conformity.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Book切面类
 * @author Administrator
 *
 */
@Aspect
@Component
public class BookAspect {

	/**
	 * 定义切入点，切入点为 edu.conformity.controller.BookController 所有的公有方法
	 */
	@Pointcut("execution(public * edu.conformity.controller.BookController.*(..))")
	public void BookAspect() {
		
	}
	
	/**
	 * 在连接点之前执行的通知
	 */
	@Order(value = 1)
	@Before("BookAspect()")
	public void doBefore() {
		System.out.println("doBefore=============上架书前需检阅内容");
	}
	
	/**
	 * 在连接点执行之后执行的通知
	 */
	@After("BookAspect()")
	public void doAfter() {
		System.out.println("doAfter=============书已上架完成");
	}
	
	/**
	 * 在连接点执行之后执行的通知（返回通知）
	 */
	@AfterReturning("BookAspect()")
	public void doAfterReturning() {
		System.out.println("doAfterReturning=============返回通知：书已上架完成");
	}
	
	/**
	 * 在连接点执行之后执行的通知（异常通知）
	 */
	@AfterThrowing(value = "BookAspect()", throwing = "ex")
	public void doAfterThrowing(Throwable ex) {
		System.out.println("doAfterThrowing=============目标方法抛出的异常：" + ex);
		System.out.println("doAfterThrowing=============异常通知：书上架失败");
	}
	
}
