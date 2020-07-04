package simulateAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import utility.jsonPayload;
import utility.rawToJson;

public class addPlace {

	private static String postResource = "/maps/api/place/add/json";
	private static String keyValue = "qaclick123";
	private static String header = "Apache/2.4.18 (Ubuntu)";
	private static String response = null, placeID = null;

	public String methodPOST() throws Throwable {
		try {

			response = given().log().all().queryParam("key", keyValue).header("Content-Type", "application/json")
					.body(jsonPayload.getBodyPost()).when().post(postResource).then().log().all().assertThat()
					.statusCode(200).body("scope", equalTo("APP")).header("server", header).extract().response()
					.asString();

			placeID = rawToJson.getPlaceID(response);
			return placeID;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in method POST.");
			return null;
		}
	}
}
