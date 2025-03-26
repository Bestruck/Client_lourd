package controleur;

import java.util.ArrayList;
import modele.Modele;


public class Controleur {
	

	    /************************* Gestion des Contrats ******************/
	    public static void insertContrat(Contrat unContrat) {
	        Modele.insertContrat(unContrat);
	    }

	    public static void deleteContrat(int idContrat) {
	        Modele.deleteContrat(idContrat);
	    }

	    public static void updateContrat(Contrat unContrat) {
	        Modele.updateContrat(unContrat);
	    }

	    public static Contrat selectWhereContrat(int idContrat) {
	        return Modele.selectWhereContrat(idContrat);
	    }

	    public static ArrayList<Contrat> selectAllContrats() {
	        return Modele.selectAllContrats();
	    }

	    public static ArrayList<Contrat> selectLikeContrat(String filtre) {
	        return Modele.selectLikeContrat(filtre);
	    }

	    /************************* Gestion des Propriétaires ******************/
	    public static void insertProprietaire(Proprietaire unProprietaire) {
	        Modele.insertProprietaire(unProprietaire);
	    }

	    public static void deleteProprietaire(int idProprietaire) {
	        Modele.deleteProprietaire(idProprietaire);
	    }

	    public static void updateProprietaire(Proprietaire unProprietaire) {
	        Modele.updateProprietaire(unProprietaire);
	    }

	    public static Proprietaire selectWhereProprietaire(int idProprietaire) {
	        return Modele.selectWhereProprietaire(idProprietaire);
	    }

	    public static ArrayList<Proprietaire> selectAllProprietaires() {
	        return Modele.selectAllProprietaires();
	    }

	    public static ArrayList<Proprietaire> selectLikeProprietaires(String filtre) {
	        return Modele.selectLikeProprietaires(filtre);
	    }

	    /************************* Gestion des Activités ******************/
	    public static void insertActivite(Activite uneActivite) {
	        Modele.insertActivite(uneActivite);
	    }

	    public static void deleteActivite(int idActivite) {
	        Modele.deleteActivite(idActivite);
	    }

	    public static void updateActivite(Activite uneActivite) {
	        Modele.updateActivite(uneActivite);
	    }

	    public static Activite selectWhereActivite(int idActivite) {
	        return Modele.selectWhereActivite(idActivite);
	    }

	    public static ArrayList<Activite> selectAllActivites() {
	        return Modele.selectAllActivites();
	    }

	    public static ArrayList<Activite> selectLikeActivites(String filtre) {
	        return Modele.selectLikeActivites(filtre);
	    }

	    /************************* Gestion des Appartements ******************/
	    public static void insertAppartement(Appartement unAppartement) {
	        Modele.insertAppartement(unAppartement);
	    }

	    public static void deleteAppartement(int idAppartement) {
	        Modele.deleteAppartement(idAppartement);
	    }

	    public static void updateAppartement(Appartement unAppartement) {
	        Modele.updateAppartement(unAppartement);
	    }

	    public static Appartement selectWhereAppartement(int idAppartement) {
	        return Modele.selectWhereAppartement(idAppartement);
	    }

	    public static ArrayList<Appartement> selectAllAppartements() {
	        return Modele.selectAllAppartements();
	    }

	    public static ArrayList<Appartement> selectLikeAppartements(String filtre) {
	        return Modele.selectLikeAppartements(filtre);
	    }

	

    /************************* Gestion des Equipements ******************/
    public static void insertEquipement(Equipement unEquipement) {
        Modele.insertEquipement(unEquipement);
    }

    public static void deleteEquipement(int idEquipement) {
        Modele.deleteEquipement(idEquipement);
    }

    public static void updateEquipement(Equipement unEquipement) {
        Modele.updateEquipement(unEquipement);
    }

    public static Equipement selectWhereEquipement(int idEquipement) {
        return Modele.selectWhereEquipement(idEquipement);
    }

    public static ArrayList<Equipement> selectAllEquipements() {
        return Modele.selectAllEquipements();
    }

    public static ArrayList<Equipement> selectLikeEquipements(String filtre) {
        return Modele.selectLikeEquipements(filtre);
    }

    /************************* Gestion des Locataires ******************/
    public static void insertLocataire(Locataire unLocataire) {
        Modele.insertLocataire(unLocataire);
    }

