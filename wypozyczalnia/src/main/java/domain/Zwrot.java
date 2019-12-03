package domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Zwrot {

    @Id
    @GeneratedValue
    private long id_zwrotu;
    @OneToOne
    @JoinColumn(name="id_wypozyczenia_id_wypozyczenia")
    private Wypozyczenie id_wypozyczenia;
    private Date data_zwrotu;
    private String stan_pojazdu;
    private float cena_ostateczna;
    @OneToOne
    @JoinColumn(name="id_pracownika_id_pracownika")
    private Pracownik id_pracownika;
    @OneToOne
    @JoinColumn(name="id_platnosci_id_platnosci")
    private Platnosc id_platnosci;

     public Zwrot(Wypozyczenie id_wypozyczenia, Date data_zwrotu, String stan_pojazdu, float cena_ostateczna, Pracownik id_pracownika, Platnosc id_platnosci) {
//        this.id_zwrotu = id_zwrotu;
        this.id_wypozyczenia = id_wypozyczenia;
        this.data_zwrotu = data_zwrotu;
        this.stan_pojazdu = stan_pojazdu;
        this.cena_ostateczna = cena_ostateczna;
        this.id_pracownika = id_pracownika;
        this.id_platnosci = id_platnosci;
    }
    
    
    public long getId_zwrotu() {
        return id_zwrotu;
    }

    public void setId_zwrotu(long id_zwrotu) {
        this.id_zwrotu = id_zwrotu;
    }

    public Wypozyczenie getId_wypozyczenia() {
        return id_wypozyczenia;
    }

    public void setId_wypozyczenia(Wypozyczenie id_wypozyczenia) {
        this.id_wypozyczenia = id_wypozyczenia;
    }

    public Date getData_zwrotu() {
        return data_zwrotu;
    }

    public void setData_zwrotu(Date data_zwrotu) {
        this.data_zwrotu = data_zwrotu;
    }

    public String getStan_pojazdu() {
        return stan_pojazdu;
    }

    public void setStan_pojazdu(String stan_pojazdu) {
        this.stan_pojazdu = stan_pojazdu;
    }

    public float getCena_ostateczna() {
        return cena_ostateczna;
    }

    public void setCena_ostateczna(float cena_ostateczna) {
        this.cena_ostateczna = cena_ostateczna;
    }

    public Pracownik getId_pracownika() {
        return id_pracownika;
    }

    public void setId_pracownika(Pracownik id_pracownika) {
        this.id_pracownika = id_pracownika;
    }

    public Platnosc getId_platnosci() {
        return id_platnosci;
    }

    public void setId_platnosci(Platnosc id_platnosci) {
        this.id_platnosci = id_platnosci;
    }
}
