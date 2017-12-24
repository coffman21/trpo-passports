package ru.iu5bmstu.GuidanceUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.iu5bmstu.DomainObjectModel.Request.Request;
import ru.iu5bmstu.DomainObjectModel.Request.RequestEntry;
import ru.iu5bmstu.DomainObjectModel.TableEntry;
import ru.iu5bmstu.TableDataGateway.TableDataGateway;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class GuidanceUIController {

    public TextField SearchTextField;
    public Button SearchPassportsButton;
    public Button SearchRequestsButton;
//    public javafx.scene.control.TableView<Object> tableView;
//    public TableColumn Col1;
//    public TableColumn Col2;
//    public TableColumn Col3;
//    public TableColumn Col4;
    public Button GetStatisticsButton;
    public CheckBox Given;
    public CheckBox Ready;
    public CheckBox NotReady;
    public CheckBox NotGiven;
    public ChoiceBox SearchType;
    public AnchorPane tableContainer;

    private TableView<? extends TableEntry> table ;

    private final ObservableList<Object> data = FXCollections.observableArrayList();

    public void SearchPassports(ActionEvent actionEvent) throws SQLException {
//        Col1.setCellValueFactory(new PropertyValueFactory<Object,String>("id"));
//        Col2.setCellValueFactory(new PropertyValueFactory<Object,String>("name"));
//        Col3.setCellValueFactory(new PropertyValueFactory<Object,String>("dob"));
//        Col4.setCellValueFactory(new PropertyValueFactory<Object,String>("city"));
//
//        Col1.setText("id");
//        Col2.setText("Имя");
//        Col3.setText("Дата рождения");
//        Col4.setText("Город");
//
//
//
//        String type = null;
//        String query = SearchTextField.getText();
//        if (SearchType.getValue() != null) {
//            type = (String) SearchType.getValue();
//        }
//        else {
//            Popup.show("Выберите тип, по которому производится поиск.");
//            return;
//        }
//
//        ArrayList<Passport> passports = TableDataGateway.selectPassports(type, query);
//        data.clear();
//        data.addAll(passports);
//        tableView.setItems(data);
    }

    public void SearchRequests(ActionEvent actionEvent) throws SQLException {
//        tableView<Request> requestsTable = new tableView<>();
//        requestsTable.getColumns().clear();
//
//
//        TableColumn<Request, String> col1 = new TableColumn<>("id");
//        col1.setCellValueFactory(new PropertyValueFactory<>("id"));
//        requestsTable.getColumns().add(col1);
//
//        TableColumn<Request, String> col2 = new TableColumn<>("name");
//        col2.setCellValueFactory(new PropertyValueFactory<>("name"));
//        requestsTable.getColumns().add(col2);
//
//        TableColumn<Request, String> col3 = new TableColumn<>("status");
//        col3.setCellValueFactory(new PropertyValueFactory<>("status"));
//        requestsTable.getColumns().add(col3);
//
//        TableColumn<Request, String> col4 = new TableColumn<>("given");
//        col4.setCellValueFactory(new PropertyValueFactory<>("given"));
//        requestsTable.getColumns().add(col4);
//
//        tableView = requestsTable;



//
//        Col1.setCellValueFactory(new PropertyValueFactory<Object,String>("id"));
//        Col2.setCellValueFactory(new PropertyValueFactory<Object,String>("name"));
//        Col3.setCellValueFactory(new PropertyValueFactory<Object,String>("status"));
//        Col4.setCellValueFactory(new PropertyValueFactory<Object,String>("given"));
//
//        Col1.setText("id");
//        Col2.setText("Имя");
//        Col3.setText("Изготовлен");
//        Col4.setText("Выдан");



        //tableView.setItems(data);
        showRequestsTable();
    }

    public void showRequestsTable() throws SQLException {
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


        final ObservableList<RequestEntry> dataRequest = FXCollections.observableArrayList();
        ArrayList<Request> requests = TableDataGateway.selectRequests(mode);
        ArrayList<RequestEntry> requestEntries = new ArrayList<>();
        for (Request req:requests) {
            requestEntries.add(new RequestEntry(req));
        }

        dataRequest.clear();
        dataRequest.addAll(requestEntries);

        TableView<RequestEntry> personTable = new TableView<>();
        personTable.getColumns().clear();

        int width = 130;

        TableColumn<RequestEntry, String> col1 = new TableColumn<>("id");
        col1.setPrefWidth(width-50);
        col1.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        personTable.getColumns().add(col1);

        TableColumn<RequestEntry, String> col2 = new TableColumn<>("name");
        col2.setPrefWidth(width+100);
        col2.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        personTable.getColumns().add(col2);

        TableColumn<RequestEntry, String> col3 = new TableColumn<>("given");
        col3.setPrefWidth(width);
        col3.setCellValueFactory(cellData -> cellData.getValue().givenProperty());
        personTable.getColumns().add(col3);

        TableColumn<RequestEntry, String> col4 = new TableColumn<>("status");
        col4.setPrefWidth(width);
        col4.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        personTable.getColumns().add(col4);

        personTable.setItems(dataRequest);

        this.table = personTable ;
        AnchorPane.setBottomAnchor(table, 10.0);
        AnchorPane.setRightAnchor(table, 10.0);
        AnchorPane.setLeftAnchor(table, 10.0);
        AnchorPane.setTopAnchor(table, 10.0);
        tableContainer.getChildren().add(table);
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
