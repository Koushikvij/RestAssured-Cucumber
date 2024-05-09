package resources;

import java.util.ArrayList;
import java.util.List;
import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language, String address) {
		AddPlace addplace= new AddPlace();
		
		try
		{
			Location loc = new Location();
			loc.setLat(-38.383494);
			loc.setLng(33.427362);
			List<String> type= new ArrayList<String>();
			type.add("shoe park");
			type.add("shop");
			
			addplace.setAccuracy(50);
			addplace.setAddress(address);
			addplace.setLanguage(language);
			addplace.setLocation(loc);
			addplace.setName(name);
			addplace.setPhone_number("(+91) 983 893 3937");
			addplace.setTypes(type);
			addplace.setWebsite("http://google.com");

			System.out.println("Add Place API Payload is built successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return addplace;
	}	
	
	public String deletePlacePayload(String placeId) {
		String returnString="";
		try
		{
			returnString="{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}\r\n";
			System.out.println("Delete Place API Payload is built successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnString;
	}	
}
