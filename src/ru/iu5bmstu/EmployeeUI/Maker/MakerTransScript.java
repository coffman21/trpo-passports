package ru.iu5bmstu.EmployeeUI.Maker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.iu5bmstu.DomainObjectModel.Passport.Passport;
import ru.iu5bmstu.DomainObjectModel.Popup;
import ru.iu5bmstu.DomainObjectModel.Request.Request;
import ru.iu5bmstu.TableDataGateway.TableDataGateway;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class MakerTransScript {

    private Request r;
    @FXML
    public TextField name;
    @FXML
    public TextField dob;
    @FXML
    public ChoiceBox gender;
    @FXML
    public TextField city;
    @FXML
    public TableView<Passport> tableView;
    @FXML
    public TableColumn<Passport,String> idCol;
    @FXML
    public TableColumn<Passport,String> nameCol;
    @FXML
    public TableColumn<Passport, String> dobCol;
    @FXML
    public TableColumn<Passport, String> genderCol;
    @FXML
    public TableColumn<Passport, String> cityCol;

    private final ObservableList<Passport> data = FXCollections.observableArrayList();

    private int transId = 1;

    public void transactionScript(ActionEvent actionEvent) throws SQLException {
        // 1. Заполнение пользователем полей
        Passport p = addPassport();
        // 2. Сохранение данных в БД
        saveData(p);
        // 3. Вывод формы "Просмотр заявок"
        showData();
        // 4. Закрытие формы приводит к завершению обработки транзакции.

        transId += 1;
    }


    private Passport addPassport() throws SQLException {
        Passport p = new Passport(
                transId,
                name.getText(),
                dob.getText(),
                (String) gender.getValue(),
                city.getText(),
                false);

        if (Arrays.stream(p.getValues()).noneMatch(x -> x == null || x.equals(""))) {

            idCol.    setCellValueFactory(new PropertyValueFactory<Passport,String>("id"));
            nameCol.  setCellValueFactory(new PropertyValueFactory<Passport,String>("name"));
            dobCol.   setCellValueFactory(new PropertyValueFactory<Passport,String>("dob"));
            genderCol.setCellValueFactory(new PropertyValueFactory<Passport,String>("gender"));
            cityCol.  setCellValueFactory(new PropertyValueFactory<Passport,String>("city"));

        }
        else { exceptNotFilled(); }
        return p;
    }


    private void saveData(Passport p) throws SQLException {
        ArrayList<Passport> passports = TableDataGateway.selectPassports();
        if (passports.stream().anyMatch(x -> x.getName().equals(p.getName()))) {
            exceptPassportExist(p.getName());
        }
        else {
            TableDataGateway.alter(p.getName(), p.getDob(), p.getGender(), p.getCity());
            Popup.show("Паспорт добавлен.");
        }
    }


    private void showData() throws SQLException {
        ArrayList<Passport> passports = TableDataGateway.selectPassports();
        data.clear();
        data.addAll(passports);
        tableView.setItems(data);
    }



    // E1. Не заполнены поля
    private void exceptNotFilled() {
        Popup.show("Не все поля заполнены.");
    }


    // Е2. Заявка уже присутствует в БД
    private void exceptPassportExist(String name) {
        Popup.show("Паспорт для гражданина " + name + " уже существует.");
    }

}
