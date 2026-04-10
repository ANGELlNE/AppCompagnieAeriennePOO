package AppCompagnieAeriennePOO.model;

import java.util.ArrayList;
import java.util.List;

public class Aeroport {
    private String nom;
    private String ville;
    private String description;

    public Aeroport(String nom, String ville, String description) {
        this.nom = nom;
        this.ville = ville;
        this.description = description;
    }

    public String getNom() { return nom;}
    public String getVille() { return ville;}
    public String getDescription() { return description;}

    public void setNom(String nom) { this.nom = nom;}
    public void setVille(String ville) { this.ville = ville;}
    public void setDescription(String description) { this.description = description;}

    public void affecterVol(Vol vol) {
        System.out.println("Vol affecté à l'aéroport : " + nom);
    }

}
