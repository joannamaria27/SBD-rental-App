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
        datarozColumn.setMinWidth(100);
        datarozColumn.setCellValueFactory(new PropertyValueFactory<Rezerwacja, Date>("data_r_rezerwacji"));

        // data_zak
        TableColumn<Rezerwacja, Date> datazakColumn = new TableColumn<Rezerwacja, Date>("Data zakończenia");
        datazakColumn.setMinWidth(100);
        datazakColumn.setCellValueFactory(new PropertyValueFactory<Rezerwacja, Date>("data_z_rezerwacji"));

        // przewidywana_cena
        TableColumn<Rezerwacja, Float> cenaColumn = new TableColumn<Rezerwacja, Float>("Przewidywana cena");
        cenaColumn.setMinWidth(100);
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
        dataColumn.setMinWidth(100);
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
        idColumn.setCellValueFactory(new PropertyValueFactory<Zwrot, Long>("id_rezerwacji"));

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
        cenaColumn.setMinWidth(100);
        cenaColumn.setCellValueFactory(new PropertyValueFactory<Zwrot, Float>("cena_ostateczna"));

        // pracownik
        TableColumn<Zwrot, Pracownik> pracownikColumn = new TableColumn<Zwrot, Pracownik>("Pracownik");
        pracownikColumn.setMinWidth(100);
        pracownikColumn.setCellValueFactory(new PropertyValueFactory<Zwrot, Pracownik>("id_pracownika"));

        //id_platnosci
        TableColumn<Zwrot, Platnosc> platnoscColumn = new TableColumn<Zwrot, Platnosc>("Platnosc");
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

}
