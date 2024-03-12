package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML
    private Button btnloggain;

    @FXML
    private TextField tfanvandarnamn;

    @FXML
    private TextField tflosenord;

    /**
     * Hanterar inloggning
     * @param event Triggar ActionEvent när man klickar på Login knappen
     */
    @FXML
    void handleLoginButtonAction(ActionEvent event) {
        String username = tfanvandarnamn.getText();
        String password = tflosenord.getText();

        DatabaseHandler dbHandler = new DatabaseHandler();
        if (dbHandler.validateUser(username, password)) {
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Du är inloggad!");
                alert.setHeaderText(null);
                alert.setContentText("Välkommen till Mars Travel " + username + "!");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uu/grupp3/marstravel/boka.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Felaktigt användarnamn eller lösenord!");
            alert.setHeaderText(null);
            alert.setContentText("FEL! Försök igen!");
            alert.showAndWait();
        }
    }
}