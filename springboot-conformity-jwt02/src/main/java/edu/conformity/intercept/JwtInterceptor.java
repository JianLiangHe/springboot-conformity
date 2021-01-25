package edu.conformity.intercept;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.conformity.annotation.JwtToken;
import edu.conformity.util.JwtUtil;

public class JwtInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 从http请求头中取出token
		String token = request.getHeader("token");
		// 如果不是映射到方法直接通过
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		
		// 检查有没有需要用户权限的注解
		if (method.isAnnotationPresent(JwtToken.class)) {
			JwtToken jwtToken = method.getAnnotation(JwtToken.class);
			if (jwtToken.required()) {
				// 执行认证
				if (token == null) {
					throw new RuntimeException("无token，请重新登录！");
				}
				// 获取token中的userId
				String userId = JwtUtil.getUserId(token);
				System.out.println("用户id：" + userId);
				
				// 验证token
				JwtUtil.checkSign(token);
			}
		}
		return true;
	}

}
