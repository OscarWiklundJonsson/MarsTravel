package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.database.DatabaseHandler;
import com.uu.grupp3.marstravel.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SparaKundController {

    @FXML
    private Button btnVarukorg;

    @FXML
    private Button btnKASSA;

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
    private Button btnInfoHalsodetaljer;

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

    @FXML
    private TextField tfAdress;

    @FXML
    private TextField tfHalsodetaljer;

    @FXML
    private TextField tfOrt;

    @FXML
    private TextField tfPersonnummer;

    @FXML
    private TextField tfPostnummer;

    @FXML
    private Button varukorg;

    public void initialize() {
        CharacterRestrictions.applyCharacterRestrictions(tffirstname, tflastname, tfPersonnummer, tfphonenumber, tfemail, tfAdress, tfPostnummer, tfOrt );
        btnKASSA.setOnAction(event -> {

            String firstName = tffirstname.getText();
            String lastName = tflastname.getText();
            String pnumber = tfPersonnummer.getText();
            String phone = tfphonenumber.getText();
            String email = tfemail.getText();
            UserData.getInstance().setEmail(email); // Sparar email i UserData singleton klassen
            String address = tfAdress.getText();
            String zipCode = tfPostnummer.getText();
            String city = tfOrt.getText();
            String healthDetails = tfHalsodetaljer.getText();
            String customerInfo = "Förnamn: " + firstName + "\n" +
                    "Efternamn: " + lastName + "\n" +
                    "Personnummer" + pnumber + "\n" +
                    "Telefon: " + phone + "\n" +
                    "Email: " + email + "\n" +
                    "Adress" + address + "\n" +
                    "Postnummer" + zipCode + "\n" +
                    "Ort" + city + "\n" +
                    "Hälsodetaljer" + healthDetails + "\n";
            StoreTravelChoices storeTravelChoices = new StoreTravelChoices();
            storeTravelChoices.writeToFile(customerInfo);

            NextButton nextButton = new NextButton();
            Stage stage = (Stage) btnKASSA.getScene().getWindow();
            nextButton.nextButton("/com/uu/grupp3/marstravel/sammanstallning.fxml", stage); //ska skickas till sammanställningen.
        });

        // denna beast som visar varukorgen
        btnVarukorg.setOnAction(event -> {
            try {
                checkoutCartService.showCheckoutCart();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        btnVALJAkundinfo.setDisable(true);

        btnInfoHalsodetaljer.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hälsodetaljer");
            alert.setHeaderText(null);
            alert.setContentText("Hälsodetaljerna är att man är fullt frisk, alternativt att man har någon kronisk krämpa (då ska information om det skrivas in), samt om någon medicin tas regelbundet (och då ska den informationen fyllas i).");
            alert.show();
        });
    }
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
        String pnumber = tfPersonnummer.getText();
        String phone = tfphonenumber.getText();
        String email = tfemail.getText();// Set the email
        String address = tfAdress.getText();
        String zipCode = tfPostnummer.getText();
        String city = tfOrt.getText();
        String healthDetails = tfHalsodetaljer.getText();

        // Check if all fields are filled
        if (firstName.isEmpty() || lastName.isEmpty() || pnumber.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty() ||
                zipCode.isEmpty() ||city.isEmpty() || healthDetails.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("AJ AJ AJ");
            alert.setHeaderText(null);
            alert.setContentText("Allt måste fyllas i!");
            alert.showAndWait();
            return;
        }

        String sql = "INSERT INTO KundInformation (Förnamn, Efternamn, Personnummer, Telefonnummer, Mail, Adress, Postnummer, Ort, HalsoInformation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Try to connect to the database and save the customer
        try (Connection conn = DatabaseHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, pnumber);
            pstmt.setString(4, phone);
            pstmt.setString(5, email);
            pstmt.setString(6, address);
            pstmt.setString(7, zipCode);
            pstmt.setString(8, city);
            pstmt.setString(9, healthDetails);
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
            tfPersonnummer.clear();
            tfphonenumber.clear();
            tfemail.clear();
            tfAdress.clear();
            tfPostnummer.clear();
            tfOrt.clear();
            tfHalsodetaljer.clear();

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
    //nästa knapp.
    CheckoutCartService checkoutCartService = new CheckoutCartService();

    private SideBarButtons sideBarButtons = new SideBarButtons();

    @FXML
    private void SideBarButtons(ActionEvent event) { // Snälla gör detta till en egen klass, jag ber er.
        String fxmlPath = null;

        if (event.getSource() == btnVALJAevenemangdit) {
            fxmlPath = "/com/uu/grupp3/marstravel/evenemang.fxml";
            System.out.println("Evenemang");
        } else if (event.getSource() == btnVALJAmatpaketdit) {
            fxmlPath = "/com/uu/grupp3/marstravel/matpaket.fxml";
            System.out.println("Matpaket");
        } else if (event.getSource() == btnVALJAhyttdit) {
            fxmlPath = "/com/uu/grupp3/marstravel/hytter.fxml";
            System.out.println("Hytter");
        } else if (event.getSource() == btnVALJAresedatum) {
            fxmlPath = "/com/uu/grupp3/marstravel/resedatum.fxml";
            System.out.println("Resedatum");
        } else if (event.getSource() == btnVALJAkundinfo) {
            fxmlPath = "/com/uu/grupp3/marstravel/sparaKundinformation.fxml";
            System.out.println("Kundinformation");
        } else if (event.getSource() == btnVALJAbetalkort) {
            fxmlPath = "/com/uu/grupp3/marstravel/betalkort.fxml";
            System.out.println("Betalkort");
        } else if (event.getSource() == btnVALJAhalsoforsakring) {
            fxmlPath = "/com/uu/grupp3/marstravel/halsoforsakring.fxml";
            System.out.println("Hälsförsäkring");
        } else if (event.getSource() == btnVALJAevenemanghem) {
            fxmlPath = "/com/uu/grupp3/marstravel/evenemanghem.fxml";
            System.out.println("Evenemang hem");
        } else if (event.getSource() == btnVALJAmatpakethem) {
            fxmlPath = "/com/uu/grupp3/marstravel/matpaketHem.fxml";
            System.out.println("Matpaket hem");
        } else if (event.getSource() == btnVALJAhytthem) {
            fxmlPath = "/com/uu/grupp3/marstravel/hytterHem.fxml";
            System.out.println("Hytter hem");
        } else if (event.getSource() == btnVALJAhotellmars) {
            fxmlPath = "/com/uu/grupp3/marstravel/hotellMars.fxml";
            System.out.println("Hotell Mars");
        }

        if (fxmlPath != null) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            sideBarButtons.sideBarButton(fxmlPath, stage);
        }
    }
}