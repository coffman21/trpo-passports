package sample.Maker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Passport;
import sample.Popup;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class MakerTransScript {
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

    public void addPassport(ActionEvent actionEvent) throws SQLException {
        Passport p = new Passport(
                transId,
                name.getText(),
                dob.getText(),
                (String) gender.getValue(),
                city.getText());

        if (Arrays.stream(p.getValues()).noneMatch(x -> x == null || x.equals(""))) {


            idCol.setCellValueFactory(new PropertyValueFactory<Passport,String>("id"));
            nameCol.setCellValueFactory(new PropertyValueFactory<Passport,String>("name"));
            dobCol.setCellValueFactory(new PropertyValueFactory<Passport,String>("dob"));
            genderCol.setCellValueFactory(new PropertyValueFactory<Passport,String>("gender"));
            cityCol.setCellValueFactory(new PropertyValueFactory<Passport,String>("city"));

            //get some data from database
            MakerTableDataGateway makerTableDataGateway = new MakerTableDataGateway();

            makerTableDataGateway.update(p.getName(), p.getDob(), p.getGender(), p.getCity());
            ArrayList passports = makerTableDataGateway.select("g");

            data.addAll(passports);
            System.out.println(data);
            tableView.setItems(data);

            Popup.show("Паспорт добавлен.");
            transId += 1;
        }
        else {
            exceptNotFilled();
        }
    }




    public void exceptNotFilled() {
        Popup.show("Не все поля заполнены.");
    }




    private void exceptPassportExist(String s) {
        Popup.show("Паспорт для гражданина " + s + " уже существует.");
    }
}
