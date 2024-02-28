package com.uu.grupp3.marstravel.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.uu.grupp3.marstravel.database.DatabaseReciveInformation;

// @todo extract method osv
public class CheckoutCartService {
    DatabaseReciveInformation databaseReciveInformation = new DatabaseReciveInformation();
    public void calculateTotalPrice() throws IOException {
        String fileName = "travelChoices.txt";
        Path path = Paths.get(fileName);
        double totalPrice = 0;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Hytt: ")) {
                    String[] parts = line.split(": ");
                    String hyttType = parts[1]; // this is the text after "Hytt: "
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
                    String hyttType = parts[1]; // this is the text after "Hytt: "
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
                    String matType = parts[1]; // this is the text after "Matpaket: "
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
                    String matType = parts[1]; // this is the text after "Matpaket: "
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
                } else if (line.contains("Konserter: ")) {
                    String[] parts = line.split(": ");
                    if (parts.length > 1) {
                        String evenemangType = parts[1].split(" ")[0]; // this is the text after "Konserter: "
                        int numberOfTickets = Integer.parseInt(parts[1].split(" ")[1]);
                        totalPrice += numberOfTickets * 30000;
                    }
                } else if (line.contains("Teaterpremiarer: ")) {
                    String[] parts = line.split(": ");
                    if (parts.length > 1) {
                        String evenemangType = parts[1].split(" ")[0]; // this is the text after "Teaterpremiarer: "
                        int numberOfTickets = Integer.parseInt(parts[1].split(" ")[1]);
                        totalPrice += numberOfTickets * 28000;
                    }
                } else if (line.contains("Filmpremiarer: ")) {
                    String[] parts = line.split(": ");
                    if (parts.length > 1) {
                        String evenemangType = parts[1].split(" ")[0]; // this is the text after "Filmpremiarer: "
                        int numberOfTickets = Integer.parseInt(parts[1].split(" ")[1]);
                        totalPrice += numberOfTickets * 25000;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Total price: " + totalPrice);
    }

    public void checkoutCartClearCart() {
        // delete the file "travelChoices.txt"
        String fileName = "travelChoices.txt";
        Path path = Paths.get(fileName);
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}