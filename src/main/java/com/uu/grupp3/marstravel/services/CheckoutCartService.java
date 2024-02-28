package com.uu.grupp3.marstravel.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckoutCartService {

    public void checkoutCartPriceCalculator() throws IOException {
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
                            totalPrice += 70000; // replace with actual price
                            break;
                        case "Inside":
                            totalPrice += 1200000; // replace with actual price
                            break;
                        case "Spaceside":
                            totalPrice += 700000; // replace with actual price
                            break;
                        case "Svit":
                            totalPrice += 1200000; // replace with actual price
                            break;
                        case "Sömnkapsel":
                            totalPrice += 2500000; // replace with actual price
                            break;
                        default:
                            totalPrice += 0; // replace with actual price for unknown hytt types
                            break;
                    }
                } else if (line.contains("Matpaket: ")) {
                    String[] parts = line.split(": ");
                    String matType = parts[1]; // this is the text after "Matpaket: "

                    switch (matType) {
                        case "Standard":
                            totalPrice += 50000; // replace with actual price
                            break;
                        case "Lyx":
                            totalPrice += 100000; // replace with actual price
                            break;
                        default:
                            totalPrice += 0; // replace with actual price for unknown mat types
                            break;
                    }
                } else if (line.contains("MatpaketHem: ")) {
                    String[] parts = line.split(": ");
                    String matHemType = parts[1]; // this is the text after "MatpaketHem: "

                    switch (matHemType) {
                        case "Standard":
                            totalPrice += 50000; // replace with actual price
                            break;
                        case "Lyx":
                            totalPrice += 100000; // replace with actual price
                            break;
                        default:
                            totalPrice += 0; // replace with actual price for unknown matHem types
                            break;
                    }
                }
                // ... add more conditions for other choices
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Total price: " + totalPrice);
    }

    public void checkoutCartClearCart() {
        /*
        Ta bort alla valda alternativ från varukorgen genom att radera den temporära filen.
         */
    }
}