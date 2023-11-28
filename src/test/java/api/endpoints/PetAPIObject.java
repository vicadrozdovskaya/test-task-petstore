package api.endpoints;

import api.payload.Pet;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static api.endpoints.Routes.*;
import static io.restassured.RestAssured.given;

public class PetAPIObject {

    public static Response post(Pet payload) {
        return given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
                .log().all()
                .when().post(CREATE_PET);
    }

    public static Response get(String petId) {
        return given()
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .pathParam("petId", petId)
                .log().all()
                .when().get(GET_PET);
    }

    public static Response update(Pet payload) {
        return given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(payload)
                .log().all()
                .when().put(UPDATE_PET);
    }

    public static Response delete(String petId) {
        return given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .pathParam("petId", petId)
                .log().all()
                .when().delete(DELETE_PET);
    }
}
