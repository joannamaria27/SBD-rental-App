package domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Wypozyczenie {

    @Id
    @GeneratedValue
    private long id_wypozyczenia;

    @OneToOne
    private Pojazd id_pojazdu;
    private Date data_wypozyczenia;
    private String kod_dostepu;;
    private String stan_pojazdu;
    @OneToOne
    private Klient id_klienta;
    private float kaucja;
    @OneToOne
    private Pracownik id_pracownika;
    
        public Wypozyczenie( Pojazd id_pojazdu, Date data_wypozyczenia, String kod_dostepu, String stan_pojazdu, Klient id_klienta, float kaucja, Pracownik id_pracownika) {
//        this.id_wypozyczenia = id_wypozyczenia;
        this.id_pojazdu = id_pojazdu;
        this.data_wypozyczenia = data_wypozyczenia;
        this.kod_dostepu = kod_dostepu;
        this.stan_pojazdu = stan_pojazdu;
        this.id_klienta = id_klienta;
        this.kaucja = kaucja;
        this.id_pracownika = id_pracownika;
    }


    public long getId_wypozyczenia() {
        return id_wypozyczenia;
    }

    public void setId_wypozyczenia(long id_wypozyczenia) {
        this.id_wypozyczenia = id_wypozyczenia;
    }

    public Pojazd getId_pojazdu() {
        return id_pojazdu;
    }

    public void setId_pojazdu(Pojazd id_pojazdu) {
        this.id_pojazdu = id_pojazdu;
    }

    public Date getData_wypozyczenia() {
        return data_wypozyczenia;
    }

    public void setData_wypozyczenia(Date data_wypozyczenia) {
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

    public Klient getId_klienta() {
        return id_klienta;
    }

    public void setId_klienta(Klient id_klienta) {
        this.id_klienta = id_klienta;
    }

    public float getKaucja() {
        return kaucja;
    }

    public void setKaucja(float kaucja) {
        this.kaucja = kaucja;
    }

    public Pracownik getId_pracownika() {
        return id_pracownika;
    }

    public void setId_pracownika(Pracownik id_pracownika) {
        this.id_pracownika = id_pracownika;
    }
}
