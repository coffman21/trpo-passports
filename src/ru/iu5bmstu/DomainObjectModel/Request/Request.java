package ru.iu5bmstu.DomainObjectModel.Request;

import com.sun.istack.internal.Nullable;
import ru.iu5bmstu.DomainObjectModel.Department;
import ru.iu5bmstu.DomainObjectModel.Passport.Passport;
import ru.iu5bmstu.DomainObjectModel.Person;
import ru.iu5bmstu.TableDataGateway.TableDataGateway;

import java.sql.SQLException;

public class Request {
//    private long id;
    private Passport passport;
    private boolean status;
    private boolean given;

    private Person person;

    private String name;

    public Request() {}

    public String checkStatus() {
        return null;
    }
}
