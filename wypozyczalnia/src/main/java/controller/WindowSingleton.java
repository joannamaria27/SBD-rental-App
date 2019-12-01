package controller;

import domain.Klient;
import domain.Pracownik;
import domain.Punkt_Wypozyczen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class WindowSingleton {

    private static WindowSingleton instance;
    private Stage primaryStage;
    private Scene scene;



    private WindowSingleton() {
    }

    public static WindowSingleton getInstance() {
        if (instance == null) {
            instance = new WindowSingleton();
            return instance;
        } else return instance;

    }

    public void setLayout(String pathToFXML) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource(pathToFXML));

        BorderPane borderPane = null;
        try {
            borderPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error");
        }

        scene = new Scene(borderPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("KCK2");
        primaryStage.show();
    }

    public void startApp(Stage stage) throws IOException, InterruptedException {
        primaryStage = stage;
        primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new Image("image/WindowIcon.png"));
        setLayout("/fxml/StartScreen.fxml");
        //WelcomeScreen.ProgressMax();
//        DBConnector.getInstance().getEntityManager();
    }

    public static void alert(String message) {
        final Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Alert");
        window.setResizable(false);
        //window.getIcons().add(new Image("image/AlertIcon.png"));
        window.setMinWidth(356);
        window.setMinHeight(220);

        Label label = new Label(message);
        Button closeButton = new Button("Zamknij");
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                window.close();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void showClientTable(final TextField idField) {

        final Stage window = new Stage();
        Button button = new Button("Wybierz");
        window.setTitle("Lista klientów");


        final TableView<Klient> table = createClientTable();
        Klient klient = table.getSelectionModel().getSelectedItem();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Klient selection;
                selection = table.getSelectionModel().getSelectedItem();
                //System.out.println(selection.getId());
                idField.setText(String.valueOf(selection.getId_klienta()));
                window.close();
            }
        });

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }
    public static void showEmployeeTable(final TextField idField) {

        final Stage window = new Stage();
        Button button = new Button("Wybierz");
        window.setTitle("Lista pracowników");


        final TableView<Pracownik> table = createEmployeeTable();
        Pracownik pracownik = table.getSelectionModel().getSelectedItem();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Pracownik selection;
                selection = table.getSelectionModel().getSelectedItem();
                //System.out.println(selection.getId());
                idField.setText(String.valueOf(selection.getId_pracownika()));
                window.close();
            }
        });

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    public static void showRentalPointTable(final TextField idField) {

        final Stage window = new Stage();
        Button button = new Button("Wybierz");
        window.setTitle("Lista punktów wypożyczeń");


        final TableView<Punkt_Wypozyczen> table = createRentalPointTable();
        Punkt_Wypozyczen punkt_wypozyczen = table.getSelectionModel().getSelectedItem();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Punkt_Wypozyczen selection;
                selection = table.getSelectionModel().getSelectedItem();
                //System.out.println(selection.getId());
                idField.setText(String.valueOf(selection.getId_punktu()));
                window.close();
            }
        });

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    public static TableView createClientTable() {
        final TableView<Klient> table;
//        final Button select = new Button("Select");
//        String choice = "";

        // id
        TableColumn<Klient, Long> idColumn = new TableColumn<Klient, Long>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<Klient, Long>("id_klienta"));

        // adres
        TableColumn<Klient, String> addressColumn = new TableColumn<Klient, String>("Adres");
        addressColumn.setMinWidth(100);
        addressColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("adres"));

        // nr prawa jazdy
        TableColumn<Klient, String> drivingLicenceNumberColumn = new TableColumn<Klient, String>("Nr prawa jazdy");
        drivingLicenceNumberColumn.setMinWidth(100);
        drivingLicenceNumberColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("nr_prawa_jazdy"));

        // imie
        TableColumn<Klient, String> nameColumn = new TableColumn<Klient, String>("Imie");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("imie"));

        // nazwisko
        TableColumn<Klient, String> surnameColumn = new TableColumn<Klient, String>("Nazwisko");
        surnameColumn.setMinWidth(100);
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("nazwisko"));

        // data urodzenia
        TableColumn<Klient, Date> birthDateColumn = new TableColumn<Klient, Date>("Data urodzenia");
        birthDateColumn.setMinWidth(100);
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<Klient, Date>("data_urodzenia"));

        // pesel
        TableColumn<Klient, String> peselColumn = new TableColumn<Klient, String>("PESEL");
        peselColumn.setMinWidth(100);
        peselColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("pesel"));

        TableColumn<Klient, String> phoneColumn = new TableColumn<Klient, String>("Telefon");
        phoneColumn.setMinWidth(100);
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("telefon"));


        table = new TableView<Klient>();
        table.setItems(WindowSingleton.getClientObservableList());
        table.getColumns().addAll(idColumn, nameColumn, surnameColumn, birthDateColumn, addressColumn, phoneColumn, drivingLicenceNumberColumn, peselColumn);

        return table;
    }
    public static TableView createEmployeeTable() {
        final TableView<Pracownik> table;

        // id
        TableColumn<Pracownik, Long> idColumn = new TableColumn<Pracownik, Long>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<Pracownik, Long>("id_pracownika"));

        // adres
        TableColumn<Pracownik, String> addressColumn = new TableColumn<Pracownik, String>("Adres");
        addressColumn.setMinWidth(100);
        addressColumn.setCellValueFactory(new PropertyValueFactory<Pracownik, String>("adres"));

        // stanowisko
        TableColumn<Pracownik, String> stanowiskoColumn = new TableColumn<Pracownik, String>("Stanowisko");
        stanowiskoColumn.setMinWidth(100);
        stanowiskoColumn.setCellValueFactory(new PropertyValueFactory<Pracownik, String>("stanowisko"));

        // imie
        TableColumn<Pracownik, String> nameColumn = new TableColumn<Pracownik, String>("Imie");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Pracownik, String>("imie"));

        // nazwisko
        TableColumn<Pracownik, String> surnameColumn = new TableColumn<Pracownik, String>("Nazwisko");
        surnameColumn.setMinWidth(100);
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Pracownik, String>("nazwisko"));

        // data urodzenia
        TableColumn<Pracownik, Date> birthDateColumn = new TableColumn<Pracownik, Date>("Data urodzenia");
        birthDateColumn.setMinWidth(100);
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<Pracownik, Date>("data_urodzenia"));

        // pesel
        TableColumn<Pracownik, String> peselColumn = new TableColumn<Pracownik, String>("PESEL");
        peselColumn.setMinWidth(100);
        peselColumn.setCellValueFactory(new PropertyValueFactory<Pracownik, String>("pesel"));

        TableColumn<Pracownik, String> phoneColumn = new TableColumn<Pracownik, String>("Telefon");
        phoneColumn.setMinWidth(100);
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Pracownik, String>("telefon"));


        table = new TableView<Pracownik>();
        table.setItems(WindowSingleton.getEmployeeObservableList());
        table.getColumns().addAll(idColumn, nameColumn, surnameColumn, birthDateColumn, addressColumn, phoneColumn, stanowiskoColumn, peselColumn);

        return table;
    }

    public static TableView createRentalPointTable() {
        final TableView<Punkt_Wypozyczen> table;
//        final Button select = new Button("Select");
//        String choice = "";

        // id
        TableColumn<Punkt_Wypozyczen, Long> idColumn = new TableColumn<Punkt_Wypozyczen, Long>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<Punkt_Wypozyczen, Long>("id_punktu"));

        // lokalizacja
        TableColumn<Punkt_Wypozyczen, String> locationColumn = new TableColumn<Punkt_Wypozyczen, String>("Lokalizacja");
        locationColumn.setMinWidth(100);
        locationColumn.setCellValueFactory(new PropertyValueFactory<Punkt_Wypozyczen, String>("lokalizacja"));

        // nazwa
        TableColumn<Punkt_Wypozyczen, String> nameColumn = new TableColumn<Punkt_Wypozyczen, String>("Nazwa");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Punkt_Wypozyczen, String>("nazwa_punktu"));

        table = new TableView<Punkt_Wypozyczen>();
        table.setItems(WindowSingleton.getRentalPointObservableList());
        table.getColumns().addAll(idColumn, nameColumn, locationColumn);

        return table;
    }

    private static ObservableList<Klient> getClientObservableList() {
        ObservableList<Klient> clients = FXCollections.observableArrayList();

        // todo FROM Klient a???
        List<Klient> list = DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Klient a", Klient.class).getResultList();

        for (Klient klient : list) {
            clients.add(klient);
        }
        return clients;
    }

    private static ObservableList<Pracownik> getEmployeeObservableList() {
        ObservableList<Pracownik> employee = FXCollections.observableArrayList();

        // todo FROM Pracownik a???
        List<Pracownik> list = DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Pracownik a", Pracownik.class).getResultList();

        for (Pracownik pracownik : list) {
            employee.add(pracownik);
        }
        return employee;
    }

    private static ObservableList<Punkt_Wypozyczen> getRentalPointObservableList() {
        ObservableList<Punkt_Wypozyczen> rental_points = FXCollections.observableArrayList();

        // todo FROM Klient a???
        List<Punkt_Wypozyczen> list = DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Punkt_Wypozyczen a", Punkt_Wypozyczen.class).getResultList();

        for (Punkt_Wypozyczen punkt_wypozyczen : list) {
            rental_points.add(punkt_wypozyczen);
        }
        return rental_points;
    }



}
