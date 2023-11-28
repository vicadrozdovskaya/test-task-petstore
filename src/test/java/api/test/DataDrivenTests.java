package api.test;

import api.payload.APIResponse;
import api.payload.User;
import api.services.UserService;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static api.endpoints.UserAPIObject.post;
import static api.endpoints.UserAPIObject.delete;

public class DataDrivenTests {

    User userPayload;
    UserService userService;
    public Logger logger;

    @BeforeClass
    public void init() {
        userService = new UserService();
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void checkUsersCreate(String userId, String userName, String firstName, String lastName, String userEmail, String pass, String phoneNumber) {
        userPayload = new User();
        userPayload.setId(Integer.parseInt(userId));
        userPayload.setUserName(userName);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(userEmail);
        userPayload.setPassword(pass);
        userPayload.setPhone(phoneNumber);
        logger.info("creating user");
        APIResponse actualResponse = userService.createUser(userPayload);
        Assert.assertEquals(actualResponse.getCode(), HttpStatus.SC_OK);
        logger.info("user is created");
    }

    @Test(dependsOnMethods = "checkUsersCreate", dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void checkDeleteUsersByName(String userName) {
        logger.info("deleting user");
        userService.deleteUserByName(userName);
        userService.verifySuccessStatusCode();
    }
}
