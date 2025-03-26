package controleur;

import java.sql.Date;

public class Reservation {
    private int idReservation;
    private Date dateDeDebutR;
    private Date dateDeFinR;
    private String statutR;
    private int idAppartement;
    private int idLocataire;

    public Reservation(int idReservation, Date dateDeDebutR, Date dateDeFinR, String statutR, int idAppartement, int idLocataire) {
        this.idReservation = idReservation;
        this.dateDeDebutR = dateDeDebutR;
        this.dateDeFinR = dateDeFinR;
        this.statutR = statutR;
        this.idAppartement = idAppartement;
        this.idLocataire = idLocataire;
    }

    public Reservation(Date dateDeDebutR, Date dateDeFinR, String statutR, int idAppartement, int idLocataire) {
        this.idReservation = 0;
        this.dateDeDebutR = dateDeDebutR;
        this.dateDeFinR = dateDeFinR;
        this.statutR = statutR;
        this.idAppartement = idAppartement;
        this.idLocataire = idLocataire;
    }
    // Getters et Setters
    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public Date getDateDeDebutR() {
        return dateDeDebutR;
    }

    public void setDateDeDebutR(Date dateDeDebutR) {
        this.dateDeDebutR = dateDeDebutR;
    }

    public Date getDateDeFinR() {
        return dateDeFinR;
    }

    public void setDateDeFinR(Date dateDeFinR) {
        this.dateDeFinR = dateDeFinR;
    }

    public String getStatutR() {
        return statutR;
    }

    public void setStatutR(String statutR) {
        this.statutR = statutR;
    }

    public int getIdAppartement() {
        return idAppartement;
    }

    public void setIdAppartement(int idAppartement) {
        this.idAppartement = idAppartement;
    }

    public int getIdLocataire() {
        return idLocataire;
    }

    public void setIdLocataire(int idLocataire) {
        this.idLocataire = idLocataire;
    }
}

