package Automation;

//import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;

//import org.testng.Assert;
import java.util.*;

import Pojo.GeoLocation;
import Pojo.Location;




public class SpecBuilderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 GeoLocation g = new GeoLocation();
		 Location l = new Location();
		 l.setLat(-38.383494);
		 l.setLng(33.427362);
		 g.setLocation(l);	
		 g.setAccuracy(50);
		 g.setName("Frontline house");
		 g.setPhone_number("(+91) 983 893 3937");
		 g.setAddress("29, side layout, cohen 09");
		 List<String> myList = new ArrayList<String>();
		 myList.add("Shoe Park");
		 myList.add("Shop");
		 g.setTypes(myList);
		 g.setWebsite("http://google.com");
		 g.setLanguage("French-IN");
		 //Add Place API 
		 RequestSpecification  req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
				 addQueryParam("Key","qaclick123").addHeader("Content-Type","application/json").build();
		 RequestSpecification reqspec = given().spec(req).log().all().body(g);
		 ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 Response response = reqspec.when().post("maps/api/place/add/json").then().log().all().spec(res).extract().response();
		 String responseString = response.asString();
		 System.out.println(responseString);
//			String address = "70 Summer walk, USA";

//		 given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(PayLoad.UpdatePlace(placeId, address))
//		 .when().put("maps/api/place/update/json").then().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
//	
	//Get place API
//	String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
//	 .when().get("maps/api/place/get/json").then().log().all().statusCode(200).extract().response().asString();
	
//	JsonPath js1 = new JsonPath(getPlaceResponse);
//	String actualAddress = js1.getString("address");
//	System.out.println(actualAddress);
//	Assert.assertEquals(actualAddress, address);
	
}
}
