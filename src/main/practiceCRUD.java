package main;

import org.testng.annotations.Test;

import assertion.assertions;
import io.restassured.RestAssured;
import simulateAPI.addPlace;
import simulateAPI.deletePlace;
import simulateAPI.getPlace;
import simulateAPI.updatePlace;


@Test (groups = { "Group1" })
public class practiceCRUD {

	public static addPlace ap = new addPlace();
	public static getPlace gp = new getPlace();
	public static updatePlace up = new updatePlace();
	public static deletePlace dp = new deletePlace();
	public static assertions a = new assertions();
	public static String placeID = null;
	public static String updatedAddress = "70 Summer walk, USA";

	
	public void simulation() throws Throwable {
		try {
			RestAssured.baseURI = "https://rahulshettyacademy.com";

			//Add a place
			System.out.println(
					"\n\n******************************************ADD PLACE API*****************************************\n\n");
			placeID = ap.methodPOST();
			System.out.println("New place added successfully! Place ID = " + placeID);
			
			
			//Get the added place
			System.out.println(
					"\n\n*****************************************GET PLACE API******************************************\n\n");
			String resultGet = gp.methodGET(placeID);
			System.out.println("Place Details: " + resultGet);

			
			//Update the added place and get it
			System.out.println(
					"\n\n*****************************************UPDATE PLACE API******************************************\n\n");
			String resultUpdate = up.methodPUT(placeID);
			a.assertEquality(updatedAddress, resultUpdate);
			System.out.println("Place Details: " + resultUpdate);

			
			//Delete the added place
			System.out.println(
					"\n\n*****************************************DELETE PLACE API******************************************\n\n");
			boolean resultDelete = dp.methodDELETE(placeID);
			if (resultDelete == true)
				System.out.println("Place deleted successfully!");
			else if (resultDelete == false)
				System.out.println("Place not deleted! Failure!");
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in main method of practiceCRUD.");
		}

	}
}
