package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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
        btnBudget2.setOnAction(event -> showInfoFromDB("budget2", "Budget 2"));
        btnBudget3.setOnAction(event -> showInfoFromDB("budget3", "Budget 3"));
        btnMellan1.setOnAction(event -> showInfoFromDB("mellan1", "Mellan 1"));
        btnMellan2.setOnAction(event -> showInfoFromDB("mellan2", "Mellan 2"));
        btnMellan3.setOnAction(event -> showInfoFromDB("mellan3", "Mellan 3"));
        btnLyx1.setOnAction(event -> showInfoFromDB("lyx1", "Lyx 1"));
        btnLyx2.setOnAction(event -> showInfoFromDB("lyx2", "Lyx 2"));
        btnLyx3.setOnAction(event -> showInfoFromDB("lyx3", "Lyx 3"));

        btnNÄSTA.setOnAction(event -> {
            try {
                // Load the FXML file for the new scene
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uu/grupp3/marstravel/forkopsalternativ.fxml"));
                Parent root = loader.load();
                // Create a new scene
                Scene scene = new Scene(root);
                // Get the stage from the button and set the new scene
                Stage stage = (Stage) btnNÄSTA.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

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
