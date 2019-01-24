package edu.umbc.dos.WebService.endpoints;

import java.util.Arrays;

public class ServerInfo {
	private String ip;
	private int port;
	private String[] serviceName;
	private int count=0; 

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String[] getServiceName() {
		return serviceName;
	}

	public void setServiceName(String[] serviceName) {
		this.serviceName = serviceName;
	}

	public void setCount(int count) {
		this.count = count;
	}
	public int getCount() {
		return count;
	}
	
	@Override
	public String toString() {
		return ip +"|"+ port + "|"+ Arrays.toString(serviceName);
	}

}
