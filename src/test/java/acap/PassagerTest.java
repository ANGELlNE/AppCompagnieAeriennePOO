package acap;

import acap.model.Passager;
import acap.model.Reservation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassagerTest {

    @Test
    void testCreerPassagerEtObtenirDonnees() {
        Passager passager = new Passager("Martin", "Lyon", "0612345678", "P987654");

        assertEquals("Martin", passager.getNom());
        assertEquals("Lyon", passager.getAdresse());
        assertEquals("0612345678", passager.getContact());
        assertEquals("P987654", passager.getPassport());
        assertTrue(passager.obtenirReservations().isEmpty());
    }

    @Test
    void testReserverVolEtAnnulerReservation() {
        Passager passager = new Passager("Martin", "Lyon", "0612345678", "P987654");
        Reservation reservation = new Reservation("RES001", "EN_ATTENTE");

        passager.reserverVol(reservation);
        assertEquals(1, passager.obtenirReservations().size());
        assertSame(reservation, passager.obtenirReservations().get(0));

        passager.annulerReservation(reservation);
        assertTrue(passager.obtenirReservations().isEmpty());
    }

    @Test
    void testModifierPasseport() {
        Passager passager = new Passager("Martin", "Lyon", "0612345678", "P987654");
        passager.setPassport("P111111");

        assertEquals("P111111", passager.getPassport());
    }
}
