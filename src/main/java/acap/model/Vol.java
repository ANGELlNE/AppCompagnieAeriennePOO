package acap.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import acap.enumeration.EtatVol;

public class Vol {
    private String numeroVol;
    private Aeroport origine;
    private Aeroport destination;
    private LocalDateTime dateHeureDepart;
    private LocalDateTime dateHeureArrivee;
    private EtatVol etat;

    private Avion avion;
    private Pilote pilote;
    private List<Passager> passagers;
    private List<PersonnelCabine> equipeCabine;

    public Vol(String numeroVol, Aeroport origine, Aeroport destination, LocalDateTime dateHeureDepart, LocalDateTime dateHeureArrivee, EtatVol etat) {
        this.numeroVol = numeroVol;
        this.origine = origine;
        this.destination = destination;
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        this.etat = etat;
        this.equipeCabine = new ArrayList<>();
        this.passagers = new ArrayList<>();
    }

    public String getNumeroVol() { return numeroVol;}
    public Aeroport getOrigine() { return origine;}
    public Aeroport getDestination() { return destination;}
    public LocalDateTime getDateHeureDepart() { return dateHeureDepart;}
    public LocalDateTime getDateHeureArrivee() { return dateHeureArrivee;}
    public EtatVol getEtat() { return etat;}
    public Avion getAvion() { return avion;}
    public Pilote getPilote() { return pilote;}
    public List<PersonnelCabine> getEquipeCabine() { return equipeCabine;}
    public List<Passager> getPassagers() { return passagers;}

    public void setNumeroVol(String numeroVol) { this.numeroVol = numeroVol;}
    public void setOrigine(Aeroport origine) { this.origine = origine;}
    public void setDestination(Aeroport destination) { this.destination = destination;}
    public void setDateHeureDepart(LocalDateTime dateHeureDepart) { this.dateHeureDepart = dateHeureDepart;}
    public void setDateHeureArrivee(LocalDateTime dateHeureArrivee) { this.dateHeureArrivee = dateHeureArrivee;}
    public void setEtat(EtatVol etat) { this.etat = etat;}
    public void setAvion(Avion avion) { this.avion = avion;}
    public void setPilote(Pilote pilote) { this.pilote = pilote;}
    public void setEquipeCabine(List<PersonnelCabine> equipeCabine) { this.equipeCabine = equipeCabine;}
    public void setPassagers(List<Passager> passagers) { this.passagers = passagers;}

    public void planifierVol() {
        this.etat = EtatVol.PLANIFIE;
    }

    public void annulerVol() {
        this.etat = EtatVol.ANNULE;
    }

    public void modifierVol(Aeroport origine, Aeroport destination, LocalDateTime depart, LocalDateTime arrivee) {
        this.origine = origine;
        this.destination = destination;
        this.dateHeureDepart = depart;
        this.dateHeureArrivee = arrivee;
    }

    public List<Passager> listerPassagers() { return passagers;
    }

    public void ajouterPassager(Passager passager) {
        if (passager != null) {
            passagers.add(passager);
        }
    }

}
