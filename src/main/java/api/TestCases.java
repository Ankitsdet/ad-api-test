package api;

import org.json.simple.JSONObject;
import org.junit.Assert; 
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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
	
	@Test(description = "Fail to create new advertisement with malformed data")
	public void FailToCreateAd() {

		String baseURL = "https://admin-advertisement.herokuapp.com/api";
		RestAssured.baseURI = baseURL;

		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "test>_one");
		requestParams.put("street", "test_street");
		requestParams.put("room", "5");
		requestParams.put("price", "310");
		requestParams.put("status", "tommy");

		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());

		Response response = request.post("/advertisements");

		String body = response.getBody().asString();
		JsonPath jsonPath = new JsonPath(body);
		
		System.out.println("This should be error response but it's currently not 400 instead it is" + response.getStatusCode());
		Assert.assertTrue("failed request", response.getStatusCode() == 400);

	}


	@Test(description = "Edit any existing advertisement by name")
	public void updateAdByName() {
		ApiMethods apiObj = new ApiMethods();

		Response response = apiObj.postrequest("Raddison Hotel", "Raddison Street", 25, 1000, true);

		Assert.assertTrue("failed request", response.getStatusCode() == 200);

		String body = response.getBody().asString();
		JsonPath jsonPath = new JsonPath(body);
		String adId = jsonPath.getString("_id");

		System.out.println("This is unique id  :" + adId);

		Response updatedResponse = apiObj.updateAd(adId, "Raddison Lodge", "Raddison Street", 25, 1000, true);

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

		Response updatedResponse = apiObj.updateAd(adId, "TestStreetUpdate1", "Hyatt Street", 25, 1000, true);

		Assert.assertTrue("failed updated request", updatedResponse.getStatusCode() == 200);

		String updatedResponseBody = updatedResponse.getBody().asString();

		System.out.println("This is update by street  :" + updatedResponseBody);

	}

}
