package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.services.CheckoutCartService;
import com.uu.grupp3.marstravel.services.NextButton;
import com.uu.grupp3.marstravel.services.SideBarButtons;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import com.uu.grupp3.marstravel.services.StoreTravelChoices;

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
    private Button btnVarukorg;

    @FXML
    private Label lblDeimosDubbelPris;

    @FXML
    private Label lblDeimosEnkelPris;

    @FXML
    private Label lblLansdorpPris;

    @FXML
    private Label lblPhobosDubbelPris;

    @FXML
    private Label lblPhobosEnkelPris;

    @FXML
    private Label lblPrismånad;

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

    private CheckoutCartService checkoutCartService = new CheckoutCartService();

    //nästa knapp, skickar vidare till hytterHem sidan.
    public void initialize() {
        ToggleGroup group = new ToggleGroup();
        StoreTravelChoices storeTravelChoices = new StoreTravelChoices();

        rbtnDeimosDubbel.setToggleGroup(group);
        rbtnDeimosEnkel.setToggleGroup(group);
        rbtnLansdorp.setToggleGroup(group);
        rbtnPhobosDubbel.setToggleGroup(group);
        rbtnPhobosEnkel.setToggleGroup(group);
        rbtnRoyalDubbel.setToggleGroup(group);
        rbtnRoyalEnkel.setToggleGroup(group);
        rbtnRoyalSvit.setToggleGroup(group);
        rbtnWeilders.setToggleGroup(group);

        btnNASTA.setDisable(true);

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (group.getSelectedToggle() != null) {
                btnNASTA.setDisable(false);
            }
        });

        btnNASTA.setOnAction(event -> {
            if (storeTravelChoices.getHotell() != null) {
                storeTravelChoices.removeHotell();
            }
            storeTravelChoices.storeSelectedRadioButton(group, "Hotell: ");
            NextButton nextButton = new NextButton();
            Stage stage = (Stage) btnNASTA.getScene().getWindow();
            nextButton.nextButton("/com/uu/grupp3/marstravel/hytterHem.fxml", stage);
        });
        btnVALJAhotellmars.setDisable(true);

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
