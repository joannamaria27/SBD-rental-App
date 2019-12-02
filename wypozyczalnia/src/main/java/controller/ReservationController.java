package controller;

import com.sun.tools.javac.util.List;
import domain.Rezerwacja;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.sql.Date;

public class ReservationController {

    @FXML
    private TextField addReservationIdPojazduTextField;
    @FXML
    private TextField addReservationPrzewidywanaCenaTextField;
    @FXML
    private TextField addReservationIdKlientaTextField;

    @FXML
    private TextField addReservationIdPracownikaTextField;
    @FXML
    private DatePicker addReservationDataRozpTextField;
    @FXML
    private DatePicker addReservationDataZakTextField;


    @FXML
    private TextField deleteReservationIdTextField;

    @FXML
    private StackPane printReservationStackPane;

    @FXML
    private TextField editReservationIdTextField;


    @FXML
    private TextField editReservationIdPojazduTextField;
    @FXML
    private TextField editReservationIdKlientaTextField;
    @FXML
    private TextField editReservationDataRTextField;
    @FXML
    private TextField editReservationDataZTextField;
    @FXML
    private TextField editReservationPrzewidywanaCenaTextField;
    @FXML
    private TextField editReservationPracownikTextField;

    @FXML
    private TextField editReservationNewIdPojazduTextField;
    @FXML
    private TextField editReservationNewIdKlientaTextField;
    @FXML
    private TextField editReservationNewDataRTextField;
    @FXML
    private TextField editReservationNewDataZTextField;
    @FXML
    private TextField editReservationNewPrzewidywanaCenaTextField;
    @FXML
    private TextField editReservationNewPracownikTextField;



    public void addClient() {
        System.out.println("addClientButton");


        if (addReservationPrzewidywanaCenaTextField.getText().equals("") || addReservationIdKlientaTextField.getText().equals("") || addReservationIdPojazduTextField.getText().equals("") || addReservationIdPracownikaTextField.getText().equals("") || addReservationDataRozpTextField.getValue().equals("") || addReservationDataZakTextField.getValue().equals("") ){
            WindowSingleton.alert("Niepoprawne dane");
            return;
        }
        List<Rezerwacja> list = (List<Rezerwacja>) DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Rezerwacja a WHERE id_pojazdu='" + addReservationIdPojazduTextField.getText() + "'", Rezerwacja.class).getResultList();
        if (list.size() > 0) {
            WindowSingleton.alert("Pojazd o podanym numerze istnieje już w bazie");
        }

        try {
            Date startdate = Date.valueOf(addReservationDataRozpTextField.getValue());
        } catch (Exception e) {
            System.out.println("nope");
        }
        System.out.println(Date.valueOf(addReservationDataRozpTextField.getValue()).getClass().getName());
        try {
            Date stopdate = Date.valueOf(addReservationDataZakTextField.getValue());
        } catch (Exception e) {
            System.out.println("nope");
        }
        System.out.println(Date.valueOf(addReservationDataZakTextField.getValue()).getClass().getName());


        DBConnector.getInstance().start();
        //todo
//        DBConnector.getInstance().addRezerwacja(new Rezerwacja(addReservationIdPojazduTextField.getClass(), addReservationIdKlientaTextField.getText(), Date.valueOf(addReservationDataRozpTextField.getValue()),  Date.valueOf(addReservationDataZakTextField.getValue()), addReservationPrzewidywanaCenaTextField.getText(), addReservationIdPracownikaTextField.getText());
        DBConnector.getInstance().stop();
        WindowSingleton.alert("Dodano klienta");
        addReservationPrzewidywanaCenaTextField.setText("");
        addReservationIdKlientaTextField.setText("");
        addReservationIdPojazduTextField.setText("");
        addReservationDataRozpTextField.setValue(null);

        addReservationDataZakTextField.setValue(null);
        addReservationIdPracownikaTextField.setText("");
    }
    public void deleteReservationShowReservationList() {
        WindowSingleton.showClientTable(deleteReservationIdTextField);
    }

