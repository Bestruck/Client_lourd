package controleur;

public class Photo {
    private int idPhoto;
    private String chemin;
    private String descriptionP;
    private int idAppartement;

    public Photo(int idPhoto, String chemin, String descriptionP, int idAppartement) {
        this.idPhoto = idPhoto;
        this.chemin = chemin;
        this.descriptionP = descriptionP;
        this.idAppartement = idAppartement;
    }

    public Photo(String chemin, String descriptionP, int idAppartement) {
        this.idPhoto = 0;
        this.chemin = chemin;
        this.descriptionP = descriptionP;
        this.idAppartement = idAppartement;
    }

    public int getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public String getDescriptionP() {
        return descriptionP;
    }

    public void setDescriptionP(String descriptionP) {
        this.descriptionP = descriptionP;
    }

    public int getIdAppartement() {
        return idAppartement;
    }

    public void setIdAppartement(int idAppartement) {
        this.idAppartement = idAppartement;
    }
}

