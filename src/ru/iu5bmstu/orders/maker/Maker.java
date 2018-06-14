package ru.iu5bmstu.orders.maker;

import ru.iu5bmstu.DomainObjectModel.Passport.Passport;
import ru.iu5bmstu.orders.printer.MyPrinter;

public abstract class Maker extends MyPrinter {
    public void login() {

    }

    public void logout() {

    }

    public void showRequests() {

    }

    public void showPassports() {

    }

    public Passport editPassport (Passport passport) {
        return passport;
    }

    public void getRequest() {}
}
