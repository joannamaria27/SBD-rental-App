package controller;

import domain.Pojazd;
import domain.Punkt_Wypozyczen;
import domain.Ubezpieczenie;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Date;


public class VehicleOptionsController {

    @FXML
    private TextField addVehicleTypTextField;
    @FXML
    private TextField addVehicleMarkaTextField;
    @FXML
    private TextField addVehicleModelTextField;
    @FXML
    private TextField addVehicleUbezpieczenieTextField;
    @FXML
    private TextField addVehicleStanPojazduTextField;
    @FXML
    private DatePicker addVehicleTerminWaznosciBadaniaDatePicker;
    @FXML
    private TextField addVehiclePunktPostojuTextField;

    public void addVehicle(){
        if (addVehicleTypTextField.getText().equals("") || addVehicleMarkaTextField.getText().equals("") || addVehicleModelTextField.getText().equals("") || addVehicleUbezpieczenieTextField.getText().equals("") || addVehicleStanPojazduTextField.getText().equals("") || addVehicleTerminWaznosciBadaniaDatePicker.getValue().toString().equals("") || addVehiclePunktPostojuTextField.getText().equals("")) {
            WindowSingleton.alert("Niepoprawne dane");
            return;
        }
        System.out.println(addVehicleTerminWaznosciBadaniaDatePicker.getValue().getClass().getName());

        Ubezpieczenie ubezpieczenie = new Ubezpieczenie();
        Punkt_Wypozyczen punkt_wypozyczen = new Punkt_Wypozyczen("Białystok", "punkt1");

        DBConnector.getInstance().start();
        DBConnector.getInstance().addPojazd(new Pojazd(addVehicleTypTextField.getText(),
                addVehicleMarkaTextField.getText(),
                addVehicleModelTextField.getText(),
                ubezpieczenie,
                addVehicleStanPojazduTextField.getText(),
                Date.valueOf(addVehicleTerminWaznosciBadaniaDatePicker.getValue().toString()),
                punkt_wypozyczen));
        DBConnector.getInstance().stop();
        WindowSingleton.alert("Dodano pojazd");

        addVehicleTypTextField.setText("");
        addVehicleMarkaTextField.setText("");
        addVehicleModelTextField.setText("");
        addVehicleStanPojazduTextField.setText("");
        addVehicleTerminWaznosciBadaniaDatePicker.setAccessibleText("");
    }

    public void showAddRentalPointList(){
        WindowSingleton.showRentalPointTable(addVehiclePunktPostojuTextField);
    }
}
