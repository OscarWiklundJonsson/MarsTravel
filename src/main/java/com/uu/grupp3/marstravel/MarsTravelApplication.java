package com.uu.grupp3.marstravel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MarsTravelApplication extends Application {
    public static Stage mainWindow; // Add this line

    @Override
    public void start(Stage stage) throws IOException {
        mainWindow = stage; // Add this line

        FXMLLoader fxmlLoader = new FXMLLoader(MarsTravelApplication.class.getResource("boka.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Mars Travel MMXXI - Welcome to Mars");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("Även ful kod kan vara vacker.");
        launch();
    }
}