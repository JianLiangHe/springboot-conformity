package edu.conformity.service.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Component;

import edu.conformity.service.CommonService;

@WebService(
		serviceName = "CommonService",
		targetNamespace = "http://service.conformity.edu/",
		endpointInterface = "edu.conformity.service.CommonService" // 接口地址
		)
@Component
public class CommonServiceImpl implements CommonService {

	@Override
	public String sayHello(String username) {
		String str = "Hello," + username;
		return str;
	}

}
