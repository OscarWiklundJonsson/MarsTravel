package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.database.DatabaseReciveInformation;
import com.uu.grupp3.marstravel.services.NextButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EvenemangController {

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
    private Circle cVarukorgen;

    @FXML
    private Label lblFilmPris;

    @FXML
    private Label lblTheatrePris;

    @FXML
    private Label lblMTheader;

    @FXML
    private Label lblConcertPris;

    @FXML
    private Label lblEvenemang;

    @FXML
    private RadioButton rbtnConcert;

    @FXML
    private RadioButton rbtnTheatre;

    @FXML
    private RadioButton rbtnFilm;

    @FXML
    private ChoiceBox<String> cboxFilmpremiarer;
    @FXML
    private ChoiceBox<String> cboxConcert;
    @FXML
    private ChoiceBox<String> cboxTheaterpremiarer;


    @FXML
    private Label lblFilmpremiarer;
    @FXML
    private Label lblTheaterpremiarer;
    @FXML
    private Label lblConcert;
    @FXML
    private Label lblAntal;


    public void initialize() {

        //ChoiceBox Filmpremiarer
        ObservableList<String> filmpremiarerAlternativ = FXCollections.observableArrayList(
                "1", "2", "3", "4", "5", "6"
        );
        cboxFilmpremiarer.setItems(filmpremiarerAlternativ);
        //ChoiceBox Konsert
        ObservableList<String> concertAlternativ = FXCollections.observableArrayList(
                "1", "2", "3"
        );
        cboxConcert.setItems(concertAlternativ);
        //ChoiceBox Teater
        ObservableList<String> theaterpremiarerAlternativ = FXCollections.observableArrayList(
                "1", "2", "3"
        );
        cboxTheaterpremiarer.setItems(theaterpremiarerAlternativ);


        DatabaseReciveInformation dbInfo = new DatabaseReciveInformation();

        //Pop-Up for Concert
        btnConcertInfo.setOnAction(event -> dbInfo.showInfoFromDB("Konserter", "Konserter", "EvenemangInformation", 1));
        //Pop-Up for Film
        btnFilmInfo.setOnAction(event -> dbInfo.showInfoFromDB("Filmpremiärer", "Filmpremiärer", "EvenemangInformation", 1));
        //Pop-Up for Teater
        btnTheatreInfo.setOnAction(event -> dbInfo.showInfoFromDB("Teaterpremiärer", "Teaterpremiärer", "EvenemangInformation", 1));



        // Nästa knappen. Skickar användaren vidare till nästa sida
        btnNASTA.setOnAction(event -> {
            NextButton nextButton = new NextButton();
            Stage stage = (Stage) btnNASTA.getScene().getWindow();
            nextButton.nextButton("/com/uu/grupp3/marstravel/sparaKundInformation.fxml", stage);
        });
    }
}
