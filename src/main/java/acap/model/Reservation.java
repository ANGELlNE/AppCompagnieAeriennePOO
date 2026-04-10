package acap.model;

import java.time.LocalDate;

import acap.enumeration.StatutReservation;

public class Reservation {
    private String numeroReservation;
    private LocalDate dateReservation;
    private StatutReservation statut;

    private Passager passager;
    private Vol vol;

    public Reservation(String numeroReservation, String statut) {
        this.numeroReservation = numeroReservation;
        this.dateReservation = LocalDate.now();
        this.statut = StatutReservation.EN_ATTENTE;
    }

    public String getNumeroReservation() { return numeroReservation;}
    public LocalDate getDateReservation() { return dateReservation;}
    public StatutReservation getStatut() { return statut;}
    public Passager getPassager() { return passager;}
    public Vol getVol() { return vol;}

    public void setNumeroReservation(String numeroReservation) { this.numeroReservation = numeroReservation;}
    public void setDateReservation(LocalDate dateReservation) { this.dateReservation = dateReservation;}
    public void setStatut(StatutReservation statut) { this.statut = statut;}
    public void setPassager(Passager passager) { this.passager = passager;}
    public void setVol(Vol vol) { this.vol = vol;}

    public void confirmerReservation() {
        statut = StatutReservation.CONFIRME;
    }

    public void annulerReservation() {
        statut = StatutReservation.ANNULE;
    }

    public void modifierReservation(Vol nouveauVol) {
        this.vol = nouveauVol;
    }
}
