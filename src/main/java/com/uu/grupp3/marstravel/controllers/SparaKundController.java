package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SparaKundController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;

    // Method to save a customer to the database

    /**
     * Method to save a customer to the database
     * Checks if all fields are filled, if not, an error message is displayed
     * .If all fields are filled, the customer is saved to the database using a prepared statement
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void saveCustomer() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        // Check if all fields are filled
        if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("AJ AJ AJ");
            alert.setHeaderText(null);
            alert.setContentText("Allt måste fyllas i!");
            alert.showAndWait();
            return;
        }

        String sql = "INSERT INTO KundInformation (Förnamn, Efternamn, Telefonnummer, Mail) VALUES (?, ?, ?, ?)";

        // Try to connect to the database and save the customer
        try (Connection conn = DatabaseHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, phone);
            pstmt.setString(4, email);
            pstmt.executeUpdate();

            // Display success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Kundinformation sparad!");
            alert.showAndWait();

            // Clear the text fields
            firstNameField.clear();
            lastNameField.clear();
            phoneField.clear();
            emailField.clear();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("AJ AJ AJ! Something went wrong!");

            // Display error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error osv");
            alert.setHeaderText(null);
            alert.setContentText("AJ AJ AJ! Something went wrong!");
            alert.showAndWait();
        }
    }
    // Method to continue to the main page(?) after saving a customer
    public void continueToMain() {
    }
}