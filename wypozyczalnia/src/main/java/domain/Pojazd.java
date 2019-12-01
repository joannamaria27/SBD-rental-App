package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Pojazd {

    @Id
    @GeneratedValue
    private long id_pojazdu;
    private String typ;
    private String marka;
    private String model;
    @OneToOne
    private Ubezpieczenie id_ubezpieczenia;
    private String stan_pojazdu;
    private String czyDostepny;
    private Date termin_waz_badania;
    @OneToOne
    private Punkt_Wypozyczen punkt_postoju;

    public Pojazd(String _typ, String _marka, String _model, Ubezpieczenie _id_ubezpieczenia, String _stan_pojazdu, Date _termin_waz_badania, Punkt_Wypozyczen _punkt_postoju){
        typ = _typ;
        marka = _marka;
        model = _model;
        id_ubezpieczenia = _id_ubezpieczenia;
        stan_pojazdu = _stan_pojazdu;
        termin_waz_badania = _termin_waz_badania;
        punkt_postoju = _punkt_postoju;
        czyDostepny = "nie";
    }

    public long getId_pojazdu() {
        return id_pojazdu;
    }

    public void setId_pojazdu(long id_pojazdu) {
        this.id_pojazdu = id_pojazdu;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Ubezpieczenie getId_ubezpieczenia() {
        return id_ubezpieczenia;
    }

    public void setId_ubezpieczenia(Ubezpieczenie id_ubezpieczenia) {
        this.id_ubezpieczenia = id_ubezpieczenia;
    }

    public String getStan_pojazdu() {
        return stan_pojazdu;
    }

    public void setStan_pojazdu(String stan_pojazdu) {
        this.stan_pojazdu = stan_pojazdu;
    }

    public String isCzyDostepny() {
        return czyDostepny;
    }

    public void setCzyDostepny(String czyDostepny) {
        this.czyDostepny = czyDostepny;
    }

    public Date getTermin_waz_badania() {
        return termin_waz_badania;
    }

    public void setTermin_waz_badania(Date termin_waz_badania) {
        this.termin_waz_badania = termin_waz_badania;
    }

    public Punkt_Wypozyczen getPunkt_postoju() {
        return punkt_postoju;
    }

    public void setPunkt_postoju(Punkt_Wypozyczen punkt_postoju) {
        this.punkt_postoju = punkt_postoju;
    }
}