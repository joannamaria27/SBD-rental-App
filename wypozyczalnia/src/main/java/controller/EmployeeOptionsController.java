package controller;

import domain.Pracownik;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

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
    private StackPane printEmployeeStackPane;


    @FXML
    private TextField editEmployeeIdTextField;
    @FXML
    private TextField editEmployeeAdresTextField;
    @FXML
    private TextField editEmployeeImieTextField;
    @FXML
    private TextField editEmployeeNazwiskoTextField;
    @FXML
    private TextField editEmployeePeselTextField;
    @FXML
    private TextField editEmployeeDataUrodzeniaTextField;
    @FXML
    private TextField editEmployeeTelefonTextField;
    @FXML
    private TextField editEmployeeStanowiskoTextField;

    @FXML
    private TextField editEmployeeNewDataUrodzeniaTextField;
    @FXML
    private TextField editEmployeeNewAdresTextField;
    @FXML
    private TextField editEmployeeNewImieTextField;
    @FXML
    private TextField editEmployeeNewNazwiskoTextField;
    @FXML
    private TextField editEmployeeNewPeselTextField;
    @FXML
    private TextField editEmployeeNewTelefonTextField;
    @FXML
    private TextField editEmployeeNewStanowiskoTextField;
    @FXML
    private DatePicker editEmployeeDatePicker;


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
        DBConnector.getInstance().addPracownik(new Pracownik(addEmployeeSurnameTextField.getText(), addEmployeeNameTextField.getText(), Date.valueOf(addEmployeeBirthDateDatePicker.getValue()), addEmployeeAddressTextField.getText(), addEmployeePeselTextField.getText(), addEmployeePhoneTextField.getText(), addEmployeeStanowiskoTextField.getText()));
        DBConnector.getInstance().stop();
        WindowSingleton.alert("Dodano pracownika");
        addEmployeeStanowiskoTextField.setText("");
        addEmployeeSurnameTextField.setText("");
        addEmployeeNameTextField.setText("");

        addEmployeeAddressTextField.setText("");
        addEmployeePeselTextField.setText("");
        addEmployeePhoneTextField.setText("");
        addEmployeeBirthDateTextField.setText("");
    }

    public void addEmployeeSetBirthDate() {
        addEmployeeBirthDateTextField.setText(String.valueOf(addEmployeeBirthDateDatePicker.getValue()));
    }

    public void fillEditedEmployeeFields() {
        if (editEmployeeIdTextField.getText().equals("")) {
            WindowSingleton.alert("Nie ma pracownika o podanym ID");
        }
        try {
            long id = Long.parseLong(editEmployeeIdTextField.getText());
        } catch (NumberFormatException e) {
            WindowSingleton.alert("Niepoprawny format ID");
            return;
        }
        Pracownik pracownik = DBConnector.getInstance().getEntityManager().find(Pracownik.class, Long.parseLong(editEmployeeIdTextField.getText()));

        // todo usunac New
        editEmployeeAdresTextField.setText(pracownik.getAdres());
        editEmployeeDataUrodzeniaTextField.setText(String.valueOf(pracownik.getData_urodzenia()));
        editEmployeeImieTextField.setText(pracownik.getImie());
        editEmployeeNazwiskoTextField.setText(pracownik.getNazwisko());
        editEmployeePeselTextField.setText(pracownik.getPesel());
        editEmployeeTelefonTextField.setText(pracownik.getTelefon());
        editEmployeeStanowiskoTextField.setText(pracownik.getStanowisko());

    }

    public void editEmployee() {
        if (editEmployeeNewAdresTextField.getText().equals("") ||
                editEmployeeNewDataUrodzeniaTextField.getText().equals("") ||
                editEmployeeNewImieTextField.getText().equals("") ||
                editEmployeeNewNazwiskoTextField.getText().equals("") ||
                editEmployeeNewPeselTextField.getText().equals("") ||
                editEmployeeNewTelefonTextField.getText().equals("") ||
                editEmployeeNewStanowiskoTextField.getText().equals("")) {
            WindowSingleton.alert("Niepoprawne dane");
            return;
        }

        if (editEmployeeTelefonTextField.getText().length() > 9) {
            WindowSingleton.alert("Numer telefonu ma maksymalnie 9 znaków");
            return;
        }

        try {
            Date.valueOf(editEmployeeNewDataUrodzeniaTextField.getText());
        } catch (Exception e) {
            WindowSingleton.alert("Niepoprawna data");
            return;
        }

        Pracownik pracownik = DBConnector.getInstance().getEntityManager().find(Pracownik.class, Long.parseLong(editEmployeeIdTextField.getText()));
        pracownik.setStanowisko(editEmployeeNewStanowiskoTextField.getText());
        pracownik.setNazwisko(editEmployeeNewNazwiskoTextField.getText());
        pracownik.setImie(editEmployeeNewImieTextField.getText());
        pracownik.setData_urodzenia(Date.valueOf(editEmployeeNewDataUrodzeniaTextField.getText()));
        pracownik.setAdres(editEmployeeNewAdresTextField.getText());
        pracownik.setPesel(editEmployeeNewPeselTextField.getText());
        pracownik.setTelefon(editEmployeeNewTelefonTextField.getText());

        DBConnector.getInstance().editPracownik(pracownik);
        WindowSingleton.alert("Zedytowano pracownika");
    }

    public void showEmployeeeditEmployeeList() {
        WindowSingleton.showEmployeeTable(editEmployeeIdTextField);
    }

    public void editEmployeeSetBirthDate() {
        editEmployeeNewDataUrodzeniaTextField.setText(String.valueOf(editEmployeeDatePicker.getValue()));
    }


    public void showMainMenu() throws IOException {
        WindowSingleton.getInstance().setLayout("/fxml/StartScreen.fxml");
    }


    public void deleteEmployeeShowEmployeeList() {
        WindowSingleton.showEmployeeTable(deleteEmployeeIdTextField);
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

    public void printEmployeeList() {
        final TableView<Pracownik> table = WindowSingleton.createEmployeeTable();
        printEmployeeStackPane.getChildren().add(table);
    }
}
