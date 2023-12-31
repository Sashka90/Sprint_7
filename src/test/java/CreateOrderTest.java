import io.qameta.allure.*;
import ru.prakticum.Order;
import ru.prakticum.OrderClient;
import ru.prakticum.factory.OrderFactory;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private Order order;
    private OrderClient orderClient;
    private final String[] color;

    public CreateOrderTest(String color) {
        this.color = new String[]{color};
    }

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Parameterized.Parameters
    public static Object[][] setColor() {
        return new Object[][]{
                {"BLACK"},
                {"GREY"},
                {""},
                {"BLACK , GREY"}
        };
    }

    @Test
    @Feature("Order")
    @Story("Order")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание заказа с разными цветами")
    @Description("/api/v1/orders")
    public void orderCreatedTest() {
        order = OrderFactory.getOrderWithoutColor(color);
        ValidatableResponse response = orderClient.createOrder(order);

        int statusCode = response.extract().statusCode();
        assertEquals(201, statusCode);
        int track = response.extract().path("track");
        assertNotEquals(0, track);
    }
}
