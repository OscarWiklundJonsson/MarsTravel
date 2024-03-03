package com.uu.grupp3.marstravel.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

import com.uu.grupp3.marstravel.database.DatabaseReciveInformation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * This class is responsible for calculating the total price of the cart and
 * displaying the cart contents in an info dialog.
 */
public class CheckoutCartService {
    DatabaseReciveInformation databaseReciveInformation = new DatabaseReciveInformation();

    /**
     * Calculate the total price of the cart by reading the file "travelChoices.txt"
     * and summing up the prices of the selected items.
     *
     * @return the total price of the cart
     * @throws IOException if an I/O error occurs
     */
    public double calculateTotalPrice() throws IOException {
        String fileName = "travelChoices.txt";
        Path path = Paths.get(fileName);
        double totalPrice = 0;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Hytt: ")) {
                    String[] parts = line.split(": ");
                    String hyttType = parts[1];
                    switch (hyttType) {
                        case "Economy":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("Economy", "HyttInformation");
                            totalPrice += 180000;
                            break;
                        case "Inside":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("Inside", "HyttInformation");
                            totalPrice += 300000;
                            break;
                        case "Spaceside":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("Spaceside", "HyttInformation");
                            totalPrice += 700000;
                            break;
                        case "Svit":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("Svit", "HyttInformation");
                            totalPrice += 1200000;
                            break;
                        case "Sömnkapsel":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("Sömnkapsel", "HyttInformation");
                            totalPrice += 2500000;
                            break;
                        default:
                            totalPrice += 0; // replace with actual price for unknown hytt types
                            break;
                    }
                } else if (line.contains("HyttHem: ")) {
                    String[] parts = line.split(": ");
                    String hyttType = parts[1];
                    switch (hyttType) {
                        case "Economy":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("Economy", "HyttInformation");
                            totalPrice += 180000;
                            break;
                        case "Inside":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("Inside", "HyttInformation");
                            totalPrice += 300000;
                            break;
                        case "Spaceside":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("Spaceside", "HyttInformation");
                            totalPrice += 700000;
                            break;
                        case "Svit":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("Svit", "HyttInformation");
                            totalPrice += 1200000;
                            break;
                        case "Sömnkapsel":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("Sömnkapsel", "HyttInformation");
                            totalPrice += 2500000;
                            break;
                        default:
                            totalPrice += 0; // replace with actual price for unknown hytt types
                            break;
                    }
                } else if (line.contains("Matpaket: ")) {
                    String[] parts = line.split(": ");
                    String matType = parts[1];
                    switch (matType) {
                        case "Budget 1":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("budget1", "MatpaketInformation");
                            totalPrice += 27000;
                            break;
                        case "Budget 2":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("budget2", "MatpaketInformation");
                            totalPrice += 40000;
                            break;
                        case "Budget 3":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("budget3", "MatpaketInformation");
                            totalPrice += 54000;
                            break;
                        case "Mellan 1":
                           // totalPrice += databaseReciveInformation.getPriceFromDatabase("mellan1", "MatpaketInformation");
                            totalPrice += 76000;
                            break;
                        case "Mellan 2":
                           // totalPrice += databaseReciveInformation.getPriceFromDatabase("mellan2", "MatpaketInformation");
                            totalPrice += 90000;
                            break;
                        case "Mellan 3":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("mellan3", "MatpaketInformation");
                            totalPrice += 108000;
                            break;
                        case "Lyx 1":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("lyx1", "MatpaketInformation");
                            totalPrice += 120000;
                            break;
                        case "Lyx 2":
                            // totalPrice += databaseReciveInformation.getPriceFromDatabase("lyx2", "MatpaketInformation");
                            totalPrice += 150000;
                            break;
                        case "Lyx 3":
                            totalPrice += 200000;
                            break;
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("lyx3", "MatpaketInformation");
                        default:
                            totalPrice += 0; // replace with actual price for unknown hytt types
                            break;
                }
                } else if (line.contains("MatpaketHem: ")) {
                    String[] parts = line.split(": ");
                    String matType = parts[1];
                    switch (matType) {
                        case "Budget 1":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("budget1", "MatpaketInformation");
                            totalPrice += 27000;
                            break;
                        case "Budget 2":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("budget2", "MatpaketInformation");
                            totalPrice += 40000;
                            break;
                        case "Budget 3":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("budget3", "MatpaketInformation");
                            totalPrice += 54000;
                            break;
                        case "Mellan 1":
                            // totalPrice += databaseReciveInformation.getPriceFromDatabase("mellan1", "MatpaketInformation");
                            totalPrice += 76000;
                            break;
                        case "Mellan 2":
                            // totalPrice += databaseReciveInformation.getPriceFromDatabase("mellan2", "MatpaketInformation");
                            totalPrice += 90000;
                            break;
                        case "Mellan 3":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("mellan3", "MatpaketInformation");
                            totalPrice += 108000;
                            break;
                        case "Lyx 1":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("lyx1", "MatpaketInformation");
                            totalPrice += 120000;
                            break;
                        case "Lyx 2":
                            // totalPrice += databaseReciveInformation.getPriceFromDatabase("lyx2", "MatpaketInformation");
                            totalPrice += 150000;
                            break;
                        case "Lyx 3":
                            totalPrice += 200000;
                            break;
                        //totalPrice += databaseReciveInformation.getPriceFromDatabase("lyx3", "MatpaketInformation");
                        default:
                            totalPrice += 0;
                            break;
                    }

                    // Evenemang
                }else if (line.contains("Konserter: ")) {
                    String[] parts = line.split(": ");
                    if (parts.length > 1) {
                        String evenemangType = parts[1].split(" ")[0];
                        int numberOfTickets = Integer.parseInt(parts[1].trim());
                        totalPrice += numberOfTickets * 30000;
                    }
                } else if (line.contains("Teaterpremiarer: ")) {
                    String[] parts = line.split(": ");
                    if (parts.length > 1) {
                        String evenemangType = parts[1].split(" ")[0];
                        int numberOfTickets = Integer.parseInt(parts[1].trim());
                        totalPrice += numberOfTickets * 28000;
                    }
                } else if (line.contains("Filmpremiarer: ")) {
                    String[] parts = line.split(": ");
                    if (parts.length > 1) {
                        String evenemangType = parts[1].split(" ")[0]; // this is the text after "Filmpremiarer: "
                        int numberOfTickets = Integer.parseInt(parts[1].trim());
                        totalPrice += numberOfTickets * 25000;
                    }

                } else if (line.contains("Hälsoförsäkring: ")) {
                    String[] parts = line.split(": ");
                    String healthIns = parts[1];
                    switch (healthIns) {
                        case "---":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("budget1", "MatpaketInformation");
                            totalPrice += 0;
                            break;
                        case "Hälsoförsäkring":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("budget2", "MatpaketInformation");
                            totalPrice += 50000;
                            break;

                    }
                }else if (line.contains("Hotell: ")) {
                    String[] parts = line.split(": ");
                    String hotellType = parts[1]; // this is the text after "Matpaket: "
                    switch (hotellType) {
                        case "Polar Lansdorp":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("budget1", "MatpaketInformation");
                            totalPrice += 3500;
                            break;
                        case "Polar Wielders":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("budget2", "MatpaketInformation");
                            totalPrice += 5000;
                            break;
                        case "Hotel Phobos Enkelrum":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("budget2", "MatpaketInformation");
                            totalPrice += 7500;
                            break;
                        case "Hotel Phobos Dubbelrum":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("budget2", "MatpaketInformation");
                            totalPrice += 12000;
                            break;
                        case "Hotel Deimos Enkelrum":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("budget2", "MatpaketInformation");
                            totalPrice += 7500;
                            break;
                        case "Hotel Deimos Dubbelrum":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("budget2", "MatpaketInformation");
                            totalPrice += 12000;
                            break;
                        case "Royal City Enkel Lyx":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("budget2", "MatpaketInformation");
                            totalPrice += 20000;
                            break;
                        case "Royal City Dubbel Lyx":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("budget2", "MatpaketInformation");
                            totalPrice += 35000;
                            break;
                        case "Royal City Svit":
                            //totalPrice += databaseReciveInformation.getPriceFromDatabase("budget2", "MatpaketInformation");
                            totalPrice += 50000;
                            break;
                    }

                }else if (line.contains("Betalkort: ")) {
                    String[] parts = line.split(": ");
                    String betalKortAmount = parts[1].trim(); // this is the text after "Betalkort: "
                    int betalKortAmountInt = Integer.parseInt(betalKortAmount);
                    totalPrice += betalKortAmountInt;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Totalt pris:"+ totalPrice);
        return totalPrice;
    }

