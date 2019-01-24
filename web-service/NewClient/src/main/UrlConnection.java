package main;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlConnection {

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {
		String service = "SUB";
		System.out.println("\nTesting 2 - Send Http POST request");
		UrlConnection http = new UrlConnection();
		String data = "<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\"><Body><findService xmlns=\"http://lb.WebService.dos.umbc.edu/\"><serviceName xmlns=\"\">"+ service +"</serviceName></findService></Body></Envelope>";
		String resp = http.sendPost("http://localhost:8081/client", data);
	
		String ipPort = resp.substring(resp.indexOf("<return>") + 8, resp.indexOf("</return>"));
		String newUrl = "http://"+ ipPort +"/"+ service.toLowerCase();
		System.out.println(newUrl);

		String arg1 = "4";
		String arg2 = "6";
		String req = "<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\"><Body><"+ service.toLowerCase() +" xmlns=\"http://www.mywebservice.com\"><args1 xmlns=\"\">"+ arg1 +"</args1><args2 xmlns=\"\">"+ arg2 +"</args2></"+ service.toLowerCase() +"></Body></Envelope>";
		System.out.println(req);
		http.sendPost(newUrl, req);
		
	}
	
	private String sendPost(String url, String soapEnv) throws Exception {

//		String url = "http://localhost:8081/client";
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
		wr.writeBytes(soapEnv);
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

