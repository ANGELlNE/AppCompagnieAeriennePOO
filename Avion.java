package MiniProjet;

import java.util.ArrayList;
import java.util.List;

public class Avion {
    private String immatriculation;
    private String modele;
    private int capacite;
    private Vol vol;
    private List<Reservation> reservations = new ArrayList<>();

    public Avion(String immatriculation, String modele, int capacite) {
        this.immatriculation = immatriculation;
        this.modele = modele;
        this.capacite = capacite;
    }

    public String getImmatriculation() { return this.immatriculation; }
    public String getModele() { return this.modele; }
    public int getCapacite() { return this.capacite; }

    public void affecterVol() {
        if (!this.verifierDisponibilite()) {
            System.out.printf("Le vol %d n'est pas disponible.\n", vol.getNumeroVol());
        }
        this.vol = vol;
    }

    public boolean verifierDisponibilite() {
        return true; 
    }
}
