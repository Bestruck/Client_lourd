package controleur;

public class Locataire {
    private int idLocataire;
    private String nomL;
    private String prenomL;
    private String adresse_Email_L;
    private String passwordL;
    private String telephoneL;

    public Locataire(int idLocataire, String nomL, String prenomL, String adresse_Email_L, String passwordL, String telephoneL) {
        this.idLocataire = idLocataire;
        this.nomL = nomL;
        this.prenomL = prenomL;
        this.adresse_Email_L = adresse_Email_L;
        this.passwordL = passwordL;
        this.telephoneL = telephoneL;
    }
    
    public Locataire(String nomL, String prenomL, String adresse_Email_L, String passwordL, String telephoneL) {
        this.idLocataire = 0;
        this.nomL = nomL;
        this.prenomL = prenomL;
        this.adresse_Email_L = adresse_Email_L;
        this.passwordL = passwordL;
        this.telephoneL = telephoneL;
    }

    // Getters and Setters
    public int getIdLocataire() {
        return idLocataire;
    }

    public void setIdLocataire(int idLocataire) {
        this.idLocataire = idLocataire;
    }

    public String getNomL() {
        return nomL;
    }

    public void setNomL(String nomL) {
        this.nomL = nomL;
    }

    public String getPrenomL() {
        return prenomL;
    }

    public void setPrenomL(String prenomL) {
        this.prenomL = prenomL;
    }

    public String getAdresseEmailL() {
        return adresse_Email_L;
    }

    public void setAdresseEmailL(String adresse_Email_L) {
        this.adresse_Email_L = adresse_Email_L;
    }

    public String getPasswordL() {
        return passwordL;
    }

    public void setPasswordL(String passwordL) {
        this.passwordL = passwordL;
    }

    public String getTelephoneL() {
        return telephoneL;
    }

    public void setTelephoneL(String telephoneL) {
        this.telephoneL = telephoneL;
    }
}