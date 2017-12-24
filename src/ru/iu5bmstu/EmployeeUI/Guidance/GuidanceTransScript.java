package ru.iu5bmstu.EmployeeUI.Guidance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.iu5bmstu.DomainObjectModel.Passport;
import ru.iu5bmstu.DomainObjectModel.Popup;
import ru.iu5bmstu.TableDataGateway.TableDataGateway;

import java.sql.SQLException;
import java.util.ArrayList;

public class GuidanceTransScript {

    public TableView<Passport> tableView;
    public TableColumn idCol;
    public TableColumn nameCol;
    public TableColumn dobCol;
    public TableColumn genderCol;
    public TableColumn cityCol;
    public TableColumn statusCol;
    public Button delete;
    public Button search;
    public Button changeStatus;
    public ChoiceBox statusChecker;


    private final ObservableList<Passport> data = FXCollections.observableArrayList();
    private int transId = 1;


    // 1. 2. Выбор статуса и отображение списка паспортов
    public void search(ActionEvent actionEvent) throws SQLException {

        idCol.    setCellValueFactory(new PropertyValueFactory<Passport,String>("id"));
        nameCol.  setCellValueFactory(new PropertyValueFactory<Passport,String>("name"));
        dobCol.   setCellValueFactory(new PropertyValueFactory<Passport,String>("dob"));
        genderCol.setCellValueFactory(new PropertyValueFactory<Passport,String>("gender"));
        cityCol.  setCellValueFactory(new PropertyValueFactory<Passport,String>("city"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Passport,String>("status"));

        boolean currentStatus = statusChecker.getValue().equals("done");
        ArrayList<Passport> passports = TableDataGateway.selectByStatus(currentStatus);

        data.clear();
        data.addAll(passports);
        tableView.setItems(data);
        tableView.refresh();


    }
    // 3. Действия над паспортом
    public void changeStatus(ActionEvent actionEvent) throws SQLException {
        if (tableView.getSelectionModel().getSelectedCells() != null) {
            ObservableList<Passport> selectedCells = tableView.getSelectionModel().getSelectedItems();

            // suppose we can select only one element
            Passport p = selectedCells.get(0);
            data.remove(p);
            p.invertStatus();
            data.add(p);
            TableDataGateway.updateRow(p.getId(), "status", (p.isStatus() ? "1" : "0"));

            tableView.setItems(data);
            tableView.refresh();
        } else {
            Popup.show("Не выбраны паспорта.");
        }
    }
    // 3. Действия над паспортом
    public void deleteRow(ActionEvent actionEvent) throws SQLException {
        if (tableView.getSelectionModel().getSelectedCells() != null) {
            ObservableList<Passport> selectedCells = tableView.getSelectionModel().getSelectedItems();

            // suppose we can select only one element
            data.remove(selectedCells.get(0));
            TableDataGateway.deleteRow(selectedCells.get(0).getId());

            tableView.setItems(data);
            tableView.refresh();
        } else {
            Popup.show("Не выбраны паспорта.");
        }
    }
}
