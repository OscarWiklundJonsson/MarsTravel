package com.uu.grupp3.marstravel.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseWriteInformation {

    public void writeTravelChoicesToDB() {
        String fileName = "travelChoices.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
             Connection connection = DatabaseHandler.getConnection()) {

            String line;
            while ((line = reader.readLine()) != null) {
                writeLineToDB(connection, line);
            }
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Stuff som borde göras om. @todo
    private void writeLineToDB(Connection connection, String line) throws SQLException {
        String sql = "INSERT INTO your_table (column_name) VALUES (?)"; // ändra detta till korrekt

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, line);
            statement.executeUpdate();
        }
    }
}