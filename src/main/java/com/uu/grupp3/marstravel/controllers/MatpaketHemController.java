package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.database.DatabaseReciveInformation;
import com.uu.grupp3.marstravel.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;

public class MatpaketHemController {
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

    @FXML private Button btnVALJAresedatum;

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
    private Label lblSleepPris;

    @FXML
    private Label lblSpacesidePris;

    @FXML
    private Label lblSvitPris;

    @FXML
    private RadioButton rbtnBudget1H;

    @FXML
    private RadioButton rbtnBudget2H;

    @FXML
    private RadioButton rbtnBudget3H;

    @FXML
    private RadioButton rbtnLyx1H;

    @FXML
    private RadioButton rbtnLyx2H;

    @FXML
    private RadioButton rbtnLyx3H;

    @FXML
    private RadioButton rbtnMellan1H;

    @FXML
    private RadioButton rbtnMellan2H;

    @FXML
    private RadioButton rbtnMellan3H;

    private CheckoutCartService checkoutCartService = new CheckoutCartService();
    RadioButtonState radioButtonState = RadioButtonState.getInstance();


    public void initialize() {
        // Funktion för att endast välja en radioknapp för mat ( @TODO gör om till en generell funktion )
        ToggleGroup group = new ToggleGroup();
        rbtnBudget1H.setToggleGroup(group);
        rbtnBudget2H.setToggleGroup(group);
        rbtnBudget3H.setToggleGroup(group);
        rbtnLyx1H.setToggleGroup(group);
        rbtnLyx2H.setToggleGroup(group);
        rbtnLyx3H.setToggleGroup(group);
        rbtnMellan1H.setToggleGroup(group);
        rbtnMellan2H.setToggleGroup(group);
        rbtnMellan3H.setToggleGroup(group);

        rbtnBudget1H.setSelected(radioButtonState.getButtonState("rbtnBudget1H"));
        rbtnBudget2H.setSelected(radioButtonState.getButtonState("rbtnBudget2H"));
        rbtnBudget3H.setSelected(radioButtonState.getButtonState("rbtnBudget3H"));
        rbtnLyx1H.setSelected(radioButtonState.getButtonState("rbtnLyx1H"));
        rbtnLyx2H.setSelected(radioButtonState.getButtonState("rbtnLyx2H"));
        rbtnLyx3H.setSelected(radioButtonState.getButtonState("rbtnLyx3H"));
        rbtnMellan1H.setSelected(radioButtonState.getButtonState("rbtnMellan1H"));
        rbtnMellan2H.setSelected(radioButtonState.getButtonState("rbtnMellan2H"));
        rbtnMellan3H.setSelected(radioButtonState.getButtonState("rbtnMellan3H"));

        rbtnBudget1H.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnBudget1H", newValue);
        });
        rbtnBudget2H.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnBudget2H", newValue);
        });
        rbtnBudget3H.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnBudget3H", newValue);
        });
        rbtnLyx1H.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnLyx1H", newValue);
        });
        rbtnLyx2H.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnLyx2H", newValue);
        });
        rbtnLyx3H.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnLyx3H", newValue);
        });
        rbtnMellan1H.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnMellan1H", newValue);
        });
        rbtnMellan2H.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnMellan2H", newValue);
        });
        rbtnMellan3H.selectedProperty().addListener((observable, oldValue, newValue) -> {
            radioButtonState.setButtonState("rbtnMellan3H", newValue);
        });


        btnNÄSTA.setDisable(true);

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (group.getSelectedToggle() != null) {
                btnNÄSTA.setDisable(false);
            }
        });

        DatabaseReciveInformation dbInfo = new DatabaseReciveInformation();
        StoreTravelChoices storeTravelChoices = new StoreTravelChoices();

        //Pop-Up for MatpaketInformation, detta borde man ju kunna göra bättre? @todo - någon ???
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
            if (storeTravelChoices.getMatHem() != null) {
                storeTravelChoices.removeMatHem();
            }
            storeTravelChoices.storeSelectedRadioButton(group, "MatpaketHem: ");
            NextButton nextButton = new NextButton();
            Stage stage = (Stage) btnNÄSTA.getScene().getWindow();
            nextButton.nextButton("/com/uu/grupp3/marstravel/evenemangHem.fxml", stage);
        });

        btnVALJAmatpakethem.setDisable(true);

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
