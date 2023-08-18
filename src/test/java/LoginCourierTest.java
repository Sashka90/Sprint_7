import io.qameta.allure.*;
import ru.prakticum.Courier;
import ru.prakticum.CourierAccount;
import ru.prakticum.CourierAuthData;
import ru.prakticum.factory.CourierFactory;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginCourierTest {

    private Courier courier;
    private CourierAccount courierAccount;
    private int courierId;

    @Before
    public void setUp() {
        courier = CourierFactory.getRandom();
        courierAccount = new CourierAccount();
        courierAccount.create(courier);
    }

    @Test
    @Feature("Login")
    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Успешная авторизация")
    @Description("/api/v1/courier/login")
    public void courierAuthPositiveTest() {
        ValidatableResponse response = courierAccount.login(CourierAuthData.from(courier));

        int statusCode = response.extract().statusCode();
        assertEquals(200, statusCode);
        courierId = response.extract().path("id");
        assertNotEquals(0, courierId);
    }

    @Test
    @Feature("Login")
    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Авторизация с неправильным паролем")
    @Description("/api/v1/courier/login")
    public void courierAuthIncorrectPassTest() {
        CourierAuthData data = new CourierAuthData(courier.getLogin(), "IncorrectPassword");
        ValidatableResponse response = courierAccount.login(data);
        courierId = courierAccount.login(CourierAuthData.from(courier)).extract().path("id");

        int statusCode = response.extract().statusCode();
        assertEquals(404, statusCode);
        String answer = response.extract().path("message");
        assertEquals("Учетная запись не найдена", answer);
    }

    @Test
    @Feature("Login")
    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Авторизация с неправильным логином")
    @Description("/api/v1/courier/login")
    public void courierAuthIncorrectLoginTest() {
        CourierAuthData data = new CourierAuthData("IncorrectLogin", courier.getPassword());
        ValidatableResponse response = courierAccount.login(data);
        courierId = courierAccount.login(CourierAuthData.from(courier)).extract().path("id");

        int statusCode = response.extract().statusCode();
        assertEquals(404, statusCode);
        String answer = response.extract().path("message");
        assertEquals("Учетная запись не найдена", answer);
    }

    @Test
    @Feature("Login")
    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Авторизация с пустым обязательным полем")
    @Description("/api/v1/courier/login")
    public void courierAuthEmptyPassword() {
        CourierAuthData data = new CourierAuthData(courier.getLogin(), "");
        ValidatableResponse response = courierAccount.login(data);
        courierId = courierAccount.login(CourierAuthData.from(courier)).extract().path("id");

        int statusCode = response.extract().statusCode();
        assertEquals(400, statusCode);
        String answer = response.extract().path("message");
        assertEquals("Недостаточно данных для входа", answer);
    }

}