    public void deleteReservation() {
        if (deleteReservationIdTextField.getText().equals("")) {
            WindowSingleton.alert("Nie ma rezerwacji o podanym ID");
        }

        long _id;
        try {
            _id = Long.parseLong(deleteReservationIdTextField.getText());
            DBConnector.getInstance().start();
            Rezerwacja rezerwacja = DBConnector.getInstance().getEntityManager().find(Rezerwacja.class, _id);
            if (rezerwacja == null) {
                WindowSingleton.alert("Nie ma takiej rezerwacji");
                DBConnector.getInstance().stop();
                return;
            }

            WindowSingleton.alert("Usunięto rezerwacje o id = " + _id);
            System.out.println("usunieto rezerwacje o id " + _id);
            DBConnector.getInstance().deleteRezerwacja(rezerwacja);
            DBConnector.getInstance().stop();
            deleteReservationIdTextField.setText("");
        } catch (NumberFormatException e) {
            System.out.println("zły format");
            return;
        }
    }



    public void printReservationList() {
        final TableView<Rezerwacja> table = WindowSingleton.createReservationTable();
        printReservationStackPane.getChildren().add(table);
    }

    public void fillEditedReservationFields() {
        if (editReservationIdTextField.getText().equals("")) {
            WindowSingleton.alert("Nie ma rezerwacji o podanym ID");
        }
        try {
            long id = Long.parseLong(editReservationIdTextField.getText());
        } catch (NumberFormatException e) {
            WindowSingleton.alert("Niepoprawny format ID");
            return;
        }
        Rezerwacja rezerwacja = DBConnector.getInstance().getEntityManager().find(Rezerwacja.class, Long.parseLong(editReservationIdTextField.getText()));
        editReservationIdPojazduTextField.setText(String.valueOf(rezerwacja.getId_pojazdu()));
        editReservationIdKlientaTextField.setText(String.valueOf(rezerwacja.getId_klienta()));
        editReservationDataRTextField.setText(String.valueOf(rezerwacja.getData_r_rezerwacji()));
        editReservationDataZTextField.setText(String.valueOf(rezerwacja.getData_z_rezerwacji()));
        editReservationPrzewidywanaCenaTextField.setText(String.valueOf(rezerwacja.getPrzewidywana_cena()));
        editReservationPracownikTextField.setText(String.valueOf(rezerwacja.getId_pracownika()));


    }

    public void editReservation() {
        if(editReservationNewIdPojazduTextField.getText().equals("") ||
                editReservationNewIdKlientaTextField.getText().equals("") ||
                editReservationNewDataRTextField.getText().equals("") ||
                editReservationNewDataZTextField.getText().equals("") ||
                editReservationNewPrzewidywanaCenaTextField.getText().equals("") ||
                editReservationNewPracownikTextField.getText().equals("")) {
            WindowSingleton.alert("Niepoprawne dane");
            return;
        }

        try {
            Date.valueOf(editReservationNewDataRTextField.getText());
        } catch (Exception e) {
            WindowSingleton.alert("Niepoprawna data");
            return;
        }
        try {
            Date.valueOf(editReservationNewDataZTextField.getText());
        } catch (Exception e) {
            WindowSingleton.alert("Niepoprawna data");
            return;
        }

        Rezerwacja rezerwacja = DBConnector.getInstance().getEntityManager().find(Rezerwacja.class, Long.parseLong(editReservationIdTextField.getText()));
//todo
        //        rezerwacja.setId_pojazdu(editReservationNewIdPojazduTextField.getText());
//        rezerwacja.setId_klienta(editReservationNewIdKlientaTextField.getText());
//        rezerwacja.setData_r_rezerwacji(editReservationNewDataRTextField.getText());
//        rezerwacja.setData_z_rezerwacji(Date.valueOf(editReservationNewDataZTextField.getText()));
//        rezerwacja.setPrzewidywana_cena(editReservationNewPrzewidywanaCenaTextField.getText());
//        rezerwacja.setId_pracownika(editReservationNewPracownikTextField.getText());

        DBConnector.getInstance().editRezerwacja(rezerwacja);
        WindowSingleton.alert("Zedytowano rezerwcje");
    }

    public void showEditReservationList() {
        WindowSingleton.showRezervationTable(editReservationIdTextField);
    }



    public void showMainMenu() throws IOException {
        WindowSingleton.getInstance().setLayout("/fxml/StartScreen.fxml");
    }

}
