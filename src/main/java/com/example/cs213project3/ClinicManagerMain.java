package com.example.cs213project3;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;




public class ClinicManagerMain extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @FXML
    private ComboBox timeslotBox;
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Clinic Manager App");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("clinic-view.fxml"));
        Parent root = fxmlLoader.load();  // Correctly loading the FXML file
        Scene scene = new Scene(root);    // Use root as the parent node in your scene
        stage.setScene(scene);
        stage.show();

    }

}