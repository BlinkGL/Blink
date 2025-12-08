package com.example.blink.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/blink_bdd";
        String user = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful!");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed.");
            return null;
        }
    }

    public static void main(String[] args) {
        getConnection();
    }
}
