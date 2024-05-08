package org.claimsystem.g24fp.logic;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static final String URL = "jdbc:postgresql://localhost:5432/fp24";
    public static final String USER = "postgres";
    public static final String PASSWORD = "123456789";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            if (conn == null) {
                System.out.println("Failed to make connection!");
            } else {
                System.out.println("Connection established!");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return conn;
    }
}
