package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.database.DatabaseReciveInformation;
import com.uu.grupp3.marstravel.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class MatPaketController {
    @FXML
    private Button btnBudget1;

    @FXML
    private Button btnBudget2;

    @FXML
    private Button btnBudget3;

    @FXML
    private Button btnLyx1;

    @FXML
    private Button btnLyx2;

    @FXML
    private Button btnLyx3;

    @FXML
    private Button btnMellan1;

    @FXML
    private Button btnMellan2;

    @FXML
    private Button btnMellan3;

    @FXML
    private Button btnNÄSTA;

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

    @FXML Button btnVALJAresedatum;

    @FXML
    private Button btnVALJAkundinfo;

    @FXML
    private Button btnVALJAmatpaketdit;

    @FXML
    private Button btnVALJAmatpakethem;

    @FXML
    private Button btnVarukorg;

    @FXML
    private Label lblEcoPris;

    @FXML
    private Label lblEcoPris1;

    @FXML
    private Label lblEcoPris2;

    @FXML
    private Label lblEcoPris21;

    @FXML
    private Label lblInsidePris;

    @FXML
    private Label lblInsidePris1;

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
    private RadioButton rbtnBudget1;

    @FXML
    private RadioButton rbtnBudget2;

    @FXML
    private RadioButton rbtnBudget3;

    @FXML
    private RadioButton rbtnLyx1;

    @FXML
    private RadioButton rbtnLyx2;

    @FXML
    private RadioButton rbtnLyx3;

    @FXML
    private RadioButton rbtnMellan1;

    @FXML
    private RadioButton rbtnMellan2;

    @FXML
    private RadioButton rbtnMellan3;

    private CheckoutCartService checkoutCartService = new CheckoutCartService();
    RadioButtonState radioButtonState = RadioButtonState.getInstance();


    public void initialize() {
        // Funktion för att endast välja en radioknapp för mat ( @TODO gör om till en generell funktion)
        ToggleGroup group = new ToggleGroup();
        rbtnBudget1.setToggleGroup(group);
        rbtnBudget2.setToggleGroup(group);
        rbtnBudget3.setToggleGroup(group);
        rbtnLyx1.setToggleGroup(group);
        rbtnLyx2.setToggleGroup(group);
        rbtnLyx3.setToggleGroup(group);
        rbtnMellan1.setToggleGroup(group);
        rbtnMellan2.setToggleGroup(group);
        rbtnMellan3.setToggleGroup(group);

        rbtnBudget1.setSelected(radioButtonState.getButtonState("rbtnBudget1"));
        rbtnBudget2.setSelected(radioButtonState.getButtonState("rbtnBudget2"));
        rbtnBudget3.setSelected(radioButtonState.getButtonState("rbtnBudget3"));
        rbtnLyx1.setSelected(radioButtonState.getButtonState("rbtnLyx1"));
        rbtnLyx2.setSelected(radioButtonState.getButtonState("rbtnLyx2"));
        rbtnLyx3.setSelected(radioButtonState.getButtonState("rbtnLyx3"));
        rbtnMellan1.setSelected(radioButtonState.getButtonState("rbtnMellan1"));
        rbtnMellan2.setSelected(radioButtonState.getButtonState("rbtnMellan2"));
        rbtnMellan3.setSelected(radioButtonState.getButtonState("rbtnMellan3"));

        rbtnBudget1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnBudget1", newValue);
        });
        rbtnBudget2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnBudget2", newValue);
        });
        rbtnBudget3.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnBudget3", newValue);
        });
        rbtnLyx1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnLyx1", newValue);
        });
        rbtnLyx2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnLyx2", newValue);
        });
        rbtnLyx3.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnLyx3", newValue);
        });
        rbtnMellan1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnMellan1", newValue);
        });
        rbtnMellan2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnMellan2", newValue);
        });
        rbtnMellan3.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnMellan3", newValue);
        });


        btnNÄSTA.setDisable(true);

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (group.getSelectedToggle() != null) {
                btnNÄSTA.setDisable(false);
            }
        });

        DatabaseReciveInformation dbInfo = new DatabaseReciveInformation();
        StoreTravelChoices storeTravelChoices = new StoreTravelChoices();

        //Pop-Up for MatpaketInformation, detta borde man ju kunna göra bättre? @todo
        btnBudget1.setOnAction(event -> dbInfo.showInfoFromDB("budget1", "Budget 1", "MatpaketInformation", 1));
        btnBudget2.setOnAction(event -> dbInfo.showInfoFromDB("budget2", "Budget 2", "MatpaketInformation", 1));
        btnBudget3.setOnAction(event -> dbInfo.showInfoFromDB("budget3", "Budget 3", "MatpaketInformation", 1));
        btnMellan1.setOnAction(event -> dbInfo.showInfoFromDB("mellan1", "Mellan 1", "MatpaketInformation", 1));
        btnMellan2.setOnAction(event -> dbInfo.showInfoFromDB("mellan2", "Mellan 2", "MatpaketInformation", 1));
        btnMellan3.setOnAction(event -> dbInfo.showInfoFromDB("mellan3", "Mellan 3", "MatpaketInformation", 1));
        btnLyx1.setOnAction(event -> dbInfo.showInfoFromDB("lyx1", "Lyx 1", "MatpaketInformation", 1));
        btnLyx2.setOnAction(event -> dbInfo.showInfoFromDB("lyx2", "Lyx 2", "MatpaketInformation", 1));
        btnLyx3.setOnAction(event -> dbInfo.showInfoFromDB("lyx3", "Lyx 3", "MatpaketInformation", 1));

        // Nästa knappen. Skickar användaren till nästa sida. (evenemang). Använder sig av NextButton klassen som är en service klass.
        btnNÄSTA.setOnAction(event -> {
            if (storeTravelChoices.getMat() != null) {
                storeTravelChoices.removeMat();
            }
            storeTravelChoices.storeSelectedRadioButton(group, "Matpaket: ");
            NextButton nextButton = new NextButton();

            Stage stage = (Stage) btnNÄSTA.getScene().getWindow();
            nextButton.nextButton("/com/uu/grupp3/marstravel/evenemang.fxml", stage);
        });
        btnVALJAmatpaketdit.setDisable(true);

        // denna beast som visar varukorgen
        btnVarukorg.setOnAction(event -> {
            try {
                checkoutCartService.showCheckoutCart();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    private SideBarButtons sideBarButtons = new SideBarButtons();

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
