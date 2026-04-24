package acap;

import acap.model.Aeroport;
import acap.model.Avion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void testDistanceEntreEstSymetriqueEtPositif() {
        Aeroport a1 = new Aeroport("AAA", "VilleA", 0.0, 0.0);
        Aeroport a2 = new Aeroport("BBB", "VilleB", 0.0, 1.0);

        double d1 = Util.distanceEntre(a1, a2);
        double d2 = Util.distanceEntre(a2, a1);

        assertTrue(d1 > 0, "Distance must be positive");
        assertEquals(d1, d2, 1e-6);
    }

    @Test
    void testTempsVolBlocAjouteTrenteMinutes() {
        Aeroport depart = new Aeroport("AAA", "VilleA", 0.0, 0.0);
        Aeroport arrivee = new Aeroport("BBB", "VilleB", 0.0, 1.0);
        Avion avion = new Avion("F-TEST", "TestPlane", 4, 100, 111.0, 1000.0);

        long duree = Util.tempsVolBloc(depart, arrivee, avion);

        assertEquals(90, duree);
    }
}
