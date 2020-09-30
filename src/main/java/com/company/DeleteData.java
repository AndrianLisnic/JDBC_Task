package com.company;

import java.sql.*;

public class DeleteData {

    public static void deleteDataFromDB() {

        ResultSet resultSet;
        String numberOfRecordsSql = "SELECT count(*) FROM [BikeStores].[production].products";


        try (Statement statement = DbConnection.getConnection().createStatement()) {

            resultSet = statement.executeQuery(numberOfRecordsSql);
            resultSet.next();
            System.out.println("Number of records in table before deletion: " + resultSet.getInt(1));


            String deleteSql = "DELETE FROM production.products WHERE model_year = '2019'";
            statement.executeUpdate(deleteSql);


            resultSet = statement.executeQuery(numberOfRecordsSql);
            resultSet.next();
            System.out.println("Number of records in table after deletion: " + resultSet.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
