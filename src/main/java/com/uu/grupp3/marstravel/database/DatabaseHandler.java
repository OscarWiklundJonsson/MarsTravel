package com.uu.grupp3.marstravel.database;

import java.sql.*;

public class DatabaseHandler {
    private static final String URL = "jdbc:sqlite:src/main/resources/database/mars_travel.db"; // Path to the database
    private static Connection connection;

    /**
     * Connects to the database and returns the connection
     * @return the connection to the database
     * @throws ClassNotFoundException if the class is not found
     * @author Oscar
     */
    public static Connection getConnection() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
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
     * @author Oscar
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
}
