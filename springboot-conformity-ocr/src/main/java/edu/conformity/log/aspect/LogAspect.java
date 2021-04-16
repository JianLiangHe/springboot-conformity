package edu.conformity.log.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import edu.conformity.log.entity.Log;
import edu.conformity.log.service.LogService;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {

	private static Logger logger = LoggerFactory.getLogger(LogAspect.class);
	private static final ThreadLocal<Date> BEGIN_TIME_THREAD_LOCAL = new NamedThreadLocal<Date>("ThreadLocal beginTime");
	private static final ThreadLocal<Log> LOG_THREAD_LOCAL = new NamedThreadLocal<Log>("ThreadLocal log");
	
	@Autowired
	private ThreadPoolTaskExecutor threadPoolExecutor;
	
	@Autowired(required = false)
	private HttpServletRequest request;
	
	@Autowired
	private LogService logService;
	
	/**
	 * service层切面
	 */
	@Pointcut("@annotation(edu.conformity.log.annotation.ServiceLog)")
	public void serviceAspect() {
		
	}
	
	/**
	 * controller层切面
	 */
	@Pointcut("@annotation(edu.conformity.log.annotation.ControllerLog)")
	public void controllerAspect() {
		
	}
	
	@Before("controllerAspect()")
	public void doBefore() {
		logger.info("====进入日志切面前置通知====");
		Date beginTime = new Date();
		// 线程绑定变量（该数据只有当前请求的现场可见）
		BEGIN_TIME_THREAD_LOCAL.set(beginTime);
		// 日志级别为debug
		if (logger.isDebugEnabled()) {
			logger.debug("开始计时：{} URI：{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(beginTime), request.getRequestURI());
		}
	}
	
	public void doAfter(JoinPoint joinPoint) {
		
	}
	
}
