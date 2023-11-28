package api.endpoints;

public class Routes {
    public static String BASE_URL = "https://petstore.swagger.io/v2/";

    //USER
    public static String CREATE_USER = BASE_URL + "user";
    public static String GET_USER = BASE_URL + "user/{username}";
    public static String UPDATE_USER = BASE_URL + "user/{username}";
    public static String DELETE_USER = BASE_URL + "user/{username}";

    //PET
    public static String CREATE_PET = BASE_URL + "pet";
    public static String GET_PET = BASE_URL + "pet/{petId}";
    public static String UPDATE_PET = BASE_URL + "pet";
    public static String DELETE_PET = BASE_URL + "pet/{petId}";

    //STORE
    public static String CREATE_ORDER = BASE_URL + "store/order";
    public static String GET_ORDER_BY_ID = BASE_URL + "store/order/{orderId}";
    public static String DELETE_ORDER = BASE_URL + "store/order/{orderId}";
    public static String GET_ORDER_BY_STATUS = BASE_URL + "store/inventory";

}
