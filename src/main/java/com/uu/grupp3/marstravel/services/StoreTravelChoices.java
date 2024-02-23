package com.uu.grupp3.marstravel.services;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Spara resvalen i en textfil, för att sedan kunna skicka vidare valda alternativ till databasen + betalning.
 */
public class StoreTravelChoices {
    /**
     * Stores the travel choices in a file.
     */
    String fileName = "travelChoices.txt";
    public void storeTravelChoices() {
        Path path = Paths.get(fileName);

        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                // Replace this with the actual travel choices
                String travelChoices = "Travel Choices...";
                String travelChoices2 = "Travel Choices 2...";

                writer.write(travelChoices);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeFile(String filePath, String text) {
        Path path = Paths.get(filePath);

        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                writer.write(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hyttAlternativ() {
        String text = "Hytt Alternativ...";
        writeFile(fileName, text);
    }
}