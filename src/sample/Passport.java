package sample;

public class Passport {
    private String name;
    private String dob;
    private String gender;
    private String city;
    private int id;

    public Passport(int id, String name, String dob, String gender, String city) {
        this.id = id;
        this.name = name;
        this.dob = gender;
        this.gender = gender;
        this.city = city;
    }



    public String[] getValues() {
        return new String[]{name, dob, gender, city};
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
}
