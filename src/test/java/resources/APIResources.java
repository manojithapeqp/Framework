package resources;

public enum APIResources {
	// instead of class enum mentioned and below added API URI's
	AddPlaceAPI("/maps/api/place/add/json"), 
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("maps/api/place/delete/json");

	// Below we declare private variable to return assigned value
	private String resource;

//Created constructor to initialize above constants and assign API URI as per feature file
	APIResources(String resources) {
		this.resource = resources;
	}
//below method used to return resource assigned value to required place

	public String getResourceAPI() {
		return resource;

	}

}
