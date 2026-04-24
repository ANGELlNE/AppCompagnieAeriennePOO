package acap;

import acap.model.Employe;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeTest {

    @Test
    void testCreerEmployeEtObtenirInfos() {
        LocalDate dateEmbauche = LocalDate.of(2022, 3, 15);
        Employe employe = new Employe("Dubois", "Nice", "0645123456", 3001, dateEmbauche) {
            @Override
            public String obtenirRole() {
                return "Test Role";
            }
        };

        assertEquals("Dubois", employe.getNom());
        assertEquals("Nice", employe.getAdresse());
        assertEquals("0645123456", employe.getContact());
        assertEquals(3001, employe.getNumeroEmploye());
        assertEquals(dateEmbauche, employe.getDateEmbauche());
        assertEquals("Test Role", employe.obtenirRole());
    }

    @Test
    void testModifierNumeroEmployeEtDateEmbauche() {
        LocalDate dateEmbauche = LocalDate.of(2022, 3, 15);
        Employe employe = new Employe("Dubois", "Nice", "0645123456", 3001, dateEmbauche) {
            @Override
            public String obtenirRole() {
                return "Test Role";
            }
        };

        LocalDate nouvelleDate = LocalDate.of(2023, 5, 20);
        employe.setNumeroEmploye(3002);
        employe.setDateEmbauche(nouvelleDate);

        assertEquals(3002, employe.getNumeroEmploye());
        assertEquals(nouvelleDate, employe.getDateEmbauche());
    }
}
