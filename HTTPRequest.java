package google;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import google.JSONReader;


public class HTTPRequest {
	
	public static void main(String[] args) throws Exception {

	}	

	public static String searchString = "";
	public static String saveFile = "";
	public static String library = "SavedEvent-" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()) + ".json";
	
	public static void sendGet() throws Exception, MalformedURLException, UnsupportedEncodingException{
		
		final String resultsFile = "C:\\Users\\brent\\Desktop\\CityGuide\\JSONFiles\\Results.json";
		final String store = String.format("C:\\Users\\brent\\Desktop\\CityGuide\\Library\\%s", library);

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
			
			if(saveFile != null) {
				try(OutputStream out = new FileOutputStream(store)){
					out.write(bytes);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try (OutputStream out = new FileOutputStream(resultsFile)){
					out.write(bytes);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		} else {
			System.out.println("Invalid Request");
		}
		JSONReader.run();
	}

	public static void saveFile(InputStream in, OutputStream out) throws IOException {
	    byte[] buffer = new byte[1024];
	    int read;
	    while ((read = in.read(buffer)) != -1) {
	        out.write(buffer, 0, read);
	    }
	}
}
