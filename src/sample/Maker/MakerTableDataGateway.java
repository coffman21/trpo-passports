package sample.Maker;

import sample.Passport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MakerTableDataGateway {
    Connection conn;
    private ArrayList arr;

    public MakerTableDataGateway() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/db1", "root", "1");
    }

    public ArrayList select(String param) throws SQLException {
        arr = new ArrayList();
        //Session s = sf.openSession();
        //arr = (ArrayList) s.createNativeQuery("SELECT * FROM passports;").list();
        String nativeQ = "SELECT * FROM passports;";
        ResultSet rs = conn.createStatement().executeQuery(nativeQ);
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String dob = rs.getString("dob");
            String gender = rs.getString("gender");
            String city = rs.getString("city");
            Passport p = new Passport(
                    id, name, dob, gender, city );
            arr.add(p);
        }
        return arr;
    }


    public void update(String name, String dob, String gender, String city) throws SQLException {
        //Session s = sf.openSession();
        String nativeQ = "insert into passports (name, dob, gender, city) values ('" +
                name+ "', '"+
                dob+ "', '"+
                gender+ "', '"+
                city+ "'); ";
        conn.createStatement().executeUpdate(nativeQ);


    }
}
