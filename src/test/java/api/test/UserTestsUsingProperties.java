package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPointsUsingProperties;
import api.payload.User;
import io.restassured.response.Response;

public class UserTestsUsingProperties {

	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setup() 
	{
		faker = new Faker();
		userPayload = new User();			
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger= LogManager.getLogger(this.getClass());
		
        logger.info("User payload initialized: " + userPayload);

	
	}

	@Test(priority = 1)
	public void testPostUser() 
	{
		logger.info("***** Starting test: testPostUser *****");
		
		Response response=UserEndPointsUsingProperties.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);	
		
		logger.info("User created successfully with status code: " + response.getStatusCode());
	}
	
	@Test(priority = 2)
	public void testGetUserByName()
	{
		logger.info("***** Starting test: testGetUserByName *****");
		
		Response response =UserEndPointsUsingProperties.readUser(this.userPayload.getUsername());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("Fetched user details successfully with status code: " + response.getStatusCode());
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName()
	{
		logger.info("Starting test: testUpdateUserByName");
		
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		logger.info("Updating user with new details: " + userPayload);
		
		Response response =UserEndPointsUsingProperties.updateUser(this.userPayload.getUsername() ,userPayload );
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("User updated successfully with status code: " + response.getStatusCode());
		
		Response responseAfterUpdate =UserEndPointsUsingProperties.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);	
		
		logger.info("Verified updated user details successfully with status code: " + responseAfterUpdate.getStatusCode());
	}
	
	@Test(priority = 4)
	public void testDeleteUserByName()
	{
		logger.info("***** Starting test: testDeleteUserByName *****");
		
		Response response=UserEndPointsUsingProperties.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("User deleted successfully with status code: " + response.getStatusCode());
	}
}
