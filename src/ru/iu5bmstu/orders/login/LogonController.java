package ru.iu5bmstu.orders.login;

public class LogonController {

    private String username;
    private String password;

    public LogonState doLogin() {
        return LogonState.ERROR_UNKNOWN;
    }



    private static void checkCredentials(String usernameHash, String passwordHash) {
    }
}
