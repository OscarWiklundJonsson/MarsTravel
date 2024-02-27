package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.database.DatabaseHandler;
import com.uu.grupp3.marstravel.services.NextButton;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SparaKundController {

    @FXML
    private Button btnNASTA;

    @FXML
    private Button btnVALJAbetalkort;

    @FXML
    private Button btnVALJAevenemangdit;

    @FXML
    private Button btnVALJAevenemanghem;

    @FXML
    private Button btnVALJAhalsoforsakring;

    @FXML
    private Button btnVALJAhotellmars;

    @FXML
    private Button btnVALJAhyttdit;

    @FXML
    private Button btnVALJAhytthem;

    @FXML
    private Button btnVALJAkundinfo;

    @FXML
    private Button btnVALJAmatpaketdit;

    @FXML
    private Button btnVALJAmatpakethem;

    @FXML
    private Button btnVALJAresedatum;

    @FXML
    private Circle cVarukorgen;

    @FXML
    private Label lblkundinfo;

    @FXML
    private TextField tfemail;

    @FXML
    private TextField tffirstname;

    @FXML
    private TextField tflastname;

    @FXML
    private TextField tfphonenumber;


    // Method to save a customer to the database

    /**
     * Method to save a customer to the database
     * Checks if all fields are filled, if not, an error message is displayed
     * .If all fields are filled, the customer is saved to the database using a prepared statement
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void saveCustomer() {
        String firstName = tffirstname.getText();
        String lastName = tflastname.getText();
        String phone = tfphonenumber.getText();
        String email = tfemail.getText();

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
            tffirstname.clear();
            tflastname.clear();
            tfphonenumber.clear();
            tfemail.clear();

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
    //nästa knapp, skickar vidare till hytterHem sidan.
    public void initialize() {
        btnNASTA.setOnAction(event -> {
            NextButton nextButton = new NextButton();
            Stage stage = (Stage) btnNASTA.getScene().getWindow();
            nextButton.nextButton("/com/uu/grupp3/marstravel/boka.fxml", stage); //ska skickas till varukorgen sen.
        });
    }
    // Method to continue to the main page(?) after saving a customer
    public void continueToMain() {
    }
}