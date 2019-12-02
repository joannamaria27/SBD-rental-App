package controller;

import domain.*;
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

    public static void showInsuranceTable(final TextField idField){
        final Stage window = new Stage();
        Button button = new Button("Wybierz");
        window.setTitle("Lista ubezpieczeń");


        final TableView<Ubezpieczenie> table = createInsuranceTable();
        Ubezpieczenie ubezpieczenie = table.getSelectionModel().getSelectedItem();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Ubezpieczenie selection;
                selection = table.getSelectionModel().getSelectedItem();
                //System.out.println(selection.getId());
                idField.setText(String.valueOf(selection.getId_ubezpieczenia()));
                window.close();
            }
        });

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    public static void showVehicleTable(final TextField idField) {

        final Stage window = new Stage();
        Button button = new Button("Wybierz");
        window.setTitle("Lista pojazdów");


        final TableView<Pojazd> table = createVehicleTable();
        Pojazd pojazd = table.getSelectionModel().getSelectedItem();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Pojazd selection;
                selection = table.getSelectionModel().getSelectedItem();
                //System.out.println(selection.getId());
                idField.setText(String.valueOf(selection.getId_pojazdu()));
                window.close();
            }
        });

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    public static TableView createVehicleTable() {
        final TableView<Pojazd> table;
//        final Button select = new Button("Select");
//        String choice = "";

        // id
        TableColumn<Pojazd, Long> idColumn = new TableColumn<Pojazd, Long>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<Pojazd, Long>("id_pojazdu"));

        // marka
        TableColumn<Pojazd, String> markaColumn = new TableColumn<Pojazd, String>("Marka");
        markaColumn.setMinWidth(100);
        markaColumn.setCellValueFactory(new PropertyValueFactory<Pojazd, String>("marka"));

        // model
        TableColumn<Pojazd, String> modelColumn = new TableColumn<Pojazd, String>("Model");
        modelColumn.setMinWidth(100);
        modelColumn.setCellValueFactory(new PropertyValueFactory<Pojazd, String>("model"));

        // id ubezpieczenia
        TableColumn<Pojazd, Long> idUbezpieczeniaColumn = new TableColumn<Pojazd, Long>("ID ubezpieczenia");
        idUbezpieczeniaColumn.setMinWidth(150);
        idUbezpieczeniaColumn.setCellValueFactory(new PropertyValueFactory<Pojazd, Long>("id_ubezpieczenia"));

        // stan pojazdu
        TableColumn<Pojazd, String> stanPojazduColumn = new TableColumn<Pojazd, String>("Stan pojazdu");
        stanPojazduColumn.setMinWidth(100);
        stanPojazduColumn.setCellValueFactory(new PropertyValueFactory<Pojazd, String>("stan_pojazdu"));

        // data badania
        TableColumn<Pojazd, Date> terminWaznosciBadaniaColumn = new TableColumn<Pojazd, Date>("Termin ważności badania");
        terminWaznosciBadaniaColumn.setMinWidth(200);
        terminWaznosciBadaniaColumn.setCellValueFactory(new PropertyValueFactory<Pojazd, Date>("termin_waz_badania"));

        // punkt postoju
        TableColumn<Pojazd, Long> rentalPointColumn = new TableColumn<Pojazd, Long>("Punkt wypożyczenia");
        rentalPointColumn.setMinWidth(150);
        rentalPointColumn.setCellValueFactory(new PropertyValueFactory<Pojazd, Long>("punkt_postoju"));

        // typ
        TableColumn<Pojazd, String> typeColumn = new TableColumn<Pojazd, String>("Typ");
        typeColumn.setMinWidth(100);
        typeColumn.setCellValueFactory(new PropertyValueFactory<Pojazd, String>("typ"));

        // dostepnosc
        TableColumn<Pojazd, Boolean> dostepnosColumn = new TableColumn<Pojazd, Boolean>("Czy dostępny");
        dostepnosColumn.setMinWidth(130);
        dostepnosColumn.setCellValueFactory(new PropertyValueFactory<Pojazd, Boolean>("czyDostepny"));

        table = new TableView<Pojazd>();
        table.setItems(WindowSingleton.getVehiclesObservableList());
        table.getColumns().addAll(idColumn, markaColumn, modelColumn, idUbezpieczeniaColumn, stanPojazduColumn, terminWaznosciBadaniaColumn, rentalPointColumn, dostepnosColumn, typeColumn);



        return table;
    }

    private static ObservableList<Pojazd> getVehiclesObservableList() {
        ObservableList<Pojazd> vehicles = FXCollections.observableArrayList();
        List<Pojazd> list = DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Pojazd a", Pojazd.class).getResultList();

        for (Pojazd pojazd : list) {
            vehicles.add(pojazd);
        }
        return vehicles;
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

    public static TableView createInsuranceTable() {
        final TableView<Ubezpieczenie> table;
//        final Button select = new Button("Select");
//        String choice = "";

        // id
        TableColumn<Ubezpieczenie, Long> idColumn = new TableColumn<Ubezpieczenie, Long>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<Ubezpieczenie, Long>("id_ubezpieczenia"));

        // typ
        TableColumn<Ubezpieczenie, String> typeColumn = new TableColumn<Ubezpieczenie, String>("Typ");
        typeColumn.setMinWidth(100);
        typeColumn.setCellValueFactory(new PropertyValueFactory<Ubezpieczenie, String>("typ"));

        // cena
        TableColumn<Ubezpieczenie, Float> priceColumn = new TableColumn<Ubezpieczenie, Float>("Cena");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<Ubezpieczenie, Float>("cena"));

        // data waznosci
        TableColumn<Ubezpieczenie, Date> dateColumn = new TableColumn<Ubezpieczenie, Date>("Data ważności");
        dateColumn.setMinWidth(100);
        dateColumn.setCellValueFactory(new PropertyValueFactory<Ubezpieczenie, Date>("data_waznosci"));



        table = new TableView<Ubezpieczenie>();
        table.setItems(WindowSingleton.getInsuranceObservableList());
        table.getColumns().addAll(idColumn, typeColumn, priceColumn, dateColumn);

        return table;
    }

    private static ObservableList<Klient> getClientObservableList() {
        ObservableList<Klient> clients = FXCollections.observableArrayList();

        List<Klient> list = DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Klient a", Klient.class).getResultList();

        for (Klient klient : list) {
            clients.add(klient);
        }
        return clients;
    }

    private static ObservableList<Pracownik> getEmployeeObservableList() {
        ObservableList<Pracownik> employee = FXCollections.observableArrayList();

        List<Pracownik> list = DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Klient a", Pracownik.class).getResultList();

        for (Pracownik pracownik : list) {
            employee.add(pracownik);
        }
        return employee;
    }

    private static ObservableList<Punkt_Wypozyczen> getRentalPointObservableList() {
        ObservableList<Punkt_Wypozyczen> rental_points = FXCollections.observableArrayList();

        List<Punkt_Wypozyczen> list = DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Punkt_Wypozyczen a", Punkt_Wypozyczen.class).getResultList();

        for (Punkt_Wypozyczen punkt_wypozyczen : list) {
            rental_points.add(punkt_wypozyczen);
        }
        return rental_points;
    }

    private static ObservableList<Ubezpieczenie> getInsuranceObservableList() {
        ObservableList<Ubezpieczenie> insurances = FXCollections.observableArrayList();

        List<Ubezpieczenie> list = DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Ubezpieczenie a", Ubezpieczenie.class).getResultList();

        for (Ubezpieczenie ubezpieczenie : list) {
            insurances.add(ubezpieczenie);
        }
        return insurances;
    }



}
