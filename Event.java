package google;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {
	
	public Event() {
		
	}
	
	public Event(String fa, String n, String oh, String r) {
		Event.formatted_address = fa;
		Event.name = n;
		Event.opening_hours = oh;
		Event.rating = r;
	}
	
	public String candidates;
	@JsonProperty ("formatted_address")
	public static String formatted_address;
	@JsonProperty ("name")
	public static String name;
	@JsonProperty ("opening_hours")
	public static String opening_hours;
	@JsonProperty ("rating")
	public static String rating;
	public static String country;

	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		Event.rating = rating;
	}
	public String getFormatted_address() {
		return formatted_address;
	}
	public void setFormatted_address(String formatted_address) {
		Event.formatted_address = formatted_address;
	}
	public static String getName() {
		return name;
	}
	public void setName(String name) {
		Event.name = name;
	}
	public static String getOpening_hours() {
		return opening_hours;
	}
	public void setOpening_hours(String opening_hours) {
		Event.opening_hours = opening_hours;
	}
	public String toString() {
		String output = String.format("\n Event Details: country %s formatted_address %s, name %s, rating %s, opening_hours %s", country, formatted_address, name, rating, opening_hours);
		return output;
	}
	
	public static String[] stringToken(String s) {
		String[] sArray = s.split(",");
		return sArray;
	}
}
