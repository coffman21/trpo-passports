package ru.iu5bmstu.DomainObjectModel.front;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.iu5bmstu.DomainObjectModel.Passport.Passport;

public class PassportEntry implements TableEntry {
    private Passport passport;
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty dob = new SimpleStringProperty();
    private final StringProperty city = new SimpleStringProperty();

    public PassportEntry(Passport passport) {
        setId(String.valueOf(passport.getId()));
        setName(passport.getName());
        setDob(passport.getDob());
        setCity(passport.getCity());
    }


    public final StringProperty idProperty() {
        return this.id;
    }

    public final String getId() {
        return this.idProperty().get();
    }

    public final void setId(final String street) {
        this.idProperty().set(street);
    }

    public final StringProperty nameProperty() {
        return this.name;
    }

    public final String getName() {
        return this.nameProperty().get();
    }

    public final void setName(final String s) {
        this.nameProperty().set(s);
    }

    public final StringProperty dobProperty() {
        return this.dob;
    }

    public final String getDob() {
        return this.dobProperty().get();
    }

    public final void setDob(final String s) {
        this.dobProperty().set(s);
    }

    public final StringProperty cityProperty() {
        return this.city;
    }

    public final String getCity() {
        return this.cityProperty().get();
    }

    public final void setCity(final String s) {
        this.cityProperty().set(s);
    }

}
