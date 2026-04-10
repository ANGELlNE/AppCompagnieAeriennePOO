package AppCompagnieAeriennePOO.model;

import java.util.ArrayList;
import java.util.List;

public class Avion {
    private String immatriculation;
    private String modele;
    private int capacite;

    public Avion(String immatriculation, String modele, int capacite) {
        this.immatriculation = immatriculation;
        this.modele = modele;
        this.capacite = capacite;
    }

    public String getImmatriculation() { return this.immatriculation; }
    public String getModele() { return this.modele; }
    public int getCapacite() { return this.capacite; }

    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation;}
    public void setModele(String modele) { this.modele = modele;}
    public void setCapacite(int capacite) { this.capacite = capacite;}


    public void affecterVol(Vol vol) {
        if (vol != null) {
            vol.setAvion(this);
        }
    }

    public boolean verifierDisponibilite() {
        // Jle fait après 
        return true;
    }
}
