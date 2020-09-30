package com.company;

import java.sql.*;

public class UpdateData {

    public static void updateDataFromDB(){
        ResultSet resultSet;

        try (Statement statement = DbConnection.getConnection().createStatement()) {

            //Show initial value
            System.out.println("Initial DB data:");

            String selectSql = "SELECT * FROM [BikeStores].[production].products where product_id = '1'";
            resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
//                System.out.println(resultSet.getString(1) + "  " + resultSet.getString(2) + "  " +resultSet.getString(3) + "  " +resultSet.getString(4) + "  " +resultSet.getString(5) + "  " +resultSet.getString(6));

                ResultSetMetaData md = resultSet.getMetaData();
                int colCount = md.getColumnCount();

                for (int i = 1; i <= colCount ; i++){
                    System.out.print(resultSet.getString(i) + "  ");
                }
                System.out.println("\n");
            }


            //Update value
            String updateSql = "UPDATE [BikeStores].[production].products SET list_price = '111.11' WHERE product_id = '1'";
            statement.executeUpdate(updateSql);


            //Show updated value
            System.out.println("Updated DB data:");

            resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "  " + resultSet.getString(2) + "  " +resultSet.getString(3) + "  " +resultSet.getString(4) + "  " +resultSet.getString(5) + "  " +resultSet.getString(6));
            }



            //Return to initial value
            String updateToInitialValueSql = "UPDATE [BikeStores].[production].products SET list_price = '379.99' WHERE product_id = '1'";
            statement.executeUpdate(updateToInitialValueSql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
