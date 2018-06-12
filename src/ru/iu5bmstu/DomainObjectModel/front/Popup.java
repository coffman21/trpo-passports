package ru.iu5bmstu.DomainObjectModel.front;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

public class Popup {
    public static void show(String s) {
        Alert alert = new Alert(Alert.AlertType.NONE, s, ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}
