package ru.iu5bmstu.orders.printer;

import ru.iu5bmstu.DomainObjectModel.Passport.Passport;

public abstract class MyPrinter implements Printer {

    public PrinterStatus sendToPrint(Passport passport) {
        return PrinterStatus.HALTED;
    }

    public PrinterStatus getPassportData(Passport passport) {
        return PrinterStatus.HALTED;
    }

    public PrinterStatus getStatus() {
        return PrinterStatus.READY;
    }

    public boolean storePassport(Passport passport) {
        return true;
    }

    public void changeStatus() {

    }
}
