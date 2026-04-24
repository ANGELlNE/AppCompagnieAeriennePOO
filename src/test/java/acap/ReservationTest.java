package acap;

import acap.enumeration.StatutReservation;
import acap.model.Aeroport;
import acap.model.Reservation;
import acap.model.Vol;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    @Test
    void testConfirmerEtAnnulerReservation() {
        Reservation reservation = new Reservation("R001", "EN_ATTENTE");

        assertEquals(StatutReservation.EN_ATTENTE, reservation.getStatut());

        reservation.confirmerReservation();
        assertEquals(StatutReservation.CONFIRME, reservation.getStatut());

        reservation.annulerReservation();
        assertEquals(StatutReservation.ANNULE, reservation.getStatut());
    }

    @Test
    void testModifierReservationAvecNouveauVol() {
        Aeroport origine = new Aeroport("CDG", "Paris", 49.0, 2.55);
        Aeroport destination = new Aeroport("JFK", "New York", 40.6, -73.8);
        LocalDateTime depart = LocalDateTime.of(2026, 4, 24, 10, 0);
        LocalDateTime arrivee = depart.plusHours(8);
        Vol volInitial = new Vol("AF126", origine, destination, depart, arrivee, acap.enumeration.EtatVol.PLANIFIE);

        Vol volNouveau = new Vol("AF127", destination, origine, depart.plusDays(1), arrivee.plusDays(1), acap.enumeration.EtatVol.PLANIFIE);
        Reservation reservation = new Reservation("R002", "EN_ATTENTE");

        reservation.setVol(volInitial);
        reservation.modifierReservation(volNouveau);

        assertSame(volNouveau, reservation.getVol());
    }
}
