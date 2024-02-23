package com.uu.grupp3.marstravel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class BokaController {

        @FXML
        private Label HeaderMT;

        @FXML
        private Button btnBoka;

        public void initialize() {
                btnBoka.setOnAction(event -> {
                        try {
                                // Load the FXML file for the new scene
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uu/grupp3/marstravel/resedatum.fxml"));
                                Parent root = loader.load();
                                // Create a new scene
                                Scene scene = new Scene(root);
                                // Get the stage from the button and set the new scene
                                Stage stage = (Stage) btnBoka.getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                });
        }

}