    /**
     * Clears the file "travelChoices.txt" by deleting it.
     * I.E the cart is cleared.
     */
    public void checkoutCartClearCart() {
        String fileName = "travelChoices.txt";
        Path path = Paths.get(fileName);
        // remove all contents from the file "travelChoices.txt"
        try {
            RandomAccessFile file = new RandomAccessFile(String.valueOf(path), "rw");
            file.setLength(0);
            file.close();
            System.out.println("Varukorgen har rensats.");
        } catch (IOException e) {
            System.out.println("Ett fel uppstod: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Shows the contents of the cart in an info dialog.
     * The contents are read from the file "travelChoices.txt".
     * The total price of the cart is calculated using the method calculateTotalPrice().
     */
    public void showCheckoutCart() {
        // read the file "travelChoices.txt" and collect the contents
        String fileName = "travelChoices.txt";
        Path path = Paths.get(fileName);
        StringBuilder contents = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                contents.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // calculate total price
        double totalPrice = 0;
        try {
            totalPrice = calculateTotalPrice();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.UK);
        String formattedTotalPrice = numberFormat.format(totalPrice);

        contents.append("Totalt pris: ").append(formattedTotalPrice).append(" kr");
        AtomicReference<Stage> stage = new AtomicReference<>(new Stage());
        TextArea textArea = new TextArea(contents.toString());
        textArea.setEditable(false);
        textArea.setWrapText(true);

        // @todo Ändra så att den öppnar boka.fxml
        Button button = new Button("Rensa varukorg");
        button.setOnAction(event -> {
            checkoutCartClearCart();
            textArea.setText("Varukorgen är rensad.");
        });

        VBox vbox = new VBox(textArea, button);
        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        Scene scene = new Scene(scrollPane);
        stage.get().setScene(scene);
        stage.get().setTitle("Varukorg - Totalt pris: " + formattedTotalPrice + " kr"); // Ändra?
        stage.get().show();
    }
}