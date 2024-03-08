package com.uu.grupp3.marstravel.controllers;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class SammanstallningController {
    private WebView webView;

    // Constructor with WebView parameter
    public SammanstallningController(WebView webView) {
        this.webView = webView;
    }

    // No-argument constructor for FXMLLoader
    public SammanstallningController() {
        // Leave empty or provide default initialization if needed
    }

    public void loadMostRecentHtml() {
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
