package ru.prakticum.factory;
import net.datafaker.Faker;
import ru.prakticum.Courier;

public class CourierFactory {

    public static Faker faker = new Faker();

    public static Courier getRandom() {
        String login = faker.name().fullName();
        String password = String.valueOf(faker.password());
        String firstName = faker.name().fullName();

        return new Courier(firstName, login, password);
    }
}
