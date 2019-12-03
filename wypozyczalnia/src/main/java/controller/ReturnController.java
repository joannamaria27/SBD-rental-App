package controller;

import domain.Platnosc;
import domain.Pracownik;
import domain.Wypozyczenie;
import domain.Zwrot;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.sql.Date;

public class ReturnController {

    @FXML
    private TextField addReturnIdWypozyczeniaTextField;
    @FXML
    private DatePicker addReturnDataZwrotuDataPicker;
    @FXML
    private TextField addReturnStanTextField;
    @FXML
    private TextField addReturnCenaTextField;
    @FXML
    private TextField addReturnIdPracownikaTextField;
    @FXML
    private TextField addReturnIdPlatnosciTextField;


    @FXML
    private TextField deleteReturnIdTextField;

    @FXML
    private StackPane printReturnStackPane;

    @FXML
    private TextField editReturnIdTextField;
    @FXML
    private TextField  editReturnIdWypozyczeniaTextField;
    @FXML
    private TextField editReturnDataZwrotuDataPicker;
    @FXML
    private TextField  editReturnStanTextField;
    @FXML
    private TextField  editReturnCenaTextField;
    @FXML
    private TextField  editReturnIdPracownikaTextField;
    @FXML
    private TextField  editReturnIdPlatnosciTextField;
    @FXML
    private TextField  editReturnNewIdWypozyczeniaTextField;
    @FXML
    private DatePicker editReturnNewDataZwrotuDataPicker;
    @FXML
    private TextField  editReturnNewStanTextField;
    @FXML
    private TextField  editReturnNewCenaTextField;
    @FXML
    private TextField  editReturnNewIdPracownikaTextField;
    @FXML
    private TextField  editReturnNewIdPlatnosciTextField;



    public void addReturn() {
        System.out.println("addReturnButton");


        if (addReturnIdWypozyczeniaTextField.getText().equals("") || addReturnDataZwrotuDataPicker.getValue().equals("") || addReturnStanTextField.getText().equals("") || addReturnCenaTextField.getText().equals("") || addReturnIdPracownikaTextField.getText().equals("") || addReturnIdPlatnosciTextField.getText().equals("")) {
            WindowSingleton.alert("Niepoprawne dane");
            return;
        }
        //todo - daty
//        List<Rezerwacja> list = (List<Rezerwacja>) DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Rezerwacja a WHERE id_pojazdu='" + addReservationIdPojazduTextField.getText() + "'", Rezerwacja.class).getResultList();
//        if (list.size() > 0) {
//            WindowSingleton.alert("Pojazd o podanym numerze jest już zarezerwowany");
//            return;
//        }

        try {
            Date startdate = Date.valueOf(addReturnDataZwrotuDataPicker.getValue());
        } catch (Exception e) {
            System.out.println("nope");
        }
        System.out.println(Date.valueOf(addReturnDataZwrotuDataPicker.getValue()).getClass().getName());

        Wypozyczenie wypozyczenie = DBConnector.getInstance().getEntityManager().find(Wypozyczenie.class, Long.parseLong(addReturnIdWypozyczeniaTextField.getText()));
        Platnosc platnosc = DBConnector.getInstance().getEntityManager().find(Platnosc.class, Long.parseLong(addReturnIdPlatnosciTextField.getText()));
        Pracownik pracownik = DBConnector.getInstance().getEntityManager().find(Pracownik.class, Long.parseLong(addReturnIdPracownikaTextField.getText()));

//        DBConnector.getInstance().start();

        DBConnector.getInstance().addZwrot(new Zwrot(wypozyczenie, Date.valueOf(addReturnDataZwrotuDataPicker.getValue()), addReturnStanTextField.getText(), Float.parseFloat(addReturnCenaTextField.getText()), pracownik, platnosc));
//        DBConnector.getInstance().stop();
        WindowSingleton.alert("Dodano zwrot");
        addReturnIdWypozyczeniaTextField.setText("");
        addReturnDataZwrotuDataPicker.setValue(null);
        addReturnStanTextField.setText("");
        addReturnCenaTextField.setText("");
        addReturnIdPracownikaTextField.setText("");
        addReturnIdPlatnosciTextField.setText("");
    }

    public void showAddPlatnoscList() {
        WindowSingleton.showPlatnoscTable(addReturnIdPlatnosciTextField);
    }

    public void showAddWypozyczenieList() {
        WindowSingleton.showRentTable(addReturnIdWypozyczeniaTextField);
    }

    public void showAddPracownikList() {
        WindowSingleton.showEmployeeTable(addReturnIdPracownikaTextField);
    }


    public void deleteReturnShowReturnList() {
        WindowSingleton.showReturnTable(deleteReturnIdTextField);
    }

