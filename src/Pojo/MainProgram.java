package Pojo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

//import org.testng.Assert;
import java.util.*;




public class MainProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 RestAssured.baseURI=("https://rahulshettyacademy.com");
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
		 String response = given().log().all().queryParam("Key","qaclick123").header("Content-Type","application/json").body(g).when()
		 .post("maps/api/place/add/json").then().log().all().statusCode(200).
		 body("scope", equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
		 System.out.println("Response = "+response);
		 JsonPath js = new JsonPath(response);
		 String placeId =js.getString("place_id");
		 System.out.println(placeId);
		 //Update place API
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
