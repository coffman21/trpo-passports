package ru.iu5bmstu.VisitorUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.iu5bmstu.DomainObjectModel.Name;
import ru.iu5bmstu.DomainObjectModel.Passport;
import ru.iu5bmstu.DomainObjectModel.Popup;
import ru.iu5bmstu.TableDataGateway.TableDataGateway;

import java.sql.SQLException;
import java.util.Objects;

public class Checker {
    public Button check;
    public Button getPassport;
    public TextField name;
    public TextField surname;
    public TextField middlename;

    public void isDone(ActionEvent actionEvent) throws SQLException {
        Name username = new Name(
                surname.getText(),
                name.getText(),
                middlename.getText()
        );
        if (Objects.equals(surname.getText(), "") ||
                Objects.equals(name.getText(), "") ||
                Objects.equals(middlename.getText(), "")) {
            Popup.show("Введите данные о пользователе.");

        } else {
            Passport p = TableDataGateway.selectByName(username.toString());
            if (p == null) {
                Popup.show("Пользователь не найден.");
            } else {
                Popup.show("Ваш паспорт находится в обработке. Статус: "
                        + (p.isStatus() ? "паспорт готов." : "в обработке."));
            }
        }

    }
    public void getPassport(ActionEvent actionEvent) throws SQLException {
        Name username = new Name(
                surname.getText(),
                name.getText(),
                middlename.getText()
        );
        System.out.println(username.toString());
        if (username.toString().length() > 2) {
            Passport p = TableDataGateway.selectByName(username.toString());

            boolean isGiven = p.isGiven();
            if (isGiven) {
                Popup.show("Паспорт уже выдан.");
            }
            else {
                Popup.show("Получите паспорт у сотрудника.");
            }
            TableDataGateway.updateRow(p.getId(), "given", "1");



        } else {
            Passport p = TableDataGateway.selectByName(username.toString());

            Popup.show("Паспорт еще не готов.");
        }
    }
}
