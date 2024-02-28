package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.database.DatabaseReciveInformation;
import com.uu.grupp3.marstravel.services.NextButton;
import com.uu.grupp3.marstravel.services.SideBarButtons;
import com.uu.grupp3.marstravel.services.StoreTravelChoices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class HalsoforsakringController {

        @FXML
        private Button btnNASTA;

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
        private Button btnhalsofinfo;

        @FXML
        private Circle cVarukorgen;

        @FXML
        private Label lblHRhalsopris;

        @FXML
        private Label lblhalsoforsakring;

        @FXML
        private Label lblhalsofpris;

        @FXML
        private RadioButton rbtnhalsof;


    public void initialize() {
        //Funktion för att endast välja radioknapp
        ToggleGroup group = new ToggleGroup();

        DatabaseReciveInformation dbInfo = new DatabaseReciveInformation();
        StoreTravelChoices storeTravelChoices = new StoreTravelChoices();

        //Pop-Up ändra till info on hälsoförsäkring @todo
        btnhalsofinfo.setOnAction(event -> dbInfo.showInfoFromDB("Hälsoförsäkring", "Budget 1", "Hälsoförsäkring", 1));

        //rbtnhalsof
        rbtnhalsof.setToggleGroup(group);

        // Nästa knappen. Skickar användaren till nästa sida. (evenemang). Använder sig av NextButton klassen som är en service klass.
        btnNASTA.setOnAction(event -> {
            if (storeTravelChoices.getHealthIns() != null) {
                storeTravelChoices.removeHealthIns();
            }
            storeTravelChoices.storeSelectedRadioButton(group, "Hälsoförsäkring: ");
            NextButton nextButton = new NextButton();
            Stage stage = (Stage) btnNASTA.getScene().getWindow();
            nextButton.nextButton("/com/uu/grupp3/marstravel/betalkort.fxml", stage);
        });
        btnVALJAhalsoforsakring.setDisable(true);
    }
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