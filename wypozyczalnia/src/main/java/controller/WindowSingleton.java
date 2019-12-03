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
        primaryStage.setTitle("SBD");
        primaryStage.show();
    }

    public void startApp(Stage stage) throws IOException, InterruptedException {
        primaryStage = stage;
        primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new Image("image/WindowIcon.png"));
        setLayout("/fxml/WelcomeScreen.fxml");
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

        List<Pracownik> list = DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Pracownik a", Pracownik.class).getResultList();

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



    public static void showRezervationTable(final TextField idField) {

        final Stage window = new Stage();
        Button button = new Button("Wybierz");
        window.setTitle("Lista rezerwacji");


        final TableView<Rezerwacja> table = createReservationTable();
        Rezerwacja rezerwacja = table.getSelectionModel().getSelectedItem();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Rezerwacja selection;
                selection = table.getSelectionModel().getSelectedItem();
                //System.out.println(selection.getId());
                idField.setText(String.valueOf(selection.getId_rezerwacji()));
                window.close();
            }
        });

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    public static TableView createReservationTable() {
        final TableView<Rezerwacja> table;

        // id
        TableColumn<Rezerwacja, Long> idColumn = new TableColumn<Rezerwacja, Long>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<Rezerwacja, Long>("id_rezerwacji"));

        // id_pojazdu
        TableColumn<Rezerwacja, Pojazd> pojazdColumn = new TableColumn<Rezerwacja, Pojazd>("Pojazd");
        pojazdColumn.setMinWidth(100);
        pojazdColumn.setCellValueFactory(new PropertyValueFactory<Rezerwacja, Pojazd>("id_pojazdu"));

        // id_klienta
        TableColumn<Rezerwacja, Klient> klientColumn = new TableColumn<Rezerwacja, Klient>("Klient");
        klientColumn.setMinWidth(100);
        klientColumn.setCellValueFactory(new PropertyValueFactory<Rezerwacja, Klient>("id_klienta"));

        // data_rozp
        TableColumn<Rezerwacja, Date> datarozColumn = new TableColumn<Rezerwacja, Date>("Data rozpoczęcia");
        datarozColumn.setMinWidth(180);
        datarozColumn.setCellValueFactory(new PropertyValueFactory<Rezerwacja, Date>("data_r_rezerwacji"));

        // data_zak
        TableColumn<Rezerwacja, Date> datazakColumn = new TableColumn<Rezerwacja, Date>("Data zakończenia");
        datazakColumn.setMinWidth(180);
        datazakColumn.setCellValueFactory(new PropertyValueFactory<Rezerwacja, Date>("data_z_rezerwacji"));

        // przewidywana_cena
        TableColumn<Rezerwacja, Float> cenaColumn = new TableColumn<Rezerwacja, Float>("Przewidywana cena");
        cenaColumn.setMinWidth(180);
        cenaColumn.setCellValueFactory(new PropertyValueFactory<Rezerwacja, Float>("przewidywana_cena"));

        // pracownik
        TableColumn<Rezerwacja, Pracownik> pracownikColumn = new TableColumn<Rezerwacja, Pracownik>("Pracownik");
        pracownikColumn.setMinWidth(100);
        pracownikColumn.setCellValueFactory(new PropertyValueFactory<Rezerwacja, Pracownik>("id_pracownika"));


        table = new TableView<Rezerwacja>();
        table.setItems(WindowSingleton.getReservationObservableList());
        table.getColumns().addAll(idColumn, pojazdColumn,klientColumn,datarozColumn, datazakColumn,cenaColumn,pracownikColumn);

        return table;
    }

    private static ObservableList<Rezerwacja> getReservationObservableList() {
        ObservableList<Rezerwacja> rezerwacjas = FXCollections.observableArrayList();

        // todo FROM Rezerwacje a???
        List<Rezerwacja> list = DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Rezerwacja a", Rezerwacja.class).getResultList();

        for (Rezerwacja rezerwacja : list) {
            rezerwacjas.add(rezerwacja);
        }
        return rezerwacjas;
    }

    public static void showRentTable(final TextField idField) {

        final Stage window = new Stage();
        Button button = new Button("Wybierz");
        window.setTitle("Lista wypozyczen");


        final TableView<Wypozyczenie> table = createRentTable();
        Wypozyczenie wypozyczenie = table.getSelectionModel().getSelectedItem();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Wypozyczenie selection;
                selection = table.getSelectionModel().getSelectedItem();
                //System.out.println(selection.getId());
                idField.setText(String.valueOf(selection.getId_wypozyczenia()));
                window.close();
            }
        });

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }


    public static TableView createRentTable() {
        final TableView<Wypozyczenie> table;

        // id
        TableColumn<Wypozyczenie, Long> idColumn = new TableColumn<Wypozyczenie, Long>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<Wypozyczenie, Long>("id_wypozyczenia"));

        // id_pojazdu
        TableColumn<Wypozyczenie, Pojazd> pojazdColumn = new TableColumn<Wypozyczenie, Pojazd>("Pojazd");
        pojazdColumn.setMinWidth(100);
        pojazdColumn.setCellValueFactory(new PropertyValueFactory<Wypozyczenie, Pojazd>("id_pojazdu"));

        // data_wypozyczenia
        TableColumn<Wypozyczenie, Date> dataColumn = new TableColumn<Wypozyczenie, Date>("Data wypożyczenia");
        dataColumn.setMinWidth(180);
        dataColumn.setCellValueFactory(new PropertyValueFactory<Wypozyczenie, Date>("data_wypozyczenia"));

        // kod_dostepu
        TableColumn<Wypozyczenie, String> kodColumn = new TableColumn<Wypozyczenie, String>("Kod Dostepu");
        kodColumn.setMinWidth(100);
        kodColumn.setCellValueFactory(new PropertyValueFactory<Wypozyczenie, String>("kod_dostepu"));

        // stan_pojazdu
        TableColumn<Wypozyczenie, String> stanColumn = new TableColumn<Wypozyczenie, String>("Stan Pojazdu");
        stanColumn.setMinWidth(100);
        stanColumn.setCellValueFactory(new PropertyValueFactory<Wypozyczenie, String>("stan_pojazdu"));

        // id_klienta
        TableColumn<Wypozyczenie, Klient> klientColumn = new TableColumn<Wypozyczenie, Klient>("Klient");
        klientColumn.setMinWidth(100);
        klientColumn.setCellValueFactory(new PropertyValueFactory<Wypozyczenie, Klient>("id_klienta"));

        // kaucja
        TableColumn<Wypozyczenie, Float> kaucjaColumn = new TableColumn<Wypozyczenie, Float>("Kaucja");
        kaucjaColumn.setMinWidth(100);
        kaucjaColumn.setCellValueFactory(new PropertyValueFactory<Wypozyczenie, Float>("kaucja"));

        // pracownik
        TableColumn<Wypozyczenie, Pracownik> pracownikColumn = new TableColumn<Wypozyczenie, Pracownik>("Pracownik");
        pracownikColumn.setMinWidth(100);
        pracownikColumn.setCellValueFactory(new PropertyValueFactory<Wypozyczenie, Pracownik>("id_pracownika"));


        table = new TableView<Wypozyczenie>();
        table.setItems(WindowSingleton.getRentObservableList());
        table.getColumns().addAll(idColumn, pojazdColumn,dataColumn,kodColumn,stanColumn,klientColumn,kaucjaColumn,pracownikColumn);

        return table;
    }


    private static ObservableList<Wypozyczenie> getRentObservableList() {
        ObservableList<Wypozyczenie> wypozyczenies = FXCollections.observableArrayList();

        // todo FROM Zwrot a???
        List<Wypozyczenie> list = DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Wypozyczenie a", Wypozyczenie.class).getResultList();

        for (Wypozyczenie wypozyczenie : list) {
            wypozyczenies.add(wypozyczenie);
        }
        return wypozyczenies;
    }

    public static void showReturnTable(final TextField idField) {

        final Stage window = new Stage();
        Button button = new Button("Wybierz");
        window.setTitle("Lista zwrotów");


        final TableView<Zwrot> table = createReturnTable();
        Zwrot zwrot = table.getSelectionModel().getSelectedItem();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Zwrot selection;
                selection = table.getSelectionModel().getSelectedItem();
                //System.out.println(selection.getId());
                idField.setText(String.valueOf(selection.getId_zwrotu()));
                window.close();
            }
        });

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }


    public static TableView createReturnTable() {
        final TableView<Zwrot> table;

        // id
        TableColumn<Zwrot, Long> idColumn = new TableColumn<Zwrot, Long>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<Zwrot, Long>("id_zwrotu"));

        // id_wypozyczenia
        TableColumn<Zwrot, Wypozyczenie> wypozyczenieColumn = new TableColumn<Zwrot, Wypozyczenie>("Wypożyczenie");
        wypozyczenieColumn.setMinWidth(100);
        wypozyczenieColumn.setCellValueFactory(new PropertyValueFactory<Zwrot, Wypozyczenie>("id_wypozyczenia"));

        // data_zwrotu
        TableColumn<Zwrot, Date> datazColumn = new TableColumn<Zwrot, Date>("Data zwrotu");
        datazColumn.setMinWidth(100);
        datazColumn.setCellValueFactory(new PropertyValueFactory<Zwrot, Date>("data_zwrotu"));

        // stan_pojazdu
        TableColumn<Zwrot, String > stanColumn = new TableColumn<Zwrot, String>("Stan Pojazdu");
        stanColumn.setMinWidth(100);
        stanColumn.setCellValueFactory(new PropertyValueFactory<Zwrot, String>("stan_pojazdu"));

        // data_rozp
        TableColumn<Zwrot, Date> datarozColumn = new TableColumn<Zwrot, Date>("Data rozpoczęcia");
        datarozColumn.setMinWidth(100);
        datarozColumn.setCellValueFactory(new PropertyValueFactory<Zwrot, Date>("data_r_rezerwacji"));

        // cena_ostateczna
        TableColumn<Zwrot, Float> cenaColumn = new TableColumn<Zwrot, Float>("Ostateczna cena");
        cenaColumn.setMinWidth(130);
        cenaColumn.setCellValueFactory(new PropertyValueFactory<Zwrot, Float>("cena_ostateczna"));

        // pracownik
        TableColumn<Zwrot, Pracownik> pracownikColumn = new TableColumn<Zwrot, Pracownik>("Pracownik");
        pracownikColumn.setMinWidth(100);
        pracownikColumn.setCellValueFactory(new PropertyValueFactory<Zwrot, Pracownik>("id_pracownika"));

        //id_platnosci
        TableColumn<Zwrot, Platnosc> platnoscColumn = new TableColumn<Zwrot, Platnosc>("Płatność");
        platnoscColumn.setMinWidth(100);
        platnoscColumn.setCellValueFactory(new PropertyValueFactory<Zwrot, Platnosc>("id_platnosci"));


        table = new TableView<Zwrot>();
        table.setItems(WindowSingleton.getReturnObservableList());
        table.getColumns().addAll(idColumn,wypozyczenieColumn,datazColumn,stanColumn,cenaColumn,pracownikColumn,platnoscColumn);

        return table;
    }


    private static ObservableList<Zwrot> getReturnObservableList() {
        ObservableList<Zwrot> zwrots = FXCollections.observableArrayList();

        // todo FROM Zwrot a???
        List<Zwrot> list = DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Zwrot a", Zwrot.class).getResultList();

        for (Zwrot zwrot : list) {
            zwrots.add(zwrot);
        }
        return zwrots;
    }



    public static void showServiceTable(final TextField idField) {

        final Stage window = new Stage();
        Button button = new Button("Wybierz");
        window.setTitle("Lista serwisów");


        final TableView<Serwis> table = createServiceTable();
        Serwis serwis = table.getSelectionModel().getSelectedItem();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Serwis selection;
                selection = table.getSelectionModel().getSelectedItem();
                //System.out.println(selection.getId());
                idField.setText(String.valueOf(selection.getId_serwisu()));
                window.close();
            }
        });

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }


    public static TableView createServiceTable() {
        final TableView<Serwis> table;

        // id
        TableColumn<Serwis, Long> idColumn = new TableColumn<Serwis, Long>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<Serwis, Long>("id_serwisu"));

        // id pojazdu
        TableColumn<Serwis, Long> pojazdColumn = new TableColumn<Serwis, Long>("ID pojazdu");
        pojazdColumn.setMinWidth(50);
        pojazdColumn.setCellValueFactory(new PropertyValueFactory<Serwis, Long>("id_pojazdu"));

        // data_r_naprawy
        TableColumn<Serwis, Date > cenaColumn = new TableColumn<Serwis, Date>("Data rozpoczecia naprawy");
        cenaColumn.setMinWidth(200);
        cenaColumn.setCellValueFactory(new PropertyValueFactory<Serwis, Date>("data_r_naprawy"));

        // data_z_naprawy
        TableColumn<Serwis, Date> datarColumn = new TableColumn<Serwis, Date>("Data zakonczenia naprawy");
        datarColumn.setMinWidth(200);
        datarColumn.setCellValueFactory(new PropertyValueFactory<Serwis, Date>("data_z_naprawy"));

        // cena_ostateczna
        TableColumn<Serwis, Float> datazColumn = new TableColumn<Serwis, Float>("Ostateczna cena");
        datazColumn.setMinWidth(100);
        datazColumn.setCellValueFactory(new PropertyValueFactory<Serwis, Float>("cena"));


        table = new TableView<Serwis>();
        table.setItems(WindowSingleton.getServiceObservableList());
        table.getColumns().addAll(idColumn,pojazdColumn,cenaColumn,datarColumn,datazColumn);

        return table;
    }


    private static ObservableList<Serwis> getServiceObservableList() {
        ObservableList<Serwis> serwiss = FXCollections.observableArrayList();

        List<Serwis> list = DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Serwis a", Serwis.class).getResultList();

        for (Serwis serwis : list) {
            serwiss.add(serwis);
        }
        return serwiss;
    }


    public static void showPlatnoscTable(final TextField idField) {

        final Stage window = new Stage();
        Button button = new Button("Wybierz");
        window.setTitle("Lista płatnosci");


        final TableView<Platnosc> table = createPlatnoscTable();
        Platnosc platnosc = table.getSelectionModel().getSelectedItem();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Platnosc selection;
                selection = table.getSelectionModel().getSelectedItem();
                //System.out.println(selection.getId());
                idField.setText(String.valueOf(selection.getId_platnosci()));
                window.close();
            }
        });

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }


    public static TableView createPlatnoscTable() {
        final TableView<Platnosc> table;

        // id
        TableColumn<Platnosc, Long> idColumn = new TableColumn<Platnosc, Long>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<Platnosc, Long>("id_platnosci"));

        // typ
        TableColumn<Platnosc, String > typColumn = new TableColumn<Platnosc, String>("Typ");
        typColumn.setMinWidth(100);
        typColumn.setCellValueFactory(new PropertyValueFactory<Platnosc, String>("typ"));

        // faktura
        TableColumn<Platnosc, String> fakturaColumn = new TableColumn<Platnosc, String>("Faktura");
        fakturaColumn.setMinWidth(100);
        fakturaColumn.setCellValueFactory(new PropertyValueFactory<Platnosc, String>("faktura"));

        table = new TableView<Platnosc>();
        table.setItems(WindowSingleton.getPlatnoscObservableList());
        table.getColumns().addAll(idColumn,typColumn,fakturaColumn);

        return table;
    }


    private static ObservableList<Platnosc> getPlatnoscObservableList() {
        ObservableList<Platnosc> platnoscs = FXCollections.observableArrayList();

        List<Platnosc> list = DBConnector.getInstance().getEntityManager().createQuery("SELECT a FROM Platnosc a", Platnosc.class).getResultList();

        for (Platnosc platnosc : list) {
            platnoscs.add(platnosc);
        }
        return platnoscs;
    }

}
