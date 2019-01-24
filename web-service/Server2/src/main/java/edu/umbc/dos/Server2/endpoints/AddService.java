package edu.umbc.dos.Server2.endpoints;
import edu.umbc.dos.Server2.config.*;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService(serviceName="add", targetNamespace=Server2Config.NAMESPACE_URI)
public class AddService {
		
	@WebMethod
	public int add(@WebParam(name="args1") int arg1, @WebParam(name="args2") int arg2) {
		return arg1 + arg2;
	}	
	
 
}
