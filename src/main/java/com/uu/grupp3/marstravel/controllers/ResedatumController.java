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
        ObservableList<String> avresaArAlternativ = FXCollections.observableArrayList(
                "2024", "2025", "2026", "2027", "2028", "2029", "2030"
        );
        Avresa_ar.setItems(avresaArAlternativ);

        ObservableList<String> avresaManadAlternativ = FXCollections.observableArrayList(
                "Januari", "Februari", "Mars", "April", "Maj", "Juni",
                "Juli", "Augusti", "September", "Oktober", "November", "December"
        );
        Avresa_manad.setItems(avresaManadAlternativ);

        ObservableList<String> hemresaArAlternativ = FXCollections.observableArrayList(
                "2024", "2025", "2026", "2027", "2028", "2029", "2030"
        );
        Hemresa_ar.setItems(hemresaArAlternativ);

        HemresaMånad.setItems(avresaManadAlternativ);
    }

    @FXML
    private Button btnNASTA;

    @FXML
    private void handleNastaButtonClick() {

        if (Avresa_ar.getValue() == null || Avresa_manad.getValue() == null || Hemresa_ar.getValue() == null || HemresaMånad.getValue() == null) {
            Alert felAlert = new Alert(Alert.AlertType.ERROR);
            felAlert.setTitle("Felaktigt datumval");
            felAlert.setHeaderText(null);
            felAlert.setContentText("Du måste välja både år och månad för avresa och hemresa för att gå vidare.");
            felAlert.showAndWait();
            return;
        }

        if (!valideraHemresaDatum()) {
            Alert felAlert = new Alert(Alert.AlertType.ERROR);
            felAlert.setTitle("Felaktigt datumval");
            felAlert.setHeaderText(null);
            felAlert.setContentText("Hemresa måste vara minst 6 månader efter avresa.");
            felAlert.showAndWait();
            return;
        }

        try {
            FXMLLoader laddare = new FXMLLoader(getClass().getResource("/com/uu/grupp3/marstravel/hytter.fxml"));
            Parent rot = laddare.load();

            Scene scen = new Scene(rot);

            Stage scenVarde = (Stage) btnNASTA.getScene().getWindow();
            scenVarde.setScene(scen);
            scenVarde.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean valideraHemresaDatum() {
        String valtAvreseår = Avresa_ar.getValue();
        String valdAvreseMånad = Avresa_manad.getValue();
        String valtReturår = Hemresa_ar.getValue();
        String valdReturMånad = HemresaMånad.getValue();

        int avreseÅr = Integer.parseInt(valtAvreseår);
        int returÅr = Integer.parseInt(valtReturår);

        ObservableList<String> avresaManadAlternativ = Avresa_manad.getItems();
        int avreseMånadsIndex = avresaManadAlternativ.indexOf(valdAvreseMånad);
        int returMånadsIndex = avresaManadAlternativ.indexOf(valdReturMånad);

        int månaderSkillnad = (returÅr - avreseÅr) * 12 + (returMånadsIndex - avreseMånadsIndex);

        return månaderSkillnad >= 6;
    }
}