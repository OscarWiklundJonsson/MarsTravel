package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.services.CharacterRestrictions;
import com.uu.grupp3.marstravel.services.NextButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class VisaBokningController {
    public Button btnTillbaka;
    @FXML
    private TextField tfBokningsnummer;
    @FXML
    private Button btnSök;
    @FXML
    private Label lblSokBokningsinformation;

    /**
     *
     * @param actionEvent
     */
    public void handleSokButtonClick(ActionEvent actionEvent) {

        //Kod för att söka på bokningsnummer från textfältet
        // Pop-Up för informationen
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bokningsinformation");
        alert.setHeaderText(null);
        String bokningsnummer = tfBokningsnummer.getText();
        alert.setContentText("Bokningsnummer: " + bokningsnummer + "\n"); //bokningsinformation
        String personnummer = tfBokningsnummer.getText();

        try {
            // Get the directory path
            Path dirPath = Paths.get("./"); // replace with your directory path
            // Filter the files based on the filename
            Optional<Path> foundFile = Files.list(dirPath)
                    .filter(path -> path.getFileName().toString().startsWith("order" + personnummer + "-"))
                    .findFirst();

            // If a file is found, display the booking information
            if (foundFile.isPresent()) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Bokningsinformation");
                alert.setHeaderText(null);
                alert.setContentText("Bokningsnummer: " + personnummer + "\n" + "Filnamn: " + foundFile.get().getFileName());

                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fel");
                alert.setHeaderText(null);
                alert.setContentText("Ingen bokning hittades för personnummer: " + personnummer);

                alert.showAndWait();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        CharacterRestrictions.applyCharacterRestrictions(tfBokningsnummer);


        btnTillbaka.setOnAction(event -> {
            NextButton nextButton = new NextButton();
            Stage stage = (Stage) btnTillbaka.getScene().getWindow();
            nextButton.nextButton("/com/uu/grupp3/marstravel/boka.fxml", stage);
        });
    }
}

