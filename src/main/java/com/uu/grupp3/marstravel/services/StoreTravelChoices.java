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
import java.util.stream.Collectors;

public class StoreTravelChoices {
    private static boolean isFirstRun = true;
    String fileName = "travelChoices.txt";

    /**
     * Stores the selected radio button in a file
     * @param group (ToggleGroup) the group of radio buttons
     */
    public void storeSelectedRadioButton(ToggleGroup group, String prefix) {
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String selectedRadioButtonValue = selectedRadioButton == null ? "---" : selectedRadioButton.getText();
        writeToFile(prefix + selectedRadioButtonValue);
    }
    public void storeDate(String date) {
        writeToFile(date);
    }

    public void storeChoiceBox(String prefix, ChoiceBox<String> choiceBox) {
        String selectedValue = choiceBox.getValue();
        writeToFile(prefix + selectedValue);
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

    public String getMat(){
        String fileName = "travelChoices.txt";
        Path path = Paths.get(fileName);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            List<String> lines = reader.lines().collect(Collectors.toList());
            for (String line : lines) {
                if (line.contains("Matpaket: ")) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public String getMatHem(){
        String fileName = "travelChoices.txt";
        Path path = Paths.get(fileName);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            List<String> lines = reader.lines().collect(Collectors.toList());
            for (String line : lines) {
                if (line.contains("MatpaketHem: ")) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getHytt(){
        String fileName = "travelChoices.txt";
        Path path = Paths.get(fileName);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            List<String> lines = reader.lines().collect(Collectors.toList());
            for (String line : lines) {
                if (line.contains("Hytt: ")) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public String getHyttHem(){
        Path path = Paths.get(fileName);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            List<String> lines = reader.lines().collect(Collectors.toList());
            for (String line : lines) {
                if (line.contains("HyttHem: ")) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getEvenemang() {
        Path path = Paths.get(fileName);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            List<String> lines = reader.lines().collect(Collectors.toList());
            for (String line : lines) {
                if (line.contains("Filmpremiarer: ")) {
                    return line;
                }
                if (line.contains("Teaterpremiarer: ")) {
                    return line;
                }
                if (line.contains("Konserter: ")) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public String getEvenemangHem() {
        Path path = Paths.get(fileName);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            List<String> lines = reader.lines().collect(Collectors.toList());
            for (String line : lines) {
                if (line.contains("FilmpremiarerHem: ")) {
                    return line;
                }
                if (line.contains("TeaterpremiarerHem: ")) {
                    return line;
                }
                if (line.contains("KonserterHem: ")) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getHealthIns() {
        String fileName = "travelChoices.txt";
        Path path = Paths.get(fileName);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            List<String> lines = reader.lines().collect(Collectors.toList());
            for (String line : lines) {
                if (line.contains("Hälsoförsäkring: ")) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Removes the stored info from the file
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
    public void removeHytt() {
        String fileName = "travelChoices.txt";
        Path path = Paths.get(fileName);

        try {
            List<String> lines = Files.readAllLines(path);
            lines.removeIf(line -> line.contains("Hytt: "));
            Files.write(path, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void removeHyttHem() {
        String fileName = "travelChoices.txt";
        Path path = Paths.get(fileName);

        try {
            List<String> lines = Files.readAllLines(path);
            lines.removeIf(line -> line.contains("HyttHem: "));
            Files.write(path, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void removeMat() {
        String fileName = "travelChoices.txt";
        Path path = Paths.get(fileName);

        try {
            List<String> lines = Files.readAllLines(path);
            lines.removeIf(line -> line.contains("Matpaket: "));
            Files.write(path, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void removeMatHem() {
        String fileName = "travelChoices.txt";
        Path path = Paths.get(fileName);

        try {
            List<String> lines = Files.readAllLines(path);
            lines.removeIf(line -> line.contains("MatpaketHem: "));
            Files.write(path, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void removeEvenemang() {
        String fileName = "travelChoices.txt";
        Path path = Paths.get(fileName);

        try {
            List<String> lines = Files.readAllLines(path);
            lines.removeIf(line -> line.contains("Filmpremiarer: "));
            lines.removeIf(line -> line.contains("Teaterpremiarer: "));
            lines.removeIf(line -> line.contains("Konserter: "));
            Files.write(path, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void removeHealthIns() {
        String fileName = "travelChoices.txt";
        Path path = Paths.get(fileName);

        try {
            List<String> lines = Files.readAllLines(path);
            lines.removeIf(line -> line.contains("Hälsoförsäkring: "));
            Files.write(path, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
