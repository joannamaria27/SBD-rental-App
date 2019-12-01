package controller;

import domain.Pracownik;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.sql.Date;

public class EmployeeOptionsController {

    @FXML
    private TextField addEmployeeSurnameTextField;
    @FXML
    private TextField addEmployeeNameTextField;
    @FXML
    private TextField addEmployeeStanowiskoTextField;
    @FXML
    private TextField addEmployeeBirthDateTextField;
    @FXML
    private TextField addEmployeeAddressTextField;
    @FXML
    private TextField addEmployeePeselTextField;
    @FXML
    private TextField addEmployeePhoneTextField;
    @FXML
    private DatePicker addEmployeeBirthDateDatePicker;

    @FXML
    private TextField deleteEmployeeIdTextField;

    @FXML
    private StackPane printClientStackPane;

    public void addEmployee() {
        System.out.println("addEmployeeButton");

        if (addEmployeeSurnameTextField.getText().equals("") || addEmployeeNameTextField.getText().equals("") || addEmployeeStanowiskoTextField.getText().equals("") || addEmployeeBirthDateTextField.getText().equals("") || addEmployeeAddressTextField.getText().equals("") || addEmployeePeselTextField.getText().equals("") || addEmployeePhoneTextField.getText().equals("")) {
            WindowSingleton.alert("Niepoprawne dane");
            return;
        }

        try {
            Date birthDate = Date.valueOf(addEmployeeBirthDateTextField.getText());
        } catch (Exception e) {
            System.out.println("nope");
        }
        System.out.println(Date.valueOf(addEmployeeBirthDateTextField.getText()).getClass().getName());

        DBConnector.getInstance().start();
        DBConnector.getInstance().addPracownik(new Pracownik( addEmployeeSurnameTextField.getText(), addEmployeeNameTextField.getText(), Date.valueOf(addEmployeeBirthDateDatePicker.getValue()), addEmployeeAddressTextField.getText(), addEmployeePeselTextField.getText(), addEmployeePhoneTextField.getText(), addEmployeeStanowiskoTextField.getText() ));
        DBConnector.getInstance().stop();
        WindowSingleton.alert("Dodano pracownika");
    }

    public void addEmployeeSetBirthDate() {
        addEmployeeBirthDateTextField.setText(String.valueOf(addEmployeeBirthDateDatePicker.getValue()));
    }

    public void showMainMenu() throws IOException {
        WindowSingleton.getInstance().setLayout("/fxml/StartScreen.fxml");
    }

    public void deleteEmployeeShowClientList() {
        WindowSingleton.showClientTable(deleteEmployeeIdTextField);
    }

    public void deleteEmployee() {
        long _id;
        try {
            _id = Long.parseLong(deleteEmployeeIdTextField.getText());
            DBConnector.getInstance().start();
            Pracownik pracownik = DBConnector.getInstance().getEntityManager().find(Pracownik.class, _id);
            if (pracownik == null) {
                WindowSingleton.alert("Nie ma takiego pracownika");
                DBConnector.getInstance().stop();
                return;
            }

            WindowSingleton.alert("Usunięto pracownika o id = " + _id);
            System.out.println("usunieto pracownika o id " + _id);
            DBConnector.getInstance().deletePracownik(pracownik);
            DBConnector.getInstance().stop();
            deleteEmployeeIdTextField.setText("");
        } catch (NumberFormatException e) {
            System.out.println("zły format");
            return;
        }
    }

   
}
