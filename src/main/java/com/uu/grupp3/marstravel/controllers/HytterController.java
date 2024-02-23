package com.uu.grupp3.marstravel.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

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

    String filePath = "";

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