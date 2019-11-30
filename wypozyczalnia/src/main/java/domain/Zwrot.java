package domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Zwrot {

    private Long id_zwrotu;
    @OneToOne
    private Wypozyczenie id_wypozyczenia;
    private String data_zwrotu;
    private String stan_pojazdu;
    private String cena_ostateczna;
    @OneToOne
    private Pracownik pracownik;
    @OneToOne
    private Platnosc platnosc;

    public Long getId_zwrotu() {
        return id_zwrotu;
    }

    public void setId_zwrotu(Long id_zwrotu) {
        this.id_zwrotu = id_zwrotu;
    }

    public Wypozyczenie getId_wypozyczenia() {
        return id_wypozyczenia;
    }

    public void setId_wypozyczenia(Wypozyczenie id_wypozyczenia) {
        this.id_wypozyczenia = id_wypozyczenia;
    }

    public String getData_zwrotu() {
        return data_zwrotu;
    }

    public void setData_zwrotu(String data_zwrotu) {
        this.data_zwrotu = data_zwrotu;
    }

    public String getStan_pojazdu() {
        return stan_pojazdu;
    }

    public void setStan_pojazdu(String stan_pojazdu) {
        this.stan_pojazdu = stan_pojazdu;
    }

    public String getCena_ostateczna() {
        return cena_ostateczna;
    }

    public void setCena_ostateczna(String cena_ostateczna) {
        this.cena_ostateczna = cena_ostateczna;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }

    public Platnosc getPlatnosc() {
        return platnosc;
    }

    public void setPlatnosc(Platnosc platnosc) {
        this.platnosc = platnosc;
    }
}
