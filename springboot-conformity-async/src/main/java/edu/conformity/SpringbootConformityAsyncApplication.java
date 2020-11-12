package edu.conformity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync //启动扫描Async注解
@SpringBootApplication
public class SpringbootConformityAsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootConformityAsyncApplication.class, args);
	}
	
}
