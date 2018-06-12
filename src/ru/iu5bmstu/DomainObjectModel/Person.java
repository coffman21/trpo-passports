package ru.iu5bmstu.DomainObjectModel;

import java.util.Date;

public class Person {
//    private long id;
    private String surname;
    private String name;
    private String middlename;
    private String gender;
    private Date dob;


    @Override
    public String toString() {
        return surname + " " + name + " " + middlename;
    }
}
