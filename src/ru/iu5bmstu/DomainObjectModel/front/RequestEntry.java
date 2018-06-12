package ru.iu5bmstu.DomainObjectModel.front;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.iu5bmstu.DomainObjectModel.Request.Request;

public class RequestEntry implements TableEntry{
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty given = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    public RequestEntry(Request request) {
        setId(String.valueOf(request.getId()));
        setName(request.getName());
        setGiven(request.isGiven() ? "Получен" : "Не получен");
        setStatus(request.isStatus() ? "Готов" : "В обработке");
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

    public final StringProperty givenProperty() {
        return this.given;
    }

    public final String getGiven() {
        return this.givenProperty().get();
    }

    public final void setGiven(final String s) {
        this.givenProperty().set(s);
    }

    public final StringProperty statusProperty() {
        return this.status;
    }

    public final String getStatus() {
        return this.statusProperty().get();
    }

    public final void setStatus(final String s) {
        this.statusProperty ().set(s);
    }

}
