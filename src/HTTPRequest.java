package google;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class HTTPRequest {

	public static String searchString = "";
	final static String resultsFile = "C:\\Users\\brent\\Desktop\\CityGuide\\JSONFiles\\Results.json";
	
	public static void sendGet() throws Exception, MalformedURLException, UnsupportedEncodingException{

		String baseUrl = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=";
		String fieldKey =  "&inputtype=textquery&fields=formatted_address,name,rating,opening_hours,geometry&key=AIzaSyA_RTaZYA3-1qX1hT-4465YhbdYJY9BgEE";
		
		String urlString = String.format("%s%s%s", baseUrl, URLEncoder.encode(searchString, "UTF-8").replace("+", "%20"), fieldKey);	
		System.out.println(urlString);
		URL obj = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		
		int responseCode = con.getResponseCode();
		System.out.println("Sending GET request");
		System.out.println("Response Code: "  + responseCode);
		
		if(responseCode == 200) {
			BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			String responseStr = response.toString();
			byte[] bytes = responseStr.getBytes();
			
			
			try (OutputStream out = new FileOutputStream(resultsFile)){
				out.write(bytes);
			} catch (IOException e) {
				e.printStackTrace();
				}
		}

	}
	
	public static void sendGetYelp() throws Exception, MalformedURLException, UnsupportedEncodingException {
		
		String authToken = "5MbnjRi-73zwJM_kovPDpiDooyaYa9XaCqty36B7BSnGNBGACycXnl_LJQcO7vnJTP9dJrcHzUdJAOe5oFuYxND7wtcyCKbfxrnvjAHTDtj1x0lMRx1j1-ugNUQIXHYx";
		String yelpBaseUrl = "https://api.yelp.com/v3/businesses/search?term=";
		String locationURL = "&location=";
		searchString = searchString.replaceAll("\\s","");
		String[] searchArray = Event.stringToken(searchString);
		
		String urlString = String.format("%s%s%s%s", yelpBaseUrl, URLEncoder.encode(searchArray[0], "UTF-8").replace("+", "%20"), locationURL, searchArray[1]);
		System.out.println(urlString);
		
		URL obj = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestProperty("Authorization", "Bearer " + authToken);
		con.setRequestMethod("GET");
		
		int responseCode = con.getResponseCode();
		System.out.println("Sending GET request to Yelp");
		System.out.println("Yelp Response Code: "  + responseCode);
		
		if(responseCode == 200) {
			BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			String responseStr = response.toString();
			byte[] bytes = responseStr.getBytes();
			
			
			try (OutputStream out = new FileOutputStream(resultsFile)){
				out.write(bytes);
			} catch (IOException e) {
				e.printStackTrace();
				}
		}
	}
}
