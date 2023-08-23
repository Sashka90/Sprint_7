package ru.prakticum;

public class CourierAuthData {

    private final String password;
    private final String login;

    public CourierAuthData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierAuthData from(Courier courier) {
        return new CourierAuthData(courier.getLogin(), courier.getPassword());
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }
}
