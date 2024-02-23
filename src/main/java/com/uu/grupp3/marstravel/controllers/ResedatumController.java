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
import javafx.scene.control.Alert;

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

        ObservableList<String> avresaArOptions = FXCollections.observableArrayList(
                "2024", "2025", "2026", "2027", "2028", "2029", "2030"
        );
        Avresa_ar.setItems(avresaArOptions);


        ObservableList<String> avresaManadOptions = FXCollections.observableArrayList(
                "Januari", "Februari", "Mars", "April", "Maj", "Juni",
                "Juli", "Augusti", "September", "Oktober", "November", "December"
        );
        Avresa_manad.setItems(avresaManadOptions);

        ObservableList<String> hemresaArOptions = FXCollections.observableArrayList(
                "2024", "2025", "2026", "2027", "2028", "2029", "2030"
        );
        Hemresa_ar.setItems(hemresaArOptions);


        HemresaMånad.setItems(avresaManadOptions);
    }

    @FXML
    private Button btnNASTA;

    @FXML
    private void handleNastaButtonClick() {

        if (!validateHemresaDate()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Felaktigt datumval");
            alert.setHeaderText(null);
            alert.setContentText("Du måste välja år och månad för att gå vidare. Hemresa måste vara minst 6 månader efter avresa");
            alert.showAndWait();
            return;
        }

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uu/grupp3/marstravel/hytter.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) btnNASTA.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validateHemresaDate() {

        String selectedDepartureYear = Avresa_ar.getValue();
        String selectedDepartureMonth = Avresa_manad.getValue();
        String selectedReturnYear = Hemresa_ar.getValue();
        String selectedReturnMonth = HemresaMånad.getValue();


        ObservableList<String> avresaManadOptions = Avresa_manad.getItems();
        int departureMonthIndex = avresaManadOptions.indexOf(selectedDepartureMonth);
        int returnMonthIndex = avresaManadOptions.indexOf(selectedReturnMonth);
        int monthsDifference = returnMonthIndex - departureMonthIndex;


        return monthsDifference >= 6; // Detta fungerar inte, måste kolla år också!
    }
}