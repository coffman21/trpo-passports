package ru.iu5bmstu.DomainObjectModel;

import ru.iu5bmstu.DomainObjectModel.Passport.Passport;

public interface Printer {

    void print(Passport passport);

    String getState();
}
