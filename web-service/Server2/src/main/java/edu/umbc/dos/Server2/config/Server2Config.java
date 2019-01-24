package edu.umbc.dos.Server2.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.feature.Features;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import edu.umbc.dos.Server2.endpoints.AddService;
import edu.umbc.dos.Server2.endpoints.MulService;



@Configuration
@ComponentScan(value="edu.umbc.dos.Server2")
@Features(features="org.apache.cxf.feature.LoggingFeature")
public class Server2Config {

	public static final String NAMESPACE_URI = "http://www.mywebservice.com";
	
	@Autowired
	private Bus bus;
	
	@Bean	
	public Endpoint addService() {
		Endpoint e = new EndpointImpl(bus, new AddService());
		e.publish("/add");
		return e;
	}
	
	
	@Bean	
	public Endpoint mulService() {
		Endpoint e = new EndpointImpl(bus, new MulService());
		e.publish("/mul");
		return e;
	}
	
	
	}

