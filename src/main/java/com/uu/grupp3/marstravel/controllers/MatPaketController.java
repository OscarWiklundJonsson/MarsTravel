package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.database.DatabaseHandler;
import com.uu.grupp3.marstravel.database.DatabaseReciveInformation;
import com.uu.grupp3.marstravel.services.NextButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MatPaketController {
    @FXML
    private Button btnBudget1;

    @FXML
    private Button btnBudget2;

    @FXML
    private Button btnBudget3;

    @FXML
    private Button btnLyx1;

    @FXML
    private Button btnLyx2;

    @FXML
    private Button btnLyx3;

    @FXML
    private Button btnMellan1;

    @FXML
    private Button btnMellan2;

    @FXML
    private Button btnMellan3;

    @FXML
    private Button btnNÄSTA;

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
    private Circle cVarukorgen;

    @FXML
    private Label lblEcoPris;

    @FXML
    private Label lblEcoPris1;

    @FXML
    private Label lblEcoPris2;

    @FXML
    private Label lblEcoPris21;

    @FXML
    private Label lblInsidePris;

    @FXML
    private Label lblInsidePris1;

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
    private RadioButton rbtnBudget1;

    @FXML
    private RadioButton rbtnBudget2;

    @FXML
    private RadioButton rbtnBudget3;

    @FXML
    private RadioButton rbtnLyx1;

    @FXML
    private RadioButton rbtnLyx2;

    @FXML
    private RadioButton rbtnLyx3;

    @FXML
    private RadioButton rbtnMellan1;

    @FXML
    private RadioButton rbtnMellan2;

    @FXML
    private RadioButton rbtnMellan3;


    public void initialize() {
        // Funktion för att endast välja en radioknapp för mat
        ToggleGroup group = new ToggleGroup();
        rbtnBudget1.setToggleGroup(group);
        rbtnBudget2.setToggleGroup(group);
        rbtnBudget3.setToggleGroup(group);
        rbtnLyx1.setToggleGroup(group);
        rbtnLyx2.setToggleGroup(group);
        rbtnLyx3.setToggleGroup(group);
        rbtnMellan1.setToggleGroup(group);
        rbtnMellan2.setToggleGroup(group);
        rbtnMellan3.setToggleGroup(group);

        btnNÄSTA.setDisable(true);

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (group.getSelectedToggle() != null) {
                btnNÄSTA.setDisable(false);
            }
        });

        DatabaseReciveInformation dbInfo = new DatabaseReciveInformation();
        //Pop-Up for MatpaketInformation
        btnBudget1.setOnAction(event -> dbInfo.showInfoFromDB("budget1", "Budget 1", "MatpaketInformation", 1));
        btnBudget2.setOnAction(event -> dbInfo.showInfoFromDB("budget2", "Budget 2", "MatpaketInformation", 1));
        btnBudget3.setOnAction(event -> dbInfo.showInfoFromDB("budget3", "Budget 3", "MatpaketInformation", 1));
        btnMellan1.setOnAction(event -> dbInfo.showInfoFromDB("mellan1", "Mellan 1", "MatpaketInformation", 1));
        btnMellan2.setOnAction(event -> dbInfo.showInfoFromDB("mellan2", "Mellan 2", "MatpaketInformation", 1));
        btnMellan3.setOnAction(event -> dbInfo.showInfoFromDB("mellan3", "Mellan 3", "MatpaketInformation", 1));
        btnLyx1.setOnAction(event -> dbInfo.showInfoFromDB("lyx1", "Lyx 1", "MatpaketInformation", 1));
        btnLyx2.setOnAction(event -> dbInfo.showInfoFromDB("lyx2", "Lyx 2", "MatpaketInformation", 1));
        btnLyx3.setOnAction(event -> dbInfo.showInfoFromDB("lyx3", "Lyx 3", "MatpaketInformation", 1));

        // Nästa knappen. Skickar användaren till nästa sida. (evenemang). Använder sig av NextButton klassen som är en service klass.
        btnNÄSTA.setOnAction(event -> {
            NextButton nextButton = new NextButton();
            Stage stage = (Stage) btnNÄSTA.getScene().getWindow();
            nextButton.nextButton("/com/uu/grupp3/marstravel/evenemang.fxml", stage);
        });

    }
}