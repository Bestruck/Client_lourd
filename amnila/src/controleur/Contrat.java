package controleur;

import java.util.Date;

public class Contrat {
    private int idContrat;
    private Date dateDeSignatureC;
    private Date dateDeDebutC;
    private Date dateDeFinC;
    private double arrhesPayees;
    private double soldePayee;
    private double cautionVersee;
    private int idAppartement;
    private int idReservation;

    public Contrat(int idContrat, Date dateDeSignatureC, Date dateDeDebutC, Date dateDeFinC, double arrhesPayees, double soldePayee, double cautionVersee, int idAppartement, int idReservation) {
        this.idContrat = idContrat;
        this.dateDeSignatureC = dateDeSignatureC;
        this.dateDeDebutC = dateDeDebutC;
        this.dateDeFinC = dateDeFinC;
        this.arrhesPayees = arrhesPayees;
        this.soldePayee = soldePayee;
        this.cautionVersee = cautionVersee;
        this.idAppartement = idAppartement;
        this.idReservation = idReservation;
    }

    public Contrat(Date dateDeSignatureC, Date dateDeDebutC, Date dateDeFinC, double arrhesPayees, double soldePayee, double cautionVersee, int idAppartement, int idReservation) {
        this.idContrat = 0;
        this.dateDeSignatureC = dateDeSignatureC;
        this.dateDeDebutC = dateDeDebutC;
        this.dateDeFinC = dateDeFinC;
        this.arrhesPayees = arrhesPayees;
        this.soldePayee = soldePayee;
        this.cautionVersee = cautionVersee;
        this.idAppartement = idAppartement;
        this.idReservation = idReservation;
    }
    // Getters and Setters
    public int getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(int idContrat) {
        this.idContrat = idContrat;
    }

    public Date getDateDeSignatureC() {
        return dateDeSignatureC;
    }

    public void setDateDeSignatureC(Date dateDeSignatureC) {
        this.dateDeSignatureC = dateDeSignatureC;
    }

    public Date getDateDeDebutC() {
        return dateDeDebutC;
    }

    public void setDateDeDebutC(Date dateDeDebutC) {
        this.dateDeDebutC = dateDeDebutC;
    }

    public Date getDateDeFinC() {
        return dateDeFinC;
    }

    public void setDateDeFinC(Date dateDeFinC) {
        this.dateDeFinC = dateDeFinC;
    }

    public double getArrhesPayees() {
        return arrhesPayees;
    }

    public void setArrhesPayees(double arrhesPayees) {
        this.arrhesPayees = arrhesPayees;
    }

    public double getSoldePayee() {
        return soldePayee;
    }

    public void setSoldePayee(double soldePayee) {
        this.soldePayee = soldePayee;
    }

    public double getCautionVersee() {
        return cautionVersee;
    }

    public void setCautionVersee(double cautionVersee) {
        this.cautionVersee = cautionVersee;
    }

    public int getIdAppartement() {
        return idAppartement;
    }

    public void setIdAppartement(int idAppartement) {
        this.idAppartement = idAppartement;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }
}
