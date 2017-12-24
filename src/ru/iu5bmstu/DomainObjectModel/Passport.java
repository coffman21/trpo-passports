package ru.iu5bmstu.DomainObjectModel;

public class Passport {
    private String name;
    private String dob;
    private String gender;
    private String city;
    private int id;

    private boolean status;
    private boolean given;
    public Passport(int id, String name, String dob, String gender, String city, boolean status) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.city = city;
        this.status = status;
        this.given = given;


    }



    public Object[] getValues() {
        return new Object[]{name, dob, gender, city, status};
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void invertStatus() {
        this.status = !this.status;
    }

    public boolean isGiven() {
        return given;
    }
}
