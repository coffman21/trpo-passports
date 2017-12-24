package ru.iu5bmstu.GuidanceUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.iu5bmstu.DomainObjectModel.Passport.Passport;
import ru.iu5bmstu.DomainObjectModel.Passport.PassportEntry;
import ru.iu5bmstu.DomainObjectModel.Popup;
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

        String type;
        String query = SearchTextField.getText();
        if (SearchType.getValue() != null) {
            type = (String) SearchType.getValue();
        }
        else {
            Popup.show("Выберите тип, по которому производится поиск.");
            return;
        }


        final ObservableList<PassportEntry> dataPassport = FXCollections.observableArrayList();
        ArrayList<Passport> passports = TableDataGateway.selectPassports(type, query);
        ArrayList<PassportEntry> passportEntries = new ArrayList<>();
        for (Passport p:passports) {
            passportEntries.add(new PassportEntry(p));
        }
        dataPassport.clear();
        dataPassport.addAll(passportEntries);

        showPassportsTable(dataPassport);

    }
    public void showPassportsTable(ObservableList<PassportEntry> data) {
        if (!this.tableContainer.getChildren().isEmpty()) {
            this.tableContainer.getChildren().removeAll();
        }

        TableView<PassportEntry> passportTable = new TableView<>();
        passportTable.getColumns().clear();

        int width = 130;

        TableColumn<PassportEntry, String> col1 = new TableColumn<>("id");
        col1.setPrefWidth(width-50);
        col1.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        passportTable.getColumns().add(col1);

        TableColumn<PassportEntry, String> col2 = new TableColumn<>("Имя");
        col2.setPrefWidth(width+100);
        col2.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        passportTable.getColumns().add(col2);

        TableColumn<PassportEntry, String> col3 = new TableColumn<>("Дата рождения");
        col3.setPrefWidth(width);
        col3.setCellValueFactory(cellData -> cellData.getValue().dobProperty());
        passportTable.getColumns().add(col3);

        TableColumn<PassportEntry, String> col4 = new TableColumn<>("Город");
        col4.setPrefWidth(width);
        col4.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
        passportTable.getColumns().add(col4);

        passportTable.setItems(data);

        this.table = passportTable ;
        AnchorPane.setBottomAnchor(table, 10.0);
        AnchorPane.setRightAnchor(table, 10.0);
        AnchorPane.setLeftAnchor(table, 10.0);
        AnchorPane.setTopAnchor(table, 10.0);
        tableContainer.getChildren().add(table);
    }


    public void SearchRequests(ActionEvent actionEvent) throws SQLException {

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

        showRequestsTable(dataRequest);
    }

    public void showRequestsTable(ObservableList<RequestEntry> data) {

        if (!this.tableContainer.getChildren().isEmpty()) {
            this.tableContainer.getChildren().removeAll();
        }


        TableView<RequestEntry> personTable = new TableView<>();
        personTable.getColumns().clear();

        int width = 130;

        TableColumn<RequestEntry, String> col1 = new TableColumn<>("id");
        col1.setPrefWidth(width-50);
        col1.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        personTable.getColumns().add(col1);

        TableColumn<RequestEntry, String> col2 = new TableColumn<>("Имя");
        col2.setPrefWidth(width+100);
        col2.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        personTable.getColumns().add(col2);

        TableColumn<RequestEntry, String> col3 = new TableColumn<>("Выдан");
        col3.setPrefWidth(width);
        col3.setCellValueFactory(cellData -> cellData.getValue().givenProperty());
        personTable.getColumns().add(col3);

        TableColumn<RequestEntry, String> col4 = new TableColumn<>("Готовность");
        col4.setPrefWidth(width);
        col4.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        personTable.getColumns().add(col4);

        personTable.setItems(data);

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
            stage.setScene(new Scene(root, 382, 187));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
