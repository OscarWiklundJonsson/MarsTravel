package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.database.DatabaseReciveInformation;
import com.uu.grupp3.marstravel.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class HytterController {

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
    private RadioButton rbtnEco;

    @FXML
    private RadioButton rbtnInside;

    @FXML
    private RadioButton rbtnSleep;

    @FXML
    private RadioButton rbtnSpaceside;

    @FXML
    private RadioButton rbtnSvit;

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
        rbtnEco.setToggleGroup(group);
        rbtnInside.setToggleGroup(group);
        rbtnSleep.setToggleGroup(group);
        rbtnSpaceside.setToggleGroup(group);
        rbtnSvit.setToggleGroup(group);

        rbtnEco.setSelected(radioButtonState.getButtonState("rbtnEco"));
        rbtnInside.setSelected(radioButtonState.getButtonState("rbtnInside"));
        rbtnSleep.setSelected(radioButtonState.getButtonState("rbtnSleep"));
        rbtnSpaceside.setSelected(radioButtonState.getButtonState("rbtnSpaceside"));
        rbtnSvit.setSelected(radioButtonState.getButtonState("rbtnSvit"));

        rbtnEco.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnEco", newValue);
        });
        rbtnInside.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnInside", newValue);
        });
        rbtnSleep.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnSleep", newValue);
        });
        rbtnSpaceside.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnSpaceside", newValue);
        });
        rbtnSvit.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnSvit", newValue);
        });

        btnNASTA.setDisable(true);

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (group.getSelectedToggle() != null) {
                btnNASTA.setDisable(false);
            }
        });
        btnNASTA.setOnAction(event -> {
            if (storeTravelChoices.getHytt() != null) {
                storeTravelChoices.removeHytt();
            }
            storeTravelChoices.storeSelectedRadioButton(group, "Hytt: "); // Spara valt alternativ till fil.
            NextButton nextButton = new NextButton();
            Stage stage = (Stage) btnNASTA.getScene().getWindow();

            RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
            if (selectedRadioButton != null && "Sömnkapsel".equals(selectedRadioButton.getText())) {
                if (storeTravelChoices.getMat() != null) {
                    storeTravelChoices.removeMat();
                }
                if (storeTravelChoices.getEvenemang() != null) {
                    storeTravelChoices.removeEvenemang();
                }
                nextButton.nextButton("/com/uu/grupp3/marstravel/hotellMars.fxml", stage); // Ändra till nästa sida
            } else {
                nextButton.nextButton("/com/uu/grupp3/marstravel/matpaket.fxml", stage);
            }
        });
        btnVALJAhyttdit.setDisable(true);

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