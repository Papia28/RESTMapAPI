package utility;

import io.restassured.path.json.JsonPath;

public class rawToJson {

	public static String getPlaceID(String response) {
		JsonPath js = new JsonPath(response);
		String place_id = js.getString("place_id");
		return place_id;
	}

	public static String getAddress(String response) {
		JsonPath js = new JsonPath(response);
		String address = js.getString("address");
		return address;
	}

	public static String getUpdateMessage(String response) {
		JsonPath js = new JsonPath(response);
		String message = js.getString("msg");
		return message;
	}

	public static String getDeleteMessage(String response) {
		JsonPath js = new JsonPath(response);
		String message = js.getString("status");
		return message;
	}

}
