package com.uu.grupp3.marstravel.services;

import javafx.scene.control.ChoiceBox;
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


public class StoreTravelChoices {
    private static final String FILE_NAME = "travelChoices.txt"; // Filnamnet ;)
    // najs, vi har en fil som heter travelChoices.txt som vi skriver till och läser från, men om filen inte finns så är det kört
    // kul grej

    /**
     * Stores the selected radio button in a file
     * @param group the toggle group
     * @param prefix the prefix to be written to the file ( e.g. "Matpaket: " )
     */
    public void storeSelectedRadioButton(ToggleGroup group, String prefix) {
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String selectedRadioButtonValue = selectedRadioButton == null ? "---" : selectedRadioButton.getText();
        writeToFile(prefix + selectedRadioButtonValue);
    }

    /**
     * Stores the selected date in a file
     * @param date the selected date
     */
    public void storeDate(String date) {
        writeToFile(date);
    }

    /**
     * Stores the selected choice box in a file
     * @param prefix the prefix to be written to the file ( e.g. "Hytt: " )
     * @param choiceBox the choice box
     */
    public void storeChoiceBox(String prefix, ChoiceBox<String> choiceBox) {
        String selectedValue = choiceBox.getValue();
        writeToFile(prefix + selectedValue);
    }

    /**
     * Stores the selected check box in a file
     * @param value
     * @param label
     */
    public void storeTextFieldValue(String value, String label) {
        writeToFile(label + value);
    }
    /**
     * Stores the selected check box in a file
     * @param content the content to be written to the file ( e.g. "Hälsoförsäkring: Hälsoförsäkring" )
     */
    public void writeToFile(String content) {
        Path path = Paths.get(FILE_NAME);

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            writer.write(content + "\n");
        } catch (IOException e) {
            e.printStackTrace(); // Robust logging? nej tack
        }
    }

    /**
     * Reads the file and returns the first line containing the search string
     * @param searchString
     * @return the first line containing the search string
     */
    public String getLineContaining(String searchString) {
        Path path = Paths.get(FILE_NAME);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return reader.lines()
                    .filter(line -> line.contains(searchString))
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Removes the first line containing the search string
     * @param searchString the search string
     */
    public void removeLineContaining(String searchString) {
        Path path = Paths.get(FILE_NAME);

        try {
            List<String> lines = Files.readAllLines(path);
            lines.removeIf(line -> line.contains(searchString));
            Files.write(path, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Removes all lines containing the search string
     * @param  // får felkod om jag har en param här, så då struntar vi i det :D
     */
    public String getDate() {
        return getLineContaining(" till ");
    }

    /**
     * Returns the date
     * @return the date
     */
    public String getMat() {
        return getLineContaining("Matpaket: ");
    }

    /**
     * Returns the mat hem
     * @return the mat hem
     */
    public String getMatHem() {
        return getLineContaining("MatpaketHem: ");
    }

    /**
     * Returns the hytt
     * @return the hytt
     */
    public String getHytt() {
        return getLineContaining("Hytt: ");
    }

    /**
     * Returns the hytt hem
     * @return
     */
    public String getHyttHem() {
        return getLineContaining("HyttHem: ");
    }
    /**
     * Returns the evenemang
     * @return the evenemang
     */
    public String getEvenemang() {
        return getLineContaining("Filmpremiarer: ") != null ? getLineContaining("Filmpremiarer: ") :
                getLineContaining("Teaterpremiarer: ") != null ? getLineContaining("Teaterpremiarer: ") :
                        getLineContaining("Konserter: ");
    }

    /**
     * Returns the evenemang hem
     * @return the evenemang hem
     */
    public String getEvenemangHem() {
        return getLineContaining("FilmpremiarerHem: ") != null ? getLineContaining("FilmpremiarerHem: ") :
                getLineContaining("TeaterpremiarerHem: ") != null ? getLineContaining("TeaterpremiarerHem: ") :
                        getLineContaining("KonserterHem: ");
    }

    // Orkar inte göra fler av dessa, koden funkar likadant för alla metoder...
    public String getHealthIns() {
        return getLineContaining("Hälsoförsäkring: ");
    }

    public String getBetalkort() {
        return getLineContaining("Betalkort: ");
    }

    public String getHotell() {
        return getLineContaining("Hotell: ");
    }

    public void removeDate() {
        removeLineContaining(" till ");
    }

    public void removeHytt() {
        removeLineContaining("Hytt: ");
    }

    public void removeHyttHem() {
        removeLineContaining("HyttHem: ");
    }

    public void removeMat() {
        removeLineContaining("Matpaket: ");
    }

    public void removeMatHem() {
        removeLineContaining("MatpaketHem: ");
    }

    public void removeEvenemang() {
        removeLineContaining("Filmpremiarer: ");
        removeLineContaining("Teaterpremiarer: ");
        removeLineContaining("Konserter: ");
    }

    public void removeHealthIns() {
        removeLineContaining("Hälsoförsäkring: ");
    }

    public void removeEvenemangHem() {
        removeLineContaining("FilmpremiarerHem: ");
        removeLineContaining("TeaterpremiarerHem: ");
        removeLineContaining("KonserterHem: ");
    }

    public void removeHotell() {
        removeLineContaining("Hotell: ");
    }

    public void removeBetalkort() {
        removeLineContaining("Betalkort: ");
    }


}