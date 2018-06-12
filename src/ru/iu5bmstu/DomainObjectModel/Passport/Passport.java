package ru.iu5bmstu.DomainObjectModel.Passport;

import ru.iu5bmstu.DomainObjectModel.Address;
import ru.iu5bmstu.DomainObjectModel.Department;
import ru.iu5bmstu.DomainObjectModel.Person;
import ru.iu5bmstu.DomainObjectModel.Request.Request;

import java.util.List;

public class Passport {
//    private long id;

    private String seriesNumber;
    private List<String> children;
    private String relations;
    private String photoLink;

    private Person namePerson;
    private Address address;

    public Passport(){

    }


}
