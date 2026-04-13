package acap.model;

public class Avion {
    private String immatriculation;
    private String modele;
    private int capacitePersonnel;
    private int capacitePassager;
    private double vitesseCroisiere;  // en km/h

    public Avion(String immatriculation, String modele, int capacitePersonnel, int capacitePassager, double vitesseCroisiere) {
        this.immatriculation = immatriculation;
        this.modele = modele;
        this.capacitePersonnel = capacitePersonnel;
        this.capacitePassager = capacitePassager;
        this.vitesseCroisiere = vitesseCroisiere;
    }

    public String getImmatriculation() { return this.immatriculation; }
    public String getModele() { return this.modele; }
    public int getcapacitePersonnel() { return this.capacitePersonnel; }
    public int getcapacitePassager() { return this.capacitePassager; }
    public double getVitesseCroisiere() { return this.vitesseCroisiere; }

    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }
    public void setModele(String modele) { this.modele = modele; }
    public void setcapacitePassager(int capacitePassager) { this.capacitePassager = capacitePassager; }

    public void affecterVol(Vol vol) {
        if (vol != null) {
            vol.setAvion(this);
        }
    }

    public boolean verifierDisponibilite() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", immatriculation, modele);
    }
}
