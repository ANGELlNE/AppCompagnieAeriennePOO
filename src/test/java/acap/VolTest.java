package acap;

import acap.enumeration.EtatVol;
import acap.model.Aeroport;
import acap.model.Passager;
import acap.model.Vol;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class VolTest {

    @Test
    void testPlanifierEtAnnulerVol() {
        Aeroport origine = new Aeroport("CDG", "Paris", 49.0, 2.55);
        Aeroport destination = new Aeroport("JFK", "New York", 40.6, -73.8);
        LocalDateTime depart = LocalDateTime.of(2026, 4, 24, 10, 0);
        LocalDateTime arrivee = depart.plusHours(8);

        Vol vol = new Vol("AF123", origine, destination, depart, arrivee, EtatVol.PLANIFIE);

        assertEquals(EtatVol.PLANIFIE, vol.getEtat());

        vol.annulerVol();
        assertEquals(EtatVol.ANNULE, vol.getEtat());

        vol.planifierVol();
        assertEquals(EtatVol.PLANIFIE, vol.getEtat());

        vol.modifierVol(destination, origine, depart.plusHours(1), arrivee.plusHours(1));
        assertEquals(destination, vol.getOrigine());
        assertEquals(origine, vol.getDestination());
        assertEquals(depart.plusHours(1), vol.getDateHeureDepart());
        assertEquals(arrivee.plusHours(1), vol.getDateHeureArrivee());
    }

    @Test
    void testAjouterPassagerEtListerPassagers() {
        Aeroport origine = new Aeroport("CDG", "Paris", 49.0, 2.55);
        Aeroport destination = new Aeroport("JFK", "New York", 40.6, -73.8);
        LocalDateTime depart = LocalDateTime.of(2026, 4, 24, 10, 0);
        LocalDateTime arrivee = depart.plusHours(8);
        Vol vol = new Vol("AF124", origine, destination, depart, arrivee, EtatVol.PLANIFIE);

        Passager passager = new Passager("Dupont", "Paris", "+33 1 23 45 67 89", "P123456");
        vol.ajouterPassager(passager);
        vol.ajouterPassager(null);

        assertEquals(1, vol.listerPassagers().size());
        assertSame(passager, vol.listerPassagers().get(0));
    }

    @Test
    void testObtenirGraphTermineQuandVolEstPasse() {
        Aeroport origine = new Aeroport("CDG", "Paris", 49.0, 2.55);
        Aeroport destination = new Aeroport("JFK", "New York", 40.6, -73.8);
        LocalDateTime depart = LocalDateTime.of(2026, 4, 24, 10, 0);
        LocalDateTime arrivee = depart.plusHours(8);
        Vol vol = new Vol("AF125", origine, destination, depart, arrivee, EtatVol.EN_COURS);

        String ligne = vol.obtenirGraph(arrivee.plusHours(1));

        assertEquals(EtatVol.TERMINE, vol.getEtat());
        assertTrue(ligne.contains("AF125"));
        assertTrue(ligne.contains("X"));
    }
}
