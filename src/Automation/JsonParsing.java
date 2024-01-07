package Automation;

import io.restassured.path.json.JsonPath;

public class JsonParsing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js = new JsonPath(PayLoad.coursePrice());
		int count  = js.getInt("courses.size()");
		System.out.println(count);
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		int sum = 0;
		for(int  i =0 ;i<count;i++) {
			String title = js.getString("courses["+i+"].title ");
			int price = js.getInt("courses["+i+"].price");
			System.out.print(title+" ");
			System.out.println (price);
		}
		for(int  i =0 ;i<count;i++) {
			String title = js.getString("courses["+i+"].title ");
			int copies = js.getInt("courses["+i+"].copies");
			if(title.equalsIgnoreCase("Cypress") ) {
				System.out.println(copies);
				break;
			}

		}
		for(int  i =0 ;i<count;i++) {
			int copies = js.getInt("courses["+i+"].copies");
			int price = js.getInt("courses["+i+"].price");
			int amount = copies * price;
			sum +=amount;
		}
	
		System.out.println(sum);


	}
}
