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

    /**
     *
     * @param url
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resourceBundle
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
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

            ButtonType buttonTypeOne = new ButtonType("Skicka e-post");
            ButtonType buttonTypeTwo = new ButtonType("Skriv ut");

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

            alert.showAndWait().ifPresent(response -> {
                if (response == buttonTypeOne) {
                    String email = UserData.getInstance().getEmail();  // Get the email
                    String pnummer = UserData.getInstance().getPersonnummer();  // Get the personal number
                    sendMail.sendEmail(email, "Faktura - MarsTravel", "Hej, här är din faktura från MarsTravel.", mostRecentHtmlFile);
                    System.out.println("Skickat e-post till kund: " + pnummer);
                } else if (response == buttonTypeTwo) {
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