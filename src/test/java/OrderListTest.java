import io.qameta.allure.*;
import ru.prakticum.OrderClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class OrderListTest {

    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("После создания заказа список заказов не пустой")
    @Feature("Orders")
    @Story("Orders")
    @Severity(SeverityLevel.NORMAL)
    @Description("/api/v1/orders")
    public void orderListIsNotEmptyTest() {
        ValidatableResponse response = orderClient.getOrderList();

        int statusCode = response.extract().statusCode();
        assertEquals(200, statusCode);

        List<String> bodyAnswer = response.extract().path("orders");
        assertFalse(bodyAnswer.isEmpty());
    }
}
