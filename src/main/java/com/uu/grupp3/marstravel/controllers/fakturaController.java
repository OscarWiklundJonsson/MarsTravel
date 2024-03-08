package com.uu.grupp3.marstravel.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class fakturaController {

    @FXML
    private TableView<FakturaItem> tvFaktura;

    @FXML
    private TableColumn<FakturaItem, String> tcProdukt;

    @FXML
    private TableColumn<FakturaItem, String> tcPris;

    public void initialize() {
        // Initialize table columns
        tcProdukt.setCellValueFactory(cellData -> cellData.getValue().produktProperty());
        tcPris.setCellValueFactory(cellData -> cellData.getValue().prisProperty());

        // Populate the table with data from stored information
        populateTable();
    }

    private void populateTable() {
        CheckoutCartService checkoutCartService = new CheckoutCartService();
        FakturaItem[] items = checkoutCartService.retrieveStoredInformation(); // Metod för att hämta data

        // Add items to the table
        tvFaktura.getItems().addAll(items);
    }
}


