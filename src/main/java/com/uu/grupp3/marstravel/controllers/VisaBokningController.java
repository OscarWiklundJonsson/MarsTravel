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

        alert.showAndWait();
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

