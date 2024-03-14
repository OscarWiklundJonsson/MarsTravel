package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.services.CheckoutCartService;
import com.uu.grupp3.marstravel.services.NextButton;
import com.uu.grupp3.marstravel.services.SendMail;
import com.uu.grupp3.marstravel.services.UserData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class SammanstallningController implements Initializable {
    @FXML
    private WebView wvSammanstallning;

    @FXML
    private Button btnAVBRYT;

    @FXML
    private Button btnGODKANN;

    @FXML
    private Button btnTILLBAKA;


    private File mostRecentHtmlFile;  // Add this line

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMostRecentHtml();
        SendMail sendMail = new SendMail();

        CheckoutCartService checkoutCartService = new CheckoutCartService();

        btnAVBRYT.setOnAction(event -> {
            checkoutCartService.checkoutCartClearCart();
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
            checkoutCartService.checkoutCartClearCart();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Spara faktura");
            alert.setHeaderText("Faktura");
            alert.setContentText("Vad vill du göra?");

            ButtonType buttonEmail = new ButtonType("Skicka e-post");
            ButtonType buttonPrint = new ButtonType("Skriv ut");

            alert.getButtonTypes().setAll(buttonEmail, buttonPrint);

            alert.showAndWait().ifPresent(response -> {
                if (response == buttonEmail) {
                    String email = UserData.getInstance().getEmail();  // Get the email
                    String pnummer = UserData.getInstance().getPersonnummer();  // Get the personal number
                    String fnamn = UserData.getInstance().getFörnamn(); // Get the first name
                    sendMail.sendEmail(email, "Faktura - MarsTravel", "Hej " + fnamn + ", här är din faktura från MarsTravel.", mostRecentHtmlFile);
                    System.out.println("Skickat e-post till kund: " + pnummer);
                } else if (response == buttonPrint) {
                    System.out.println("Skriv ut");
                    try {
                        Desktop.getDesktop().print(mostRecentHtmlFile);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        });
    }

    /**
     * ???
     */
    public void loadMostRecentHtml() {
        try {
            // Find the most recent HTML file
            mostRecentHtmlFile = Arrays.stream(new File(".").listFiles((dir, name) -> name.startsWith("order") && name.endsWith(".html")))
                    .filter(Objects::nonNull)
                    .max(Comparator.comparingLong(f -> f.lastModified()))
                    .orElse(null);

            // Load the most recent HTML file into the WebView with UTF-8 encoding
            if (mostRecentHtmlFile != null) {
                String url = mostRecentHtmlFile.toURI().toURL().toString();
                url += "?encoding=UTF-8"; // Add encoding information to the URL
                System.out.println("Loading URL: " + url); // Print the URL to the console
                wvSammanstallning.getEngine().load(url);
                wvSammanstallning.getEngine().getLoadWorker().exceptionProperty().addListener((o, old, value) -> {
                    if (value != null) {
                        System.out.println("Caught exception: " + value);
                    }
                });
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}