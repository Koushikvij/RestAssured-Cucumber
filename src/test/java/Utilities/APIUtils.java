package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class APIUtils {
	public static String user_dir = System.getProperty("user.dir");
	public static String globalPropertyFile=user_dir + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "resources" + File.separator + "global.properties";
	
	public static JsonPath ResponsetoJSON(String response)
	{
		JsonPath js=new JsonPath(response);
		return js;
	}
	
	public static String getGlobalProperty(String key)
	{
		String value="";
		try
		{
			Properties prop = new Properties();
			FileInputStream file = new FileInputStream(globalPropertyFile);
			prop.load(file);
			value = prop.getProperty(key);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return value;
	}
	
	public static String getJSONValue(Response response, String key)
	{
		JsonPath js=new JsonPath(response.asString());
		return js.get(key).toString();
	}
}