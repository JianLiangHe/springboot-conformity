package edu.conformity.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class CommonClient {

	public static void main(String[] args) {
		sayHello();
	}
	
	private static void sayHello() {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://192.168.3.103:8080/services/CommonService?wsdl");
		
		Object[] objects = new Object[0];

		try {
			objects = client.invoke("sayHello", "hejianliang");
			System.out.println("返回数据：" + objects[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
