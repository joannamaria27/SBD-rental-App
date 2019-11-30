package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Platnosc {

    @Id
    @GeneratedValue
    private Long id;
    private String typ;
    private boolean faktura;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public boolean isFaktura() {
        return faktura;
    }

    public void setFaktura(boolean faktura) {
        this.faktura = faktura;
    }
}
