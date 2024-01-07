package Automation;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class sumValidation {
	@Test
	public void validation() {
		JsonPath js = new JsonPath(PayLoad.coursePrice());
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		int count = js.getInt("courses.size()");
		int sum = 0;

		for(int  i =0 ;i<count;i++) {
			int copies = js.getInt("courses["+i+"].copies");
			int price = js.getInt("courses["+i+"].price");
			int amount = copies * price;
			sum +=amount;
		}
		System.out.println(sum);
		Assert.assertEquals(purchaseAmount, sum);
	}
}
