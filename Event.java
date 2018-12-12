package google;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

public class Event implements iSerialiazable{
	
	public Event() {
		
	}
	
	public Event(String fa, String n, String oh, String r) {
		formatted_address = fa;
		name = n;
		opening_hours = oh;
		rating = r;
	}
	
	public String candidates;
	@JsonProperty ("formatted_address")
	public String formatted_address = "Address";
	@JsonProperty ("name")
	public String name = "Name";
	@JsonProperty ("opening_hours")
	public String opening_hours = "Hours";
	@JsonProperty ("rating")
	public String rating = "Rating";
	public String location = "Location";

	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getFormatted_address() {
		return formatted_address;
	}
	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOpening_hours() {
		return opening_hours;
	}
	public void setOpening_hours(String opening_hours) {
		this.opening_hours = opening_hours;
	}
	public String toString() {
		String output = String.format("\n Event Details: location %s formatted_address %s, name %s, rating %s, opening_hours %s", location, formatted_address, name, rating, opening_hours);
		return output;
	}
	
	public static String[] stringToken(String s) {
		String[] sArray = s.split(",");
		return sArray;
	}
	
	@Override
	public String serialize() {
		Gson g = new Gson();
		String json = g.toJson(this);
		
			
		return json;
	}
}
