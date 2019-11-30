package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cennik {

    @Id
    @GeneratedValue
    private Long id_ceny;

    @OneToOne
    private Pojazd pojazd;

    private Float cena;

    public Long getId_ceny() {
        return id_ceny;
    }

    public void setId_ceny(Long id_ceny) {
        this.id_ceny = id_ceny;
    }

    public Pojazd getPojazd() {
        return pojazd;
    }

    public void setPojazd(Pojazd pojazd) {
        this.pojazd = pojazd;
    }

    public Float getCena() {
        return cena;
    }

    public void setCena(Float cena) {
        this.cena = cena;
    }
}
