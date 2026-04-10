package AppCompagnieAeriennePOO.model;

import java.time.LocalDate;

public abstract class Employe extends Personne {
    private int numeroEmploye;
    private LocalDate dateEmbauche;

    public Employe(String nom, String adresse, String contact, int numeroEmploye, LocalDate dateEmbauche) {
        super(nom, adresse, contact);
        this.numeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
    }

    public String getNumeroEmploye() { return numeroEmploye;}
    public LocalDate getDateEmbauche() { return dateEmbauche;}

    public void setNumeroEmploye(String numeroEmploye) { this.numeroEmploye = numeroEmploye;}
    public void setDateEmbauche(LocalDate dateEmbauche) { this.dateEmbauche = dateEmbauche;}

    public abstract String obtenirRole();

}
