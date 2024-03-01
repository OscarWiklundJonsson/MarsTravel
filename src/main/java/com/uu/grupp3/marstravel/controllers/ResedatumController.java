package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.services.StoreTravelChoices;
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
import javafx.event.ActionEvent;
import javafx.scene.Node;

import com.uu.grupp3.marstravel.services.SideBarButtons;


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
        btnVALJAresedatum.setDisable(true);
    }

    @FXML
    private Button btnNASTA;

    @FXML
    private Button btnVALJAevenemangdit;

    @FXML
    private Button btnVALJAmatpaketdit;

    @FXML
    private Button btnVALJAhyttdit;

    @FXML
    private Button btnVALJAresedatum;
    @FXML
    private Button btnVALJAkundinfo;

    @FXML
    private Button btnVALJAbetalkort;

    @FXML
    private Button btnVALJAhalsoforsakring;

    @FXML
    private Button btnVALJAevenemanghem;

    @FXML
    private Button btnVALJAmatpakethem;

    @FXML
    private Button btnVALJAhytthem;

    @FXML
    private Button btnVALJAhotellmars;
    @FXML
    private void handleNastaButtonClick() {

        StoreTravelChoices storeTravelChoices = new StoreTravelChoices();

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
        // check if there is already a date stored, if yes remove the old date
        if (storeTravelChoices.getDate() != null) {
            storeTravelChoices.removeDate();
        }
        String chosenDate = Avresa_ar.getValue() + "-" + Avresa_manad.getValue() + " till " + Hemresa_ar.getValue() + "-" + HemresaMånad.getValue();
        storeTravelChoices.storeDate(chosenDate);

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

    // är bara denna kod som behöver kopieras till andra controllers
    private SideBarButtons sideBarButtons = new SideBarButtons();

    @FXML
    private void SideBarButtons(ActionEvent event) {
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