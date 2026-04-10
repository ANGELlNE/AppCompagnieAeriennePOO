package MiniProjet;

import java.time.LocalDate;

public abstract class Employe extends Personne {
    private int numeroEmploye;
    private LocalDate dateEmbauche;

    public Employe(int identifiant, String nom, String adresse, String contact) {
        super(identifiant, nom, adresse, contact);
        this.numeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
    }
    


}


