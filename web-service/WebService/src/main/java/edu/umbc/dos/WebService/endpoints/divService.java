package edu.umbc.dos.WebService.endpoints;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import edu.umbc.dos.WebService.config.WebConfig;


@WebService(serviceName="div", targetNamespace=WebConfig.NAMESPACE_URI)
public class divService {
	@WebMethod
	public int div(@WebParam(name="args1") int arg1, @WebParam(name="args2") int arg2) {
		return arg1 / arg2;
	}
	
}
