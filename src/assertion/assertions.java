package assertion;

import org.testng.Assert;

public class assertions {

	public boolean assertEquality(String actual, String expected) throws Throwable {
		try {
			Assert.assertEquals(actual, expected);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
