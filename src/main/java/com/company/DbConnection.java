package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static String username;
    private static String password;

    public static void setUsername(String inputUsername) {
        username = inputUsername;
    }

    public static void setPassword(String inputPassword) {
        password = inputPassword;
    }

    public static Connection getConnection() throws SQLException {

        String connectionUrl = "jdbc:sqlserver://localhost;database=BikeStores;user=" + username + ";password=" + password + ";";

        return DriverManager.getConnection(connectionUrl);
    }
}
