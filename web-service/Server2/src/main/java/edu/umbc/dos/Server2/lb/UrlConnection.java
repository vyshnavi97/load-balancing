package edu.umbc.dos.Server2.lb;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class UrlConnection implements Runnable {

	private final String USER_AGENT = "Mozilla/5.0";
	private String url, soapEnvelope, response;
	
	public UrlConnection(String url, String soapEnvelope) {
		this.soapEnvelope = soapEnvelope;
		this.url = url;
	}

	public String getResponse() {
		return response;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(3000);
			this.response = this.sendPost();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String sendPost() throws Exception {
		
		URL obj = new URL(url);
		HttpURLConnection c = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		c.setRequestMethod("POST");
		c.setRequestProperty("Accept", "*/");
		c.setRequestProperty("User-Agent", USER_AGENT);
		c.setRequestProperty("Content-Type", "text/xml");
//		c.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		// Send post request
		c.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(c.getOutputStream());
		wr.writeBytes(soapEnvelope);
		wr.flush();
		wr.close();

		int responseCode = c.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(c.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		return response.toString();
		
	}

}

