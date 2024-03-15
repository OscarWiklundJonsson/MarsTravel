package com.uu.grupp3.marstravel.services;

import java.io.*;
import java.util.Properties;

/**
 * Små metoder som håller programmet vid liv
 */
public class BackgroundTasks {

    /**
     * Kontrollerar så att en fil existerar vid start av programmet(MarsTravel)
     * @param fileName namnet på filen som ska kontrolleras.
     */
    public static void fileCheck(String fileName){
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("Fil skapad: " + file.getName());
            } else {
                System.out.println(fileName + " Existerar.");
            }
        } catch (IOException e) {
            System.out.println("Trubbel.");
            e.printStackTrace();
        }
    }

    /**
     * Används endast vid debugging, ingen stor grej
     *
     */
    public static void applicationHealthCheck() {
        /* Get the Runtime instance associated with the current Java application */
        Runtime runtime = Runtime.getRuntime();

        /* Calculate the used memory by subtracting the free memory from the total memory */
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();

        /* Calculate the available processors (cores) */
        int availableProcessors = runtime.availableProcessors();

        /* Get the system properties */
        Properties properties = System.getProperties();

        /* Log the information */
        System.out.println("Used Memory: " + usedMemory);
        System.out.println("Available Processors: " + availableProcessors);
        System.out.println("Java Version: " + properties.getProperty("java.version"));
        System.out.println("Operating System: " + properties.getProperty("os.name"));
        System.out.println("User Home Directory: " + properties.getProperty("user.home"));
    }
}
