package api.test;

import api.payload.APIResponse;
import api.payload.Category;
import api.payload.Pet;
import api.payload.Tag;
import api.services.PetService;
import com.github.javafaker.Faker;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class PetTests {
    Faker faker;
    Pet petPayload;
    PetService petService;
    public Logger logger;

    @BeforeClass
    public void setupData() {
        faker = new Faker();
        petPayload = new Pet();
        petService = new PetService();

        Category category = new Category();
        category.setId(faker.idNumber().hashCode());
        category.setName(faker.pokemon().name());
        petPayload.setId(faker.idNumber().hashCode());
        petPayload.setCategory(category);
        petPayload.setName(faker.animal().name());
        petPayload.setPhotoUrls(List.of(faker.internet().image()));
        Tag tag = new Tag();
        tag.setId(faker.idNumber().hashCode());
        tag.setName(faker.ancient().hero());
        petPayload.setTags(List.of(tag));
        petPayload.setStatus("available");

        logger = LogManager.getLogger(this.getClass());
    }

    @Test
    public void checkPetCreated() {
        logger.info("creating pet");
        Pet actualPet = petService.createPet(petPayload);
        petService.verifySuccessStatusCode();
        Assert.assertEquals(actualPet, petPayload, "Check that created Pet is equal");
        logger.info("pet is created");

    }

    @Test(dependsOnMethods = {"checkPetCreated"})
    public void checkGetPetById() {
        logger.info("getting pet by ID");
        Pet actualPet = petService.getPetById(String.valueOf(petPayload.getId()), Pet.class);
        petService.verifySuccessStatusCode();
        Assert.assertEquals(actualPet, petPayload, "Check that created Pet is equal");
        logger.info("pet is got by ID");
    }

    @Test(dependsOnMethods = {"checkGetPetById"})
    public void checkPetUpdate() {
        logger.info("updating pet");
        petPayload.setName(faker.animal().name());
        petPayload.setPhotoUrls(List.of(faker.internet().image()));
        petService.updatePet(petPayload);
        petService.verifySuccessStatusCode();
        logger.info("pet is updated");

        logger.info("getting pet by ID");
        Pet actualPet = petService.getPetById(String.valueOf(petPayload.getId()), Pet.class);
        petService.verifySuccessStatusCode();
        Assert.assertEquals(actualPet, petPayload, "Check that created Pet is equal");
    }

    @Test(dependsOnMethods = {"checkPetUpdate"})
    public void checkPetDeleteById() {
        logger.info("deleting pet by ID");
        petService.deletePetByPetId(String.valueOf(petPayload.getId()));
        petService.verifySuccessStatusCode();
        logger.info("pet is deleted by ID");

        logger.info("getting pet by ID");
        petService.getPetById(String.valueOf(petPayload.getId()), APIResponse.class);
        petService.verifyNotFoundStatusCode();
    }

    @Test
    public void checkErrorWhenPetNotFound() {
        logger.info("getting pet by ID");
        petService.getPetById(String.valueOf(petPayload.getId()), APIResponse.class);
        petService.verifyStatusCodeAndErrorMessage(HttpStatus.SC_NOT_FOUND, "Pet not found");
    }
}
