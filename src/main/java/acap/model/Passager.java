package acap.model;

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

    public void reserverVol(Reservation reservation) {
        this.reservations.add(reservation);
    }

    public void annulerReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public List<Reservation> obtenirReservations() { return this.reservations; }
    public String getPassport() { return this.passeport; }

    public void setReservations(List<Reservation> reservations) { this.reservations = reservations; }
    public void setPassport(String passport) { this.passeport = passport; }
}
