package ru.iu5bmstu.GuidanceUI.Statistic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ru.iu5bmstu.DomainObjectModel.Request.Request;
import ru.iu5bmstu.TableDataGateway.TableDataGateway;

import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;

public class StatisticUI {
    public ChoiceBox departmentSelector;
    public Label total;
    public Label totalGiven;
    public Label totalMade;
    public Button okButton;


    public StatisticUI() {
    }


    @FXML
    public void initialize() throws SQLException {
        departmentSelector.getSelectionModel().selectFirst();

        // so dirty
        total.setText(String.valueOf(
                getTotal(
                        new ArrayList() {{
                            add(new AbstractMap.SimpleEntry<>("given", "1"));
                            add(new AbstractMap.SimpleEntry<>("given", "0"));
                        }})
        ));

        totalGiven.setText(String.valueOf(
                getTotal(
                        new ArrayList() {{
                            add(new AbstractMap.SimpleEntry<>("given", "1"));
                        }})
        ));
        totalMade.setText(String.valueOf(
                getTotal(
                        new ArrayList() {{
                            add(new AbstractMap.SimpleEntry<>("status", "1"));
                        }})
        ));

    }

    private int getTotal(ArrayList mode) throws SQLException {
        ArrayList<Request> requests = TableDataGateway.selectRequests(mode);
        return requests.size();
    }

    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
}
