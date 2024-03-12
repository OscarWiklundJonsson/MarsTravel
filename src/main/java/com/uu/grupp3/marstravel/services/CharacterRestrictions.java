package com.uu.grupp3.marstravel.services;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

import java.util.function.UnaryOperator;

public class CharacterRestrictions {


    // Method to create a text formatter with character restrictions
    public static TextFormatter<String> createFormatter(String pattern) {
        UnaryOperator<Change> filter = change -> {
            if (change.getText().matches(pattern)) {
                return change;
            }
            return null;
        };
        return new TextFormatter<>(filter);
    }

    // Apply character restrictions to the text fields
    public static void applyCharacterRestrictions(TextField... textFields) {
        for (TextField textField : textFields) {
            if (textField.getId().equals("tffirstname") || textField.getId().equals("tflastname")) {
                textField.setTextFormatter(createFormatter("[a-öA-Ö]*"));
            } else if (textField.getId().equals("tfphonenumber")) {
                textField.setTextFormatter(createFormatter("[0-9#]*"));
            } else if (textField.getId().equals("tfemail")) {
                textField.setTextFormatter(createFormatter("[0-9a-zA-Z.+\\-_~@]*"));
            } else if (textField.getId().equals("tfPersonnummer")) {
                textField.setTextFormatter(createFormatter("[0-9-]*"));
            } else if (textField.getId().equals("tfAdress")) {
                textField.setTextFormatter(createFormatter("[0-9a-öA-Ö\s]*"));
            } else if (textField.getId().equals("tfOrt")) {
                textField.setTextFormatter(createFormatter("[a-öA-Ö]*"));
            } else if (textField.getId().equals("tfPostnummer")) {
                textField.setTextFormatter(createFormatter("[0-9]*"));
            } else if (textField.getId().equals("tfHalsodetaljer")) {
                textField.setTextFormatter(createFormatter("[a-öA-Ö]*"));
            } else if (textField.getId().equals("tfBokningsnummer")) {
                textField.setTextFormatter(createFormatter("[0-9]*"));
            }
        }
    }
}

