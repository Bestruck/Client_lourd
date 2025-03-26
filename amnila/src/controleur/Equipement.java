package controleur;

public class Equipement {
    private int idEquipement;
    private String typeDEquipement;
    private String detailAppart;
    private int idAppartement;

    public Equipement(int idEquipement, String typeDEquipement, String detailAppart, int idAppartement) {
        this.idEquipement = idEquipement;
        this.typeDEquipement = typeDEquipement;
        this.detailAppart = detailAppart;
        this.idAppartement = idAppartement;
    }
    
    public Equipement( String typeDEquipement, String detailAppart, int idAppartement) {
        this.idEquipement = 0;
        this.typeDEquipement = typeDEquipement;
        this.detailAppart = detailAppart;
        this.idAppartement = idAppartement;
    }

    public int getIdEquipement() {
        return idEquipement;
    }

    public void setIdEquipement(int idEquipement) {
        this.idEquipement = idEquipement;
    }

    public String getTypeDEquipement() {
        return typeDEquipement;
    }

    public void setTypeDEquipement(String typeDEquipement) {
        this.typeDEquipement = typeDEquipement;
    }

    public String getDetailAppart() {
        return detailAppart;
    }

    public void setDetailAppart(String detailAppart) {
        this.detailAppart = detailAppart;
    }

    public int getIdAppartement() {
        return idAppartement;
    }

    public void setIdAppartement(int idAppartement) {
        this.idAppartement = idAppartement;
    }
}