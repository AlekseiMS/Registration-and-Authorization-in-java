package com.samoilov.itsamoilov.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import com.samoilov.itsamoilov.Application;
import com.samoilov.itsamoilov.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Button btn_exit;

    @FXML
    void initialize() {
        btn_exit.setOnAction(event -> {
            Scene scene = null;
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream("user.settings");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(new User(""));

                oos.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("sample.fxml")); //resources/com/samoilov/itsamoilov/
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(fxmlLoader.load(), 600, 400);
                stage.setTitle("Программа itSamoilov!");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

}
