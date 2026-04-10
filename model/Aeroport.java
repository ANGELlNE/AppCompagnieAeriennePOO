package MiniProjet;

import java.util.ArrayList;
import java.util.List;

public class Aeroport {
    private String nom;
    private String ville;
    private String description;

    private List<Vol> volsDepart;
    private List<Vol> volsArrivee;

    public Aeroport(String nom, String ville, String description) {
        this.nom = nom;
        this.ville = ville;
        this.description = description;
        this.volsDepart = new ArrayList<>();
        this.volsArrivee = new ArrayList<>();
    }

    public void affecterVol(Vol vol) {
        if (vol == null) {
            System.out.println("Erreur : vol nul.");
            return;
        }

        if (vol.getOrigine() == this) {
            volsDepart.add(vol);
        }

        if (vol.getDestination() == this) {
            volsArrivee.add(vol);
        }
    }

    public List<Vol> getVolsDepart() { return volsDepart;}
    public List<Vol> getVolsArrivee() { return volsArrivee;}

}
