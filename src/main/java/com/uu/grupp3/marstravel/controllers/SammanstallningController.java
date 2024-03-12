package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.services.NextButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMostRecentHtml();

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


    }

        public void loadMostRecentHtml() {
            // Find the most recent HTML file
            File[] htmlFiles = new File(".").listFiles((dir, name) -> name.startsWith("order") && name.endsWith(".html"));
            if (htmlFiles != null && htmlFiles.length > 0) {
                Arrays.sort(htmlFiles, Comparator.comparingLong(f -> Long.parseLong(f.getName().replaceAll("[^\\d]", ""))));
                File mostRecentHtmlFile = htmlFiles[htmlFiles.length - 1];

                // Load the most recent HTML file into the WebView
                wvSammanstallning.getEngine().load(mostRecentHtmlFile.toURI().toString());
            }
        }
}
