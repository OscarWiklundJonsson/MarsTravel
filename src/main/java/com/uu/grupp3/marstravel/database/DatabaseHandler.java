package com.uu.grupp3.marstravel.database;

import java.sql.*;

public class DatabaseHandler {
    private static final String URL = "jdbc:sqlite:src/main/resources/database/mars_travel.db"; // Path to the database
    private static final String DRIVER = "org.sqlite.JDBC";

    // Ifall man vill använda en databas som är online, borde ju vara mer kod? men det är ett problem för framtiden
    private static final String USER = " ";
    private static final String PASSWORD = " ";

    private static Connection connection;

    /**
     * Connects to the database and returns the connection
     * @return the connection to the database
     * @throws ClassNotFoundException if the class is not found
     */
    public static Connection getConnection() throws ClassNotFoundException {
        Class.forName(DRIVER);
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * Closes the connection to the database
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




    // Den här rackaren får vara här tillsvidare. @todo flytta till lämplig klass
    /**
     * Validates the user by checking if the username and password exists in the database
     * @param username the username to validate
     * @param password the password to validate
     * @return true if the user is validated, false if not
     */
    public boolean validateUser(String username, String password) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM loggain WHERE användarnamn = ? AND lösenord = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println();
        }
        return false;
    }
}
