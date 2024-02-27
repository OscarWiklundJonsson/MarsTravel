package com.uu.grupp3.marstravel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class HotellMarsController {

    @FXML
    private Button btnDeimosDubbelInfo;

    @FXML
    private Button btnDeimosEnkelInfo;

    @FXML
    private Button btnLansdorpInfo;

    @FXML
    private Button btnNASTA;

    @FXML
    private Button btnPhobosDubbelInfo;

    @FXML
    private Button btnPhobosEnkelInfo;

    @FXML
    private Button btnRoyalDubbelInfo;

    @FXML
    private Button btnRoyalEnkelInfo;

    @FXML
    private Button btnRoyalSvitInfo;

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
    private Button btnWeildersInfo;

    @FXML
    private Circle cVarukorgen;

    @FXML
    private Label lblDeimosDubbelPris;

    @FXML
    private Label lblDeimosEnkelPris;

    @FXML
    private Label lblEndastBaddar;

    @FXML
    private Label lblHotelDeimos;

    @FXML
    private Label lblHotelPhobos;

    @FXML
    private Label lblLansdorpPris;

    @FXML
    private Label lblMTheader;

    @FXML
    private Label lblPhobosDubbelPris;

    @FXML
    private Label lblPhobosEnkelPris;

    @FXML
    private Label lblPrismånad;

    @FXML
    private Label lblRoyalCity;

    @FXML
    private Label lblRoyalDubbelPris;

    @FXML
    private Label lblRoyalEnkelPris;

    @FXML
    private Label lblRoyalSvitPris;

    @FXML
    private Label lblWeildersPris;

    @FXML
    private Label lblhotellalternativ;

    @FXML
    private RadioButton rbtnDeimosDubbel;

    @FXML
    private RadioButton rbtnDeimosEnkel;

    @FXML
    private RadioButton rbtnLansdorp;

    @FXML
    private RadioButton rbtnPhobosDubbel;

    @FXML
    private RadioButton rbtnPhobosEnkel;

    @FXML
    private RadioButton rbtnRoyalDubbel;

    @FXML
    private RadioButton rbtnRoyalEnkel;

    @FXML
    private RadioButton rbtnRoyalSvit;

    @FXML
    private RadioButton rbtnWeilders;

    public void initialize() {
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
