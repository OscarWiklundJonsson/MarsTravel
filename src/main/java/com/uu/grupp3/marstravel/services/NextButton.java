package com.uu.grupp3.marstravel.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Nästa knapp som tar användaren till nästa fönster
 * @param pathToNextFXML - sökväg till nästa fönster
 */
public class NextButton {
    public void nextButton(String pathToNextFXML, Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pathToNextFXML));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

