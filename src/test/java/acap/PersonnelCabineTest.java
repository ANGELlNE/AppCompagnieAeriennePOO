package acap;

import acap.model.PersonnelCabine;
import acap.model.Vol;
import acap.enumeration.EtatVol;
import acap.model.Aeroport;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PersonnelCabineTest {

    @Test
    void testCreerPersonnelCabineEtObtenirInfos() {
        LocalDate dateEmbauche = LocalDate.of(2021, 6, 10);
        PersonnelCabine personnel = new PersonnelCabine("Bernard", "Toulouse", "0756349872", 2001, dateEmbauche, "Chef de cabine");

        assertEquals("Bernard", personnel.getNom());
        assertEquals("Toulouse", personnel.getAdresse());
        assertEquals("0756349872", personnel.getContact());
        assertEquals(2001, personnel.getNumeroEmploye());
        assertEquals(dateEmbauche, personnel.getDateEmbauche());
        assertEquals("Chef de cabine", personnel.getQualification());
    }

    @Test
    void testAffecterVolAuPersonnelCabine() {
        LocalDate dateEmbauche = LocalDate.of(2021, 6, 10);
        PersonnelCabine personnel = new PersonnelCabine("Bernard", "Toulouse", "0756349872", 2001, dateEmbauche, "Chef de cabine");

        Aeroport origine = new Aeroport("CDG", "Paris", 49.0, 2.55);
        Aeroport destination = new Aeroport("JFK", "New York", 40.6, -73.8);
        LocalDateTime depart = LocalDateTime.of(2026, 4, 24, 10, 0);
        LocalDateTime arrivee = depart.plusHours(8);
        Vol vol = new Vol("AF160", origine, destination, depart, arrivee, EtatVol.PLANIFIE);

        personnel.affecterVol(vol);

        assertEquals(1, personnel.obtenirVol().size());
        assertSame(vol, personnel.obtenirVol().get(0));
    }

    @Test
    void testAffecterVolNullNe() {
        LocalDate dateEmbauche = LocalDate.of(2021, 6, 10);
        PersonnelCabine personnel = new PersonnelCabine("Bernard", "Toulouse", "0756349872", 2001, dateEmbauche, "Chef de cabine");

        personnel.affecterVol(null);

        assertTrue(personnel.obtenirVol().isEmpty());
    }

    @Test
    void testModifierQualification() {
        LocalDate dateEmbauche = LocalDate.of(2021, 6, 10);
        PersonnelCabine personnel = new PersonnelCabine("Bernard", "Toulouse", "0756349872", 2001, dateEmbauche, "Chef de cabine");

        personnel.setQualification("Hôtesse de l'air");

        assertEquals("Hôtesse de l'air", personnel.getQualification());
    }

    @Test
    void testObtenirRole() {
        LocalDate dateEmbauche = LocalDate.of(2021, 6, 10);
        PersonnelCabine personnel = new PersonnelCabine("Bernard", "Toulouse", "0756349872", 2001, dateEmbauche, "Chef de cabine");

        assertEquals("Rôle : Personnel de Cabine", personnel.obtenirRole());
    }
}
