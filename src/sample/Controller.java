package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Controller {

    @FXML
    public Label msg;
    public Button ok;

    @FXML
    private void initialize() {
//        msg.setText(s);
    }

    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    }
}
