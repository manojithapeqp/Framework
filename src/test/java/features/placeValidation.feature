
Feature: Validating Place API

@AddPlace @Regression
Scenario Outline: Verify if place is being successfullu added using AddPlaceAPI

	Given Add Place Playload "<address>" "<name>" "<language>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then The API calls got success with status code 200
	And "status" in response body "OK"
	And "scope" in response body "APP"
	And verify place_ID created maps to "<address>" using "getPlaceAPI"
Examples: 
	|address								|name  	|language	|	
	|Himganga Niwas A Nagar	|ITHub 	|English	|
#	|Himganga Niwas A Pune	|SOP		|English	|


@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working

	Given DeletePlace Payload
	When user calls "deletePlaceAPI" with "POST" http request
	Then The API calls got success with status code 200
	And "status" in response body "OK"
	
	

