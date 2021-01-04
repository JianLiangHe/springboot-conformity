package edu.conformity.interceptor;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;

public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	
	
	public AuthInterceptor(String phase) {
		super(phase);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleMessage(SoapMessage arg0) throws Fault {
		// TODO Auto-generated method stub
		
	}

}
