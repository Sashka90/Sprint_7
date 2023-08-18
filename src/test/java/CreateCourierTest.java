import io.qameta.allure.*;
import ru.prakticum.Courier;
import ru.prakticum.CourierAccount;
import ru.prakticum.CourierAuthData;
import ru.prakticum.factory.CourierFactory;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateCourierTest {

    private Courier courier;
    private CourierAccount courierClient;
    private int courierId;

    @Before
    public void setUp() {
        courierClient = new CourierAccount();
    }

    @Test
    @Feature("Courier")
    @Story("Courier")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Успешное создание курьера")
    @Description("/api/v1/courier")
    public void courierCreatedTest() {
        courier = CourierFactory.getRandom();
        ValidatableResponse response = courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierAuthData.from(courier));

        int loginStatusCode = loginResponse.extract().statusCode();
        assertEquals(200, loginStatusCode);
        int statusCode = response.extract().statusCode();
        assertEquals(201, statusCode);
        courierId = loginResponse.extract().path("id");
        assertNotEquals(0, courierId);
        boolean isCreated = response.extract().path("ok");
        assertTrue(isCreated);
    }

    @Test
    @Feature("Courier")
    @Story("Courier")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание существующего курьера")
    @Description("/api/v1/courier")
    public void courierCreatedExitingTest() {
        Courier courier = new Courier(
                CourierFactory.getRandom().getLogin(),
                CourierFactory.getRandom().getPassword(),
                CourierFactory.getRandom().getFirstName());
        courierClient.create(courier);
        ValidatableResponse response = courierClient.create(courier);

        int statusCode = response.extract().statusCode();
        assertEquals(409, statusCode);
        String answer = response.extract().path("message");
        assertEquals("Этот логин уже используется. Попробуйте другой.", answer);
    }

    @Test
    @Feature("Courier")
    @Story("Courier")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание курьера без обязательного поля")
    @Description("/api/v1/courier")
    public void courierWithoutRequiredField() {
        Courier courier = new Courier(
                CourierFactory.getRandom().getLogin(),
                null,
                CourierFactory.getRandom().getFirstName());
        courierClient.create(courier);
        ValidatableResponse response = courierClient.create(courier);

        int statusCode = response.extract().statusCode();
        assertEquals(400, statusCode);
        String answer = response.extract().path("message");
        assertEquals("Недостаточно данных для создания учетной записи", answer);
    }

    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }

}
