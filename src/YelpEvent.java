package google;

import com.google.gson.Gson;

public class YelpEvent implements iSerialiazable{
	
	public YelpEvent() {
		
	}
	
	public YelpEvent(String n, String rc, String ic, String r, String da, String dp) {
		name = n;
		review_count = rc;
		is_closed = ic;
		rating = r;
		display_address = da;
		display_phone = dp;
	}
	
	public String businesses;
	public String name = "Name";
	public String review_count = "Review Count";
	public String is_closed = "Hours";
	public String rating = "Rating";
	public String display_address = "Address";
	public String display_phone = "Phone Number";
	
	public String getReview_count() {
		return review_count;
	}

	public void setReview_count(String review_count) {
		this.review_count = review_count;
	}

	public String getIs_closed() {
		return is_closed;
	}

	public void setIs_closed(String is_closed) {
		this.is_closed = is_closed;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getDisplay_address() {
		return display_address;
	}

	public void setDisplay_address(String display_address) {
		this.display_address = display_address;
	}

	public String getDisplay_phone() {
		return display_phone;
	}

	public void setDisplay_phone(String display_phone) {
		this.display_phone = display_phone;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		String output = String.format("\n Event Details:  display_phone %s formatted_address %s, name %s, rating %s, is_closed %s, review_count %s", display_phone, display_address, name, rating, is_closed, review_count);
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
