package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Rezerwacja {

    @Id
    @GeneratedValue
    private Long id_rezerwacji;
    @OneToOne
    private Pojazd pojazd;
    @OneToOne
    private Klient klient;
    private String data_rozp_rez;
    private String data_z_rezerwacji;
    private float przewidywana_cena;
    @OneToOne
    private Pracownik pracownik_dod_rez;

    public Long getId_rezerwacji() {
        return id_rezerwacji;
    }

    public void setId_rezerwacji(Long id_rezerwacji) {
        this.id_rezerwacji = id_rezerwacji;
    }

    public Pojazd getPojazd() {
        return pojazd;
    }

    public void setPojazd(Pojazd pojazd) {
        this.pojazd = pojazd;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public String getData_rozp_rez() {
        return data_rozp_rez;
    }

    public void setData_rozp_rez(String data_rozp_rez) {
        this.data_rozp_rez = data_rozp_rez;
    }

    public String getData_z_rezerwacji() {
        return data_z_rezerwacji;
    }

    public void setData_z_rezerwacji(String data_z_rezerwacji) {
        this.data_z_rezerwacji = data_z_rezerwacji;
    }

    public float getPrzewidywana_cena() {
        return przewidywana_cena;
    }

    public void setPrzewidywana_cena(float przewidywana_cena) {
        this.przewidywana_cena = przewidywana_cena;
    }

    public Pracownik getPracownik_dod_rez() {
        return pracownik_dod_rez;
    }

    public void setPracownik_dod_rez(Pracownik pracownik_dod_rez) {
        this.pracownik_dod_rez = pracownik_dod_rez;
    }
}
