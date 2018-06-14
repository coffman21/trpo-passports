package ru.iu5bmstu.orders.login;

public enum LogonState {
    LOGGED_IN,
    LOGGED_OUT,
    ERROR_WRONG_CREDENTIALS,
    ERROR_USER_BLOCKED,
    ERROR_UNKNOWN;

    @Override
    public String toString() {
        return super.toString();
    }


}
