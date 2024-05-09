package stepDefinitions;

import static org.junit.Assert.*;
import static io.restassured.RestAssured.given;

import Utilities.APIUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.*;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.Constants;
import resources.SpecBuilder;
import resources.TestDataBuild;

public class StepDefinition {
	public RequestSpecification request;
	public ResponseSpecification response;
	public Response fullResponse;
	public static String BaseURI="https://rahulshettyacademy.com";
	public static String placeId="";
	public TestDataBuild data = new TestDataBuild();
	public SpecBuilder specs = new SpecBuilder();

	@Given("Add Place Payload is available with {string}, {string} and {string}")
	public void add_place_payload_is_available_with_and(String name, String language, String address) {
		try
		{					
			request = given()
					.spec(specs.requestSpecBuilder())
					.body(data.addPlacePayload(name,language,address));

			System.out.println("Add Place API Request and Response Spec are created successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Given("DeletePlace Payload is available")
	public void delete_place_payload_is_available() {
		try
		{					
			request = given()
					.spec(specs.requestSpecBuilder())
					.body(data.deletePlacePayload(placeId));

			System.out.println("Delete Place API Request and Response Spec are created successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@When("User calls {string} with {string} HTTP request")
	public void user_calls_with_http_request(String apiName, String requestMethod) {
		try
		{
			Constants constants=Constants.valueOf(apiName);
			String apiResource=constants.getConstantValue();
			Response resp = null;
			switch(requestMethod.toUpperCase().trim())
			{
			case "POST":
				resp = request
					.when()
					.post(apiResource);
				break;
			case "GET":
				resp = request
				.when()
				.get(apiResource);
				break;
			case "DELETE":
				resp = request
					.when()
					.delete(apiResource);
				break;
			}
				
			fullResponse = resp.then()
					.spec(specs.responseSpecBuilder())
					.extract()
					.response();

				System.out.println(apiName + " is called successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Then("API Call is successful with status code {string}")
	public void api_call_is_successful_with_status_code(String expStatusCode) {
		try
		{
			assertEquals(fullResponse.getStatusCode(),Integer.parseInt(expStatusCode));
			System.out.println("Add Place API is called successfully with status Code " + expStatusCode);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String responseBodyField, String responseBodyValue) {
		try
		{
			String fullResponseString=fullResponse.asString();
			JsonPath js=APIUtils.ResponsetoJSON(fullResponseString);
			String actualBodyValue = js.get(responseBodyField).toString();
			assertEquals(actualBodyValue.trim(),responseBodyValue.trim());
			System.out.println("The "+responseBodyField+ " field in the response boday is " + responseBodyValue);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String name, String apiResource) {
		try
		{
			placeId=APIUtils.getJSONValue(fullResponse, "place_id");
			request = given()
					.spec(specs.requestSpecBuilder())
					.queryParam("place_id", placeId);
			user_calls_with_http_request(apiResource, "GET");
			String nameInResponse=APIUtils.getJSONValue(fullResponse, "name");
			assertEquals(nameInResponse.trim(),name.trim());				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
