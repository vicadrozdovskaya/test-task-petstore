package api.test;

import api.payload.APIResponse;
import api.payload.User;
import api.services.UserService;
import com.github.javafaker.Faker;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class UserTests {
    Faker faker;
    User userPayload;
    UserService userService;

    public Logger logger;

    @BeforeClass
    public void setupData() {
        faker = new Faker();
        userPayload = new User();
        userService = new UserService();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUserName(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(7, 8));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        logger = LogManager.getLogger(this.getClass());
    }

    @Test
    public void checkUserCreate() {
        logger.info("creating user");
        APIResponse actualResponse = userService.createUser(userPayload);
        Assert.assertEquals(actualResponse.getCode(), HttpStatus.SC_OK);
        logger.info("user is created");
    }

    @Test(dependsOnMethods = {"checkUserCreate"})
    public void checkGetUserByName() {
        logger.info("get user information");
        User actualUser = userService.getUserByName(userPayload.getUserName(), User.class);
        userService.verifySuccessStatusCode();
        Assert.assertEquals(actualUser, userPayload, "Check that created User is equal");
    }

    @Test(dependsOnMethods = {"checkGetUserByName"})
    public void checkUserUpdateByName() {
        logger.info("updating user information");
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        userService.updateUserByName(userPayload);
        userService.verifySuccessStatusCode();

        logger.info("get user information");
        User actualUser = userService.getUserByName(userPayload.getUserName(), User.class);
        userService.verifySuccessStatusCode();
        Assert.assertEquals(actualUser, userPayload, "Check that created User is equal");
    }

    @Test(dependsOnMethods = {"checkUserUpdateByName"})
    public void checkUserDeleteByName() {
        logger.info("deleting user");
        userService.deleteUserByName(userPayload.getUserName());
        userService.verifySuccessStatusCode();

        userService.getUserByName(userPayload.getUserName(), APIResponse.class);
        userService.verifyNotFoundStatusCode();
    }
}
