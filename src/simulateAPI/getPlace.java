package simulateAPI;

import static io.restassured.RestAssured.given;
import utility.rawToJson;

public class getPlace {

	private static String getResource = "/maps/api/place/get/json";
	private static String keyValue = "qaclick123";
	private static String header = "Apache/2.4.18 (Ubuntu)";
	private static String response = null, address = null;

	public String methodGET(String placeID) throws Throwable {
		try {

			response = given().log().all().queryParam("key", keyValue).queryParam("place_id", placeID).when()
					.get(getResource).then().log().all().assertThat().statusCode(200).header("server", header).extract()
					.response().asString();

			address = rawToJson.getAddress(response);
			return address;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in methodGET.");
			return null;
		}
	}
}
