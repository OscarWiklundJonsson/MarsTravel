package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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


    public void initialize() {
        //Pop-Up info concert
        btnConcertInfo.setOnAction(event -> showInfoFromDB("Konsert", ""));{

        };


        btnNASTA.setOnAction(event -> {
            try {
                // Load the FXML file for the new scene
                FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/resources/com/uu/grupp3/marstravel/evenemang.fxml"));
                Parent root = loader.load();
                // Create a new scene
                Scene scene = new Scene(root);
                // Get the stage from the button and set the new scene
                Stage stage = (Stage) btnNASTA.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    private void showInfoFromDB(String evenemangType, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null); // Ändra denna till något mer passande

        Connection connection = null;
        try {
            connection = DatabaseHandler.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Information FROM EvenemangInformation WHERE Namn = '" + evenemangType + "'");

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
