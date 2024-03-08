package com.uu.grupp3.marstravel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ResourceBundle;


public class sammanstallningController implements Initializable {
    @FXML
    private WebView webView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Find the most recent HTML file
        File[] htmlFiles = new File(".").listFiles((dir, name) -> name.startsWith("order") && name.endsWith(".html"));
        if (htmlFiles != null && htmlFiles.length > 0) {
            Arrays.sort(htmlFiles, Comparator.comparingLong(f -> Long.parseLong(f.getName().replaceAll("[^\\d]", ""))));
            File mostRecentHtmlFile = htmlFiles[htmlFiles.length - 1];

            // Load the most recent HTML file into the WebView
            WebEngine webEngine = webView.getEngine();
            webEngine.load(mostRecentHtmlFile.toURI().toString());
        }
    }
}
