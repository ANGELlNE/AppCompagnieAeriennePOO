package MiniProjet.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Vol {
    private String numeroVol;
    private Aeroport origine;
    private Aeroport destination;
    private LocalDateTime dateHeureDepart;
    private LocalDateTime dateHeureArrivee;
    private String etat;
    private ArrayList<Passager> passagers;
    private ArrayList<PersonnelCabine> equipeCabine;

    public Vol(String numeroVol, Aeroport origine, Aeroport destination, LocalDateTime dateHeureDepart, LocalDateTime dateHeureArrivee, String etat) {
        this.numeroVol = numeroVol;
        this.origine = origine;
        this.destination = destination;
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        this.etat = etat;
    }

    public String getNumeroVol() { return this.numeroVol; }
    public Aeroport getOrigine() { return this.origine; }
    public Aeroport getDestination() { return this.destination; }
    public LocalDateTime getDateHeureDepart() { return this.dateHeureDepart; }
    public LocalDateTime getDateHeureArrivee() { return this.dateHeureArrivee; }
    public String getEtat() { return this.etat; }

    public planifierVol() {
        
    }
}