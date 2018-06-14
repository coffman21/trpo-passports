package ru.iu5bmstu.orders.printer;

import ru.iu5bmstu.DomainObjectModel.Passport.Passport;

public interface Printer {

    String print(Passport passport);

    String getState(Passport passport);

    void halt();
}
