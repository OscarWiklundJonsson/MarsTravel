package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.database.DatabaseReciveInformation;
import com.uu.grupp3.marstravel.services.StoreTravelChoices;
import com.uu.grupp3.marstravel.services.NextButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class HytterHemController {

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
        DatabaseReciveInformation dbInfo = new DatabaseReciveInformation();
        StoreTravelChoices storeTravelChoices = new StoreTravelChoices();

        //Pop-Up for EcoInformation
        btnEcoInfo.setOnAction(event -> dbInfo.showInfoFromDB("Economy", "Hytt Economy", "HyttInformation",1));
        //Pop-Up for InsideInformation
        btnInsideInfo.setOnAction(event -> dbInfo.showInfoFromDB("Inside", "Hytt Inside", "HyttInformation",1));
        //Pop-Up for SleepInformation, Sömnkapsel
        btnSleepInfo.setOnAction(event -> dbInfo.showInfoFromDB("Sömnkapsel", "Sömnkapsel", "HyttInformation",1));
        //Pop-Up for hytt Inside
        btnSpacesideInfo.setOnAction(event -> dbInfo.showInfoFromDB("Spaceside", "Hytt Spaceside", "HyttInformation",1));
        //Pop-Up for hytt Svit
        btnSvitInfo.setOnAction(event -> dbInfo.showInfoFromDB("Svit", "Hytt Svit", "HyttInformation",1));

        // Funktion för att endast välja en radioknapp
        ToggleGroup group = new ToggleGroup();
        rbtnEco.setToggleGroup(group);
        rbtnInside.setToggleGroup(group);
        rbtnSleep.setToggleGroup(group);
        rbtnSpaceside.setToggleGroup(group);
        rbtnSvit.setToggleGroup(group);

        btnNASTA.setDisable(true);

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (group.getSelectedToggle() != null) {
                btnNASTA.setDisable(false);
            }
        });
        btnNASTA.setOnAction(event -> {
            storeTravelChoices.storeSelectedRadioButton(group, "HyttHem: ");
            NextButton nextButton = new NextButton();
            Stage stage = (Stage) btnNASTA.getScene().getWindow();

            RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
            if (selectedRadioButton != null && "Sömnkapsel".equals(selectedRadioButton.getText())) {
                storeTravelChoices.writeToFile("Matpaket: -----");
                storeTravelChoices.writeToFile("Evenemang: -----");
                nextButton.nextButton("/com/uu/grupp3/marstravel/sparaKundInformation.fxml", stage); // Ändra till nästa sida
            } else {
                nextButton.nextButton("/com/uu/grupp3/marstravel/matpaketHem.fxml", stage);
            }
        });
    }

}
