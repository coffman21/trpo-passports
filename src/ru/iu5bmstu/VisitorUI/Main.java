package ru.iu5bmstu.VisitorUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("TRPO DZ");
        primaryStage.setScene(new Scene(root, 300, 270));
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    public void go(ActionEvent actionEvent) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("checker.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Checker");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}