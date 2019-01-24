package edu.umbc.dos.WebService.lb;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.google.gson.Gson;

import edu.umbc.dos.WebService.endpoints.ServerInfo;

@WebService
public class ServerComm {
  
	public static Map<String, ServerInfo> operations = new ConcurrentHashMap<>();
    
    private ServerInfo putInfo(String x, int y, String[] z) {
    	ServerInfo e = new ServerInfo();
    	e.setIp(x);
    	e.setPort(y);
        e.setServiceName(z);
        return e;    
    }
    

	@WebMethod
	public void Alive(@WebParam(name="ipAddress") String ipAddress, @WebParam(name="port") int port, @WebParam(name="serviceName") String servicenames) {
		Gson g = new Gson();
		String[] arr = g.fromJson(servicenames, String[].class);
		ServerInfo s = putInfo(ipAddress, port, arr);
		String key = ipAddress+"_"+port;
		System.out.println("Putting server info with key "+ key +" and services "+ arr[0] );
		operations.put(key, s);
	}

	@WebMethod
	public void Dead(@WebParam(name="ipAddress") String ipAddress, @WebParam(name="port") int port) {
		String key = ipAddress+"_"+port;
		operations.remove(key);
	}

	
	 
	
}

