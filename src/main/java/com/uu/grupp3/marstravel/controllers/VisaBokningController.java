package com.uu.grupp3.marstravel.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

public class VisaBokningController {
    @FXML
    private TextField tfBokningsnummer;
    @FXML
    private Button btnSök;
    @FXML
    private Label lblSokBokningsinformation;

    public void handleSokButtonClick(ActionEvent actionEvent) {
        //Kod för att söka på bokningsnummer från textfältet

        // Pop-Up för informationen
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bokningsinformation");
        alert.setHeaderText(null);
        String bokningsnummer = tfBokningsnummer.getText();
        alert.setContentText("Bokningsnummer: " + bokningsnummer + "\n"); //bokningsinformation

        alert.showAndWait();
    }
}

