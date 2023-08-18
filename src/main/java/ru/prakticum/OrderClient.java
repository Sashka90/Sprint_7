package ru.prakticum;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static ru.prakticum.Constants.ORDER_CREATE_URL;

public class OrderClient extends BaseSettings {

    @Step("Создание заказа")
    public ValidatableResponse createOrder(Order order) {
        return given()
                .spec(getBaseUrlAndContentType())
                .body(order)
                .when()
                .post(ORDER_CREATE_URL)
                .then();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getOrderList() {
        return given()
                .spec(getBaseUrlAndContentType())
                .when()
                .get(ORDER_CREATE_URL)
                .then();
    }
}
