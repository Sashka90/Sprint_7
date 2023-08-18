package ru.prakticum;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static ru.prakticum.Constants.COURIER_PATH;

public class CourierAccount extends BaseSettings {

    @Step("Логин курьера")
    public ValidatableResponse login(CourierAuthData credentials) {
        return given()
                .spec(getBaseUrlAndContentType())
                .body(credentials)
                .when()
                .post(COURIER_PATH + "login")
                .then();
    }

    @Step("Создание курьера")
    public ValidatableResponse create(Courier courier) {
        return given()
                .spec(getBaseUrlAndContentType())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then();
    }

    @Step("Удаление курьера")
    public ValidatableResponse delete(int courierId) {
        return given()
                .spec(getBaseUrlAndContentType())
                .when()
                .delete(COURIER_PATH + courierId)
                .then();
    }
}
