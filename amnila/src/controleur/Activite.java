package controleur;

public class Activite {
    private int idActivite;
    private int idStation ; 
    private String nomA;
    private double prixA;
    private String descriptionA;

    public Activite( int idActivite, int idStation, String nomA, double prixA, String descriptionA) {
    	this.idActivite = idActivite;
    	this.idStation = idStation;
        this.nomA = nomA;
        this.prixA = prixA;
        this.descriptionA = descriptionA;
    }

    public Activite(int idStation, String nomA, double prixA, String descriptionA) {
        this.idActivite = 0;
        this.idStation = idStation;
        this.nomA = nomA;
        this.prixA = prixA;
        this.descriptionA = descriptionA;
    }
    

	public int getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(int idActivite) {
        this.idActivite = idActivite;
    }
    
    public int getIdStation() {
		return idStation;
	}

	public void setIdStation(int idStation) {
		this.idStation = idStation;
	}

    public String getNomA() {
        return nomA;
    }

    public void setNomA(String nomA) {
        this.nomA = nomA;
    }

    public double getPrixA() {
        return prixA;
    }

    public void setPrixA(double prixA) {
        this.prixA = prixA;
    }

    public String getDescriptionA() {
        return descriptionA;
    }

    public void setDescriptionA(String descriptionA) {
        this.descriptionA = descriptionA;
    }
}
