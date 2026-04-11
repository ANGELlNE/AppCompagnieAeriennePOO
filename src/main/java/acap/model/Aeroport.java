package acap.model;

import java.util.ArrayList;
import java.util.List;

public class Aeroport {
    private String nom;
    private String ville;
    private String description;

    private List<Vol> volsDepart;
    private List<Vol> volsArrivee;

    public Aeroport(String nom, String ville) {
        this.nom = nom;
        this.ville = ville;
        this.description = "Aucun";
        this.volsDepart = new ArrayList<>();
        this.volsArrivee = new ArrayList<>();
    }

    public Aeroport(String nom, String ville, String description) {
        this.nom = nom;
        this.ville = ville;
        this.description = description;
        this.volsDepart = new ArrayList<>();
        this.volsArrivee = new ArrayList<>();
    }

    public String getNom() { return nom;}
    public String getVille() { return ville;}
    public String getDescription() { return description;}

    public void setNom(String nom) { this.nom = nom;}
    public void setVille(String ville) { this.ville = ville;}
    public void setDescription(String description) { this.description = description;}

    public void affecterVol(Vol vol) {
        if (vol.getOrigine() == this) {
            volsDepart.add(vol);
        } else if (vol.getDestination() == this) {
            volsArrivee.add(vol);
        }
        System.out.println("Vol affecté à l'aéroport : " + nom);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", nom, ville);
    }
}
