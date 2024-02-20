module com.uu.grupp3.marstravel {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.uu.grupp3.marstravel to javafx.fxml;
    exports com.uu.grupp3.marstravel;
}