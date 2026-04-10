package AppCompagnieAeriennePOO.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pilote extends Employe {
    private String licence;
    private int heuresDeVol;
    private List<Vol> vols;

    public Pilote(String nom, String adresse, String contact, int numeroEmploye, LocalDate dateEmbauche, String qualification, String licence, int heuresDeVol) {
        super(nom, adresse, contact, numeroEmploye, dateEmbauche);
        this.licence = licence;
        this.heuresDeVol = heuresDeVol;
        this.vols = new ArrayList<>();
    }

    public String getLicence() { return licence;}
    public int getHeuresDeVol() { return heuresDeVol;}
    public List<Vol> obtenirVol() { return vols;}

    public void setLicence(String licence) { this.licence = licence;}
    public void setHeuresDeVol(int heuresDeVol) { this.heuresDeVol = heuresDeVol;}
    public void setVols(List<Vol> vols) { this.vols = vols;}

    public void affecterVol(Vol vol) {
        vols.add(vol);
    }

    @Override
    public String obtenirRole() {
        return "Rôle : Pilote";
    }

}
