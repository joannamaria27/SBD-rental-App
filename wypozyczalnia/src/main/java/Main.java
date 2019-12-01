import controller.WindowSingleton;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) throws IOException { launch(); }

    public void start(Stage primaryStage) throws Exception {
        System.out.println("halo");
        WindowSingleton window = WindowSingleton.getInstance();
        window.startApp(primaryStage);
    }
}
