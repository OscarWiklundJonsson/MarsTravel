package com.uu.grupp3.marstravel.services;

import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class StoreTravelChoices {
    private static boolean isFirstRun = true;

    /**
     * Stores the selected radio button in a file
     * @param group (ToggleGroup) the group of radio buttons
     */
    public void storeSelectedRadioButton(ToggleGroup group) {
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String selectedRadioButtonValue = selectedRadioButton == null ? "Ingen radio knapp har valts" : selectedRadioButton.getText();
        writeToFile(selectedRadioButtonValue);
    }

    /**
     * Writes the content to a file
     * @param content (String) the content to write to the file (HyttAlternativ, MatPaket osv)
     */
    private void writeToFile(String content) {
        String fileName = "travelChoices.txt";
        Path path = Paths.get(fileName);
        int kundNr = 1; // TODO: anpassa till något lämpligt

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            if (isFirstRun) {
                writer.write("---------Kund-" + kundNr + "----------\n");
                isFirstRun = false;
            }
            writer.write(content + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}