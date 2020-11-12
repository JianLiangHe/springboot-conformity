package edu.conformity.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * book环绕切面
 * @author Administrator
 *
 */
@Aspect
@Component
public class BookAroundAspect {

	@Pointcut("execution(* edu.conformity.controller.BookAroundController.*(..))")
	public void BookAroundAspect() {
		
	}
	
	
	@Around("BookAroundAspect()")
	public void doAround(ProceedingJoinPoint pjp) {
		try {
			/*
			 *环绕通知接受ProceedingJoinPoint作为参数，它来调用被通知的方法。
			 *通知方法中可以做任何的事情，当要将控制权交给被通知的方法时，需要调用ProceedingJoinPoint的proceed()方法。
			 *当你不调用proceed()方法时，将会阻塞被通知方法的访问。 
			 */
			System.out.println("书本上架前检阅");
			pjp.proceed();
			System.out.println("返回通知：书已上架完成");
		} catch (Throwable e) {
			System.out.println("异常通知：书上架失败，存在不合法信息");
			e.printStackTrace();
		}
	}
	
}
