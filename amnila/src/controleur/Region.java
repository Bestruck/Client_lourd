package controleur;

public class Region {
    private int idRegion;
    private String nomR;
    private String departementR;

    public Region(int idRegion, String nomR, String departementR) {
        this.idRegion = idRegion;
        this.nomR = nomR;
        this.departementR = departementR;
    }
    
    public Region(String nomR, String departementR) {
        this.idRegion = 0;
        this.nomR = nomR;
        this.departementR = departementR;
    }

    // Getters and Setters
    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public String getNomR() {
        return nomR;
    }

    public void setNomR(String nomR) {
        this.nomR = nomR;
    }

    public String getDepartementR() {
        return departementR;
    }

    public void setDepartementR(String departementR) {
        this.departementR = departementR;
    }
}