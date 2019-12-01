package controller;

import domain.Klient;
import domain.Pojazd;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.sql.Date;

public class ClientOptionsController {

    @FXML
    private TextField addClientSurnameTextField;
    @FXML
    private TextField addClientNameTextField;
    @FXML
    private TextField addClientLicenceTextField;
    @FXML
    private TextField addClientBirthDateTextField;
    @FXML
    private TextField addClientAddressTextField;
    @FXML
    private TextField addClientPeselTextField;
    @FXML
    private TextField addclientPhoneTextField;
    @FXML
    private DatePicker addClientBirthDateDatePicker;
    @FXML
    private TextField deleteClientIdTextField;
    @FXML
    private StackPane printClientStackPane;

    public void addClient() {
        System.out.println("addClientButton");

        if (addClientSurnameTextField.getText().equals("") || addClientNameTextField.getText().equals("") || addClientLicenceTextField.getText().equals("") || addClientBirthDateTextField.getText().equals("") || addClientAddressTextField.getText().equals("") || addClientPeselTextField.getText().equals("") || addclientPhoneTextField.getText().equals("")) {
            WindowSingleton.alert("Niepoprawne dane");
            return;
        }

        try {
            Date birthDate = Date.valueOf(addClientBirthDateTextField.getText());
        } catch (Exception e) {
            System.out.println("nope");
        }
        System.out.println(Date.valueOf(addClientBirthDateTextField.getText()).getClass().getName());

        DBConnector.getInstance().start();
        DBConnector.getInstance().addKlient(new Klient(addClientLicenceTextField.getText(), addClientSurnameTextField.getText(), addClientNameTextField.getText(), Date.valueOf(addClientBirthDateDatePicker.getValue()), addClientAddressTextField.getText(), addClientPeselTextField.getText(), addclientPhoneTextField.getText()));
        DBConnector.getInstance().stop();
        WindowSingleton.alert("Dodano klienta");
    }

    public void addClientSetBirthDate() {
        addClientBirthDateTextField.setText(String.valueOf(addClientBirthDateDatePicker.getValue()));
    }



    public void deleteClientShowClientList() {
        WindowSingleton.showClientTable(deleteClientIdTextField);
    }

    public void deleteClient() {
        long _id;
        try {
            _id = Long.parseLong(deleteClientIdTextField.getText());
            DBConnector.getInstance().start();
            Klient klient = DBConnector.getInstance().getEntityManager().find(Klient.class, _id);
            if (klient == null) {
                WindowSingleton.alert("Nie ma takiego klienta");
                DBConnector.getInstance().stop();
                return;
            }

            WindowSingleton.alert("Usunięto klienta o id = " + _id);
            System.out.println("usunieto klienta o id " + _id);
            DBConnector.getInstance().deleteKlient(klient);
            DBConnector.getInstance().stop();
            deleteClientIdTextField.setText("");
        } catch (NumberFormatException e) {
            System.out.println("zły format");
            return;
        }
    }

    public void printClientList() {
        final TableView<Klient> table = WindowSingleton.createClientTable();
        printClientStackPane.getChildren().add(table);
    }


    public void showMainMenu() throws IOException {
        WindowSingleton.getInstance().setLayout("/fxml/StartScreen.fxml");
    }
}
