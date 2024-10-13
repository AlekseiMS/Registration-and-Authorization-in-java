package com.samoilov.itsamoilov.controller;

import com.samoilov.itsamoilov.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AddArticleController {

    @FXML
    private Button btn_add;

    @FXML
    private TextArea intro_field;

    @FXML
    private TextArea text_field;

    @FXML
    private TextField title_field;

    private DB db = new DB();

    @FXML
    void initialize() {
        btn_add.setOnAction(event -> {
            title_field.setStyle("-fx-border-color: #fafafa");
            intro_field.setStyle("-fx-border-color: #fafafa");
            text_field.setStyle("-fx-border-color: #fafafa");


            if (title_field.getCharacters().length() <= 3) {
                title_field.setStyle("-fx-border-color: red");
                return;
            } else if (intro_field.getText().length() <= 5) {
                intro_field.setStyle("-fx-border-color: red");
                return;
            } else if (text_field.getText().length() <= 3) {
                text_field.setStyle("-fx-border-color: red");
                return;
            }

            try {
                db.addArticle(title_field.getCharacters().toString(), intro_field.getText(),
                        text_field.getText());

                Parent root = FXMLLoader.load(getClass().getResource
                        ("/com/samoilov/itsamoilov/main.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Программа itSamoilov!");
                stage.setScene(new Scene(root, 600, 400));
                stage.show();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
