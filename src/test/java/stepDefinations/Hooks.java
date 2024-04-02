package stepDefinations;

import java.io.IOException;

import org.junit.runner.RunWith;

import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//execute below code only if place_id is null 
		stepDefination sd=new stepDefination();
		if(stepDefination.place_ID==null) {
			sd.add_place_playload("Ithape", "French", "Asia");
			sd.user_calls_with_http_request("AddPlaceAPI", "POST");
			sd.verify_place_id_created_maps_to_using("Ithape", "AddPlaceAPI");
		}
		
	}

}
