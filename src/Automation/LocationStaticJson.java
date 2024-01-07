package Automation;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class LocationStaticJson {

@Test
public void locationAPI() throws IOException {
RestAssured.baseURI=("https://rahulshettyacademy.com");


//Add Place API
String response = given().log().all().queryParam("Key","qaclick123").header("Content-Type","application/json")
.body(new String(Files.readAllBytes(Paths.get("/Users/shishir/Desktop/Location.json")))).
when().post("maps/api/place/add/json").then().log().all().statusCode(200).
body("scope", equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
System.out.println("Response = "+response);
JsonPath js = new JsonPath(response);
String placeId =js.getString("place_id");
System.out.println(placeId);

}

}
