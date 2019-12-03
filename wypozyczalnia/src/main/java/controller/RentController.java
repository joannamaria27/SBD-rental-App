package controller;

import domain.*;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class RentController {

    @FXML
    private TextField addRentIdPojazduTextField;
    //    @FXML
//    private DatePicker addRentDataWTextField;
    @FXML
    private TextField addRentKodTextField;
    @FXML
    private TextField addRentStanTextField;
    @FXML
    private TextField addRentIdKlientaTextField;
    @FXML
    private TextField addRentKaucjaTextField;
//    @FXML
//    private TextField addRentIdPracownikaTextField;


    @FXML
    private TextField deleteRentIdTextField;

    @FXML
    private StackPane printRentStackPane;

    @FXML
    private TextField editRentIdTextField;


    @FXML
    private TextField editRentNewIdTextField;


    @FXML
    private TextField editRentIdPojazduTextField;
    @FXML
    private TextField editRentDataWTextField;
    @FXML
    private TextField editRentKodTextField;
    @FXML
    private TextField editRentStanTextField;
    @FXML
    private TextField editRentIdKlientaTextField;
    @FXML
    private TextField editRentKaucjaTextField;
    @FXML
    private TextField editRentIdPracownikaTextField;

    @FXML
    private TextField editRentNewIdPojazduTextField;
    @FXML
    private DatePicker editRentNewDataWTextField;
    @FXML
    private TextField editRentNewKodTextField;
    @FXML
    private TextField editRentNewStanTextField;
    @FXML
    private TextField editRentNewIdKlientaTextField;
    @FXML
    private TextField editRentNewKaucjaTextField;
    @FXML
    private TextField editRentNewIdPracownikaTextField;
    @FXML
    private TextField addRezerwacjaIdTextField;
    @FXML
    private TextField addRentPracownikIdTextField;


    public void addRent() {
        System.out.println("addRentButton");

        if (addRezerwacjaIdTextField.getText().equals("") ||
                addRentPracownikIdTextField.getText().equals("") ||
                addRentKaucjaTextField.getText().equals("") ||
                addRentStanTextField.getText().equals("") ||
                addRentKodTextField.getText().equals("")) {
            WindowSingleton.alert("Niepoprawne dane");
            return;
        }

        if(addRentKodTextField.getText().length()>5){
            WindowSingleton.alert("Kod dostępu może posiadać maksymalnie 5 znaków");
            return;
        }

        try {
            Float.parseFloat(addRentKaucjaTextField.getText());
        } catch (NumberFormatException e) {
            WindowSingleton.alert("Niepoprawny format kaucji");
            return;
        }

        Rezerwacja rezerwacja = DBConnector.getInstance().getEntityManager().find(Rezerwacja.class, Long.parseLong(addRezerwacjaIdTextField.getText()));
        if(rezerwacja == null){
            WindowSingleton.alert("Nie ma rezerwacji o takim ID");
            return;
        }

        Pracownik pracownik = DBConnector.getInstance().getEntityManager().find(Pracownik.class, Long.parseLong(addRentPracownikIdTextField.getText()));
        if (pracownik == null) {
            WindowSingleton.alert("Nie ma pracownika o takim ID");
            return;
        }

        Wypozyczenie wypozyczenie = new Wypozyczenie(rezerwacja.getId_pojazdu(),
                rezerwacja.getData_r_rezerwacji(),
                addRentKodTextField.getText(),
                addRentStanTextField.getText(),
                rezerwacja.getId_klienta(),
                Float.parseFloat(addRentKaucjaTextField.getText()),
                pracownik);

        rezerwacja.getId_pojazdu().setCzyDostepny("nie");
        rezerwacja.getId_pojazdu().setStan_pojazdu(addRentStanTextField.getText());
        DBConnector.getInstance().editPojazd(rezerwacja.getId_pojazdu());
        DBConnector.getInstance().deleteRezerwacja(rezerwacja);
        DBConnector.getInstance().addWypozyczenie(wypozyczenie);
        WindowSingleton.alert("Dodano wypożyczenie");

        // todo all od nowa bo Aśce sie nie chcialo robic porzadnie


    }

    public void showAddPojazduList() {
        WindowSingleton.showVehicleTable(addRentIdPojazduTextField);
    }

    public void showAddKlientaList() {
        WindowSingleton.showClientTable(addRentIdKlientaTextField);
    }

    public void showAddRezerwacjaList(){
        WindowSingleton.showRezervationTable(addRezerwacjaIdTextField);
    }

    public void showAddPracownikList() {
        WindowSingleton.showEmployeeTable(addRentPracownikIdTextField);
    }

    public void deleteRent() {
        if (deleteRentIdTextField.getText().equals("")) {
            WindowSingleton.alert("Nie ma wypozyczenia o podanym ID");
        }

        long _id;
        try {
            _id = Long.parseLong(deleteRentIdTextField.getText());
            //DBConnector.getInstance().start();
            Wypozyczenie wypozyczenie = DBConnector.getInstance().getEntityManager().find(Wypozyczenie.class, _id);
            if (wypozyczenie == null) {
                WindowSingleton.alert("Nie ma takiego wypozyczenia");
//                DBConnector.getInstance().stop();
                return;
            }
            Pojazd pojazd = DBConnector.getInstance().getEntityManager().find(Pojazd.class, wypozyczenie.getId_pojazdu().getId_pojazdu());
            pojazd.setCzyDostepny("tak");

            DBConnector.getInstance().editPojazd(pojazd);
            DBConnector.getInstance().deleteWypozyczenie(wypozyczenie);
//            DBConnector.getInstance().stop();
            WindowSingleton.alert("Usunięto wypozyecznie o id = " + _id);
            System.out.println("usunieto wypozyczenie o id " + _id);
            deleteRentIdTextField.setText("");
        } catch (NumberFormatException e) {
            System.out.println("zły format");
            return;
        }
    }

    public void deleteRentShowRentList() {
        WindowSingleton.showRentTable(deleteRentIdTextField);
    }

    public void printRentList() {
        final TableView<Wypozyczenie> table = WindowSingleton.createRentTable();
        printRentStackPane.getChildren().add(table);
    }

    public void fillEditedRentFields() {
        if (editRentIdTextField.getText().equals("")) {
            WindowSingleton.alert("Nie ma wypozyczenia o podanym ID");
        }
        try {
            long id = Long.parseLong(editRentIdTextField.getText());
        } catch (NumberFormatException e) {
            WindowSingleton.alert("Niepoprawny format ID");
            return;
        }
        Wypozyczenie wypozyczenie = DBConnector.getInstance().getEntityManager().find(Wypozyczenie.class, Long.parseLong(editRentIdTextField.getText()));
        editRentIdPojazduTextField.setText(String.valueOf(wypozyczenie.getId_pojazdu()));
        editRentIdKlientaTextField.setText(String.valueOf(wypozyczenie.getId_klienta()));
        //todo
        editRentDataWTextField.setText(String.valueOf(wypozyczenie.getData_wypozyczenia()));
        editRentIdPracownikaTextField.setText(String.valueOf(wypozyczenie.getId_pracownika()));
        editRentKaucjaTextField.setText(String.valueOf(wypozyczenie.getKaucja()));
        editRentStanTextField.setText(String.valueOf(wypozyczenie.getStan_pojazdu()));
        editRentKodTextField.setText(String.valueOf(wypozyczenie.getKod_dostepu()));

    }

    public void editReservation() {
        if (editRentNewIdKlientaTextField.getText().equals("") ||
                editRentNewIdPojazduTextField.getText().equals("") ||
                editRentNewIdPracownikaTextField.getText().equals("") ||
                editRentNewKaucjaTextField.getText().equals("") ||
                editRentNewKodTextField.getText().equals("") ||
                editRentNewStanTextField.getText().equals("") ||
                editRentNewDataWTextField.getValue().equals(null)) {
            WindowSingleton.alert("Niepoprawne dane");
            return;
        }

        try {
            Date.valueOf(editRentNewDataWTextField.getValue());
        } catch (Exception e) {
            WindowSingleton.alert("Niepoprawna data");
            return;
        }


        Pojazd pojazd = DBConnector.getInstance().getEntityManager().find(Pojazd.class, Long.parseLong(editRentNewIdPojazduTextField.getText()));
        Klient klient = DBConnector.getInstance().getEntityManager().find(Klient.class, Long.parseLong(editRentNewIdKlientaTextField.getText()));
        Pracownik pracownik = DBConnector.getInstance().getEntityManager().find(Pracownik.class, Long.parseLong(editRentNewIdPracownikaTextField.getText()));


        Wypozyczenie wypozyczenie = DBConnector.getInstance().getEntityManager().find(Wypozyczenie.class, Long.parseLong(editRentIdTextField.getText()));
        pojazd.setStan_pojazdu(editRentNewStanTextField.getText());
        wypozyczenie.setId_pojazdu(pojazd);
        wypozyczenie.setId_klienta(klient);
        wypozyczenie.setData_wypozyczenia(Date.valueOf(editRentNewDataWTextField.getValue().toString()));
        wypozyczenie.setStan_pojazdu((editRentNewStanTextField.getText()));
        wypozyczenie.setKod_dostepu(editRentNewKodTextField.getText());
        wypozyczenie.setKaucja(Float.parseFloat(editRentNewKaucjaTextField.getText()));
        wypozyczenie.setId_pracownika(pracownik);

        DBConnector.getInstance().editPojazd(pojazd);
        DBConnector.getInstance().editWypozyczenie(wypozyczenie);
        WindowSingleton.alert("Zedytowano wypozyczenie");
    }

    public void showEditRentList() {
        WindowSingleton.showRentTable(editRentIdTextField);
    }


    public void showMainMenu() throws IOException {
        WindowSingleton.getInstance().setLayout("/fxml/StartScreen.fxml");
    }

    public void showEditVehicleList(){
        WindowSingleton.showVehicleTable(editRentNewIdPojazduTextField);
    }

    public void showEditClientList(){
        WindowSingleton.showClientTable(editRentNewIdKlientaTextField);
    }

    public void showEditEmployeeList(){
        WindowSingleton.showEmployeeTable(editRentNewIdPracownikaTextField);
    }

}
