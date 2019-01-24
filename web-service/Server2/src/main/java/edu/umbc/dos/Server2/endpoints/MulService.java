package edu.umbc.dos.Server2.endpoints;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import edu.umbc.dos.Server2.config.Server2Config;


@WebService(serviceName="mul", targetNamespace=Server2Config.NAMESPACE_URI)
public class MulService {
	@WebMethod
	public int mul(@WebParam(name="args1") int arg1, @WebParam(name="args2") int arg2) {
		return arg1 * arg2;
	}
}
