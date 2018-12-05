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
	
	public void run() throws JSONException {
		
		
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
			
			//Get json array from json object (candidates)
			JSONArray ja_data = jsonObj.getJSONArray("candidates");
			int length = ja_data.length();
			for(int i=0; i<length; i++) {
			  JSONObject jsonObj2 = ja_data.getJSONObject(i);
			  String address = jsonObj2.getString("formatted_address");
			  String name = jsonObj2.getString("name");
			  String hours = jsonObj2.getString("opening_hours");
			  String rating = jsonObj2.getString("rating");
			  evt.formatted_address = address;
			  evt.name = name;
			  evt.opening_hours = hours;
			  evt.rating = rating;
			  String[] addressArray = Event.stringToken(address);
			  evt.country = addressArray[2];
			}

			//convert json object to string
			System.out.println(evt.toString());
			
		}
		 catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	public static Event returnEvent() {
		return evt;
	}
}
