package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Ubezpieczenie {

    @Id
    @GeneratedValue
    private long id_ubezpieczenia;
    private String typ;
    private float cena;
    private Date data_waznosci;

    public long getId_ubezpieczenia() {
        return id_ubezpieczenia;
    }

    public void setId_ubezpieczenia(long id_ubezpieczenia) {
        this.id_ubezpieczenia = id_ubezpieczenia;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public Date getData_waznosci() {
        return data_waznosci;
    }

    public void setData_waznosci(Date data_waznosci) {
        this.data_waznosci = data_waznosci;
    }

    @Override
    public String toString() {
        return String.valueOf(id_ubezpieczenia);
    }
}
