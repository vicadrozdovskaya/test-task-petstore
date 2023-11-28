package api.services;

import api.endpoints.OrderAPIObject;
import api.endpoints.PetAPIObject;
import api.payload.APIResponse;
import api.payload.Order;
import io.restassured.response.Response;

public class OrderService extends BaseService {
    public Order createOrder(Order payload) {
        response = OrderAPIObject.post(payload);
        response.then().log().all();
        return getEntity(Order.class);
    }

    public <E> E getOrderById(String orderId, Class<E> entityClass) {
        response = OrderAPIObject.get(orderId);
        response.then().log().all();
        return getEntity(entityClass);
    }

    public Response getOrderByStatus() {
        response = OrderAPIObject.get();
        response.then().log().all();
        return response;
    }

    public APIResponse deleteOrderById(String orderId) {
        response = OrderAPIObject.delete(orderId);
        response.then().log().all();
        return getEntity(APIResponse.class);
    }
}
