package MiniProjet.model;

import java.time.LocalDate;

import MiniProjet.enumeration.StatutReservation;

public class Reservation {
    private String numeroReservation;
    private LocalDate dateReservation;
    private StatutReservation statut;
    private Vol vol;

    public Reservation(String numeroReservation, String statut) {
        this.numeroReservation = numeroReservation;
        this.dateReservation = LocalDate.now();
        this.statut = StatutReservation.EN_ATTENTE;
    }

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