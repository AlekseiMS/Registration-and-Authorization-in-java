package com.samoilov.itsamoilov.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import com.samoilov.itsamoilov.Application;
import com.samoilov.itsamoilov.DB;
import com.samoilov.itsamoilov.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Button btn_exit;

    @FXML
    private VBox paneVbox;
    private DB db = new DB();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        ResultSet res = db.getArtecles();

//        Node[] nodes = new Node[10];
//        for (int i = 0; i < nodes.length; i++) {
//            //Button btn = new Button("Кнопка");
//            try {
//                nodes[i] = FXMLLoader.load(getClass().getResource("/com/samoilov/itsamoilov/article.fxml"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        while(res.next()){
            Node node = null;
            try {
                node = FXMLLoader.load(getClass().getResource("/com/samoilov/itsamoilov/article.fxml"));

                Label title =(Label) node.lookup("#title");
                title.setText(res.getString("title"));

                Label intro =(Label) node.lookup("#intro");
                intro.setText(res.getString("intro"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            HBox hBox = new HBox();
            hBox.getChildren().add(node);
            hBox.setAlignment(Pos.BASELINE_CENTER);
            paneVbox.getChildren().add(hBox);
            paneVbox.setSpacing(10);
        }
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
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/com/samoilov/itsamoilov/sample.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Программа itSamoilov!");
                stage.setScene(new Scene(root, 600, 400));
                stage.show();
//                FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("sample.fxml")); //resources/com/samoilov/itsamoilov/
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                scene = new Scene(fxmlLoader.load(), 600, 400);
//                stage.setTitle("Программа itSamoilov!");
//                stage.setScene(scene);
//                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

}
