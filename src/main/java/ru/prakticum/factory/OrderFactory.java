package ru.prakticum.factory;

import net.datafaker.Faker;
import ru.prakticum.Order;

public class OrderFactory {

    public static Faker faker = new Faker();

    public static Order getOrderWithoutColor(String[] color) {
        String firstName = faker.name().toString();
        String lastName = faker.name().lastName();
        String address = faker.address().toString();
        String metroStation = faker.number().toString();
        String phone = faker.phoneNumber().toString();
        int rentTime = 15;
        String deliveryDate = "2023-05-20";
        String comment = "Комментарий";
        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }
}
