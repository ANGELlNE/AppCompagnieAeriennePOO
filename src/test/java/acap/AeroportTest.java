package acap;

import acap.model.Aeroport;
import acap.model.Vol;
import acap.enumeration.EtatVol;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AeroportTest {

    @Test
    void testCreerAeroportAvecCoordinates() {
        Aeroport aeroport = new Aeroport("CDG", "Paris", 49.0097, 2.5479);

        assertEquals("CDG", aeroport.getNom());
        assertEquals("Paris", aeroport.getVille());
        assertEquals(49.0097, aeroport.getLatitude());
        assertEquals(2.5479, aeroport.getLongitude());
        assertEquals("Aucun", aeroport.getDescription());
    }

    @Test
    void testCreerAeroportAvecDescription() {
        Aeroport aeroport = new Aeroport("ORY", "Paris", "Aéroport d'Orly");

        assertEquals("ORY", aeroport.getNom());
        assertEquals("Paris", aeroport.getVille());
        assertEquals("Aéroport d'Orly", aeroport.getDescription());
    }

    @Test
    void testModifierNomVilleDescription() {
        Aeroport aeroport = new Aeroport("CDG", "Paris", "Aucun");

        aeroport.setNom("CDG2");
        aeroport.setVille("Île-de-France");
        aeroport.setDescription("Charles de Gaulle");

        assertEquals("CDG2", aeroport.getNom());
        assertEquals("Île-de-France", aeroport.getVille());
        assertEquals("Charles de Gaulle", aeroport.getDescription());
    }

    @Test
    void testToString() {
        Aeroport aeroport = new Aeroport("CDG", "Paris", 49.0, 2.55);

        assertEquals("CDG (Paris)", aeroport.toString());
    }
}
