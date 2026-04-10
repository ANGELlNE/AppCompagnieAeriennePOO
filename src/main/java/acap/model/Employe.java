package acap.model;

import java.time.LocalDate;

public abstract class Employe extends Personne {
    private int numeroEmploye;
    private LocalDate dateEmbauche;

    public Employe(String nom, String adresse, String contact, int numeroEmploye, LocalDate dateEmbauche) {
        super(nom, adresse, contact);
        this.numeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
    }

    public int getNumeroEmploye() { return this.numeroEmploye;}
    public LocalDate getDateEmbauche() { return this.dateEmbauche;}

    public void setNumeroEmploye(int numeroEmploye) { this.numeroEmploye = numeroEmploye;}
    public void setDateEmbauche(LocalDate dateEmbauche) { this.dateEmbauche = dateEmbauche;}

    public abstract String obtenirRole();

}