    public static void deleteLocataire(int idLocataire) {
        Modele.deleteLocataire(idLocataire);
    }

    public static void updateLocataire(Locataire unLocataire) {
        Modele.updateLocataire(unLocataire);
    }

    public static Locataire selectWhereLocataire(int idLocataire) {
        return Modele.selectWhereLocataire(idLocataire);
    }

    public static ArrayList<Locataire> selectAllLocataires() {
        return Modele.selectAllLocataires();
    }

    public static ArrayList<Locataire> selectLikeLocataires(String filtre) {
        return Modele.selectLikeLocataires(filtre);
    }

    public static ArrayList<Locataire> Locataire() {
		return Modele.selectAllLocataires();
	}

    /************************* Gestion des Photos ******************/
    public static void insertPhoto(Photo unePhoto) {
        Modele.insertPhoto(unePhoto);
    }

    public static void deletePhoto(int idPhoto) {
        Modele.deletePhoto(idPhoto);
    }

    public static void updatePhoto(Photo unePhoto) {
        Modele.updatePhoto(unePhoto);
    }

    public static Photo selectWherePhoto(int idPhoto) {
        return Modele.selectWherePhoto(idPhoto);
    }

    public static ArrayList<Photo> selectAllPhotos() {
        return Modele.selectAllPhotos();
    }

    public static ArrayList<Photo> selectLikePhotos(String filtre) {
        return Modele.selectLikePhotos(filtre);
    }


    /************************* Gestion des Stations ******************/
    public static void insertStation(Station uneStation) {
        Modele.insertStation(uneStation);
    }

    public static void deleteStation(int idStation) {
        Modele.deleteStation(idStation);
    }

    public static void updateStation(Station uneStation) {
        Modele.updateStation(uneStation);
    }

    public static Station selectWhereStation(int idStation) {
        return Modele.selectWhereStation(idStation);
    }

    public static ArrayList<Station> selectAllStations() {
        return Modele.selectAllStations();
    }

    public static ArrayList<Station> selectLikeStations(String filtre) {
        return Modele.selectLikeStations(filtre);
    }

    /************************* Gestion des Administrateurs ******************/
    public static void insertAdministrateur(Administrateur unAdministrateur) {
        Modele.insertAdministrateur(unAdministrateur);
    }

    public static void deleteAdministrateur(int idAdministrateur) {
        Modele.deleteAdministrateur(idAdministrateur);
    }

    public static void updateAdministrateur(Administrateur unAdministrateur) {
        Modele.updateAdministrateur(unAdministrateur);
    }

    public static Administrateur selectWhereAdministrateur(String username, String passwordA) {
        return Modele.selectWhereAdministrateur(username, passwordA);
    }
    
    public static ArrayList<Administrateur> selectLikeAdministrateur(String filtre) {
        return Modele.selectLikeAdministrateur(filtre);
    }
    
    public static ArrayList<Administrateur> selectAllAdministrateurs() {
        return Modele.selectAllAdministrateurs();
    }
    
    
    /************************* Gestion des Regions ******************/
    public static void insertRegion(Region uneRegion) {
        Modele.insertRegion(uneRegion);
    }

    public static void deleteRegion(int idRegion) {
        Modele.deleteRegion(idRegion);
    }

    public static void updateRegion(Region uneRegion) {
        Modele.updateRegion(uneRegion);
    }

    public static Region selectWhereRegion(int idRegion) {
        return Modele.selectWhereRegion(idRegion);
    }

    public static ArrayList<Region> selectAllRegions() {
        return Modele.selectAllRegions();
    }

    public static ArrayList<Region> selectLikeRegions(String filtre) {
        return Modele.selectLikeRegions(filtre);
    }

    /************************* Gestion des Reservations ******************/
    public static void insertReservation(Reservation uneReservation) {
        Modele.insertReservation(uneReservation);
    }

    public static void deleteReservation(int idReservation) {
        Modele.deleteReservation(idReservation);
    }

    public static void updateReservation(Reservation uneReservation) {
        Modele.updateReservation(uneReservation);
    }

    public static Reservation selectWhereReservation(int idReservation) {
        return Modele.selectWhereReservation(idReservation);
    }

    public static ArrayList<Reservation> selectAllReservations() {
        return Modele.selectAllReservations();
    }

    public static ArrayList<Reservation> selectLikeReservations(String filtre) {
        return Modele.selectLikeReservations(filtre);
    }

}