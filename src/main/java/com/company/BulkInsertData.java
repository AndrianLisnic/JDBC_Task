package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class BulkInsertData {
    public static void bulkInsertDataInDB() {

        String csvFilePath = "data.csv";
        String numberOfRecordsSql = "SELECT count(*) FROM [BikeStores].[sales].customers";
        String sql = "INSERT INTO sales.customers (first_name,last_name,phone,email,street,city,state,zip_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        ResultSet resultSet;
        int batchSize = 20;

        try (Connection connection = DbConnection.getConnection();
                Statement statement = connection.createStatement()) {

            resultSet = statement.executeQuery(numberOfRecordsSql);
            resultSet.next();
            System.out.println("Number of records in table before insertion: " + resultSet.getInt(1));


            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText;
            int count = 0;

            lineReader.readLine(); // skip header line

            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String first_name = data[0];
                String last_name = data[1];
                String phone = data[2];
                String email = data[3];
                String street = data[4];
                String city = data[5];
                String state = data[6];
                String zip_code = data[7];

                count++;

                preparedStatement.setString(1, first_name);
                preparedStatement.setString(2, last_name);
                preparedStatement.setString(3, phone);
                preparedStatement.setString(4, email);
                preparedStatement.setString(5, street);
                preparedStatement.setString(6, city);
                preparedStatement.setString(7, state);
                preparedStatement.setString(8, zip_code);

                preparedStatement.addBatch();

                if (count % batchSize == 0) {
                    preparedStatement.executeBatch();
                }
            }

            lineReader.close();

            preparedStatement.executeBatch(); // execute the remaining queries

            connection.commit();

            resultSet = statement.executeQuery(numberOfRecordsSql);
            resultSet.next();
            System.out.println("Number of records in table after insertion: " + resultSet.getInt(1));

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}

