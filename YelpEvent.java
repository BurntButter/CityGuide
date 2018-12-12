package google;

public class YelpEvent {
	
	public YelpEvent() {
		
	}
	
	public YelpEvent(String n, String rc, String ic, String r, String da, String dp) {
		YelpEvent.name = n;
		YelpEvent.review_count = rc;
		YelpEvent.is_closed = ic;
		YelpEvent.rating = r;
		YelpEvent.display_address = da;
		YelpEvent.display_phone = dp;
	}
	
	public String businesses;
	public static String name = "Name";
	public static String review_count = "Review Count";
	public static String is_closed = "Hours";
	public static String rating = "Rating";
	public static String display_address = "Address";
	public static String display_phone = "Phone Number";
	
	public static String getReview_count() {
		return review_count;
	}

	public static void setReview_count(String review_count) {
		YelpEvent.review_count = review_count;
	}

	public static String getIs_closed() {
		return is_closed;
	}

	public static void setIs_closed(String is_closed) {
		YelpEvent.is_closed = is_closed;
	}

	public static String getRating() {
		return rating;
	}

	public static void setRating(String rating) {
		YelpEvent.rating = rating;
	}

	public static String getDisplay_address() {
		return display_address;
	}

	public static void setDisplay_address(String display_address) {
		YelpEvent.display_address = display_address;
	}

	public static String getDisplay_phone() {
		return display_phone;
	}

	public static void setDisplay_phone(String display_phone) {
		YelpEvent.display_phone = display_phone;
	}
	
	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		YelpEvent.name = name;
	}
	
	public String toString() {
		String output = String.format("\n Event Details:  display_phone %s formatted_address %s, name %s, rating %s, is_closed %s, review_count %s", display_phone, display_address, name, rating, is_closed, review_count);
		return output;
	}
}
