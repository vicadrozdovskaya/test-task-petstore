package api.test;

import api.payload.APIResponse;
import api.payload.Order;
import api.payload.Order;
import api.services.OrderService;
import com.github.javafaker.Faker;
import io.restassured.common.mapper.TypeRef;
import io.restassured.internal.http.ContentEncoding;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class StoreTests {

    private static final String AVAILABLE = "available";
    Faker faker;
    Order orderPayload;
    OrderService orderService;
    public Logger logger;

    @BeforeClass
    public void setupData() {
        faker = new Faker();
        orderPayload = new Order();
        orderService = new OrderService();

        orderPayload.setId(faker.idNumber().hashCode());
        orderPayload.setPetId(faker.idNumber().hashCode());
        orderPayload.setQuantity(faker.number().numberBetween(1, 5));
        orderPayload.setShipDate(faker.date().future(1, TimeUnit.HOURS));
        orderPayload.setStatus("placed");
        orderPayload.setComplete(false);

        logger = LogManager.getLogger(this.getClass());
    }

    @Test
    public void checkOrderCreated() {
        logger.info("creating order");
        Order actualOrder = orderService.createOrder(orderPayload);
        orderService.verifySuccessStatusCode();
        Assert.assertEquals(actualOrder, orderPayload, "Check that created Order is equal");
        logger.info("order is created");
    }

    @Test(dependsOnMethods = {"checkOrderCreated"})
    public void checkGetOrderById() {
        logger.info("getting order by ID");
        Order actualOrder = orderService.getOrderById(String.valueOf(orderPayload.getId()), Order.class);
        orderService.verifySuccessStatusCode();
        Assert.assertEquals(actualOrder, orderPayload, "Check that created Order is equal");
        logger.info("order is got by ID");
    }

    @Test(dependsOnMethods = {"checkGetOrderById"})
    public void checkGetOrderStatus() {
        logger.info("getting order statuses");
        Map<String, String> actual = orderService.getOrderByStatus().body().as(new TypeRef<Map<String, String>>() {
        });
        orderService.verifySuccessStatusCode();
        Assert.assertTrue(actual.containsKey(AVAILABLE), "Check that " + AVAILABLE + " status exist in response");
        logger.info("order statuses is got");
    }

    @Test(dependsOnMethods = {"checkGetOrderStatus"})
    public void checkDeleteOrderById() {
        logger.info("deleting order by ID");
        orderService.deleteOrderById(String.valueOf(orderPayload.getId()));
        orderService.verifySuccessStatusCode();
        logger.info("order by ID");

        orderService.getOrderById(String.valueOf(orderPayload.getId()), APIResponse.class);
        orderService.verifyNotFoundStatusCode();
    }

    @Test
    public void checkErrorWhenOrderNotFound() {
        logger.info("getting order by ID");
        orderService.getOrderById(String.valueOf(orderPayload.getId()), APIResponse.class);
        orderService.verifyStatusCodeAndErrorMessage(HttpStatus.SC_NOT_FOUND, "Order not found");
    }
}
