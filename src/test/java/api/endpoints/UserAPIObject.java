package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static api.endpoints.Routes.*;
import static io.restassured.RestAssured.given;

public class UserAPIObject {

    public static Response post(User payload) {
        return given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
                .log().all()
                .when().post(CREATE_USER);
    }

    public static Response get(String userName) {
        return given()
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .pathParam("username", userName)
                .log().all()
                .when().get(GET_USER);
    }

    public static Response update(User payload) {
        return given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .pathParam("username", payload.getUserName()).body(payload)
                .log().all()
                .when().put(UPDATE_USER);
    }

    public static Response delete(String userName) {
        return given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .pathParam("username", userName)
                .log().all()
                .when().delete(DELETE_USER);
    }
}
