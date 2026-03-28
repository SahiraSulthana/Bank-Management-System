package common;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "Sahira@2003"; 

    public static Connection getConnection(String dbName) throws Exception {
        return DriverManager.getConnection(URL + dbName, USER, PASSWORD);
    }
}

