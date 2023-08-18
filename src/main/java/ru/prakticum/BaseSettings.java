package ru.prakticum;

import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

import static ru.prakticum.Constants.BASE_URL;

public class BaseSettings {

    public RequestSpecification getBaseUrlAndContentType() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }
}
