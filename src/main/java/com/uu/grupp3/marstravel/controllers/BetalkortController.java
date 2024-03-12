package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.database.DatabaseReciveInformation;
import com.uu.grupp3.marstravel.services.CheckoutCartService;
import com.uu.grupp3.marstravel.services.NextButton;
import com.uu.grupp3.marstravel.services.SideBarButtons;
import com.uu.grupp3.marstravel.services.StoreTravelChoices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class BetalkortController {

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
        private Button btnVALJAresedatum;

        @FXML
        private Button btnVarukorg;

        @FXML
        private Label lblbetalkort;

        @FXML
        private TextField tfbetalkort;

    private CheckoutCartService checkoutCartService = new CheckoutCartService();

    /**
     * Metoden initialize körs när fönstret öppnas och innehåller all funktionalitet för fönstret.
     * Här finns funktionalitet för att spara betalkortsinformation och för att visa varukorgen.
     * Här finns även funktionalitet för att navigera mellan sidorna i applikationen.
     * Här finns även funktionalitet för att kontrollera att summan som ska laddas på betalkortet är minst 20000.
     */
    public void initialize() {

        DatabaseReciveInformation dbInfo = new DatabaseReciveInformation();
        StoreTravelChoices storeTravelChoices = new StoreTravelChoices();

        btnNÄSTA.setDisable(true);

        // Filter för att endast siffror ska kunna skrivas in i textfältet
        tfbetalkort.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfbetalkort.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        // Kontrollerar att summan som ska laddas på betalkortet är minst 20000, är det det så aktiveras knappen NÄSTA
        tfbetalkort.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.trim().isEmpty() && Integer.parseInt(newValue.trim()) >= 20000) {
                btnNÄSTA.setDisable(false);
            } else {
                btnNÄSTA.setDisable(true);
            }
        });

        btnNÄSTA.setOnAction(event -> {
            if (storeTravelChoices.getBetalkort() != null) {
                storeTravelChoices.removeBetalkort();
            }
            storeTravelChoices.storeTextFieldValue(tfbetalkort.getText(), "Betalkort: ");
            NextButton nextButton = new NextButton();
            Stage stage = (Stage) btnNÄSTA.getScene().getWindow();
            nextButton.nextButton("/com/uu/grupp3/marstravel/sparaKundInformation.fxml", stage);
        });
        btnVALJAbetalkort.setDisable(true);

        // denna beast som visar varukorgen
        btnVarukorg.setOnAction(event -> {
            try {
                checkoutCartService.showCheckoutCart();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private SideBarButtons sideBarButtons = new SideBarButtons();

    /**
     * Här hanteras sidoknapparnas funktion att navigera mellan de olika kategorierna
     * @param event Triggar Action Eventet när man klickar på knapparna
     */
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
            fxmlPath = "/com/uu/grupp3/marstravel/sparaKundInformation.fxml";
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