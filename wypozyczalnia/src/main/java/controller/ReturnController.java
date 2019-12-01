package controller;

import java.io.IOException;

public class ReturnController {



    public void showMainMenu() throws IOException {
        WindowSingleton.getInstance().setLayout("/fxml/StartScreen.fxml");
    }

}
