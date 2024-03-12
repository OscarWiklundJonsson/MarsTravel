package com.uu.grupp3.marstravel.controllers;

import com.uu.grupp3.marstravel.services.CheckoutCartService;
import com.uu.grupp3.marstravel.services.StoreTravelChoices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import com.uu.grupp3.marstravel.services.SideBarButtons;

public class ResedatumController implements Initializable {
    @FXML
    private ChoiceBox<String> Avresa_ar;

    @FXML
    private ChoiceBox<String> Avresa_manad;

    @FXML
    private ChoiceBox<String> Hemresa_ar;

    @FXML
    private ChoiceBox<String> Antal_resenarer;

    @FXML
    private ChoiceBox<String> HemresaMånad;

    @FXML
    private Button btnVALJAresedatum;

    @FXML
    private Text AvgangTid;

    private CheckoutCartService checkoutCartService = new CheckoutCartService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> avresaArAlternativ = FXCollections.observableArrayList(
                "2024", "2025", "2026", "2027", "2028", "2029", "2030"
        );
        Avresa_ar.setItems(avresaArAlternativ);

        ObservableList<String> avresaManadAlternativ = FXCollections.observableArrayList(
                "Januari", "Februari", "Mars", "April", "Maj", "Juni",
                "Juli", "Augusti", "September", "Oktober", "November", "December"
        );
        Avresa_manad.setItems(avresaManadAlternativ);

        ObservableList<String> hemresaArAlternativ = FXCollections.observableArrayList(
                "0","1", "2", "3", "4", "5", "6", "7","8","9","10","11","12"
        );
        Hemresa_ar.setItems(hemresaArAlternativ);

        ObservableList<String> hemresaManadAlternativ = FXCollections.observableArrayList(
                "1", "2", "3", "4", "5", "6", "7","8","9","10","11","12"
        );
        HemresaMånad.setItems(hemresaManadAlternativ);

        ObservableList<String> AntalResenarerAlternativ = FXCollections.observableArrayList(
                "1", "2", "3", "4","5","6","7","8"
        );
        Antal_resenarer.setItems(AntalResenarerAlternativ);

