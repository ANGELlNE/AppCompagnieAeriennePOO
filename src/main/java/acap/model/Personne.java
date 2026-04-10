package acap.model;

public abstract class Personne {
    private static int compteurId;

    private int identifiant;
    private String nom;
    private String adresse;
    private String contact;

    public Personne(String nom, String adresse, String contact) {
        this.identifiant = compteurId++;
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
    }

    public int getIdentifiant() { return identifiant; }
    public String getNom() { return nom; }
    public String getAdresse() { return adresse; }
    public String getContact() { return contact; }

    public String ObtenirInfos() {
        return String.format("ID : %i | Nom : %s\nAdresse : %s\nContact : %s", identifiant, nom, adresse, contact);
    }
}
