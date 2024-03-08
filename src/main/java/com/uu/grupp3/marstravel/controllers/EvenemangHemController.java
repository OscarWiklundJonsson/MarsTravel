package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.database.DatabaseReciveInformation;
import com.uu.grupp3.marstravel.services.CheckoutCartService;
import com.uu.grupp3.marstravel.services.NextButton;
import com.uu.grupp3.marstravel.services.SideBarButtons;
import com.uu.grupp3.marstravel.services.StoreTravelChoices;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EvenemangHemController {

    @FXML
    private Button btnFilmInfo;

    @FXML
    private Button btnTheatreInfo;

    @FXML
    private Button btnNASTA;

    @FXML
    private Button btnConcertInfo;

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
    private Label lblFilmPris;

    @FXML
    private Label lblTheatrePris;

    @FXML
    private Label lblConcertPris;

    @FXML
    private Label lblEvenemang;

    @FXML
    private ChoiceBox<String> cboxFilmpremiarerH;
    @FXML
    private ChoiceBox<String> cboxConcertH;
    @FXML
    private ChoiceBox<String> cboxTheaterpremiarerH;


    @FXML
    private Label lblFilmpremiarer;
    @FXML
    private Label lblTheaterpremiarer;
    @FXML
    private Label lblConcert;
    @FXML
    private Label lblAntal;

    private CheckoutCartService checkoutCartService = new CheckoutCartService();


    public void initialize() {

        //ChoiceBox Filmpremiarer
        ObservableList<String> filmpremiarerAlternativH = FXCollections.observableArrayList(
                "0","1", "2", "3", "4", "5", "6"
        );
        cboxFilmpremiarerH.setItems(filmpremiarerAlternativH);
        //ChoiceBox Konsert
        ObservableList<String> concertAlternativH = FXCollections.observableArrayList(
                "0","1", "2", "3"
        );
        cboxConcertH.setItems(concertAlternativH);
        //ChoiceBox Teater
        ObservableList<String> theaterpremiarerAlternativH = FXCollections.observableArrayList(
                "0","1", "2", "3"
        );
        cboxTheaterpremiarerH.setItems(theaterpremiarerAlternativH);


        DatabaseReciveInformation dbInfo = new DatabaseReciveInformation();

        //Pop-Up for Concert
        btnConcertInfo.setOnAction(event -> dbInfo.showInfoFromDB("Konserter", "Konserter", "EvenemangInformation", 1));
        //Pop-Up for Film
        btnFilmInfo.setOnAction(event -> dbInfo.showInfoFromDB("Filmpremiärer", "Filmpremiärer", "EvenemangInformation", 1));
        //Pop-Up for Teater
        btnTheatreInfo.setOnAction(event -> dbInfo.showInfoFromDB("Teaterpremiärer", "Teaterpremiärer", "EvenemangInformation", 1));

        // Add listeners to the ChoiceBoxes
        ChangeListener<String> choiceBoxListener = (observable, oldValue, newValue) -> {
            if (cboxFilmpremiarerH.getValue() != null && cboxConcertH.getValue() != null && cboxTheaterpremiarerH.getValue() != null) {
                btnNASTA.setDisable(false);
            } else {
                btnNASTA.setDisable(true);
            }
        };

        cboxFilmpremiarerH.getSelectionModel().selectedItemProperty().addListener(choiceBoxListener);
        cboxConcertH.getSelectionModel().selectedItemProperty().addListener(choiceBoxListener);
        cboxTheaterpremiarerH.getSelectionModel().selectedItemProperty().addListener(choiceBoxListener);

        // Initially disable the "Nästa" button
        btnNASTA.setDisable(true);

        // Nästa knappen. Skickar användaren vidare till nästa sida
        btnNASTA.setOnAction(event -> {
            StoreTravelChoices storeTravelChoices = new StoreTravelChoices();
            if (storeTravelChoices.getEvenemangHem() != null) {
                storeTravelChoices.removeEvenemangHem   ();
            }
            storeTravelChoices.storeChoiceBox("Filmpremiarer: ", cboxFilmpremiarerH);
            storeTravelChoices.storeChoiceBox("Teaterpremiarer: ", cboxTheaterpremiarerH);
            storeTravelChoices.storeChoiceBox("Konserter: ", cboxConcertH);
            NextButton nextButton = new NextButton();
            Stage stage = (Stage) btnNASTA.getScene().getWindow();
            nextButton.nextButton("/com/uu/grupp3/marstravel/halsoforsakring.fxml", stage);
        });
        btnVALJAevenemanghem.setDisable(true);

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