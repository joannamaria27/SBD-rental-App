package controller;

import domain.Klient;
import domain.Pojazd;
import domain.Punkt_Wypozyczen;
import domain.Wypozyczenie;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.List;


public class RentalPointController {

    @FXML
    private TextField addRentalPointLokalizacjaTextField;
    @FXML
    private TextField addRentalPointNazwaTextField;
    @FXML
    private TextField deleteRentalPointIdTextField;
    @FXML
    private StackPane printRentalPointStackPane;
    @FXML
    private TextField editRentalPointIdTextField;
    @FXML
    private TextField editRentalPointNazwaTextField;
    @FXML
    private TextField editRentalPointLokalizacjaTextField;
    @FXML
    private TextField editRentalPointNewNazwaTextField;
    @FXML
    private TextField editRentalPointNewLokalizacjaTextField;


    public void addRentalPoint() {
        if (addRentalPointLokalizacjaTextField.getText().equals("") || addRentalPointNazwaTextField.getText().equals("")) {
            WindowSingleton.alert("Niepoprawne dane");
            return;
        }
        List<Punkt_Wypozyczen> list = DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Punkt_Wypozyczen a WHERE lokalizacja='" + addRentalPointLokalizacjaTextField.getText() + "'", Punkt_Wypozyczen.class).getResultList();
        if (list.size() > 0) {
            WindowSingleton.alert("W podanej lokalizacji już istnieje punkt wypożyczeń");
            return;
        }


        DBConnector.getInstance().addRentalPoint(new Punkt_Wypozyczen(addRentalPointLokalizacjaTextField.getText(), addRentalPointNazwaTextField.getText()));
        WindowSingleton.alert("Dodano punkt wypożyczeń");
    }

    public void showRentalPointList() {
        WindowSingleton.showRentalPointTable(deleteRentalPointIdTextField);
    }

    public void printRentalPoint() {
        final TableView<Pojazd> table = WindowSingleton.createRentalPointTable();
        printRentalPointStackPane.getChildren().add(table);
    }

    public void deleteRentalPoint() {

        if (deleteRentalPointIdTextField.getText().equals("")) {
            WindowSingleton.alert("Niepoprawne dane");
            return;
        }

        Punkt_Wypozyczen punkt_wypozyczen = DBConnector.getInstance().getEntityManager().find(Punkt_Wypozyczen.class, Long.parseLong(deleteRentalPointIdTextField.getText()));
        if (punkt_wypozyczen == null) {
            if (punkt_wypozyczen == null) {
                WindowSingleton.alert("Nie ma punktu wypożyczeń o tym ID");
                return;
            }
        }
        WindowSingleton.alert("Usunięto punkt wypożyczeń o ID = " + deleteRentalPointIdTextField.getText());
        DBConnector.getInstance().deleteRentalPoint(punkt_wypozyczen);
        deleteRentalPointIdTextField.setText("");
    }


    public void showMainMenu() throws IOException {
        WindowSingleton.getInstance().setLayout("/fxml/StartScreen.fxml");
    }

    public void showEditRentalPointList(){
        WindowSingleton.showRentalPointTable(editRentalPointIdTextField);
    }

    public void fillEditedRentalPointFields(){
        if(editRentalPointIdTextField.getText().equals("")){
            WindowSingleton.alert("Nie ma punktu wypożyczeń o tym ID");
            return;
        }

        Punkt_Wypozyczen punkt_wypozyczen = DBConnector.getInstance().getEntityManager().find(Punkt_Wypozyczen.class, Long.parseLong(editRentalPointIdTextField.getText()));

        editRentalPointLokalizacjaTextField.setText(punkt_wypozyczen.getLokalizacja());
        editRentalPointNazwaTextField.setText(punkt_wypozyczen.getNazwa_punktu());



    }

    public void editRentalPoint(){
        if(editRentalPointNewLokalizacjaTextField.getText().equals("") || editRentalPointNewNazwaTextField.getText().equals("")){
            WindowSingleton.alert("Niepoprawne dane");
            return;
        }

        List<Punkt_Wypozyczen> list = DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Punkt_Wypozyczen a WHERE lokalizacja='" + editRentalPointNewLokalizacjaTextField.getText() + "' AND id_punktu <> '"+editRentalPointIdTextField.getText()+"'", Punkt_Wypozyczen.class).getResultList();
        Punkt_Wypozyczen punkt_wypozyczen = DBConnector.getInstance().getEntityManager().find(Punkt_Wypozyczen.class, Long.parseLong(editRentalPointIdTextField.getText()));

        if (list.size() > 0) {
            WindowSingleton.alert("W podanej lokalizacji istnieje już punkt wypożyczeń");
            return;
        }

        punkt_wypozyczen.setLokalizacja(editRentalPointNewLokalizacjaTextField.getText());
        punkt_wypozyczen.setNazwa_punktu(editRentalPointNewNazwaTextField.getText());

        DBConnector.getInstance().editPunktWypozyczen(punkt_wypozyczen);
        WindowSingleton.alert("Zedytowano punkt wypożyczeń");
        editRentalPointNewLokalizacjaTextField.setText("");
        editRentalPointNewNazwaTextField.setText("");
        editRentalPointNazwaTextField.setText("");
        editRentalPointLokalizacjaTextField.setText("");

    }

}
