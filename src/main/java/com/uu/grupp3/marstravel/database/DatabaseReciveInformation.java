package com.uu.grupp3.marstravel.database;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseReciveInformation {
    public void showInfoFromDB(String type, String title, String tableName, int columnNumber) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);

        Connection connection = null;
        try {
            connection = DatabaseHandler.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT Information FROM " + tableName + " WHERE Namn = '" + type + "'";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String info = resultSet.getString(columnNumber);
                alert.setContentText(info);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Something went right or wrong, but it's over now.");
        }

        alert.showAndWait();
    }
}
