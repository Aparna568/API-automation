package Automation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static  io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class LibraryAPI {

//	static String aisle = "0020";
//	static String  isbn = "hjyt";
@Test(dataProvider = "addBook")
public static void library(String isbn , int aisle ) {
	 RestAssured.baseURI=("http://216.10.245.166");
	 String addResponse = given().log().all().header("Content-Type","application/json").body(PayLoad.addBook(isbn,aisle)).
	 when().post("Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().response().asString();
	 
	JsonPath js = new JsonPath(addResponse);
	String id=js.get("ID");
	System.out.println(id);
	
	String deleteResponse = given().log().all().header("Content-Type","application/json").body(addResponse).
			 when().delete("Library/DeleteBook.php").then().log().all().assertThat().statusCode(200).extract().response().asString();
	JsonPath js1 = new JsonPath(deleteResponse);
	System.out.println(js1.getString("msg"));
}
	@DataProvider(name ="addBook")
	public  static Object[][]  trialRun() {
		return new Object[][] {
			{"gfyu",001},
			{"ggiu",002},
			{"gtrt",003},
			{"ggip",004}
			
		};

	
}
}
