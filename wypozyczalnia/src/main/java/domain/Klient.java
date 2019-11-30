package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Klient{

    @Id
    @GeneratedValue
    private Long id_klienta;
    private String nazwisko;
    private String imie;
    private String data_urodzenia;
    private String adres;
    private String pesel;
    private String telefon;
    private String nr_prawa_jazdy;

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

    public String getData_urodzenia() {
        return data_urodzenia;
    }

    public void setData_urodzenia(String data_urodzenia) {
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
}
