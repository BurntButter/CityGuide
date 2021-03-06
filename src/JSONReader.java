package google;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.json.*;

public class JSONReader {
	@JsonIgnoreProperties
	static Event evt = new Event();
	
	static YelpEvent yelpEvt = new YelpEvent();
	
	public static iSerialiazable run(int dec) throws JSONException {
		
		
		try {
			//read a string from json file
			InputStream is = new FileInputStream("C:\\Users\\brent\\Desktop\\CityGuide\\JSONFiles\\Results.json");
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));
			        
			String inputLine = buf.readLine();
			StringBuilder sb = new StringBuilder();
			        
			while(inputLine != null){
			   sb.append(inputLine).append("\n");
			   inputLine = buf.readLine();
			}
			buf.close();
			        
			String fileAsString = sb.toString();
			
			
			//Create a json object from the string
			JSONObject jsonObj = new JSONObject(fileAsString);
			
			YelpEvent ye = new YelpEvent();
			Event ge = new Event();
			
			//Get json array from json object (candidates)
			if(dec == 1) {
				JSONArray ja_data = jsonObj.getJSONArray("candidates");
				int length = ja_data.length();
				for(int i=0; i<length; i++) {
				  JSONObject jsonObj2 = ja_data.getJSONObject(i);
				  String address = jsonObj2.getString("formatted_address");
				  String name = jsonObj2.getString("name");
				  String hours = jsonObj2.getString("opening_hours");
				  String rating = jsonObj2.getString("rating");
	
				  ge.formatted_address = address;
				  ge.name = name;
				  String[] ohArray = hours.split(":");
				  ge.opening_hours = ohArray[1];
				  ge.rating = rating;
				  String[] addressArray = Event.stringToken(address);
				  ge.location = addressArray[2];
				  System.out.println(evt.toString());
				}
			} else {
				JSONArray ja_data = jsonObj.getJSONArray("businesses");
				int length = ja_data.length();
				for(int i=0; i<length; i++) {
				  JSONObject jsonObj2 = ja_data.getJSONObject(i);
				  String name = jsonObj2.getString("name");
				  String hours = jsonObj2.getString("is_closed");
				  String rating = jsonObj2.getString("rating");
				  String reviewCount = jsonObj2.getString("review_count");
				  String phoneNO = jsonObj2.getString("display_phone");
				  JSONObject jsonObj3 = jsonObj2.getJSONObject("location");
				  String address = jsonObj3.getString("display_address");
				  
				  ye.name = name;
				  ye.display_address = address;
				  ye.is_closed = hours;
				  ye.rating = rating;
				  ye.review_count = reviewCount;
				  ye.display_phone = phoneNO;
				  
				  System.out.println(yelpEvt.toString());
				}

			}
			
			if(dec == 1) {
				return ge;
			} else {
				return ye;
			}

			
		}
		 catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return null;
		}
}
