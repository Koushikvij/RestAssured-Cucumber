package resources;

public enum Constants {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	private String value;
	
	Constants(String value)
	{
		this.value=value;
	}

	public String getConstantValue()
	{
		return this.value;
	}
}
