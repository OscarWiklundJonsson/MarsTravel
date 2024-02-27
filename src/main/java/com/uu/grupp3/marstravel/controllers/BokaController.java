package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.services.NextButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class BokaController {

        @FXML
        private Label HeaderMT;

        @FXML
        private Button btnBoka;

        public void initialize() {
                btnBoka.setOnAction(event -> {
                        NextButton nextButton = new NextButton();
                        Stage stage = (Stage) btnBoka.getScene().getWindow();
                        nextButton.nextButton("/com/uu/grupp3/marstravel/hytter.fxml", stage);
                });
        }
}