package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.services.NextButton;
import com.uu.grupp3.marstravel.services.SendMail;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import javafx.scene.web.WebView;


import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ResourceBundle;

public class SammanstallningController implements Initializable {
    @FXML
    private WebView wvSammanstallning;

    @FXML
    private Button btnAVBRYT;

    @FXML
    private Button btnGODKANN;

    @FXML
    private Button btnTILLBAKA;


    private File mostRecentHtmlFile;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMostRecentHtml();
        SendMail sendMail = new SendMail();


        btnAVBRYT.setOnAction(event -> {
            NextButton nextButton = new NextButton();
            Stage stage = (Stage) btnAVBRYT.getScene().getWindow();
            nextButton.nextButton("/com/uu/grupp3/marstravel/boka.fxml", stage);
        });

        btnTILLBAKA.setOnAction(event -> {
            NextButton nextButton = new NextButton();
            Stage stage = (Stage) btnTILLBAKA.getScene().getWindow();
            nextButton.nextButton("/com/uu/grupp3/marstravel/resedatum.fxml", stage);
        });

        btnGODKANN.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Spara faktura");
            alert.setHeaderText("Ahooga");
            alert.setContentText("Vad vill du göra?");

            ButtonType buttonTypeOne = new ButtonType("Skicka e-post");
            ButtonType buttonTypeTwo = new ButtonType("Skriv ut");

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

            alert.showAndWait().ifPresent(response -> {
                if (response == buttonTypeOne) {
                    sendMail.sendEmail("wiklundoscar45@gmail.com","Faktura","Här är din faktura",mostRecentHtmlFile);
                } else if (response == buttonTypeTwo) {
                    // Handle the second button click
                }
            });
        });
    }
    public void loadMostRecentHtml() {
        // Find the most recent HTML file
        File[] htmlFiles = new File(".").listFiles((dir, name) -> name.startsWith("order") && name.endsWith(".html"));
        if (htmlFiles != null && htmlFiles.length > 0) {
            Arrays.sort(htmlFiles, Comparator.comparingLong(f -> {
                String digits = f.getName().replaceAll("[^\\d]", "");
                return digits.isEmpty() ? 0 : Long.parseLong(digits);
            }));
            mostRecentHtmlFile = htmlFiles[htmlFiles.length - 1];
            wvSammanstallning.getEngine().load(mostRecentHtmlFile.toURI().toString());
        }

    }
}
