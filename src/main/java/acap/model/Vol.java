package acap.model;

import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import acap.enumeration.EtatVol;

public class Vol {
    private String numeroVol;
    private Aeroport origine;
    private Aeroport destination;
    private LocalDateTime dateHeureDepart;
    private LocalDateTime dateHeureArrivee;
    private EtatVol etat;

    private Avion avion;
    private List<Pilote> pilotes;
    private List<Passager> passagers;
    private List<PersonnelCabine> equipeCabine;

    public Vol(String numeroVol, Aeroport origine, Aeroport destination, LocalDateTime dateHeureDepart, LocalDateTime dateHeureArrivee, EtatVol etat) {
        this.numeroVol = numeroVol;
        this.origine = origine;
        this.destination = destination;
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        this.etat = etat;
        this.pilotes = new ArrayList<>();
        this.equipeCabine = new ArrayList<>();
        this.passagers = new ArrayList<>();
    }

    public String getNumeroVol() { return numeroVol; }
    public Aeroport getOrigine() { return origine; }
    public Aeroport getDestination() { return destination; }
    public LocalDateTime getDateHeureDepart() { return dateHeureDepart; }
    public LocalDateTime getDateHeureArrivee() { return dateHeureArrivee; }
    public EtatVol getEtat() { return etat; }
    public Avion getAvion() { return avion; }
    public List<Pilote> getPilotes() { return pilotes; }
    public List<PersonnelCabine> getEquipeCabine() { return equipeCabine; }
    public List<Passager> getPassagers() { return passagers; }

    public void setNumeroVol(String numeroVol) { this.numeroVol = numeroVol; }
    public void setOrigine(Aeroport origine) { this.origine = origine; }
    public void setDestination(Aeroport destination) { this.destination = destination;}
    public void setDateHeureDepart(LocalDateTime dateHeureDepart) { this.dateHeureDepart = dateHeureDepart; }
    public void setDateHeureArrivee(LocalDateTime dateHeureArrivee) { this.dateHeureArrivee = dateHeureArrivee; }
    public void setEtat(EtatVol etat) { this.etat = etat; }
    public void setAvion(Avion avion) { this.avion = avion; }
    public void setPilotes(List<Pilote> pilotes) { this.pilotes = pilotes; }
    public void setEquipeCabine(List<PersonnelCabine> equipeCabine) { this.equipeCabine = equipeCabine; }
    public void setPassagers(List<Passager> passagers) { this.passagers = passagers; }

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

    public List<Passager> listerPassagers() { return passagers; }

    public void ajouterPassager(Passager passager) {
        if (passager != null) {
            passagers.add(passager);
        }
    }

    public String obtenirGraph(LocalDateTime currentTime) {
        String nomOrigine = this.origine.getNom();
        String nomDestination = this.destination.getNom();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        String heureDepart = dateHeureDepart.format(timeFormat);
        String heureArrivee = dateHeureArrivee.format(timeFormat);
        if (this.etat == EtatVol.PLANIFIE) {
            return String.format("%-7s %s (%s) %46s (%s) %s", numeroVol, nomOrigine, heureDepart, "", heureArrivee, nomDestination);
        } else if (this.etat == EtatVol.EN_COURS) {
            Duration dureeVol = Duration.between(dateHeureDepart, dateHeureArrivee);
            Duration dureeEcoule = Duration.between(dateHeureDepart, currentTime);
            int pctProgression = Math.round(dureeEcoule.getSeconds() * 100 / dureeVol.getSeconds());
            if (pctProgression < 0) pctProgression = 0;
            if (pctProgression == 100) {
                this.etat = EtatVol.TERMINE;
                return String.format("%-7s %s (%s) %sX (%s) %s", numeroVol, nomOrigine, heureDepart, "-".repeat(45), heureArrivee, nomDestination);
            }
            int nTirets = Math.round(45 * pctProgression / 100);
            return String.format("%-7s %s (%s) %s>%s (%s) %s", numeroVol, nomOrigine, heureDepart, "-".repeat(nTirets), " ".repeat(45-nTirets), heureArrivee, nomDestination);
        } else if (this.etat == EtatVol.RETARDE) {
            return String.format("\033[33m%-7s %s (%s) [RETARDÉ] %36s (%s) %s\033[0m", numeroVol, nomOrigine, heureDepart, "", heureArrivee, nomDestination);
        } else if (this.etat == EtatVol.ANNULE) {
            return String.format("\033[31m%-7s %s (%s) [ANNULÉ] %37s (%s) %s\033[0m",numeroVol, nomOrigine, heureDepart, "", heureArrivee, nomDestination);
        } else if (this.etat == EtatVol.TERMINE) {
            return String.format("\033[32m%-7s %s (%s) [TERMINÉ] %36s (%s) %s\033[0m",numeroVol, nomOrigine, heureDepart, "", heureArrivee, nomDestination);
        } else if (this.etat == EtatVol.CRASHED) {
            return String.format("\033[35m%-7s %s (%s) [CRASHED] %36s (%s) %s\033[0m",numeroVol, nomOrigine, heureDepart, "", heureArrivee, nomDestination);
        } else {
            return String.format("%23sAffichage de l'état non supporté%23s", "", "");
        }
    }
}
