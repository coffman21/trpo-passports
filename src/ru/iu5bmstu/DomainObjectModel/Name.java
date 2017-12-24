package ru.iu5bmstu.DomainObjectModel;

public class Name {
    private String surname;
    private String name;
    private String middlename;

    public Name(String n1, String n2, String n3) {
        this.surname = n1;
        this.name = n2;
        this.middlename = n3;
    }

    @Override
    public String toString() {
        return surname + " " + name + " " + middlename;
    }
}
