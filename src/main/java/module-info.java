module com.example.cs213project3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.example.cs213project3 to javafx.fxml;
    exports com.example.cs213project3;
}