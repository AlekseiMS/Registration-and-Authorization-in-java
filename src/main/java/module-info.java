module com.samoilov.itsamoilov {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.samoilov.itsamoilov to javafx.fxml;
    exports com.samoilov.itsamoilov;
    exports com.samoilov.itsamoilov.controller;
    opens com.samoilov.itsamoilov.controller to javafx.fxml;
}