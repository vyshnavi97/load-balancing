package edu.umbc.dos.WebService.lb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import edu.umbc.dos.WebService.endpoints.ServerInfo;

@WebService
public class Clientcomm {
	
	
	@WebMethod
	public String findService(@WebParam(name="serviceName") String serviceName) {
		Iterator<String> iter = ServerComm.operations.keySet().iterator();
		List<ServerInfo> list = new ArrayList<>();
		
		while( iter.hasNext() ) {
			
			String key = iter.next();
			ServerInfo s = ServerComm.operations.get(key);
			
			if ( Arrays.asList(s.getServiceName()).contains(serviceName) ) {
				list.add(s);
			}			
			
		}
		
//		list.forEach(s -> System.out.println(s.toString()));
		
		Collections.sort(list, new Comparator<ServerInfo>() {
			@Override
			public int compare(ServerInfo arg0, ServerInfo arg1) {
				int val = Integer.valueOf(arg0.getCount()).compareTo(arg1.getCount());
				if( val != 0 ) {
					return val;
				} else {
					Random ran = new Random();
					return ran.nextBoolean()?-1:1;
				}
			}			
		});

//		list.forEach(s -> System.out.println(s.toString()));
		
		ServerInfo s = list.get(0);
		int count  = s.getCount() + 1;
		s.setCount(count);
		return s.getIp() +":"+ s.getPort();
	}
}
