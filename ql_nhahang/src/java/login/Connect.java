package login;


import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

    public Connection getConnection() {
        Connection conn = null;
        try {
            //Establish the connection
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/ql_cuahang","root","bocap_xinhdepdep1188");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;       
    }
}
