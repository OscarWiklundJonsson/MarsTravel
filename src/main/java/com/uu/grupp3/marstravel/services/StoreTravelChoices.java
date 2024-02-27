package com.uu.grupp3.marstravel.services;

import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class StoreTravelChoices {
    private static boolean isFirstRun = true;

    /**
     * Stores the selected radio button in a file
     * @param group (ToggleGroup) the group of radio buttons
     */
    public void storeSelectedRadioButton(ToggleGroup group, String prefix) {
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String selectedRadioButtonValue = selectedRadioButton == null ? "Ingen radio knapp har valts" : selectedRadioButton.getText();
        writeToFile(prefix + selectedRadioButtonValue);
    }
    public void storeDate(String date) {
        writeToFile(date);
    }

    /**
     * Writes the content to a file
     * @param content (String) the content to write to the file (HyttAlternativ, MatPaket osv)
     */
    public void writeToFile(String content) {
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

    /**
     * Retrieves the stored date from the file
     * @return (String) the stored date
     */
    public String getDate() {
        String fileName = "travelChoices.txt";
        Path path = Paths.get(fileName);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            List<String> lines = reader.lines().collect(Collectors.toList());
            for (String line : lines) {
                if (line.contains(" till ")) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Removes the stored date from the file
     */
    public void removeDate() {
        String fileName = "travelChoices.txt";
        Path path = Paths.get(fileName);

        try {
            List<String> lines = Files.readAllLines(path);
            lines.removeIf(line -> line.contains(" till "));
            Files.write(path, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}