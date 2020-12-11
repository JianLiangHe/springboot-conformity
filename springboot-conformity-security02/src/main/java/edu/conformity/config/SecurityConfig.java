package edu.conformity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * spring security配置类
 * @author hejianliang
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 登录处理
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/v1/index/index").hasRole("admin")
			.antMatchers("/", "/home").permitAll()
			.antMatchers("/v1/index/test").permitAll()
			.anyRequest().permitAll()
			.and().anonymous()
			.and().formLogin()
			.and().csrf().disable()
			.authorizeRequests();
	}

	/**
	 * 忽略拦截
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// 设置拦截忽略url，会直接过滤该url，不会经过spring security过滤器链拦截
		web.ignoring().antMatchers("/v1/index/test");
		// 设置拦截忽略文件夹，可以对静态资源放行
		web.ignoring().antMatchers("/css/**", "/js/**");
	}
	
}
