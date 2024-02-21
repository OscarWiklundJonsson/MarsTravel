module com.uu.grupp3.marstravel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.uu.grupp3.marstravel to javafx.fxml;
    exports com.uu.grupp3.marstravel;
    exports com.uu.grupp3.marstravel.controllers;
    opens com.uu.grupp3.marstravel.controllers to javafx.fxml;
}