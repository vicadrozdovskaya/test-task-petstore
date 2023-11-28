package api.services;

import api.payload.APIResponse;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;

public class BaseService {

    protected Response response;

    public void verifySuccessStatusCode() {
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }

    protected <E> E getEntity(Class<E> entityClass) {
        return response.as(entityClass);
    }

    public void verifyNotFoundStatusCode() {
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }

    public void verifyStatusCodeAndErrorMessage(int statusCode, String message) {
        Assert.assertEquals(response.getStatusCode(), statusCode);
        Assert.assertEquals(getEntity(APIResponse.class).getMessage(), message);
    }
}
