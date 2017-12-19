package ru.iu5bmstu.EmployeeUI;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Popup {
    public static void show(String s) {
        Alert alert = new Alert(Alert.AlertType.NONE, s, ButtonType.OK);
        alert.showAndWait();
    }
}
