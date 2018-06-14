package ru.iu5bmstu.DomainObjectModel;

import ru.iu5bmstu.DomainObjectModel.Passport.Passport;
import ru.iu5bmstu.DomainObjectModel.Request.Request;
import ru.iu5bmstu.orders.printer.Printer;

import java.util.Date;
import java.util.List;

public class Department {
//    private long id;
    private String address;
    private List<Date> workingTime;

    private List<Request> requests;
    private List<Passport> passports;
    private List<Printer> printers;
}
