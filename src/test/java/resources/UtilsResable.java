package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UtilsResable {
	public static RequestSpecification requestSpec;

	public RequestSpecification requestSpecification() throws IOException {

		// Below code need to use in every request
		// Below code written to add logger in framework
		if (requestSpec == null) {
			
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			requestSpec = new RequestSpecBuilder().setBaseUri(readGlobalProperties("baseURL"))
					.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
			return requestSpec;
		}
		return requestSpec;
	}

	// Below we write down code to fetch data from property file
	public static String readGlobalProperties(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"/Users/manojithape/Learn Automation Workspace/BDDCucumberRestAssuredFrameworkMJ/src/test/java/resources/Global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	//Below method reusable written to fetch key value from JSON response
	public String getJsonPath(Response response, String key) {
		String resp=response.asString();
		JsonPath js=new JsonPath(resp);
		return js.get(key).toString();		
	}
}
