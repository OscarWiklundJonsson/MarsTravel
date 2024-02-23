package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.shape.Circle;

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
    private RadioButton rbtnEco;

    @FXML
    private RadioButton rbtnEco1;

    @FXML
    private RadioButton rbtnEco11;

    @FXML
    private RadioButton rbtnEco2;

    @FXML
    private RadioButton rbtnInside;

    @FXML
    private RadioButton rbtnInside1;

    @FXML
    private RadioButton rbtnSleep;

    @FXML
    private RadioButton rbtnSpaceside;

    @FXML
    private RadioButton rbtnSvit;


    public void initialize() {
        //Pop-Up for EcoInformation
        btnBudget1.setOnAction(event -> showInfoFromDB("budget1", "Budget 1"));
        //Pop-Up for MatPaketInformation
        btnBudget2.setOnAction(event -> showInfoFromDB("budget2", "Budget 2"));
        //Pop-Up for MatPaketInformation
        btnBudget3.setOnAction(event -> showInfoFromDB("budget3", "Budget 3"));
        //Pop-Up for MatPaketInformation
        btnMellan1.setOnAction(event -> showInfoFromDB("mellan1", "Mellan 1"));
        //Pop-Up for MatPaketInformation
        btnMellan2.setOnAction(event -> showInfoFromDB("mellan2", "Mellan 2"));
        //Pop-Up for MatPaketInformation
        btnMellan3.setOnAction(event -> showInfoFromDB("mellan2", "Mellan 3"));

    }

    private void showInfoFromDB(String matpaket, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null); // Ändra denna till något mer passande :D

        Connection connection = null;
        try {
            connection = DatabaseHandler.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Information FROM MatpaketInformation WHERE Namn = '" + matpaket + "'");

            if (resultSet.next()) {
                String info = resultSet.getString(1);
                alert.setContentText(info);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Something went right or wrong, but it's over now.");
        }

        alert.showAndWait();
    }
}
