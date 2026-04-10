package AppCompagnieAeriennePOO.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonnelCabine extends Employe {
    private String qualification;
    private List<Vol> vols;

    public PersonnelCabine(String nom, String adresse, String contact, String numeroEmploye, LocalDate dateEmbauche, String qualification) {
        super(nom, adresse, contact, numeroEmploye, dateEmbauche);
        this.qualification = qualification;
        this.vols = new ArrayList<>();
    }

    public List<Vol> obtenirVol() { return vols;}
    public String getQualification() { return qualification;}
    public void setQualification(String qualification) { this.qualification = qualification;}
    public void setVols(List<Vol> vols) { this.vols = vols;}

    public void affecterVol(Vol vol) {
        if (vol != null) {
            vols.add(vol);
        }
    }

    @Override
    public String obtenirRole() {
        return "Rôle : Personnel de Cabine";
    }
}