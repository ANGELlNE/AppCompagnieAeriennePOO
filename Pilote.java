package Mini_Projet;

public class Pilote extends Employe {

    private String licence;
    private int heuresDeVol;

    public Pilote(int numeroEmploye, LocalDateTime dateEmbauche, String licence, int heuresDeVol) {
        super(numeroEmploye, dateEmbauche);
        this.licence = licence;
        this.heuresDeVol = heuresDeVol;
    }

}