package api.test;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import io.restassured.response.Response;

public class StoreTests {

	Faker faker;
	Store storePayload;
	public Logger logger;
	
	@BeforeClass
	public void setup()
	{
		faker = new Faker();
		storePayload = new Store();
		
		storePayload.setId(faker.number().numberBetween(1, 1000));
		storePayload.setPetId(faker.number().numberBetween(1, 100));
		storePayload.setQuantity(faker.number().numberBetween(1, 10));
		storePayload.setShipDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
			    .format(faker.date().future(10, TimeUnit.DAYS)));
		storePayload.setStatus("placed");
		storePayload.setComplete(true);
		
		logger = LogManager.getLogger(this.getClass());
        logger.info("Store payload initialized: " + storePayload);
	}
	
	@Test(priority = 1)
	public void testPlaceOrder()
	{
		logger.info("***** Starting test: testPlaceOrder *****");
		
		Response response=StoreEndPoints.placeOrder(storePayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("Order placed successfully with status code: " + response.getStatusCode());
	}
	
	@Test(priority = 2)
	public void testGetOrderById()
	{
		logger.info("***** Starting test: testGetOrderById *****");
		
		Response response=StoreEndPoints.getOrderById(this.storePayload.getId());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("Fetched order details successfully with status code: " + response.getStatusCode());
	}
	
	@Test(priority = 3)
	public void testDeleteOrderById()
	{
		logger.info("***** Starting test: testDeleteOrderById *****");
		
		Response response=StoreEndPoints.deleteOrder(this.storePayload.getId());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("Order deleted successfully with status code: " + response.getStatusCode());
		
		Response responseAfterDeletion=StoreEndPoints.getOrderById(this.storePayload.getId());
		
		Assert.assertEquals(responseAfterDeletion.getStatusCode(), 404);
		
		logger.info("Verified order deletion successfully.");
	}
	
	@Test(priority = 4)
	public void testGetInventory()
	{
		logger.info("***** Starting test: testGetInventory *****");
		
		Response response=StoreEndPoints.getInventory();
		response.then().log().all();
				
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("Fetched inventory successfully with status code: " + response.getStatusCode());
	}
}
