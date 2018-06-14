package ru.iu5bmstu.orders.maker;

import ru.iu5bmstu.DomainObjectModel.Passport.Passport;
import ru.iu5bmstu.orders.printer.MyPrinter;

import java.util.List;

public abstract class MakerUI extends MyPrinter {

    private static List<Passport> passports;

    void loadPassports() {}

    void render() {}

    void fillPassport() {}

    void login() {}

    void logout() {}

    void hash() {}

    void printPassport() {}

    void editState() {}
}
