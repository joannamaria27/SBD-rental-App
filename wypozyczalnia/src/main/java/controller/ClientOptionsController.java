package controller;

import domain.Klient;
import domain.Pojazd;
import domain.Punkt_Wypozyczen;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

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
    private TextField editClientIdTextField;
    @FXML
    private TextField editClientNazwiskoTextField;
    @FXML
    private TextField editClientImieTextField;
    @FXML
    private TextField editClientNumerPrawaJazdyTextField;
    @FXML
    private TextField editClientDataUrodzeniaTextField;
    @FXML
    private TextField editClientAdresTextField;
    @FXML
    private TextField editClientPeselTextField;
    @FXML
    private TextField editClientTelefonTextField;
    @FXML
    private TextField editClientNewNazwiskoTextField;
    @FXML
    private TextField editClientNewImieTextField;
    @FXML
    private TextField editClientNewNumerPrawaJazdyTextField;
    @FXML
    private TextField editClientNewDataUrodzeniaTextField;
    @FXML
    private TextField editClientNewAdresTextField;
    @FXML
    private TextField editClientNewPeselTextField;
    @FXML
    private TextField editClientNewTelefonTextField;
    @FXML
    private DatePicker editClientDatePicker;
    @FXML
    private StackPane printClientStackPane;

    public void addClient() {
        System.out.println("addClientButton");


        if (addClientSurnameTextField.getText().equals("") || addClientNameTextField.getText().equals("") || addClientLicenceTextField.getText().equals("") || addClientBirthDateTextField.getText().equals("") || addClientAddressTextField.getText().equals("") || addClientPeselTextField.getText().equals("") || addclientPhoneTextField.getText().equals("")) {
            WindowSingleton.alert("Niepoprawne dane");
            return;
        }
        List<Klient> list = DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Klient a WHERE nr_prawa_jazdy='" + addClientLicenceTextField.getText() + "'", Klient.class).getResultList();
        if (list.size() > 0) {
            WindowSingleton.alert("Prawo jazdy o podanym numerze istnieje już w bazie");
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
        addClientLicenceTextField.setText("");
        addClientSurnameTextField.setText("");
        addClientNameTextField.setText("");
        //addClientBirthDateDatePicker.setValue(null);
        addClientAddressTextField.setText("");
        addClientPeselTextField.setText("");
        addclientPhoneTextField.setText("");
        addClientBirthDateTextField.setText("");
    }

    public void addClientSetBirthDate() {
        addClientBirthDateTextField.setText(String.valueOf(addClientBirthDateDatePicker.getValue()));
    }


    public void deleteClientShowClientList() {
        WindowSingleton.showClientTable(deleteClientIdTextField);
    }

    public void deleteClient() {
        if (deleteClientIdTextField.getText().equals("")) {
            WindowSingleton.alert("Nie ma klienta o podanym ID");
        }

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

    public void fillEditedClientFields() {
        if (editClientIdTextField.getText().equals("")) {
            WindowSingleton.alert("Nie ma klienta o podanym ID");
        }
        try {
            long id = Long.parseLong(editClientIdTextField.getText());
        } catch (NumberFormatException e) {
            WindowSingleton.alert("Niepoprawny format ID");
            return;
        }
        Klient klient = DBConnector.getInstance().getEntityManager().find(Klient.class, Long.parseLong(editClientIdTextField.getText()));

        // todo usunac New
        editClientAdresTextField.setText(klient.getAdres());
        editClientDataUrodzeniaTextField.setText(String.valueOf(klient.getData_urodzenia()));
        editClientImieTextField.setText(klient.getImie());
        editClientNazwiskoTextField.setText(klient.getNazwisko());
        editClientPeselTextField.setText(klient.getPesel());
        editClientTelefonTextField.setText(klient.getTelefon());
        editClientNumerPrawaJazdyTextField.setText(klient.getNr_prawa_jazdy());


    }

    public void editClient() {
        if(editClientNewAdresTextField.getText().equals("") ||
                editClientNewDataUrodzeniaTextField.getText().equals("") ||
                editClientNewImieTextField.getText().equals("") ||
                editClientNewNazwiskoTextField.getText().equals("") ||
                editClientNewPeselTextField.getText().equals("") ||
                editClientNewTelefonTextField.getText().equals("") ||
                editClientNewNumerPrawaJazdyTextField.getText().equals("")){
            WindowSingleton.alert("Niepoprawne dane");
            return;
        }

        List<Klient> list = DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Klient a WHERE nr_prawa_jazdy='" + addClientLicenceTextField.getText() + "'", Klient.class).getResultList();
        if (list.size() > 0) {
            WindowSingleton.alert("Prawo jazdy o podanym numerze istnieje już w bazie");
            return;
        }

        if(editClientTelefonTextField.getText().length()>9){
            WindowSingleton.alert("Numer telefonu ma maksymalnie 9 znaków");
            return;
        }

        try {
            Date.valueOf(editClientNewDataUrodzeniaTextField.getText());
        } catch (Exception e) {
            WindowSingleton.alert("Niepoprawna data");
            return;
        }

        Klient klient = DBConnector.getInstance().getEntityManager().find(Klient.class, Long.parseLong(editClientIdTextField.getText()));
        klient.setNr_prawa_jazdy(editClientNewNumerPrawaJazdyTextField.getText());
        klient.setNazwisko(editClientNewNazwiskoTextField.getText());
        klient.setImie(editClientNewImieTextField.getText());
        klient.setData_urodzenia(Date.valueOf(editClientNewDataUrodzeniaTextField.getText()));
        klient.setAdres(editClientNewAdresTextField.getText());
        klient.setPesel(editClientNewPeselTextField.getText());
        klient.setTelefon(editClientNewTelefonTextField.getText());

        DBConnector.getInstance().editKlient(klient);
        WindowSingleton.alert("Zedytowano klienta");
    }

    public void showEditClientList() {
        WindowSingleton.showClientTable(editClientIdTextField);
    }

    public void editClientSetBirthDate(){
        editClientNewDataUrodzeniaTextField.setText(String.valueOf(editClientDatePicker.getValue()));
    }
}