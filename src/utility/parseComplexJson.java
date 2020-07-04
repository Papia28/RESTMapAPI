package utility;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class parseComplexJson {

	JsonPath js = new JsonPath(jsonPayload.getBodyComplexJson());
	static int size = 0;
	static int purchaseAmount = 0;
	String[] courses;
	

	// Print No of courses returned by API
	@Test (priority = 0, groups = { "first" }, alwaysRun = true)
	public void numberOfCourses() {
		
		size = js.getInt("courses.size()");
		courses = new String[size];
		System.out.println("Number of courses: " + size);
	}
	

	// Print Purchase Amount
	@Test ( priority = 1, groups = { "first" }, alwaysRun = true)
	public void purchaseAmout() {

		purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount: " + purchaseAmount);
	}

	
	// Print Title of the first course
	@Test (priority = 2, groups = { "first" })
	public void titleOfCourse() {
		
		String courseName = js.getString("courses[0].title");
		System.out.println("Title of the first course: " + courseName);
	}
	

	// Print All course titles and their respective Prices
	@Test (priority = 2, groups = { "first" })
	public void courseTitlesPrices() {
		
		for (int i = 0; i < size; i++) {
			String title = null;
			int price = 0;
			System.out.println("Course: " + (i + 1));
			title = js.getString("courses[" + i + "].title");
			System.out.println("Title: " + title);
			price = js.getInt("courses[" + i + "].price");
			System.out.println("Price: " + price);
		}
	}
	

	// Print no of copies sold by RPA Course
	@Test(priority = 2, groups = { "first" })
	public void RPACourse() {
		
		int num = 0;
		
		for(int i = 0; i<size; i++) {
			if(js.getString("courses[" + i + "].title").equalsIgnoreCase("RPA")) {
				num = js.getInt("courses[" + i + "].copies");
				System.out.println("Number of RPA copies sold: " + num);
				break;
			}				
		}
	}
	

	// Verify if Sum of all Course prices matches with Purchase Amount
	@Test(priority = 2, dependsOnGroups = { "first" })
	public void allCoursePrices() {
		
		int sum = 0;	
		
		for(int i = 0; i<size; i++) {
			int price = 0;
			int copies = 0;
			int total = 0;
			price = js.getInt("courses[" + i + "].price");
			copies = js.getInt("courses[" + i + "].copies");
			total = price * copies;
			sum += total;
		}
		
		System.out.println("Sum of all course prices: " + sum);
		Assert.assertEquals(sum, purchaseAmount);
	}

}
