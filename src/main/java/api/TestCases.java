package api;

import org.junit.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestCases {

	@Test(description = "Create new advertisement with valid data")
	public void CreateNewAd() {

		ApiMethods apiObj = new ApiMethods();

		Response response = apiObj.postrequest("TestName1", "Raddison Street", 25, 1000, true);

		Assert.assertTrue("failed request", response.getStatusCode() == 200);
		String body = response.getBody().asString();
		JsonPath jsonPath = new JsonPath(body);
		String adId = jsonPath.getString("_id");

		System.out.println("This is response  :" + adId);

	}

	@Test(description = "Edit any existing advertisement by name")
	public void updateAdByName() {
		ApiMethods apiObj = new ApiMethods();

		Response response = apiObj.postrequest("TestNameUpdate1", "Raddison Street", 25, 1000, true);

		Assert.assertTrue("failed request", response.getStatusCode() == 200);

		String body = response.getBody().asString();
		JsonPath jsonPath = new JsonPath(body);
		String adId = jsonPath.getString("_id");

		System.out.println("This is unique id  :" + adId);

		Response updatedResponse = apiObj.updateAdByName(adId, "TestNameUpdateFinally");

		Assert.assertTrue("failed updated request", updatedResponse.getStatusCode() == 200);

		String updatedResponseBody = updatedResponse.getBody().asString();

		System.out.println("This is update response  :" + updatedResponseBody);

	}

	@Test(description = "Edit existing advertisement by street")
	public void updateAdByStreet() {
		ApiMethods apiObj = new ApiMethods();

		Response response = apiObj.postrequest("TestStreetUpdate1", "Taj Street", 25, 1000, true);

		Assert.assertTrue("failed request", response.getStatusCode() == 200);

		String body = response.getBody().asString();
		JsonPath jsonPath = new JsonPath(body);
		String adId = jsonPath.getString("_id");

		System.out.println("This is unique id  :" + adId);

		Response updatedResponse = apiObj.updateAdByStreet(adId, "Hyatt Street");

		Assert.assertTrue("failed updated request", updatedResponse.getStatusCode() == 200);

		String updatedResponseBody = updatedResponse.getBody().asString();

		System.out.println("This is update by street  :" + updatedResponseBody);

	}

}