        btnVALJAresedatum.setDisable(true);
        Avresa_ar.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateAvgangTid();
        });
        Avresa_manad.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateAvgangTid();
        });
        Hemresa_ar.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateAvgangTid();
        });
        HemresaMånad.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateAvgangTid();
        });

        // denna beast som visar varukorgen
        btnVarukorg.setOnAction(event -> {
            try {
                checkoutCartService.showCheckoutCart();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void updateAvgangTid() {

        String avresaAr = Avresa_ar.getValue();
        String avresaManad = Avresa_manad.getValue();
        String hemresaAr = Hemresa_ar.getValue();
        String hemresaManad = HemresaMånad.getValue();

        if (avresaAr != null && avresaManad != null && hemresaAr != null && hemresaManad != null) {
            int avresaYear = Integer.parseInt(avresaAr);
            int avresaMonth = Avresa_manad.getItems().indexOf(avresaManad) + 1;
            int hemresaYear = Integer.parseInt(hemresaAr);
            int hemresaMonth = HemresaMånad.getItems().indexOf(hemresaManad) + 1;

            int returnYear = avresaYear + hemresaYear;
            int returnMonth = avresaMonth + hemresaMonth + 6;

            if (returnMonth > 12) {
                returnMonth -= 12;
                returnYear++;
            }
            String avgangText;
            String monthName;

            switch (returnMonth) {
                case 1:
                    monthName = "Januari";
                    break;
                case 2:
                    monthName = "Februari";
                    break;
                case 3:
                    monthName = "Mars";
                    break;
                case 4:
                    monthName = "April";
                    break;
                case 5:
                    monthName = "Maj";
                    break;
                case 6:
                    monthName = "Juni";
                    break;
                case 7:
                    monthName = "Juli";
                    break;
                case 8:
                    monthName = "Augusti";
                    break;
                case 9:
                    monthName = "September";
                    break;
                case 10:
                    monthName = "Oktober";
                    break;
                case 11:
                    monthName = "November";
                    break;
                case 12:
                    monthName = "December";
                    break;
                default:
                    monthName = ""; // Default value if returnMonth is not in range 1-12
            }

            avgangText = "Avgångstid: " + returnYear + "-" + monthName + "-01";
            AvgangTid.setText(avgangText);
        }
    }

    @FXML
    private Button btnVarukorg;

    @FXML
    private Button btnNASTA;
    @FXML
    private Button btnVALJAevenemangdit;

    @FXML
    private Button btnVALJAmatpaketdit;

    @FXML
    private Button btnVALJAhyttdit;

    @FXML
    private Button btnVALJAkundinfo;

    @FXML
    private Button btnVALJAbetalkort;

    @FXML
    private Button btnVALJAhalsoforsakring;

    @FXML
    private Button btnVALJAevenemanghem;

    @FXML
    private Button btnVALJAmatpakethem;

    @FXML
    private Button btnVALJAhytthem;

    @FXML
    private Button btnVALJAhotellmars;

    @FXML
    private void handleNastaButtonClick() {
        StoreTravelChoices storeTravelChoices = new StoreTravelChoices();

        if (Avresa_ar.getValue() == null || Avresa_manad.getValue() == null || Hemresa_ar.getValue() == null || HemresaMånad.getValue() == null) {
            Alert felAlert = new Alert(Alert.AlertType.ERROR);
            felAlert.setTitle("Felaktigt datumval");
            felAlert.setHeaderText(null);
            felAlert.setContentText("Du måste välja både år och månad för avresa och hemresa för att gå vidare.");
            felAlert.showAndWait();
            return;
        }
        if (storeTravelChoices.getDate() != null) {
            storeTravelChoices.removeDate();
        }
        if(storeTravelChoices.getAntalResenarer() != null){
            storeTravelChoices.removeAntalResenarer();
        }

        // TODO: Fixa detta så att det står månadens namn istället för siffror

        String chosenDate = Avresa_ar.getValue() + "-" + Avresa_manad.getValue() + " till " + Hemresa_ar.getValue() + "-" + HemresaMånad.getValue();
        storeTravelChoices.storeDate(chosenDate);
        storeTravelChoices.storeChoiceBox("Antal resenärer: ", Antal_resenarer);

        try {
            FXMLLoader laddare = new FXMLLoader(getClass().getResource("/com/uu/grupp3/marstravel/hytter.fxml"));
            Parent rot = laddare.load();

            Scene scen = new Scene(rot);

            Stage scenVarde = (Stage) btnNASTA.getScene().getWindow();
            scenVarde.setScene(scen);
            scenVarde.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean valideraHemresaDatum() {
        String valtAvreseAr = Avresa_ar.getValue();
        String valdAvreseManad = Avresa_manad.getValue();
        String valtHemresaAr = Hemresa_ar.getValue();
        String valdHemresaManad = HemresaMånad.getValue();

        if (valtAvreseAr == null || valdAvreseManad == null || valtHemresaAr == null || valdHemresaManad == null) {
            Alert felAlert = new Alert(Alert.AlertType.ERROR);
            felAlert.setTitle("Felaktigt datumval");
            felAlert.setHeaderText(null);
            felAlert.setContentText("Du måste välja både år och månad för avresa och hemresa för att gå vidare.");
            felAlert.showAndWait();
            return false;
        }

        int avreseAr = Integer.parseInt(valtAvreseAr);
        int hemresaAr = Integer.parseInt(valtHemresaAr);
        int avreseManad = Avresa_manad.getSelectionModel().getSelectedIndex() + 1;
        int hemresaManad = HemresaMånad.getSelectionModel().getSelectedIndex() + 1;

        hemresaManad += 6;

        if (hemresaManad > 12) {
            hemresaManad -= 12;
            hemresaAr += 1;
        }

        Hemresa_ar.setValue(String.valueOf(hemresaAr));
        HemresaMånad.setValue(String.valueOf(hemresaManad));

        String avgangText = "Text: " + avreseAr + "-" + avreseManad;
        AvgangTid.setText(avgangText);

        return true;
    }

    // är bara denna kod som behöver kopieras till andra controllers
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