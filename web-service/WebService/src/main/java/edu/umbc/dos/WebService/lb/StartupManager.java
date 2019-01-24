package edu.umbc.dos.WebService.lb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
public class StartupManager implements DisposableBean {

	@PostConstruct
	public void init() {
		System.out.println("Starting Load Balancer");
		String data = "<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\"><Body><Alive xmlns=\"http://lb.WebService.dos.umbc.edu/\"><ipAddress xmlns=\"\">localhost</ipAddress><port xmlns=\"\">8081</port><serviceName xmlns=\"\">[ADD,SUB,MUL,DIV]</serviceName></Alive></Body></Envelope>";
		UrlConnection con = new UrlConnection("http://localhost:8081/server", data);
		Thread t = new Thread(con);
		t.start();
	}
	
	@PreDestroy
	@Override
	public void destroy() throws Exception {
		String data = "<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\"><Body><Dead xmlns=\"http://lb.WebService.dos.umbc.edu/\"><ipAddress xmlns=\"\">localhost</ipAddress><port xmlns=\"\">8081</port></Dead></Body></Envelope>";
		UrlConnection con = new UrlConnection("http://localhost:8081/server", data);
		Thread t = new Thread(con);
		t.start();
		t.join();
	}
	
}
