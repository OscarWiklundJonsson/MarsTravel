package com.uu.grupp3.marstravel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResedatumController implements Initializable {
    @FXML
    private ChoiceBox<String> Avresa_ar;

    @FXML
    private ChoiceBox<String> Avresa_manad;

    @FXML
    private ChoiceBox<String> Hemresa_ar;

    @FXML
    private ChoiceBox<String> HemresaMånad;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Define choices for Avresa_ar (Departure year)
        ObservableList<String> avresaArOptions = FXCollections.observableArrayList(
                "2024", "2025", "2026", "2027", "2028", "2029", "2030" // Add more years if needed
        );
        Avresa_ar.setItems(avresaArOptions);

        // Define choices for Avresa_manad (Departure month)
        ObservableList<String> avresaManadOptions = FXCollections.observableArrayList(
                "Januari", "Februari", "Mars", "April", "Maj", "Juni",
                "Juli", "Augusti", "September", "Oktober", "November", "December"
        );
        Avresa_manad.setItems(avresaManadOptions);

        // Define choices for Hemresa_ar (Return year)
        ObservableList<String> hemresaArOptions = FXCollections.observableArrayList(
                "2024", "2025", "2026", "2027", "2028", "2029", "2030" // Add more years if needed
        );
        Hemresa_ar.setItems(hemresaArOptions);

        // Define choices for HemresaMånad (Return month)
        HemresaMånad.setItems(avresaManadOptions); // Same options as Departure month
    }
    @FXML
    private Button btnNASTA;
    @FXML
    private void handleNastaButtonClick() {
        try {
            // Load the FXML file for the new scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uu/grupp3/marstravel/hytter.fxml"));
            Parent root = loader.load();
            // Create a new scene
            Scene scene = new Scene(root);
            // Get the stage from the button and set the new scene
            Stage stage = (Stage) btnNASTA.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}