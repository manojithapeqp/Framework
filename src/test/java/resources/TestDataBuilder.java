package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.CreateLocationPOJO;
import pojo.LocationPOJO;

public class TestDataBuilder {
	public CreateLocationPOJO addPlacePlayload(String address, String name, String language) {
		// Create object of POJO parent class
		CreateLocationPOJO pojpObj = new CreateLocationPOJO();
		pojpObj.setAccuracy(50);
		pojpObj.setName(name);
		;
		pojpObj.setPhone_number("(+91) 983 893 3937");
		pojpObj.setAddress(address);
		pojpObj.setWebsite("http://google.com");
		pojpObj.setLanguage(language);
		// Below create arrayList to pass the array data for Type object of Json
		List<String> typ = new ArrayList<String>();
		typ.add("shoe park");
		typ.add("shop");
		// Pass type obj to set array
		pojpObj.setTypes(typ);
		// Location class Obj we create
		LocationPOJO locobj = new LocationPOJO();
		locobj.setLat(-38.383494);
		locobj.setLng(33.427362);
		pojpObj.setLocation(locobj);
		return pojpObj;

	}
	//Below playload used to pass payload to the delete place API
	public String deletePlaceAPIPlayload(String placeID) {
		return "{\n"
				+ "    \"place_id\":\""+placeID+"\"\n"
				+ "}";
	}
}
