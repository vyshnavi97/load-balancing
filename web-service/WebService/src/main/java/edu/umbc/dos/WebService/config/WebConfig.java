package edu.umbc.dos.WebService.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.feature.Features;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import edu.umbc.dos.WebService.endpoints.AddService;
import edu.umbc.dos.WebService.endpoints.divService;
import edu.umbc.dos.WebService.endpoints.mulService;
import edu.umbc.dos.WebService.endpoints.subService;
import edu.umbc.dos.WebService.lb.Clientcomm;
import edu.umbc.dos.WebService.lb.ServerComm;
	
@Configuration
@ComponentScan(value="edu.umbc.dos.WebService")
@Features(features="org.apache.cxf.feature.LoggingFeature")
public class WebConfig {

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
	public Endpoint subService() {
		Endpoint e = new EndpointImpl(bus, new subService());
		e.publish("/sub");
		return e;
	}

	@Bean	
	public Endpoint mulService() {
		Endpoint e = new EndpointImpl(bus, new mulService());
		e.publish("/mul");
		return e;
	}
	
	@Bean	
	public Endpoint divService() {
		Endpoint e = new EndpointImpl(bus, new divService());
		e.publish("/div");
		return e;
	}
	
	
	@Bean	
	public Endpoint Server() {
		Endpoint e = new EndpointImpl(bus, new ServerComm());
		e.publish("/server");
		return e;
	}
	
	@Bean	
	public Endpoint Client() {
		Endpoint e = new EndpointImpl(bus, new Clientcomm());
		e.publish("/client");
		return e;
	}
	
}
