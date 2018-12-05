package google;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {
	
	public Event() {
		
	}
	
	public Event(String fa, String n, String oh, String r) {
		this.formatted_address = fa;
		this.name = n;
		this.opening_hours = oh;
		this.rating = r;
	}
	
	public String candidates;
	@JsonProperty ("formatted_address")
	public String formatted_address;
	@JsonProperty ("name")
	public String name;
	@JsonProperty ("opening_hours")
	public String opening_hours;
	@JsonProperty ("rating")
	public String rating;
	public String country;

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
		String output = String.format("\n Event Details: country %s formatted_address %s, name %s, rating %s, opening_hours %s", country, formatted_address, name, rating, opening_hours);
		return output;
	}
	
	public static String[] stringToken(String s) {
		String[] sArray = s.split(",");
		return sArray;
	}
}
