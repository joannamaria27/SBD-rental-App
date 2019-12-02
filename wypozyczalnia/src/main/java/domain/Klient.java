package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Klient{

    @Id
    @GeneratedValue
    private long id_klienta;
    private String nazwisko;
    private String imie;
    private Date data_urodzenia;
    private String adres;
    private String pesel;
    private String telefon;
    private String nr_prawa_jazdy;

    public Klient(){}

    public Klient(String nr_prawa_jazdy_, String nazwisko_, String imie_, Date data_urodzenia_, String adres_, String pesel_, String telefon_) {
        nr_prawa_jazdy = nr_prawa_jazdy_;
        nazwisko = nazwisko_;
        imie = imie_;
        data_urodzenia = data_urodzenia_;
        adres = adres_;
        pesel = pesel_;
        telefon = telefon_;
    }

    public long getId_klienta() {
        return id_klienta;
    }

    public void setId_klienta(long id_klienta) {
        this.id_klienta = id_klienta;
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

    public String getNr_prawa_jazdy() {
        return nr_prawa_jazdy;
    }

    public void setNr_prawa_jazdy(String nr_prawa_jazdy) {
        this.nr_prawa_jazdy = nr_prawa_jazdy;
    }

    @Override
    public String toString() {
        return String.valueOf(id_klienta);
    }
}