    public void deleteReturn() {
        if (deleteReturnIdTextField.getText().equals("")) {
            WindowSingleton.alert("Nie ma zwrotu o podanym ID");
        }

        long _id;
        try {
            _id = Long.parseLong(deleteReturnIdTextField.getText());
//            DBConnector.getInstance().start();
            Zwrot zwrot = DBConnector.getInstance().getEntityManager().find(Zwrot.class, _id);
            if (zwrot == null) {
                WindowSingleton.alert("Nie ma takiego zwrotu");
//                DBConnector.getInstance().stop();
                return;
            }

            WindowSingleton.alert("Usunięto zwrot o id = " + _id);
            System.out.println("usunieto zwrot o id " + _id);
            DBConnector.getInstance().deleteZwrot(zwrot);
//            DBConnector.getInstance().stop();
            deleteReturnIdTextField.setText("");
        } catch (NumberFormatException e) {
            System.out.println("zły format");
            return;
        }
    }

    public void printReturnList() {
        final TableView<Zwrot> table = WindowSingleton.createReturnTable();
        printReturnStackPane.getChildren().add(table);
    }

    public void fillEditedReturnFields() {
        if (editReturnIdTextField.getText().equals("")) {
            WindowSingleton.alert("Nie ma rezerwacji o podanym ID");
        }
        try {
            long id = Long.parseLong(editReturnIdTextField.getText());
        } catch (NumberFormatException e) {
            WindowSingleton.alert("Niepoprawny format ID");
            return;
        }
        Zwrot zwrot = DBConnector.getInstance().getEntityManager().find(Zwrot.class, Long.parseLong(editReturnIdTextField.getText()));
        editReturnIdWypozyczeniaTextField.setText(String.valueOf(zwrot.getId_zwrotu()));
        //todo
        editReturnDataZwrotuDataPicker.setText(String.valueOf(zwrot.getData_zwrotu()));
        editReturnStanTextField.setText(String.valueOf(zwrot.getStan_pojazdu()));
        editReturnCenaTextField.setText(String.valueOf(zwrot.getCena_ostateczna()));
        editReturnIdPracownikaTextField.setText(String.valueOf(zwrot.getId_pracownika()));
        editReturnIdPlatnosciTextField.setText(String.valueOf(zwrot.getId_platnosci()));
    }

    public void showEditWypozyczenieList() {
        WindowSingleton.showRentTable(editReturnNewIdWypozyczeniaTextField);
    }

    public void showEditEmployeeList() {
        WindowSingleton.showEmployeeTable(editReturnNewIdPracownikaTextField);
    }

    public void showEditPlatnosctList() {
        //WindowSingleton.showClientTable( );
    }

    public void editReservation() {
        if (editReturnNewIdWypozyczeniaTextField.getText().equals("") ||
            editReturnNewDataZwrotuDataPicker.getValue().equals(null) ||
            editReturnNewStanTextField.getText().equals("") ||
            editReturnNewCenaTextField.getText().equals("") ||
            editReturnNewIdPracownikaTextField.getText().equals("") ||
            editReturnNewIdPlatnosciTextField.getText().equals("")) {
            WindowSingleton.alert("Niepoprawne dane");
            return;
        }

        try {
            Date.valueOf(editReturnNewDataZwrotuDataPicker.getValue());
        } catch (Exception e) {
            WindowSingleton.alert("Niepoprawna data");
            return;
        }

        Zwrot zwrot = DBConnector.getInstance().getEntityManager().find(Zwrot.class, Long.parseLong(editReturnIdTextField.getText()));

        Wypozyczenie wypozyczenie = DBConnector.getInstance().getEntityManager().find(Wypozyczenie.class, Long.parseLong(editReturnIdWypozyczeniaTextField.getText()));
        Platnosc platnosc = DBConnector.getInstance().getEntityManager().find(Platnosc.class, Long.parseLong(editReturnIdPlatnosciTextField.getText()));
        Pracownik pracownik = DBConnector.getInstance().getEntityManager().find(Pracownik.class, Long.parseLong(editReturnIdPracownikaTextField.getText()));


        zwrot.setId_wypozyczenia(wypozyczenie);
        zwrot.setId_platnosci(platnosc);
        zwrot.setStan_pojazdu(editReturnNewStanTextField.getText());
        zwrot.setData_zwrotu(Date.valueOf(editReturnDataZwrotuDataPicker.getText()));
        zwrot.setCena_ostateczna(Float.parseFloat(editReturnCenaTextField.getText()));
        zwrot.setId_pracownika(pracownik);

        DBConnector.getInstance().editZwrot(zwrot);
        WindowSingleton.alert("Zedytowano zwrot");

        editReturnIdTextField.setText("");

        editReturnIdWypozyczeniaTextField.setText("");
        editReturnDataZwrotuDataPicker.setText("");
        editReturnStanTextField.setText("");
        editReturnCenaTextField.setText("");
        editReturnIdPracownikaTextField.setText("");
        editReturnIdPlatnosciTextField.setText("");

        editReturnNewIdWypozyczeniaTextField.setText("");
        editReturnNewDataZwrotuDataPicker.getEditor().clear();
        editReturnNewStanTextField.setText("");
        editReturnNewCenaTextField.setText("");
        editReturnNewIdPracownikaTextField.setText("");
        editReturnNewIdPlatnosciTextField.setText("");
    }

    public void showEditReturnList() {
        WindowSingleton.showReturnTable(editReturnIdTextField);
    }




    public void showMainMenu() throws IOException {
        WindowSingleton.getInstance().setLayout("/fxml/StartScreen.fxml");
    }

}
