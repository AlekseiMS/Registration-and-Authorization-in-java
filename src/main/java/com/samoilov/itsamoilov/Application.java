package com.samoilov.itsamoilov;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        String scen = "sample.fxml";
        File file = new File("user.settings");
        boolean exists = file.exists();
        if (exists) {
            FileInputStream fis = new FileInputStream("user.settings");
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                User user = (User) ois.readObject();
                if (!user.getLogin().equals("")){
                    scen = "main.fxml";
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            ois.close();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(scen));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Программа itSamoilov!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}