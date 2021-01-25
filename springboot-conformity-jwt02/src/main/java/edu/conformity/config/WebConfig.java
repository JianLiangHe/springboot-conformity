package edu.conformity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import edu.conformity.intercept.JwtInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor())
		// 拦截所有请求，通过判断是否有@JwtToken注解 决定是否需要登录
		.addPathPatterns("/**");
	}

	@Bean
	public JwtInterceptor jwtInterceptor() {
		return new JwtInterceptor();
	}
	
}
