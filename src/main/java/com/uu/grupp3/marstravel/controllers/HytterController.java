package com.uu.grupp3.marstravel.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class HytterController {

    @FXML
    private Button btnEcoInfo;

    @FXML
    private Button btnInsideInfo;

    @FXML
    private Button btnNASTA;

    @FXML
    private Button btnSleepInfo;

    @FXML
    private Button btnSpacesideInfo;

    @FXML
    private Button btnSvitInfo;

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
    private Label lblEcoPris;

    @FXML
    private Label lblInsidePris;

    @FXML
    private Label lblMTheader;

    @FXML
    private Label lblPris;

    @FXML
    private Label lblSleepPris;

    @FXML
    private Label lblSpacesidePris;

    @FXML
    private Label lblSvitPris;

    @FXML
    private Label lblhyttalternativ;

    @FXML
    private RadioButton rbtnEco;

    @FXML
    private RadioButton rbtnInside;

    @FXML
    private RadioButton rbtnSleep;

    @FXML
    private RadioButton rbtnSpaceside;

    @FXML
    private RadioButton rbtnSvit;


public void initialize() {
    //Pop-Up för EcoInformation
    btnEcoInfo.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hytt Economy");
            alert.setHeaderText(null);
            alert.setContentText("Insideshytt med 4 bäddar (två under- och överbäddar) där\n" +
                    "underbäddarna kan ändras till soffa dagtid. Dusch finns till hytten, men toaletter\n" +
                    "finns i korridoren.");
            alert.showAndWait();
        }
    });
    //Pop-Up för InsideInformation
    btnInsideInfo.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hytt Inside");
            alert.setHeaderText(null);
            alert.setContentText("Information om hytt Inside");
            alert.showAndWait();
        }
    });
    //Pop-Up för SleepInformation, Sömnkapsel
    btnSleepInfo.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sömnkapsel");
            alert.setHeaderText(null);
            alert.setContentText("Information om sömnkapseln");
            alert.showAndWait();
        }
    });
    //Pop-Up för hytt Inside
    btnSpacesideInfo.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hytt Spaceside");
            alert.setHeaderText(null);
            alert.setContentText("Information om hytt Spaceside");
            alert.showAndWait();
        }
    });
        btnNASTA.setOnAction(event -> {
            try {
                // Load the FXML file for the new scene
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uu/grupp3/marstravel/testhugo.fxml"));
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
        });
    }
}
