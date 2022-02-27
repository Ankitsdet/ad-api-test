package api;

import org.json.simple.JSONObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiMethods {
	String baseURL = "https://admin-advertisement.herokuapp.com/api";

	public Response postrequest(String name, String street, int room, double price, boolean status) {
		RestAssured.baseURI = baseURL;

		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", name);
		requestParams.put("street", street);
		requestParams.put("room", room);
		requestParams.put("price", price);
		requestParams.put("status", status);

		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());

		Response response = request.post("/advertisements");

		return response;

	}

	public Response updateAdByName(String id, String name) {

		RestAssured.baseURI = baseURL;

		RequestSpecification request = RestAssured.given();

		JSONObject updateRequestParams = new JSONObject();
		updateRequestParams.put("name", name);

		request.header("Content-Type", "application/json");
		request.body(updateRequestParams.toJSONString());

		Response updatedResponse = request.put("/advertisements/" + id);

		return updatedResponse;

	}
	
	
	public Response updateAdByStreet(String id, String Street) {

		RestAssured.baseURI = baseURL;

		RequestSpecification request = RestAssured.given();

		JSONObject updateRequestParams = new JSONObject();
		updateRequestParams.put("street", Street);

		request.header("Content-Type", "application/json");
		request.body(updateRequestParams.toJSONString());

		Response updatedResponse = request.put("/advertisements/" + id);

		return updatedResponse;

	}

}
