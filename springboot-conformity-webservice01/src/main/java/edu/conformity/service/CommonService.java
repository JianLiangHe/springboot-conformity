package edu.conformity.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(
		name = "CommonService", // 暴露服务名称
		targetNamespace = "http://service.conformity.edu/" // 命名空间，一般是接口的倒叙
		)
public interface CommonService {

	@WebMethod
	@WebResult(name = "String", targetNamespace = "")
	String sayHello(@WebParam(name = "username") String username);
	
}
