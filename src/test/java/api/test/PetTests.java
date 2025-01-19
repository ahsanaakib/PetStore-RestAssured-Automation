package api.test;

import java.util.Arrays;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PetEndPoints;
import api.payload.Category;
import api.payload.Pet;
import api.payload.Tag;
import io.restassured.response.Response;

public class PetTests {

	Faker faker;
	Pet petPayload;
	public Logger logger;
	
	@BeforeClass
	public void setup()
	{
		faker = new Faker();
		petPayload = new Pet();
		
		petPayload.setId(faker.number().numberBetween(1, 10000));
		petPayload.setName(faker.animal().name());
		petPayload.setStatus("available");
		
		Category category = new Category();
		category.setId(faker.number().numberBetween(1, 100));
		category.setName(faker.commerce().department());
		petPayload.setCategory(category);
		
		petPayload.setPhotoUrls(Collections.singletonList(faker.internet().url()));
		
		Tag tag = new Tag();
		tag.setId(faker.number().numberBetween(1, 100));
		tag.setName(faker.color().name());
		petPayload.setTags(Arrays.asList(tag));	
		
		logger = LogManager.getLogger(this.getClass());
        logger.info("Pet payload initialized: " + petPayload);
	}
	
	@Test(priority = 1)
	public void testCreatePet()
	{
		logger.info("***** Starting test: testCreatePet *****");
		
		Response response =PetEndPoints.createPet(petPayload);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("Pet created successfully with status code: " + response.getStatusCode());
	}
	
	@Test(priority = 2)
	public void testReadPetById()
	{
		logger.info("***** Starting test: testReadPetById *****");
		
		Response response=PetEndPoints.readPet(petPayload.getId());
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("Fetched pet details successfully with status code: " + response.getStatusCode());
	}
	
	@Test(priority = 3)
	public void testUpdatePet()
	{
		logger.info("***** Starting test: testUpdatePet *****");
		
		//Update pet details
		petPayload.setName(faker.animal().name());
		petPayload.setStatus("sold");
		
		logger.info("Updating pet with new details: " + petPayload);
		
		Response response=PetEndPoints.updatePet(petPayload);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Pet updated successfully with status code: " + response.getStatusCode());
		
		//Verify updated details
		Response responseAfterUpdate =PetEndPoints.readPet(petPayload.getId());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		Assert.assertEquals(responseAfterUpdate.jsonPath().getString("status"), "sold");
		
		logger.info("Verified updated pet details successfully with status code: " + responseAfterUpdate.getStatusCode());
	}
	
	@Test(priority = 4)
	public void testDeletePetById()
	{
		logger.info("***** Starting test: testDeletePetById *****");
		
		Response response= PetEndPoints.deletePet(petPayload.getId());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("Pet deleted successfully with status code: " + response.getStatusCode());
		
		Response responseAfterDelete=PetEndPoints.readPet(petPayload.getId());
		
		Assert.assertEquals(responseAfterDelete.getStatusCode(), 404);
		
		logger.info("Verified pet is deleted successfully.");
	}
	
}
