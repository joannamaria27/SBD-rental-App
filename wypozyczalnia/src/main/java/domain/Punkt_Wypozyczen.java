package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Punkt_Wypozyczen {

    @Id
    @GeneratedValue
    private Long id_punktu;

    private String lokalizacja;
    private String nazwa;

    public Long getId_punktu() {
        return id_punktu;
    }

    public void setId_punktu(Long id_punktu) {
        this.id_punktu = id_punktu;
    }

    public String getLokalizacja() {
        return lokalizacja;
    }

    public void setLokalizacja(String lokalizacja) {
        this.lokalizacja = lokalizacja;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
