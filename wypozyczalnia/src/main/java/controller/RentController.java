package controller;

import domain.Wypozyczenie;
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
    @FXML
    private DatePicker addRentDataWTextField;
    @FXML
    private TextField addRentKodTextField;
    @FXML
    private TextField addRentStanTextField;
    @FXML
    private TextField addRentIdKlientaTextField;
    @FXML
    private TextField addRentKaucjaTextField;
    @FXML
    private TextField addRentIdPracownikaTextField;


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
    private DatePicker editRentDataWTextField;
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


    public void addRent()
    {
        System.out.println("addRentButton");

        if(addRentDataWTextField.getValue().equals("") || addRentIdPojazduTextField.getText().equals("") || addRentKodTextField.getText().equals("") || addRentStanTextField.getText().equals("") || addRentIdKlientaTextField.getText().equals("") || addRentKaucjaTextField.getText().equals("") || addRentIdPracownikaTextField.getText().equals(""))
        {
            WindowSingleton.alert("niepoprawne dane");
            return;
        }

        //todo - obsługa błędów???
//        List<Wypozyczenie> list = (List<Wypozyczenie>) DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Wypozyczenie a WHERE id_pojazdu='" + addRentIdPojazduTextField.getText() + "'", Wypozyczenie.class).getResultList();
//        if (list.size() > 0) {
//            WindowSingleton.alert("Wypozyczenie o podanym numerze istnieje już w bazie");
//        }

        try {
            Date startdate = Date.valueOf(addRentDataWTextField.getValue());
        } catch (Exception e) {
            System.out.println("nope");
        }
        System.out.println(Date.valueOf(addRentDataWTextField.getValue()).getClass().getName());

        DBConnector.getInstance().start();
        //todo
//        DBConnector.getInstance().addWypozyczenie(new Wypozyczenie(addRentIdPojazduTextField.getText(), Date.valueOf(addRentDataWTextField.getValue()), addRentKodTextField.getText(),   addRentStanTextField.getText(), addRentIdKlientaTextField.getText(), addRentKaucjaTextField.getText(), addRentIdPracownikaTextField.getText() );
        DBConnector.getInstance().stop();
        WindowSingleton.alert("Dodano wypozyczenie");
        addRentIdPojazduTextField.setText("");
        addRentKodTextField.setText("");
        addRentStanTextField.setText("");
        addRentDataWTextField.setValue(null);
        addRentIdPracownikaTextField.setText("");
        addRentIdKlientaTextField.setText("");
        addRentKaucjaTextField.setText("");


    }


    public void deleteRent() {
        if (deleteRentIdTextField.getText().equals("")) {
            WindowSingleton.alert("Nie ma wypozyczenia o podanym ID");
        }

        long _id;
        try {
            _id = Long.parseLong(deleteRentIdTextField.getText());
            DBConnector.getInstance().start();
            Wypozyczenie wypozyczenie = DBConnector.getInstance().getEntityManager().find(Wypozyczenie.class, _id);
            if (wypozyczenie == null) {
                WindowSingleton.alert("Nie ma takiego wypozyczenia");
                DBConnector.getInstance().stop();
                return;
            }

            WindowSingleton.alert("Usunięto wypozyecznie o id = " + _id);
            System.out.println("usunieto wypozyczenie o id " + _id);
            DBConnector.getInstance().deleteWypozyczenie(wypozyczenie);
            DBConnector.getInstance().stop();
            deleteRentIdTextField.setText("");
        } catch (NumberFormatException e) {
            System.out.println("zły format");
            return;
        }
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
//        editRentDataWTextField.setText(String.valueOf(wypozyczenie.getData_wypozyczenia()));
        editRentIdPracownikaTextField.setText(String.valueOf(wypozyczenie.getId_pracownika()));
        editRentKaucjaTextField.setText(String.valueOf(wypozyczenie.getKaucja()));
        editRentStanTextField.setText(String.valueOf(wypozyczenie.getStan_pojazdu()));
        editRentKodTextField.setText(String.valueOf(wypozyczenie.getKod_dostepu()));

    }

    public void editReservation() {
        if(editRentNewIdKlientaTextField.getText().equals("") ||
                editRentNewIdPojazduTextField.getText().equals("") ||
                editRentNewIdPracownikaTextField.getText().equals("") ||
                editRentNewKaucjaTextField.getText().equals("") ||
                editRentNewKodTextField.getText().equals("") ||
                editRentNewStanTextField.getText().equals("") ||
                editRentNewDataWTextField.getValue().equals(null) ) {
            WindowSingleton.alert("Niepoprawne dane");
            return;
        }

        try {
            Date.valueOf(editRentNewDataWTextField.getValue());
        } catch (Exception e) {
            WindowSingleton.alert("Niepoprawna data");
            return;
        }


        Wypozyczenie wypozyczenie = DBConnector.getInstance().getEntityManager().find(Wypozyczenie.class, Long.parseLong(editRentNewIdTextField.getText()));
//todo
//        wypozyczenie.setId_pojazdu(editRentNewIdPojazduTextField.getText());
//        wypozyczenie.setId_klienta(editRentNewIdKlientaTextField.getText());
//        wypozyczenie.setData_wypozyczenia(editRentNewDataWTextField.getText());
//        wypozyczenie.setStan_pojazdu((editRentNewStanTextField.getText()));
//        wypozyczenie.getKod_dostepu(editRentNewKodTextField.getText());
//        wypozyczenie.setKaucja(editRentNewKaucjaTextField.getText());
//        wypozyczenie.setId_pracownika(editRentNewIdPracownikaTextField.getText());

        DBConnector.getInstance().editWypozyczenie(wypozyczenie);
        WindowSingleton.alert("Zedytowano wypozyczenie");
    }

    public void showEditRentList() {
        WindowSingleton.showRentTable(editRentIdTextField);
    }



    public void showMainMenu() throws IOException {
        WindowSingleton.getInstance().setLayout("/fxml/StartScreen.fxml");
    }

}
