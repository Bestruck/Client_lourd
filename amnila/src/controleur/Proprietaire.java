package controleur;

public class Proprietaire {
    private int idProprietaire;
    private String nomP;
    private String prenomP;
    private String adresseEmailP;
    private String passwordP;
    private String telephoneP;
    private String rib;

    public Proprietaire(int idProprietaire, String nomP, String prenomP, String adresseEmailP, String passwordP, String telephoneP, String rib) {
        this.idProprietaire = idProprietaire;
        this.nomP = nomP;
        this.prenomP = prenomP;
        this.adresseEmailP = adresseEmailP;
        this.passwordP = passwordP;
        this.telephoneP = telephoneP;
        this.rib = rib;
    }
    
    public Proprietaire(String nomP, String prenomP, String adresseEmailP, String passwordP, String telephoneP, String rib) {
        this.idProprietaire = 0;
        this.nomP = nomP;
        this.prenomP = prenomP;
        this.adresseEmailP = adresseEmailP;
        this.passwordP = passwordP;
        this.telephoneP = telephoneP;
        this.rib = rib;
    }

    // Getters and Setters
    public int getIdProprietaire() {
        return idProprietaire;
    }

    public void setIdProprietaire(int idProprietaire) {
        this.idProprietaire = idProprietaire;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public String getPrenomP() {
        return prenomP;
    }

    public void setPrenomP(String prenomP) {
        this.prenomP = prenomP;
    }

    public String getAdresseEmailP() {
        return adresseEmailP;
    }

    public void setAdresseEmailP(String adresseEmailP) {
        this.adresseEmailP = adresseEmailP;
    }

    public String getPasswordP() {
        return passwordP;
    }

    public void setPasswordP(String passwordP) {
        this.passwordP = passwordP;
    }

    public String getTelephoneP() {
        return telephoneP;
    }

    public void setTelephoneP(String telephoneP) {
        this.telephoneP = telephoneP;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }
}
