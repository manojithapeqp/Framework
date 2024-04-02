package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;

import io.restassured.path.json.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.CreateLocationPOJO;
import pojo.LocationPOJO;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.UtilsResable;

@RunWith(Cucumber.class)
public class stepDefination extends UtilsResable {
	RequestSpecification res;
	ResponseSpecification responseSpec;
	Response response;
	TestDataBuilder data = new TestDataBuilder();
	static String place_ID;

	@Given("Add Place Playload {string} {string} {string}")
	public void add_place_playload(String address, String name, String language) throws IOException {

//From resources package we call data here
		res = given().spec(requestSpecification()).body(data.addPlacePlayload(address, name, language));
		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/json").build();
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String apiResource, String httpMethod) {
		// From APIResource we call object here and store and apiResource comes from
		// feature file
		APIResources resourcesAPI = APIResources.valueOf(apiResource);
		System.out.println(resourcesAPI.getResourceAPI());
		if (httpMethod.equalsIgnoreCase("POST")) {
			response = res.when().post(resourcesAPI.getResourceAPI());
		} else if (httpMethod.equalsIgnoreCase("get")) {
			response = res.when().get(resourcesAPI.getResourceAPI());
		} else if (httpMethod.equalsIgnoreCase("delete")) {
			response = res.when().delete(resourcesAPI.getResourceAPI());
		}
	}

	@Then("The API calls got success with status code {int}")
	public void the_api_calls_got_success_with_status_code(Integer int1) {
		// response.then().spec(responseSpec).extract().response();
		assertEquals(response.getStatusCode(), 200);

	}

	@Then("{string} in response body {string}")
	public void in_response_body(String keyvalue, String expectedValue) {
		assertEquals(getJsonPath(response, keyvalue), expectedValue);

	}
	@Then("verify place_ID created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expPlaceName, String APIresource) throws IOException {
		place_ID=getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id",place_ID );
		user_calls_with_http_request(APIresource, "get");
		String actualPlaceName=getJsonPath(response, "address");
	    assertEquals(actualPlaceName,expPlaceName);
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		res = given().spec(requestSpecification()).body(data.deletePlaceAPIPlayload(place_ID));
		
		
	}

}
