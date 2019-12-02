package controller;

import domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

public class DBConnector {

    private static DBConnector instance;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private DBConnector() {
        entityManagerFactory = Persistence.createEntityManagerFactory("wypozyczalnia");
        entityManager = entityManagerFactory.createEntityManager();

//        Wypozyczenie wypozyczenie = new Wypozyczenie();
//        wypozyczenie.setData_wypozyczenia(new Date());
//        wypozyczenie.setId_klienta(1);
//        wypozyczenie.setId_pojazdu(2);
//        wypozyczenie.setKaucja(1717);
//        wypozyczenie.setStan_pojazdu("dobry");
//        wypozyczenie.setId_pracownika(3);
//        wypozyczenie.setKod_dostepu(4444);


    }

    public static DBConnector getInstance() {
        if (instance == null) instance = new DBConnector();
        return instance;
    }

    public static List<Pojazd> getAllCars() throws IOException {
        return DBConnector.getInstance().entityManager.createQuery("SELECT a FROM Pojazd a WHERE typ='Samochód'", Pojazd.class).getResultList();
    }

    public static List<Pojazd> getAllScooters() throws IOException {
        return DBConnector.getInstance().entityManager.createQuery("SELECT a FROM Pojazd a WHERE typ='Skuter'", Pojazd.class).getResultList();
    }

    public static List<Wypozyczenie> getAllRental() throws IOException {
        return DBConnector.getInstance().entityManager.createQuery("SELECT a FROM Wypozyczenie a", Wypozyczenie.class).getResultList();
    }

    public static List<Pojazd> getAllBikes() throws IOException {
        return DBConnector.getInstance().entityManager.createQuery("SELECT a FROM Pojazd a WHERE typ='Rower'", Pojazd.class).getResultList();
    }

    public static List<Klient> getAllClients() throws IOException {
        return DBConnector.getInstance().entityManager.createQuery("SELECT a FROM Klient a", Klient.class).getResultList();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void start() {
        //entityManagerFactory = Persistence.createEntityManagerFactory("wypozyczalnia");
        //entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    public void addWypozyczenie(Wypozyczenie w) {
        entityManager.getTransaction().begin();
        entityManager.persist(w);
        entityManager.getTransaction().commit();
    }

    public List<Wypozyczenie> printAllRentals() {
        List<Wypozyczenie> list = entityManager.createQuery("SELECT a FROM Wypozyczenie a", Wypozyczenie.class).getResultList();
        for (Wypozyczenie wypozyczenie : list) {
            System.out.println(wypozyczenie.getId_wypozyczenia());
        }
        return list;
    }

    public void addKlient(Klient k) {
        //entityManager.getTransaction().begin();
        entityManager.persist(k);
        //entityManager.getTransaction().commit();
    }

    public void addPojazd(Pojazd p) {
        entityManager.getTransaction().begin();
        entityManager.persist(p);
        entityManager.getTransaction().commit();
    }

    public void addRentalPoint(Punkt_Wypozyczen p) {
        entityManager.getTransaction().begin();
        entityManager.persist(p);
        entityManager.getTransaction().commit();
    }

    public void addInsurance(Ubezpieczenie u){
        entityManager.getTransaction().begin();
        entityManager.persist(u);
        entityManager.getTransaction().commit();
    }

    public void addService(Serwis s){
        entityManager.getTransaction().begin();
        entityManager.persist(s);
        entityManager.getTransaction().commit();
    }

    public void deletePojazd(Pojazd p) {
        entityManager.remove(p);
    }

    public void deleteKlient(Klient k) {
        entityManager.remove(k);
    }

    public void deleteWypozyczenie(Wypozyczenie w) {
        entityManager.remove(w);
    }

//    public void deletePracownik(Pracownik p) {
//        entityManager.remove(p);
//    }

    public void deleteRentalPoint(Punkt_Wypozyczen p) {
        entityManager.getTransaction().begin();
        entityManager.remove(p);
        entityManager.getTransaction().commit();
    }

    public void deleteUbezpieczenie(Ubezpieczenie u){
        entityManager.getTransaction().begin();
        entityManager.remove(u);
        entityManager.getTransaction().commit();
    }
    // nie wiem czy działa

    public void editPojazd(Pojazd p) {
        entityManager.getTransaction().begin();
        entityManager.merge(p);
        entityManager.getTransaction().commit();
    }

    public void editKlient(Klient k) {
        entityManager.getTransaction().begin();
        entityManager.merge(k);
        entityManager.getTransaction().commit();
    }

    public void editWypozyczenie(Wypozyczenie w) {
        entityManager.getTransaction().begin();
        entityManager.merge(w);
        entityManager.getTransaction().commit();
    }

    public void editUbezpieczenie(Ubezpieczenie u){
        entityManager.getTransaction().begin();
        entityManager.merge(u);
        entityManager.getTransaction().commit();
    }

    public void editPunktWypozyczen(Punkt_Wypozyczen p) {
        entityManager.getTransaction().begin();
        entityManager.merge(p);
        entityManager.getTransaction().commit();
    }

    public void stop() {
        entityManager.getTransaction().commit();
    }

    public void stopdb() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public void addPracownik(Pracownik pracownik) {
        entityManager.persist(pracownik);
    }

    public void deletePracownik(Pracownik p) {
        entityManager.remove(p);
    }
    public void editPracownik(Pracownik p) {
        entityManager.getTransaction().begin();
        entityManager.merge(p);
        entityManager.getTransaction().commit();
    }

        public void addRezerwacja(Rezerwacja pracownik) {
        entityManager.persist(pracownik);
    }

    public void deleteRezerwacja(Rezerwacja p) {
        entityManager.remove(p);
    }

    public void editRezerwacja(Rezerwacja p) {
        entityManager.getTransaction().begin();
        entityManager.merge(p);
        entityManager.getTransaction().commit();
    }
    public void addZwrot(Zwrot z) {
        entityManager.persist(z);
    }

    public void deleteZwrot(Zwrot p) {
        entityManager.remove(p);
    }

    public void editZwrot(Zwrot p) {
        entityManager.getTransaction().begin();
        entityManager.merge(p);
        entityManager.getTransaction().commit();
    }
}
