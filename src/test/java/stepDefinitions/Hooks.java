package stepDefinitions;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeDeleteScenario()
	{
		StepDefinition step = new StepDefinition();
		if(StepDefinition.placeId=="")
		{
			step.add_place_payload_is_available_with_and("Koushik", "Tamil", "Chennai");
			step.user_calls_with_http_request("AddPlaceAPI", "POST");
			step.api_call_is_successful_with_status_code("200");
			step.in_response_body_is("status", "OK");
			step.verify_place_id_created_maps_to_using("Koushik", "GetPlaceAPI");
		}
	}
}
