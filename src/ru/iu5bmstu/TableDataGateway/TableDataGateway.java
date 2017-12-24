package ru.iu5bmstu.TableDataGateway;

import com.sun.javafx.logging.Logger;
import ru.iu5bmstu.DomainObjectModel.Passport;
import ru.iu5bmstu.DomainObjectModel.Request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

public class TableDataGateway {
    private static Logger logger = new Logger();
    private static Connection conn = createConnection("jdbc:mysql://localhost/db1", "root", "1");

    private static Connection createConnection(String url, String user, String pswd) {
        Connection c = null;
        try {
            c = DriverManager.getConnection(url, user, pswd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    // Запрос на получение данных
    public static ArrayList<Passport> selectPassports() throws SQLException {


        String nativeQ = "SELECT * FROM passports;";
        ResultSet rs = conn.createStatement().executeQuery(nativeQ);
        System.out.println("executed query: " + nativeQ);
        return rsHelperPassport(rs);
    }

    // Запрос с параметром
    public static ArrayList<Passport> selectPassports(String type, String value) throws SQLException {
        String nativeQ = "SELECT * FROM passports WHERE " + type + " LIKE \'%" + value + "%\';" ;
        if (type.equals("id")) {
            nativeQ = "SELECT * FROM passports WHERE " + type + " = \'" + value + "\';" ;
        }

        ResultSet rs = conn.createStatement().executeQuery(nativeQ);
        System.out.println("executed query: " + nativeQ);
        return rsHelperPassport(rs);
    }

    // Запрос с параметром
    public static ArrayList<Request> selectRequests(
            ArrayList<AbstractMap.SimpleEntry> params) throws SQLException {

        StringBuilder nativeQ = new StringBuilder("SELECT * FROM requests WHERE null");

        for (AbstractMap.SimpleEntry entry:params) {
            String type = (String) entry.getKey();
            String value = (String) entry.getValue();
            nativeQ.append(" OR " + type + " = " + value);
        };
        nativeQ.append(";");

        ResultSet rs = conn.createStatement().executeQuery(String.valueOf(nativeQ));
        System.out.println("executed query: " + nativeQ);
        return rsHelperRequest(rs);
    }

    // Запрос на вставку данных
    public static void alter(String name, String dob, String gender, String city) throws SQLException {
        //Session s = sf.openSession();

        String nativeQ = "insert into passports (name, dob, gender, city, status, given) values ('" +
                name+ "', '"+
                dob+ "', '"+
                gender+ "', '"+
                city+ "', '"+
                "0', '0'); ";

        conn.createStatement().executeUpdate(nativeQ);
        System.out.println("executed query: " + nativeQ);
    }

    //Установить значение поля строки
    public static void updateRow(int id, String param, String value) throws SQLException {

        String nativeQ = "update passports set " +
                param + " = '" + value + "' where id = " + id +";";
        conn.createStatement().executeUpdate(nativeQ);
        System.out.println("executed query: " + nativeQ);
    }

    // Удалить строку
    public static void deleteRow(int id) throws SQLException {

        String nativeQ = "delete from passports " +
                " where id = " + id +";";
        conn.createStatement().executeUpdate(nativeQ);
        System.out.println("executed query: " + nativeQ);
    }

    // Выбрать пользователей по статусу
    public static ArrayList<Passport> selectByStatus(boolean status) throws SQLException {
        String nativeQ = "SELECT * FROM passports WHERE STATUS = " + (status ? 1 : 0) + ";";
        ResultSet rs = conn.createStatement().executeQuery(nativeQ);

        System.out.println("executed query: " + nativeQ);

        return rsHelperPassport(rs);
    }

    // Выбрать пользователей по имени
    public static Passport selectByName(String name) throws SQLException {
        String nativeQ = "SELECT * FROM passports WHERE name = '" + name + "';";
        ResultSet rs = conn.createStatement().executeQuery(nativeQ);
        System.out.println("executed query: " + nativeQ);

        ArrayList rsh = rsHelperPassport(rs);

        return rsh.size() == 0 ? null : (Passport) rsh.get(0);
    }




    private static ArrayList<Passport> rsHelperPassport(ResultSet rs) throws SQLException {
        ArrayList<Passport> arr = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            if (name == null) { name = ""; }
            String dob = rs.getString("dob");
            if (dob == null) { dob = ""; }
            String gender = rs.getString("gender");
            String city = rs.getString("city");
            if (city == null) { city = ""; }
            boolean status = rs.getBoolean("status");

            Passport p = new Passport(
                    id, name, dob, gender, city, status );
            arr.add(p);
        }
        return arr;
    }

    private static ArrayList<Request> rsHelperRequest(ResultSet rs) throws SQLException {
        ArrayList<Request> arr = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            int passportFk = rs.getInt("passports_fk");
            boolean given = rs.getBoolean("given");
            boolean status = rs.getBoolean("status");

            Request r = new Request(
                    id, passportFk, given, status );
            arr.add(r);
        }
        return arr;
    }
}
