package com.example.blink.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/blink_bdd";
    private static final String USER = "postgres";
    private static final String PASSWORD = "ID#yassine2004";

    public static Connection getConnection() {
        try {
            // Class.forName is no longer needed in modern JDBC
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("PostgreSQL connection successful!");
            return connection;

        } catch (SQLException e) {
            System.out.println("PostgreSQL connection failed!");
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}