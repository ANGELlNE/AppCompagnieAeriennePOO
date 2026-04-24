package acap;

import acap.model.Avion;
import acap.model.Vol;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AvionTest {

    @Test
    void testAffecterVolLieLeVolALavion() {
        Avion avion = new Avion("F-GKXA", "Airbus A320", 6, 180, 828, 6100);
        Vol vol = new Vol("AF100", null, null, LocalDateTime.now(), LocalDateTime.now().plusHours(2), acap.enumeration.EtatVol.PLANIFIE);

        avion.affecterVol(vol);

        assertSame(avion, vol.getAvion());
        assertEquals("F-GKXA (Airbus A320)", avion.toString());
    }
}
