package simulateAPI;

import static io.restassured.RestAssured.given;
import utility.jsonPayload;
import utility.rawToJson;

public class updatePlace {

	private static String putResource = "/maps/api/place/update/json";
	private static String keyValue = "qaclick123";
	private static String header = "Apache/2.4.18 (Ubuntu)";
	private static getPlace gp = new getPlace();
	private static String successMessage = "Address successfully updated";
	private static String response = null, message = null;

	public String methodPUT(String placeID) throws Throwable {
		try {

			response = given().log().all().queryParam("key", keyValue).header("Content-Type", "application/json")
					.body(jsonPayload.getBodyPut(placeID)).when().put(putResource).then().log().all().assertThat()
					.statusCode(200).header("server", header).extract().response().asString();

			message = rawToJson.getUpdateMessage(response);

			if (successMessage.equalsIgnoreCase(message)) {
				String newAddress = gp.methodGET(placeID);
				return newAddress;
			} else
				return message;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in methodPUT.");
			return null;
		}
	}
}
