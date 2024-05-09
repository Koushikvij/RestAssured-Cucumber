Feature: Validating place APIs

	@AddPlace
  Scenario Outline: Validation of Add Place API
    Given Add Place Payload is available with "<name>", "<language>" and "<address>"
    When User calls "AddPlaceAPI" with "Post" HTTP request
    Then API Call is successful with status code "200"
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "GetPlaceAPI"

  Examples:
  	|name						|language		|address									|
  	|Frontline house|French-IN	|29, side layout, cohen 09|
 # 	|Apartment House|Tamil-IN		|3, Nandanar St, TV Nagar, Chennai 75|
  	
  @DeletePlace
 	Scenario: Verify if Delete Place functionality is working
 		Given DeletePlace Payload is available
    When User calls "DeletePlaceAPI" with "Post" HTTP request
    Then API Call is successful with status code "200"
    And "status" in response body is "OK"