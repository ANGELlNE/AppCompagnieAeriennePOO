package MiniProjet;

import java.time.LocalDate;

import enumeration.StatutReservation;

public class Reservation {
    private String numeroReservation;
    private String dateReservation;
    private String statut;
    private Vol vol;

    public Reservation(String numeroReservation, String statut) {
        this.numeroReservation = numeroReservation;
        this.dateReservation = LocalDate.now();
        this.statut = StatutReservation.EN_ATTENTE;
    }

    public void confirmerReservation() {
        statut = "Confirmée";
    }

    public void annulerReservation() {
        statut = "Annulée";
    }

    public void modifierReservation(String nouvelleDate) {
        this.dateReservation = nouvelleDate;
    }
}