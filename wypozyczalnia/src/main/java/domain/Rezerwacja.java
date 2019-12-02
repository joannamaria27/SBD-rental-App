package domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Rezerwacja {

    @Id
    @GeneratedValue
    private long id_rezerwacji;
    @OneToOne
    @JoinColumn(name="id_pojazdu_id_pojazdu")
    private Pojazd id_pojazdu;

    @OneToOne
    @JoinColumn(name="id_klienta_id_klienta")
    private Klient id_klienta;
    private Date data_r_rezerwacji;
    private Date data_z_rezerwacji;
    private float przewidywana_cena;
    @OneToOne
    @JoinColumn(name="id_pracownika_id_pracownika")
    private Pracownik id_pracownika;

        public Rezerwacja( Pojazd id_pojazdu, Klient id_klienta, Date data_r_rezerwacji, Date data_z_rezerwacji, float przewidywana_cena, Pracownik id_pracownika) {
//        this.id_rezerwacji = id_rezerwacji;
        this.id_pojazdu = id_pojazdu;
        this.id_klienta = id_klienta;
        this.data_r_rezerwacji = data_r_rezerwacji;
        this.data_z_rezerwacji = data_z_rezerwacji;
        this.przewidywana_cena = przewidywana_cena;
        this.id_pracownika = id_pracownika;
    }

    public Rezerwacja(){

    }
    
    public long getId_rezerwacji() {
        return id_rezerwacji;
    }

    public void setId_rezerwacji(long id_rezerwacji) {
        this.id_rezerwacji = id_rezerwacji;
    }

    public Pojazd getId_pojazdu() {
        return id_pojazdu;
    }

    public void setId_pojazdu(Pojazd id_pojazdu) {
        this.id_pojazdu = id_pojazdu;
    }

    public Klient getId_klienta() {
        return id_klienta;
    }

    public void setId_klienta(Klient id_klienta) {
        this.id_klienta = id_klienta;
    }

    public Date getData_r_rezerwacji() {
        return data_r_rezerwacji;
    }

    public void setData_r_rezerwacji(Date data_r_rezerwacji) {
        this.data_r_rezerwacji = data_r_rezerwacji;
    }

    public Date getData_z_rezerwacji() {
        return data_z_rezerwacji;
    }

    public void setData_z_rezerwacji(Date data_z_rezerwacji) {
        this.data_z_rezerwacji = data_z_rezerwacji;
    }

    public float getPrzewidywana_cena() {
        return przewidywana_cena;
    }

    public void setPrzewidywana_cena(float przewidywana_cena) {
        this.przewidywana_cena = przewidywana_cena;
    }

    public Pracownik getId_pracownika() {
        return id_pracownika;
    }

    public void setId_pracownika(Pracownik id_pracownika) {
        this.id_pracownika = id_pracownika;
    }
}
