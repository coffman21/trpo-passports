package ru.iu5bmstu.GuidanceUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ru.iu5bmstu.DomainObjectModel.Passport;
import ru.iu5bmstu.DomainObjectModel.Popup;
import ru.iu5bmstu.DomainObjectModel.Request;
import ru.iu5bmstu.TableDataGateway.TableDataGateway;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class GuidanceUIController {

    public TextField SearchTextField;
    public Button SearchPassportsButton;
    public Button SearchRequestsButton;
    public TableView TableView;
    public TableColumn Col1;
    public TableColumn Col2;
    public TableColumn Col3;
    public TableColumn Col4;
    public Button GetStatisticsButton;
    public CheckBox Given;
    public CheckBox Ready;
    public CheckBox NotReady;
    public CheckBox NotGiven;
    public ChoiceBox SearchType;

    private final ObservableList<Object> data = FXCollections.observableArrayList();

    public void SearchPassports(ActionEvent actionEvent) throws SQLException {
        Col1.setCellValueFactory(new PropertyValueFactory<Passport,String>("id"));
        Col2.setCellValueFactory(new PropertyValueFactory<Passport,String>("name"));
        Col3.setCellValueFactory(new PropertyValueFactory<Passport,String>("dob"));
        Col4.setCellValueFactory(new PropertyValueFactory<Passport,String>("city"));

        Col1.setText("id");
        Col2.setText("Имя");
        Col3.setText("Дата рождения");
        Col4.setText("Город");

        String type = null;
        String query = SearchTextField.getText();
        if (SearchType.getValue() != null) {
            type = (String) SearchType.getValue();
        }
        else {
            Popup.show("Выберите тип, по которому производится поиск.");
            return;
        }

        ArrayList<Passport> passports = TableDataGateway.selectPassports(type, query);
        data.clear();
        data.addAll(passports);
        TableView.setItems(data);
    }

    public void SearchRequests(ActionEvent actionEvent) throws SQLException {
        Col1.setCellValueFactory(new PropertyValueFactory<Request,String>("id"));
        Col2.setCellValueFactory(new PropertyValueFactory<Request,String>("name"));
        Col3.setCellValueFactory(new PropertyValueFactory<Request,String>("status"));
        Col4.setCellValueFactory(new PropertyValueFactory<Request,String>("given"));

        Col1.setText("id");
        Col2.setText("Имя");
        Col3.setText("Изготовлен");
        Col4.setText("Выдан");


        ArrayList mode = new ArrayList<Map.Entry <String, String>> () {{
            if (Given.isSelected()) {
                add(new AbstractMap.SimpleEntry<>("given", "1"));
            }
            if (NotGiven.isSelected()) {
                add(new AbstractMap.SimpleEntry<>("given", "0"));
            }
            if (NotReady.isSelected()) {
                add(new AbstractMap.SimpleEntry<>("status", "0"));
            }
            if (Ready.isSelected()) {
                add(new AbstractMap.SimpleEntry<>("status", "1"));
            }
        }};

        ArrayList<Request> requests = TableDataGateway.selectRequests(mode);
        data.clear();
        data.addAll(requests);
        TableView.setItems(data);
    }

    public void GetStatistics(ActionEvent actionEvent) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistic/statistic.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Statistics");
            stage.setScene(new Scene(root, 10, 10));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
