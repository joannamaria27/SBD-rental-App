package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Platnosc {

    @Id
    @GeneratedValue
    private long id_platnosci;
    private String typ;
    private String faktura;

    public String getFaktura() {
        return faktura;
    }

    public void setFaktura(String faktura) {
        this.faktura = faktura;
    }

    @Override
    public String toString() {
        return String.valueOf(id_platnosci);
    }

    public long getId_platnosci() {
        return id_platnosci;
    }

    public void setId_platnosci(long id_platnosci) {
        this.id_platnosci = id_platnosci;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

//    public boolean isFaktura() {
//        return faktura;
//    }
//
//    public void setFaktura(boolean faktura) {
//        this.faktura = faktura;
//    }
}
