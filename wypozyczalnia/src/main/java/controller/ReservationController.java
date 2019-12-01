package controller;

import java.io.IOException;

public class ReservationController {

    public void showMainMenu() throws IOException {
        WindowSingleton.getInstance().setLayout("/fxml/StartScreen.fxml");
    }

}
