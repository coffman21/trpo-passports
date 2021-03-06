package ru.iu5bmstu.DomainObjectModel.Request;

import ru.iu5bmstu.DomainObjectModel.Name;
import ru.iu5bmstu.TableDataGateway.TableDataGateway;

import java.sql.SQLException;

public class Request {
    private int id;
    private int passportFk;
    private boolean status;
    private boolean given;
    private int departmentFk;


    private Name nameName;

    private String name;

    public Request(int id, int passportFk, boolean given, boolean status) throws SQLException {
        this.id = id;
        this.passportFk = passportFk;
        this.given = given;
        this.status = status;

        this.name = TableDataGateway.selectPassports("id", Integer.toString(this.passportFk)).get(0).getName();
        // TODO: reassign
        this.departmentFk = 1;
    }

    public Object[] getValues() {
        return new Object[]{id, passportFk, status, given};
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isStatus(){
        return status;
    }

    public boolean isGiven() {
        return given;
    }
}
