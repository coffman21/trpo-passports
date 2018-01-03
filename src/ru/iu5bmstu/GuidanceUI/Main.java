package ru.iu5bmstu.GuidanceUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.iu5bmstu.GuidanceUI.Statistic.StatisticUI;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Guidance");
        primaryStage.setScene(new Scene(root, 622, 400));
        primaryStage.show();
    }
    private GuidanceUIController g;
    public static void main(String[] args) {
        launch(args);
    }

}
