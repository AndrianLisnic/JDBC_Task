package com.company;

import java.sql.*;

public class ReadData {

    public static void readDataFromDB(){
        ResultSet resultSet;

        try (Statement statement = DbConnection.getConnection().createStatement()) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT product_name, list_price FROM [BikeStores].[production].products";
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "  " + resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

