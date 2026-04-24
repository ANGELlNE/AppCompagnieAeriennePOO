package acap;

import acap.model.Pilote;
import acap.model.Vol;
import acap.enumeration.EtatVol;
import acap.model.Aeroport;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PiloteTest {

    @Test
    void testCreerPiloteEtObtenirInfos() {
        LocalDate dateEmbauche = LocalDate.of(2020, 1, 15);
        Pilote pilote = new Pilote("Durand", "Marseille", "0698765432", 1001, dateEmbauche, "CPL", "FR-2020-001", 5000);

        assertEquals("Durand", pilote.getNom());
        assertEquals("Marseille", pilote.getAdresse());
        assertEquals("0698765432", pilote.getContact());
        assertEquals(1001, pilote.getNumeroEmploye());
        assertEquals(dateEmbauche, pilote.getDateEmbauche());
        assertEquals("FR-2020-001", pilote.getLicence());
        assertEquals(5000, pilote.getHeuresDeVol());
    }

    @Test
    void testAffecterVolAuPilote() {
        LocalDate dateEmbauche = LocalDate.of(2020, 1, 15);
        Pilote pilote = new Pilote("Durand", "Marseille", "0698765432", 1001, dateEmbauche, "CPL", "FR-2020-001", 5000);

        Aeroport origine = new Aeroport("CDG", "Paris", 49.0, 2.55);
        Aeroport destination = new Aeroport("JFK", "New York", 40.6, -73.8);
        LocalDateTime depart = LocalDateTime.of(2026, 4, 24, 10, 0);
        LocalDateTime arrivee = depart.plusHours(8);
        Vol vol = new Vol("AF150", origine, destination, depart, arrivee, EtatVol.PLANIFIE);

        pilote.affecterVol(vol);

        assertEquals(1, pilote.obtenirVol().size());
        assertSame(vol, pilote.obtenirVol().get(0));
    }

    @Test
    void testModifierHeuresDeVol() {
        LocalDate dateEmbauche = LocalDate.of(2020, 1, 15);
        Pilote pilote = new Pilote("Durand", "Marseille", "0698765432", 1001, dateEmbauche, "CPL", "FR-2020-001", 5000);

        pilote.setHeuresDeVol(5500);

        assertEquals(5500, pilote.getHeuresDeVol());
    }

    @Test
    void testObtenirRole() {
        LocalDate dateEmbauche = LocalDate.of(2020, 1, 15);
        Pilote pilote = new Pilote("Durand", "Marseille", "0698765432", 1001, dateEmbauche, "CPL", "FR-2020-001", 5000);

        assertEquals("Rôle : Pilote", pilote.obtenirRole());
    }
}
