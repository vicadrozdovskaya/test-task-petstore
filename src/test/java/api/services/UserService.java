package api.services;

import api.endpoints.UserAPIObject;
import api.payload.APIResponse;
import api.payload.User;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;

public class UserService extends BaseService {

    public APIResponse createUser(User payload) {
        response = UserAPIObject.post(payload);
        response.then().log().all();
        return getEntity(APIResponse.class);
    }

    public <E> E getUserByName(String userName, Class<E> entityClass) {
        response = UserAPIObject.get(userName);
        response.then().log().all();
        return getEntity(entityClass);
    }

    public APIResponse updateUserByName(User payload) {
        response = UserAPIObject.update(payload);
        response.then().log().all();
        return getEntity(APIResponse.class);
    }

    public APIResponse deleteUserByName(String userName) {
        response = UserAPIObject.delete(userName);
        response.then().log().all();
        return getEntity(APIResponse.class);
    }

}
