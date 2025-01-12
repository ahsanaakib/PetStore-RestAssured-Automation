package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndPoints {

	public static Response placeOrder(Store payload)
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		
		.when()
			.post(Routes.placeOrder_url);
		
		return response;
	}
	
	public static Response getOrderById(int orderId)
	{
		Response response = given()
			.pathParam("orderId", orderId)
		.when() 
			.get(Routes.getOrder_url);
		
		return response;
	}
	
	public static Response deleteOrder(int orderId)
	{
		Response response=given()
			.pathParam("orderId", orderId)	
		.when()
			.delete(Routes.deleteOrder_url);
		
		return response;
	}
	
	public static Response getInventory()
	{
		Response response= given()
			.when()
				.get(Routes.inventory_url);
		
		return response;
	}
	
}
