package Ecommerce;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
//import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import Pojo.LoginDetails;
import Pojo.LoginResponse;
import Pojo.OrderDetails;
//import Pojo.OrderResponse;
import Pojo.Orders;


public class EndToEndEcommerceApiTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Login
		LoginDetails loginDetails = new LoginDetails();
		loginDetails.setUserEmail("yesthatsright@gmail.com");
		loginDetails.setUserPassword("0854085408A@a");
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		RequestSpecification reqSpec = given().log().all().spec(req).body(loginDetails);
		LoginResponse loginResponse = reqSpec.when().post("api/ecom/auth/login").then().log().all().extract().response().as(LoginResponse.class);
		System.out.println(loginResponse.getToken());
		String token = loginResponse.getToken();
		System.out.println(loginResponse.getUserId());
		String userId = loginResponse.getUserId();
		//Create product
		RequestSpecification reqCreateProduct = new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com").addHeader("Authorization",token).build();
		RequestSpecification request =given().spec(reqCreateProduct).param("productName","qwerty").param("productAddedBy",userId)
		.param("productCategory","fashion").param("productSubCategory","shirts").param("productPrice","11500").param("productDescription","Addias Originals").
		param("productFor","women").multiPart("productImage",new File("/Users/shishir/Postman/files/7834df02451f1f377a3bf9ac6d5f217b.jpeg"));
		String response = request.when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();
		JsonPath jsonPath = new JsonPath(response);
		String productId = jsonPath.get("productId");
		System.out.println(productId);
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setCountry("india");
		orderDetails.setProductOrderedId(productId);
		List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
		orderDetailsList.add(orderDetails);
		Orders orders = new Orders();
		orders.setOrders(orderDetailsList);
		
		RequestSpecification reqCreateOrder = new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com").addHeader("Authorization",token)
				.setContentType(ContentType.JSON).build();
		RequestSpecification createOrderReq = given().spec(reqCreateOrder).body(orders);
		String orderResponse = createOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
		System.out.println(orderResponse);
//		JsonPath jsonPath1 = new JsonPath(orderResponse);
//		OrderResponse orderResponse = new OrderResponse();
//		List<String> list = new ArrayList<String>();
//		list.add(response)
		RequestSpecification deleteOrderReq = new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com").addHeader("Authorization",token)
		.addPathParam("productId", productId).build();
		String deleteResponse= given().spec(deleteOrderReq).when().delete("api/ecom/product/delete-product/{productId}").then().log().all().extract().response().asString();
		JsonPath js= new JsonPath(deleteResponse);
		String expectedMessage = js.get("message");
		System.out.println(expectedMessage);
		String actualMessage = "Product Deleted Successfully";
		Assert.assertEquals(expectedMessage, actualMessage);
		
		

	}

}
