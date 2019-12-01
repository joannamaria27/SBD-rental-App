package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class StartScreenController {

    @FXML
    private Button mainMenuClientButton;

    public void showClientMenu() throws IOException {
        System.out.println("showCustomerMenu");
        WindowSingleton.getInstance().setLayout("/fxml/ClientOptions.fxml");
    }

    public void showEmployeeMenu() throws IOException {
        System.out.println("showEmployeeMenu");
        WindowSingleton.getInstance().setLayout("/fxml/EmployeeOptions.fxml");
    }

    public void showVehicleMenu() throws IOException {
        System.out.println("showVehicleMenu");
        WindowSingleton.getInstance().setLayout("/fxml/VehicleOptions.fxml");
    }

    public void showRentalPointMenu() throws IOException {
        System.out.println("showRentalMenu");
        WindowSingleton.getInstance().setLayout("/fxml/RentalPoint.fxml");
    }

    public void showReturnMenu() throws IOException {
        System.out.println("showReturnMenu");
        WindowSingleton.getInstance().setLayout("/fxml/ReturnOptions.fxml");
    }

    public void showRenteMenu() throws IOException {
        System.out.println("showRentMenu");
        WindowSingleton.getInstance().setLayout("/fxml/ClientOptions.fxml");
    }

    public void showReservationMenu() throws IOException {
        System.out.println("showReservationMenu");
        WindowSingleton.getInstance().setLayout("/fxml/ClientOptions.fxml");
    }

}
