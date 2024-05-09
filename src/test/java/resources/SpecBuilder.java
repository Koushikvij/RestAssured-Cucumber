package resources;

import java.io.FileOutputStream;
import java.io.PrintStream;

import Utilities.APIUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
	public static RequestSpecification requestHead;
	public static ResponseSpecification responseHead;

	public RequestSpecification requestSpecBuilder() {
		try
		{			
			if(requestHead==null)
			{
				PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
				requestHead = new RequestSpecBuilder()
						.setContentType(ContentType.JSON)
						.setBaseUri(APIUtils.getGlobalProperty("baseUrl"))
						.addQueryParam("key","qaclick123")
						.addFilter(RequestLoggingFilter.logRequestTo(log))
						.addFilter(ResponseLoggingFilter.logResponseTo(log))
						.build();
				System.out.println("Add Place API Request Spec is built successfully");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return requestHead;
	}
	

	public ResponseSpecification responseSpecBuilder() {
		try
		{					
			responseHead=new ResponseSpecBuilder()
					.expectStatusCode(200)
					.expectContentType(ContentType.JSON)
					.build();

			System.out.println("Add Place API Response Spec is built successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return responseHead;
	}
}
