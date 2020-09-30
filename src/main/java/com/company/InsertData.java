package com.company;

import java.sql.*;

public class InsertData {
    public static void insertDataInDB() {

        ResultSet resultSet;
        String numberOfRecordsSql = "SELECT count(*) FROM [BikeStores].[sales].customers";


        try (Statement statement = DbConnection.getConnection().createStatement()) {

            resultSet = statement.executeQuery(numberOfRecordsSql);
            resultSet.next();
            System.out.println("Number of records in table before insertion: " + resultSet.getInt(1));


            String insertSql = "INSERT INTO sales.customers(first_name,last_name,phone,email,street,city,state,zip_code) VALUES('Test','Test','(111) 111-1111','test@gmail.com','123 test street','Test_city','TA','12345')";
            statement.executeUpdate(insertSql);


            resultSet = statement.executeQuery(numberOfRecordsSql);
            resultSet.next();
            System.out.println("Number of records in table after insertion: " + resultSet.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
