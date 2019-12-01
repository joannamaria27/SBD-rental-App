package domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Serwis {

    @Id
    @GeneratedValue
    private long id_serwisu;
    @ManyToOne
    private Pojazd id_pojazdu;
    private Date data_r_naprawy;
    private Date data_z_naprawy;
    private float cena;

    public long getId_serwisu() {
        return id_serwisu;
    }

    public void setId_serwisu(long id_serwisu) {
        this.id_serwisu = id_serwisu;
    }

    public Pojazd getId_pojazdu() {
        return id_pojazdu;
    }

    public void setId_pojazdu(Pojazd id_pojazdu) {
        this.id_pojazdu = id_pojazdu;
    }

    public Date getData_r_naprawy() {
        return data_r_naprawy;
    }

    public void setData_r_naprawy(Date data_r_naprawy) {
        this.data_r_naprawy = data_r_naprawy;
    }

    public Date getData_z_naprawy() {
        return data_z_naprawy;
    }

    public void setData_z_naprawy(Date data_z_naprawy) {
        this.data_z_naprawy = data_z_naprawy;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }
}
