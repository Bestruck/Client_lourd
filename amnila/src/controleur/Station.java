package controleur;

public class Station {
    private int idStation;
    private String nomS;
    private int idRegion;

    public Station(int idStation, String nomS, int idRegion) {
        this.idStation = idStation;
        this.nomS = nomS;
        this.idRegion = idRegion;
    }

    public Station( String nomS, int idRegion) {
        this.idStation = 0;
        this.nomS = nomS;
        this.idRegion = idRegion;
    }
    // Getters and Setters
    public int getIdStation() {
        return idStation;
    }

    public void setIdStation(int idStation) {
        this.idStation = idStation;
    }

    public String getNomS() {
        return nomS;
    }

    public void setNomS(String nomS) {
        this.nomS = nomS;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }
}

