package com.uu.grupp3.marstravel.database;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hämtar hem information från databasen baserat på givna args
 * @param , type, title, tableName, columnNumber
 * type = vilken information som skall hämtas, budget1, svit osv
 * title = Vad ska info fönstret heta ?
 * tableName = från vilken tabell ska det hämtas?
 * columnNumber = Från vilken kolumn ska det hämtas ifrån(?)
 *
 * pilla inte, då går allt sönder, och det är ju inte kul
 */
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
            ResultSet resultSet = statement.executeQuery(query); // Unsafe? gråt då

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
