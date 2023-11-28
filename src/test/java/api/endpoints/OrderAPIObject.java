package api.endpoints;

import api.payload.Order;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static api.endpoints.Routes.*;
import static io.restassured.RestAssured.given;

public class OrderAPIObject {
    public static Response post(Order payload) {
        return given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
                .log().all()
                .when().post(CREATE_ORDER);
    }

    public static Response get(String orderId) {
        return given()
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .pathParam("orderId", orderId)
                .log().all()
                .when().get(GET_ORDER_BY_ID);
    }

    public static Response get() {
        return given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .log().all()
                .when().get(GET_ORDER_BY_STATUS);
    }

    public static Response delete(String orderId) {
        return given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .pathParam("orderId", orderId)
                .log().all()
                .when().delete(DELETE_ORDER);
    }
}
