package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Pracownik{

    @Id
    @GeneratedValue
    private long id_pracownika;
    private String nazwisko;
    private String imie;
    private Date data_urodzenia;
    private String adres;
    private String pesel;
    private String telefon;
    private String stanowisko;
    
     public Pracownik() {}

    public Pracownik( String nazwisko, String imie, Date data_urodzenia, String adres, String pesel, String telefon, String stanowisko) {
        this.id_pracownika = id_pracownika;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.data_urodzenia = data_urodzenia;
        this.adres = adres;
        this.pesel = pesel;
        this.telefon = telefon;
        this.stanowisko = stanowisko;
    }

    public long getId_pracownika() {
        return id_pracownika;
    }

    public void setId_pracownika(long id_pracownika) {
        this.id_pracownika = id_pracownika;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public Date getData_urodzenia() {
        return data_urodzenia;
    }

    public void setData_urodzenia(Date data_urodzenia) {
        this.data_urodzenia = data_urodzenia;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    @Override
    public String toString() {
        return String.valueOf(id_pracownika);
    }
}
