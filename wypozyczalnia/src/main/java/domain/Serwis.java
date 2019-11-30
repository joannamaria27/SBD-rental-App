package domain;

import javax.persistence.*;

@Entity
public class Serwis {

    @Id
    @GeneratedValue
    private Long id_serwisu;

    @ManyToOne
    private Pojazd pojazd;

    private String data_rozp_naprawy;
    private  String data_zak_naprawy;
    private Float cena;

    public Long getId_serwisu() {
        return id_serwisu;
    }

    public void setId_serwisu(Long id_serwisu) {
        this.id_serwisu = id_serwisu;
    }

    public Pojazd getPojazd() {
        return pojazd;
    }

    public void setPojazd(Pojazd pojazd) {
        this.pojazd = pojazd;
    }

    public String getData_rozp_naprawy() {
        return data_rozp_naprawy;
    }

    public void setData_rozp_naprawy(String data_rozp_naprawy) {
        this.data_rozp_naprawy = data_rozp_naprawy;
    }

    public String getData_zak_naprawy() {
        return data_zak_naprawy;
    }

    public void setData_zak_naprawy(String data_zak_naprawy) {
        this.data_zak_naprawy = data_zak_naprawy;
    }

    public Float getCena() {
        return cena;
    }

    public void setCena(Float cena) {
        this.cena = cena;
    }
}
