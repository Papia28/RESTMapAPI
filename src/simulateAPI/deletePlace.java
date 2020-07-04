package simulateAPI;

import static io.restassured.RestAssured.given;

import assertion.assertions;
import utility.jsonPayload;
import utility.rawToJson;

public class deletePlace {

	private static String deleteResource = "/maps/api/place/delete/json";
	private static String keyValue = "qaclick123";
	private static String header = "Apache/2.4.18 (Ubuntu)";
	private static String response = null, message = null;
	private static assertions a = new assertions();

	public boolean methodDELETE(String placeID) throws Throwable {
		try {

			response = given().log().all().queryParam("key", keyValue).header("Content-Type", "application/json")
					.body(jsonPayload.getBodyDelete(placeID)).when().delete(deleteResource).then().log().all()
					.assertThat().statusCode(200).header("server", header).extract().response().asString();

			message = rawToJson.getDeleteMessage(response);

			if (a.assertEquality("OK", message))
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in methodDELETE.");
			return false;
		}
	}
}
