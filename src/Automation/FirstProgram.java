package Automation;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;


public class FirstProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 RestAssured.baseURI=("https://rahulshettyacademy.com");
		 
		 //Add Place API
		 String response = given().log().all().queryParam("Key","qaclick123").header("Content-Type","application/json").body(PayLoad.AddPlace()).when()
		 .post("maps/api/place/add/json").then().log().all().statusCode(200).
		 body("scope", equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
		 System.out.println("Response = "+response);
		 JsonPath js = new JsonPath(response);
		 String placeId =js.getString("place_id");
		 System.out.println(placeId);
		 //Update place API
			String address = "70 Summer walk, USA";

		 given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(PayLoad.UpdatePlace(placeId, address))
		 .when().put("maps/api/place/update/json").then().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
	//Get place API
	String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
	 .when().get("maps/api/place/get/json").then().log().all().statusCode(200).extract().response().asString();
	
	JsonPath js1 = new JsonPath(getPlaceResponse);
	String actualAddress = js1.getString("address");
	System.out.println(actualAddress);
	Assert.assertEquals(actualAddress, address);
	
}
}
