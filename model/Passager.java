package MiniProjet;

import java.util.ArrayList;
import java.util.List;

public class Passager extends Personne {
    private String passeport;
    private List<Reservation> reservations;

    public Passager(String nom, String adresse, String contact, String passeport) {
        super(nom, adresse, contact);
        this.passeport = passeport;
        this.reservations = new ArrayList<>();
    }

    public void reserverVol(Vol vol) {
        this.reservations.add();
    }

    public void annulerReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public List<Reservation> obtenirReservations() {
        return reservations;
    }
}
