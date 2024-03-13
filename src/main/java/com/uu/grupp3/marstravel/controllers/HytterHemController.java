package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.database.DatabaseReciveInformation;
import com.uu.grupp3.marstravel.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class HytterHemController {

    @FXML
    private Button btnEcoInfo;

    @FXML
    private Button btnInsideInfo;

    @FXML
    private Button btnNASTA;

    @FXML
    private Button btnSleepInfo;

    @FXML
    private Button btnSpacesideInfo;

    @FXML
    private Button btnSvitInfo;

    @FXML
    private Button btnVALJAbetalkort;

    @FXML
    private Button btnVALJAevenemangdit;

    @FXML
    private Button btnVALJAevenemanghem;

    @FXML
    private Button btnVALJAhalsoforsakring;

    @FXML
    private Button btnVALJAhotellmars;

    @FXML
    private Button btnVALJAhyttdit;

    @FXML
    private Button btnVALJAhytthem;

    @FXML
    private Button btnVALJAkundinfo;

    @FXML
    private Button btnVALJAmatpaketdit;

    @FXML
    private Button btnVALJAmatpakethem;

    @FXML
    private Button btnVALJAresedatum;

    @FXML
    private Button btnVarukorg;

    @FXML
    private Label lblEcoPris;

    @FXML
    private Label lblInsidePris;

    @FXML
    private Label lblPris;

    @FXML
    private Label lblSleepPris;

    @FXML
    private Label lblSpacesidePris;

    @FXML
    private Label lblSvitPris;

    @FXML
    private Label lblhyttalternativ;

    @FXML
    private RadioButton rbtnEcoH;

    @FXML
    private RadioButton rbtnInsideH;

    @FXML
    private RadioButton rbtnSleepH;

    @FXML
    private RadioButton rbtnSpacesideH;

    @FXML
    private RadioButton rbtnSvitH;

    private CheckoutCartService checkoutCartService = new CheckoutCartService();

    /**
     * Hanterar infoknappar
     * Hanterar att man endast kan välja ett val
     * Hanterar varukorg knappen
     */
    public void initialize() {
        DatabaseReciveInformation dbInfo = new DatabaseReciveInformation();
        StoreTravelChoices storeTravelChoices = new StoreTravelChoices();
        RadioButtonState radioButtonState = RadioButtonState.getInstance();

        //Pop-Up for EcoInformation
        btnEcoInfo.setOnAction(event -> dbInfo.showInfoFromDB("Economy", "Hytt Economy", "HyttInformation",1));
        //Pop-Up for InsideInformation
        btnInsideInfo.setOnAction(event -> dbInfo.showInfoFromDB("Inside", "Hytt Inside", "HyttInformation",1));
        //Pop-Up for SleepInformation, Sömnkapsel
        btnSleepInfo.setOnAction(event -> dbInfo.showInfoFromDB("Sömnkapsel", "Sömnkapsel", "HyttInformation",1));
        //Pop-Up for hytt Inside
        btnSpacesideInfo.setOnAction(event -> dbInfo.showInfoFromDB("Spaceside", "Hytt Spaceside", "HyttInformation",1));
        //Pop-Up for hytt Svit
        btnSvitInfo.setOnAction(event -> dbInfo.showInfoFromDB("Svit", "Hytt Svit", "HyttInformation",1));

        // Funktion för att endast välja en radioknapp
        ToggleGroup group = new ToggleGroup();
        rbtnEcoH.setToggleGroup(group);
        rbtnInsideH.setToggleGroup(group);
        rbtnSleepH.setToggleGroup(group);
        rbtnSpacesideH.setToggleGroup(group);
        rbtnSvitH.setToggleGroup(group);

        rbtnEcoH.setSelected(radioButtonState.getButtonState("rbtnEcoH"));
        rbtnInsideH.setSelected(radioButtonState.getButtonState("rbtnInsideH"));
        rbtnSleepH.setSelected(radioButtonState.getButtonState("rbtnSleepH"));
        rbtnSpacesideH.setSelected(radioButtonState.getButtonState("rbtnSpacesideH"));
        rbtnSvitH.setSelected(radioButtonState.getButtonState("rbtnSvitH"));

        rbtnEcoH.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnEcoH", newValue);
        });
        rbtnInsideH.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnInsideH", newValue);
        });
        rbtnSleepH.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnSleepH", newValue);
        });
        rbtnSpacesideH.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnSpacesideH", newValue);
        });
        rbtnSvitH.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnSvitH", newValue);
        });

        btnNASTA.setDisable(true);

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (group.getSelectedToggle() != null) {
                btnNASTA.setDisable(false);
            }
        });
        btnNASTA.setOnAction(event -> {
            if (storeTravelChoices.getHyttHem() != null) {
                storeTravelChoices.removeHyttHem();
            }
            storeTravelChoices.storeSelectedRadioButton(group, "HyttHem: ");
            NextButton nextButton = new NextButton();
            Stage stage = (Stage) btnNASTA.getScene().getWindow();

            RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
            if (selectedRadioButton != null && "Sömnkapsel".equals(selectedRadioButton.getText())) {
                if (storeTravelChoices.getMatHem() != null) {
                    storeTravelChoices.removeMatHem();
                }
                if (storeTravelChoices.getEvenemangHem() != null) {
                    storeTravelChoices.removeEvenemangHem();
                }
                nextButton.nextButton("/com/uu/grupp3/marstravel/halsoforsakring.fxml", stage); // Ändra till nästa sida
            } else {
                nextButton.nextButton("/com/uu/grupp3/marstravel/matpaketHem.fxml", stage);
            }
        });
        btnVALJAhytthem.setDisable(true);

        // denna beast som visar varukorgen
        btnVarukorg.setOnAction(event -> {
            try {
                checkoutCartService.showCheckoutCart();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    private final SideBarButtons sideBarButtons = new SideBarButtons();

    /**
     * Här hanteras sidoknapparnas funktion att navigera mellan de olika kategorierna
     * @param event Triggar Action Eventet när man klickar på knapparna
     */
    @FXML
    private void SideBarButtons(ActionEvent event) {
        String fxmlPath = null;

        if (event.getSource() == btnVALJAevenemangdit) {
            fxmlPath = "/com/uu/grupp3/marstravel/evenemang.fxml";
            System.out.println("Evenemang");
        } else if (event.getSource() == btnVALJAmatpaketdit) {
            fxmlPath = "/com/uu/grupp3/marstravel/matpaket.fxml";
            System.out.println("Matpaket");
        } else if (event.getSource() == btnVALJAhyttdit) {
            fxmlPath = "/com/uu/grupp3/marstravel/hytter.fxml";
            System.out.println("Hytter");
        } else if (event.getSource() == btnVALJAresedatum) {
            fxmlPath = "/com/uu/grupp3/marstravel/resedatum.fxml";
            System.out.println("Resedatum");
        } else if (event.getSource() == btnVALJAkundinfo) {
            fxmlPath = "/com/uu/grupp3/marstravel/sparaKundinformation.fxml";
            System.out.println("Kundinformation");
        } else if (event.getSource() == btnVALJAbetalkort) {
            fxmlPath = "/com/uu/grupp3/marstravel/betalkort.fxml";
            System.out.println("Betalkort");
        } else if (event.getSource() == btnVALJAhalsoforsakring) {
            fxmlPath = "/com/uu/grupp3/marstravel/halsoforsakring.fxml";
            System.out.println("Hälsförsäkring");
        } else if (event.getSource() == btnVALJAevenemanghem) {
            fxmlPath = "/com/uu/grupp3/marstravel/evenemanghem.fxml";
            System.out.println("Evenemang hem");
        } else if (event.getSource() == btnVALJAmatpakethem) {
            fxmlPath = "/com/uu/grupp3/marstravel/matpaketHem.fxml";
            System.out.println("Matpaket hem");
        } else if (event.getSource() == btnVALJAhytthem) {
            fxmlPath = "/com/uu/grupp3/marstravel/hytterHem.fxml";
            System.out.println("Hytter hem");
        } else if (event.getSource() == btnVALJAhotellmars) {
            fxmlPath = "/com/uu/grupp3/marstravel/hotellMars.fxml";
            System.out.println("Hotell Mars");
        }

        if (fxmlPath != null) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            sideBarButtons.sideBarButton(fxmlPath, stage);
        }
    }
}