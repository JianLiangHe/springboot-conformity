package edu.conformity.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.conformity.service.CommonService;

@Configuration
public class CxfConfig {

	@Autowired
	private Bus bus;
	
	@Autowired
	private CommonService commonService;
	
	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, commonService);
		endpoint.publish("/CommonService");
		return endpoint;
	}
	
}
