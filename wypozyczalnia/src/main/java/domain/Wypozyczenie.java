package domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Wypozyczenie {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Pojazd pojazd;
    private String data_wypozyczenia;
    private String kod_dostepu;;
    private String stan_pojazdu;
    @OneToOne
    private Klient klient;
    private float kaucja;
    @OneToOne
    private Pracownik pracownik;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pojazd getPojazd() {
        return pojazd;
    }

    public void setPojazd(Pojazd pojazd) {
        this.pojazd = pojazd;
    }

    public String getData_wypozyczenia() {
        return data_wypozyczenia;
    }

    public void setData_wypozyczenia(String data_wypozyczenia) {
        this.data_wypozyczenia = data_wypozyczenia;
    }

    public String getKod_dostepu() {
        return kod_dostepu;
    }

    public void setKod_dostepu(String kod_dostepu) {
        this.kod_dostepu = kod_dostepu;
    }

    public String getStan_pojazdu() {
        return stan_pojazdu;
    }

    public void setStan_pojazdu(String stan_pojazdu) {
        this.stan_pojazdu = stan_pojazdu;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public float getKaucja() {
        return kaucja;
    }

    public void setKaucja(float kaucja) {
        this.kaucja = kaucja;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }
}
