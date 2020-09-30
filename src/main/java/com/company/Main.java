package com.company;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        String username;
        String password;

        try {
            username = args[0];
            password = args[1];
        } catch (Exception e) {
            System.out.println("Not enough arguments passed as input");
            return;
        }

        DbConnection.setUsername(username);
        DbConnection.setPassword(password);

        ReadData.readDataFromDB();
//        UpdateData.updateDataFromDB();
//        DeleteData.deleteDataFromDB();
//        InsertData.insertDataInDB();
//        BulkInsertData.bulkInsertDataInDB();
    }
}
