package controleur;

public class Administrateur {
    private int idAdministrateur;
    private String nomA;
    private String prenomA;
    private String username;
    private String passwordA;

    public Administrateur(int idAdministrateur, String nomA, String prenomA, String username, String passwordA) {
        this.idAdministrateur = idAdministrateur;
        this.nomA = nomA;
        this.prenomA = prenomA;
        this.username = username;
        this.passwordA = passwordA;
    }

    // Getters and Setters
    public int getIdAdministrateur() {
        return idAdministrateur;
    }

    public void setIdAdministrateur(int idAdministrateur) {
        this.idAdministrateur = idAdministrateur;
    }

    public String getNomA() {
        return nomA;
    }

    public void setNomA(String nomA) {
        this.nomA = nomA;
    }

    public String getPrenomA() {
        return prenomA;
    }

    public void setPrenomA(String prenomA) {
        this.prenomA = prenomA;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordA() {
        return passwordA;
    }

    public void setPasswordA(String passwordA) {
        this.passwordA = passwordA;
    }
}
