package controleur;

public class Appartement {
    private int idAppartement;
    private String typeDappartementA;
    private String rueA;
    private String codePostalA;
    private String villeA;
    private String nImmeubleA;
    private String surfaceA;
    private double prixJournalier;
    private int idProprietaire;
    private int idRegion;

    public Appartement(int idAppartement, String typeDappartementA, String rueA, String codePostalA, String villeA, String nImmeubleA, String surfaceA, double prixJournalier, int idProprietaire, int idRegion) {
        this.idAppartement = idAppartement;
        this.typeDappartementA = typeDappartementA;
        this.rueA = rueA;
        this.codePostalA = codePostalA;
        this.villeA = villeA;
        this.nImmeubleA = nImmeubleA;
        this.surfaceA = surfaceA;
        this.prixJournalier = prixJournalier;
        this.idProprietaire = idProprietaire;
        this.idRegion = idRegion;
    }

    public Appartement( String typeDappartementA, String rueA, String codePostalA, String villeA, String nImmeubleA, String surface, double prixJournalier, int idProprietaire, int idRegion) {
        this.idAppartement = 0;
        this.typeDappartementA = typeDappartementA;
        this.rueA = rueA;
        this.codePostalA = codePostalA;
        this.villeA = villeA;
        this.nImmeubleA = nImmeubleA;
        this.surfaceA = surface;
        this.prixJournalier = prixJournalier;
        this.idProprietaire = idProprietaire;
        this.idRegion = idRegion;
    }

    
    public int getIdAppartement() {
        return idAppartement;
    }

    public void setIdAppartement(int idAppartement) {
        this.idAppartement = idAppartement;
    }

    public String getTypeDappartementA() {
        return typeDappartementA;
    }

    public void setTypeDappartementA(String typeDappartementA) {
        this.typeDappartementA = typeDappartementA;
    }

    public String getRueA() {
        return rueA;
    }

    public void setRueA(String rueA) {
        this.rueA = rueA;
    }

    public String getCodePostalA() {
        return codePostalA;
    }

    public void setCodePostalA(String codePostalA) {
        this.codePostalA = codePostalA;
    }

    public String getVilleA() {
        return villeA;
    }

    public void setVilleA(String villeA) {
        this.villeA = villeA;
    }

    public String getNImmeubleA() {
        return nImmeubleA;
    }

    public void setNImmeubleA(String nImmeubleA) {
        this.nImmeubleA = nImmeubleA;
    }

    public String getSurfaceA() {
        return surfaceA;
    }

    public void setSurfaceA(String surfaceA) {
        this.surfaceA = surfaceA;
    }

    public double getPrixJournalier() {
        return prixJournalier;
    }

    public void setPrixJournalier(double prixJournalier) {
        this.prixJournalier = prixJournalier;
    }

    public int getIdProprietaire() {
        return idProprietaire;
    }

    public void setIdProprietaire(int idProprietaire) {
        this.idProprietaire = idProprietaire;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }
}