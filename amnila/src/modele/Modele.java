package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Activite;
import controleur.Administrateur;
import controleur.Appartement;
import controleur.Contrat;
import controleur.Equipement;
import controleur.Locataire;
import controleur.Photo;
import controleur.Proprietaire;
import controleur.Region;
import controleur.Reservation;
import controleur.Station;

public class Modele {
	private static Connexion uneConnexion=new Connexion ("localhost","amnila","root","");
	
	
	/**************************** Gestion des Proprietaire ********************/
	
	public static void insertProprietaire(Proprietaire unProprietaire) {
	    String requete = "INSERT INTO Proprietaire VALUES (null, '" + unProprietaire.getNomP()
	            + "','" + unProprietaire.getPrenomP() + "','" + unProprietaire.getAdresseEmailP()
	            + "','" + unProprietaire.getPasswordP() + "','" + unProprietaire.getTelephoneP()
	            + "','" + unProprietaire.getRib() + "');";
	    executerRequete(requete);
	}

	public static void deleteProprietaire(int idProprietaire) {
	    String requete = "DELETE FROM Proprietaire WHERE idProprietaire = '" + idProprietaire + "';";
	    executerRequete(requete);
	}

	public static void updateProprietaire(Proprietaire unProprietaire) {
	    String requete = "UPDATE Proprietaire SET nomP = '" + unProprietaire.getNomP()
	            + "', prenomP = '" + unProprietaire.getPrenomP() + "', adresseEmailP = '" 
	            + unProprietaire.getAdresseEmailP() + "', passwordP = '" + unProprietaire.getPasswordP()
	            + "', telephoneP = '" + unProprietaire.getTelephoneP() + "', rib = '" 
	            + unProprietaire.getRib() + "' WHERE idProprietaire = '" + unProprietaire.getIdProprietaire() + "';";
	    executerRequete(requete);
	}

	public static Proprietaire selectWhereProprietaire(int idProprietaire) {
	    String requete = "SELECT * FROM Proprietaire WHERE idProprietaire = '" + idProprietaire + "';";
	    Proprietaire unProprietaire = null;
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        if (lesResultats.next()) {
	            unProprietaire = new Proprietaire(
	                    lesResultats.getInt("idProprietaire"), lesResultats.getString("nomP"),
	                    lesResultats.getString("prenomP"), lesResultats.getString("adresseEmailP"),
	                    lesResultats.getString("passwordP"), lesResultats.getString("telephoneP"),
	                    lesResultats.getString("rib"));
	        }
	        unStat.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'exécution de la requête : " + requete);
	    }
	    return unProprietaire;
	}

	public static ArrayList<Proprietaire> selectAllProprietaires() {
	    String requete = "SELECT * FROM Proprietaire;";
	    ArrayList<Proprietaire> lesProprietaires = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            Proprietaire unProprietaire = new Proprietaire(
	                    lesResultats.getInt("idProprietaire"), lesResultats.getString("nomP"),
	                    lesResultats.getString("prenomP"), lesResultats.getString("adresse_Email_P"),
	                    lesResultats.getString("passwordP"), lesResultats.getString("telephoneP"),
	                    lesResultats.getString("rib"));
	            lesProprietaires.add(unProprietaire);
	        }
	        unStat.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'exécution de la requête : " + requete);
	    }
	    return lesProprietaires;
	}

	public static ArrayList<Proprietaire> selectLikeProprietaires(String filtre) {
	    String requete = "SELECT * FROM Proprietaire WHERE nomP LIKE '%" + filtre + "%' OR "
	            + "prenomP LIKE '%" + filtre + "%' OR adresseEmailP LIKE '%" + filtre + "%' OR "
	            + "telephoneP LIKE '%" + filtre + "%' OR rib LIKE '%" + filtre + "%';";
	    ArrayList<Proprietaire> lesProprietaires = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            Proprietaire unProprietaire = new Proprietaire(
	                    lesResultats.getInt("idProprietaire"), lesResultats.getString("nomP"),
	                    lesResultats.getString("prenomP"), lesResultats.getString("adresse_Email_P"),
	                    lesResultats.getString("passwordP"), lesResultats.getString("telephoneP"),
	                    lesResultats.getString("rib"));
	            lesProprietaires.add(unProprietaire);
	        }
	        unStat.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'exécution de la requête : " + requete);
	    }
	    return lesProprietaires;
	}
	
	
	/**************************** Gestion des Activite ********************/
	public static void insertActivite(Activite uneActivite) {
	    String requete = "INSERT INTO Activite (IDStation, idActivite, nomActivite, prixA, descriptionA) VALUES (" 
	            + uneActivite.getIdStation() + "," + uneActivite.getIdActivite() + ",'" + uneActivite.getNomA() + "'," + uneActivite.getPrixA() 
	            + ",'" + uneActivite.getDescriptionA() + "');";
	    executerRequete(requete);
	}

	public static void deleteActivite(int idActivite) {
	    String requete = "DELETE FROM Activite WHERE idActivite = '" + idActivite + "';";
	    executerRequete(requete);
	}

	public static void updateActivite(Activite uneActivite) {
	    String requete = "UPDATE Activite SET IDStation = " + uneActivite.getIdStation() + ", nomActivite = '" + uneActivite.getNomA()
	            + "', prixA = " + uneActivite.getPrixA() + ", descriptionA = '" 
	            + uneActivite.getDescriptionA() + "' WHERE idActivite = '" + uneActivite.getIdActivite() + "';";
	    executerRequete(requete);
	}

	public static Activite selectWhereActivite(int idActivite) {
	    String requete = "SELECT * FROM Activite WHERE idActivite = '" + idActivite + "';";
	    Activite uneActivite = null;
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        if (lesResultats.next()) {
	            uneActivite = new Activite(
	            		lesResultats.getInt("IDStation"), lesResultats.getInt("idActivite"), lesResultats.getString("nomActivite"),
	                    lesResultats.getDouble("prixA"), lesResultats.getString("descriptionA"));
	        }
	        unStat.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'exécution de la requête : " + requete);
	    }
	    return uneActivite;
	}

	public static ArrayList<Activite> selectAllActivites() {
	    String requete = "SELECT * FROM Activite;";
	    ArrayList<Activite> lesActivites = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	        	Activite uneActivite = new Activite(
	        		    lesResultats.getInt("idActivite"), lesResultats.getInt("IDStation"), // On inverse ces deux valeurs
	        		    lesResultats.getString("nomActivite"), lesResultats.getDouble("prixA"),
	        		    lesResultats.getString("descriptionA"));
	            lesActivites.add(uneActivite);
	        }
	        unStat.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'exécution de la requête : " + requete);
	    }
	    return lesActivites;
	}

	public static ArrayList<Activite> selectLikeActivites(String filtre) {
	    String requete = "SELECT * FROM Activite WHERE nomActivite LIKE '%" + filtre + "%' OR "
	            + "descriptionA LIKE '%" + filtre + "%';";
	    ArrayList<Activite> lesActivites = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            Activite uneActivite = new Activite(
	            		 lesResultats.getInt("idActivite"), lesResultats.getInt("IDStation"), lesResultats.getString("nomActivite"),
	                    lesResultats.getDouble("prixA"), lesResultats.getString("descriptionA"));
	            lesActivites.add(uneActivite);
	        }
	        unStat.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'exécution de la requête : " + requete);
	    }
	    return lesActivites;
	}

	
	
	
	/**************************** Gestion des Appartement ********************/
	public static void insertAppartement(Appartement unAppartement) {
	    String requete = "INSERT INTO Appartement (typeDappartementA, rueA, codePostalA, villeA, nImmeubleA, surfaceA, prixJournalier, idProprietaire, idRegion) VALUES ('"
	            + unAppartement.getTypeDappartementA() + "','" + unAppartement.getRueA() + "','" + unAppartement.getCodePostalA() 
	            + "','" + unAppartement.getVilleA() + "','" + unAppartement.getNImmeubleA() + "','" + unAppartement.getSurfaceA() + "',"
	            + unAppartement.getPrixJournalier() + ",'" + unAppartement.getIdProprietaire() + "','" + unAppartement.getIdRegion() + "');";
	    executerRequete(requete);
	}

	public static void deleteAppartement(int idAppartement) {
	    String requete = "DELETE FROM Appartement WHERE idAppartement = '" + idAppartement + "';";
	    executerRequete(requete);
	}

	public static void updateAppartement(Appartement unAppartement) {
	    String requete = "UPDATE Appartement SET typeDappartementA = '" + unAppartement.getTypeDappartementA()
	            + "', rueA = '" + unAppartement.getRueA() + "', codePostalA = '" + unAppartement.getCodePostalA() 
	            + "', villeA = '" + unAppartement.getVilleA() + "', nImmeubleA = '" + unAppartement.getNImmeubleA() 
	            + "', surfaceA = " + unAppartement.getSurfaceA() + ", prixJournalier = " + unAppartement.getPrixJournalier()
	            + ", idProprietaire = '" + unAppartement.getIdProprietaire() + "', idRegion = '" + unAppartement.getIdRegion() 
	            + "' WHERE idAppartement = '" + unAppartement.getIdAppartement() + "';";
	    executerRequete(requete);
	}

	public static Appartement selectWhereAppartement(int idAppartement) {
	    String requete = "SELECT * FROM Appartement WHERE idAppartement = '" + idAppartement + "';";
	    Appartement unAppartement = null;
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        if (lesResultats.next()) {
	            unAppartement = new Appartement(
	                    lesResultats.getInt("idAppartement"), lesResultats.getString("typeDappartementA"),
	                    lesResultats.getString("rueA"), lesResultats.getString("codePostalA"), lesResultats.getString("villeA"),
	                    lesResultats.getString("nImmeubleA"), lesResultats.getString("surfaceA"),
	                    lesResultats.getDouble("prixJournalier"), lesResultats.getInt("idProprietaire"), lesResultats.getInt("idRegion"));
	        }
	        unStat.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'exécution de la requête : " + requete);
	    }
	    return unAppartement;
	}

	public static ArrayList<Appartement> selectAllAppartements() {
	    String requete = "SELECT * FROM appartement;";
	    ArrayList<Appartement> lesAppartements = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            Appartement unAppartement = new Appartement(
	                    lesResultats.getInt("idAppartement"), lesResultats.getString("typeDappartementA"),
	                    lesResultats.getString("rueA"), lesResultats.getString("codePostalA"), lesResultats.getString("villeA"),
	                    lesResultats.getString("nImmeubleA"), lesResultats.getString("surfaceA"),
	                    lesResultats.getDouble("prixJournalier"), lesResultats.getInt("idProprietaire"), lesResultats.getInt("idRegion"));
	            lesAppartements.add(unAppartement);
	        }
	        unStat.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'exécution de la requête : " + requete);
	    }
	    return lesAppartements;
	}

	public static ArrayList<Appartement> selectLikeAppartements(String filtre) {
	    String requete = "SELECT * FROM Appartement WHERE typeDappartementA LIKE '%" + filtre + "%' OR "
	            + "rueA LIKE '%" + filtre + "%' OR codePostalA LIKE '%" + filtre + "%' OR "
	            + "villeA LIKE '%" + filtre + "%' OR nImmeubleA LIKE '%" + filtre + "%';";
	    ArrayList<Appartement> lesAppartements = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet lesResultats = unStat.executeQuery(requete);
	        while (lesResultats.next()) {
	            Appartement unAppartement = new Appartement(
	                    lesResultats.getInt("idAppartement"), lesResultats.getString("typeDappartementA"),
	                    lesResultats.getString("rueA"), lesResultats.getString("codePostalA"), lesResultats.getString("villeA"),
	                    lesResultats.getString("nImmeubleA"), lesResultats.getString("surfaceA"),
	                    lesResultats.getDouble("prixJournalier"), lesResultats.getInt("idProprietaire"), lesResultats.getInt("idRegion"));
	            lesAppartements.add(unAppartement);
	        }
	        unStat.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'exécution de la requête : " + requete);
	    }
	    return lesAppartements;
	}


	/**************************** Gestion des Contrat ********************/

	    public static void insertContrat(Contrat unContrat) {
	        String requete = "INSERT INTO Contrat (dateDeSignatureC, dateDeDebutC, dateDeFinC, arrhesPayees, soldePayee, cautionVersee, idAppartement, idReservation) VALUES ('"
	                + new java.sql.Date(unContrat.getDateDeSignatureC().getTime()) + "', '"
	                + new java.sql.Date(unContrat.getDateDeDebutC().getTime()) + "', '"
	                + new java.sql.Date(unContrat.getDateDeFinC().getTime()) + "', "
	                + unContrat.getArrhesPayees() + ", "
	                + unContrat.getSoldePayee() + ", "
	                + unContrat.getCautionVersee() + ", '"
	                + unContrat.getIdAppartement() + "', '"
	                + unContrat.getIdReservation() + "');";
	        executerRequete(requete);
	    }

	    public static void deleteContrat(int idContrat) {
	        String requete = "DELETE FROM Contrat WHERE idContrat = " + idContrat + ";";
	        executerRequete(requete);
	    }

	    public static void updateContrat(Contrat unContrat) {
	        String requete = "UPDATE Contrat SET dateDeSignatureC = '" + new java.sql.Date(unContrat.getDateDeSignatureC().getTime()) 
	                + "', dateDeDebutC = '" + new java.sql.Date(unContrat.getDateDeDebutC().getTime()) 
	                + "', dateDeFinC = '" + new java.sql.Date(unContrat.getDateDeFinC().getTime()) 
	                + "', arrhesPayees = " + unContrat.getArrhesPayees() 
	                + ", soldePayee = " + unContrat.getSoldePayee() 
	                + ", cautionVersee = " + unContrat.getCautionVersee() 
	                + ", idAppartement = '" + unContrat.getIdAppartement() 
	                + "', idReservation = '" + unContrat.getIdReservation() 
	                + "' WHERE idContrat = " + unContrat.getIdContrat() + ";";
	        executerRequete(requete);
	    }

	    public static Contrat selectWhereContrat(int idContrat) {
	        String requete = "SELECT * FROM Contrat WHERE idContrat = " + idContrat + ";";
	        Contrat unContrat = null;
	        try {
	            uneConnexion.seConnecter();
	            Statement unStat = uneConnexion.getMaConnexion().createStatement();
	            ResultSet lesResultats = unStat.executeQuery(requete);
	            if (lesResultats.next()) {
	                unContrat = new Contrat(
	                        lesResultats.getInt("idContrat"),
	                        lesResultats.getDate("dateDeSignatureC"),
	                        lesResultats.getDate("dateDeDebutC"),
	                        lesResultats.getDate("dateDeFinC"),
	                        lesResultats.getDouble("arrhesPayees"),
	                        lesResultats.getDouble("soldePayee"),
	                        lesResultats.getDouble("cautionVersee"),
	                        lesResultats.getInt("idAppartement"),
	                        lesResultats.getInt("idReservation")
	                );
	            }
	            unStat.close();
	            uneConnexion.seDeconnecter();
	        } catch (SQLException exp) {
	            System.out.println("Erreur d'exécution de la requête : " + requete);
	        }
	        return unContrat;
	    }

	    public static ArrayList<Contrat> selectAllContrats() {
	        String requete = "SELECT * FROM Contrat;";
	        ArrayList<Contrat> lesContrats = new ArrayList<>();
	        try {
	            uneConnexion.seConnecter();
	            Statement unStat = uneConnexion.getMaConnexion().createStatement();
	            ResultSet lesResultats = unStat.executeQuery(requete);
	            while (lesResultats.next()) {
	                Contrat unContrat = new Contrat(
	                        lesResultats.getInt("idContrat"),
	                        lesResultats.getDate("dateDeSignatureC"),
	                        lesResultats.getDate("dateDeDebutC"),
	                        lesResultats.getDate("dateDeFinC"),
	                        lesResultats.getDouble("arrhesPayees"),
	                        lesResultats.getDouble("soldePayee"),
	                        lesResultats.getDouble("cautionVersee"),
	                        lesResultats.getInt("idAppartement"),
	                        lesResultats.getInt("idReservation")
	                );
	                lesContrats.add(unContrat);
	            }
	            unStat.close();
	            uneConnexion.seDeconnecter();
	        } catch (SQLException exp) {
	            System.out.println("Erreur d'exécution de la requête : " + requete);
	        }
	        return lesContrats;
	    }
	    public static ArrayList<Contrat> selectLikeContrat(String filtre) {
	        String requete = "SELECT * FROM Contrat WHERE dateDeSignatureC LIKE '%" + filtre + "%' OR " +
	                         "dateDeDebutC LIKE '%" + filtre + "%' OR " +
	                         "dateDeFinC LIKE '%" + filtre + "%' OR " +
	                         "arrhesPayees LIKE '%" + filtre + "%' OR " +
	                         "soldePayee LIKE '%" + filtre + "%' OR " +
	                         "cautionVersee LIKE '%" + filtre + "%' OR " +
	                         "idAppartement LIKE '%" + filtre + "%' OR " +
	                         "idReservation LIKE '%" + filtre + "%';";
	        ArrayList<Contrat> lesContrats = new ArrayList<>();
	        try {
	            uneConnexion.seConnecter();
	            Statement unStat = uneConnexion.getMaConnexion().createStatement();
	            ResultSet lesResultats = unStat.executeQuery(requete);
	            while (lesResultats.next()) {
	                Contrat unContrat = new Contrat(
	                        lesResultats.getInt("idContrat"),
	                        lesResultats.getDate("dateDeSignatureC"),
	                        lesResultats.getDate("dateDeDebutC"),
	                        lesResultats.getDate("dateDeFinC"),
	                        lesResultats.getDouble("arrhesPayees"),
	                        lesResultats.getDouble("soldePayee"),
	                        lesResultats.getDouble("cautionVersee"),
	                        lesResultats.getInt("idAppartement"),
	                        lesResultats.getInt("idReservation")
	                );
	                lesContrats.add(unContrat);
	            }
	            unStat.close();
	            uneConnexion.seDeconnecter();
	        } catch (SQLException exp) {
	            System.out.println("Erreur d'exécution de la requête : " + requete);
	        }
	        return lesContrats;
	    }
	    
	/**************************** Gestion des Equipement ********************/
	    public static void insertEquipement(Equipement unEquipement) {
	        String requete = "INSERT INTO Equipement (idEquipement, typeDEquipement, detailAppart, idAppartement) VALUES ('"
	                + unEquipement.getIdEquipement() + "', '"
	                + unEquipement.getTypeDEquipement() + "', '"
	                + unEquipement.getDetailAppart() + "', '"
	                + unEquipement.getIdAppartement() + "');";
	        executerRequete(requete);
	    }

	    public static void deleteEquipement(int idEquipement) {
	        String requete = "DELETE FROM Equipement WHERE idEquipement = '" + idEquipement + "';";
	        executerRequete(requete);
	    }

	    public static void updateEquipement(Equipement unEquipement) {
	        String requete = "UPDATE Equipement SET typeDEquipement = '" + unEquipement.getTypeDEquipement()
	                + "', detailAppart = '" + unEquipement.getDetailAppart()
	                + "', idAppartement = '" + unEquipement.getIdAppartement()
	                + "' WHERE idEquipement = '" + unEquipement.getIdEquipement() + "';";
	        executerRequete(requete);
	    }

	    public static Equipement selectWhereEquipement(int idEquipement) {
	        String requete = "SELECT * FROM Equipement WHERE idEquipement = '" + idEquipement + "';";
	        Equipement unEquipement = null;
	        try {
	            uneConnexion.seConnecter();
	            Statement unStat = uneConnexion.getMaConnexion().createStatement();
	            ResultSet lesResultats = unStat.executeQuery(requete);
	            if (lesResultats.next()) {
	                unEquipement = new Equipement(
	                        lesResultats.getInt("idEquipement"),
	                        lesResultats.getString("typeDEquipement"),
	                        lesResultats.getString("detailAppart"),
	                        lesResultats.getInt("idAppartement")
	                );
	            }
	            unStat.close();
	            uneConnexion.seDeconnecter();
	        } catch (SQLException exp) {
	            System.out.println("Erreur d'exécution de la requête : " + requete);
	        }
	        return unEquipement;
	    }

	    public static ArrayList<Equipement> selectAllEquipements() {
	        String requete = "SELECT * FROM Equipement;";
	        ArrayList<Equipement> lesEquipements = new ArrayList<>();
	        try {
	            uneConnexion.seConnecter();
	            Statement unStat = uneConnexion.getMaConnexion().createStatement();
	            ResultSet lesResultats = unStat.executeQuery(requete);
	            while (lesResultats.next()) {
	                Equipement unEquipement = new Equipement(
	                        lesResultats.getInt("idEquipement"),
	                        lesResultats.getString("typeDEquipement"),
	                        lesResultats.getString("detailAppart"),
	                        lesResultats.getInt("idAppartement")
	                );
	                lesEquipements.add(unEquipement);
	            }
	            unStat.close();
	            uneConnexion.seDeconnecter();
	        } catch (SQLException exp) {
	            System.out.println("Erreur d'exécution de la requête : " + requete);
	        }
	        return lesEquipements;
	    }

	    public static ArrayList<Equipement> selectLikeEquipements(String filtre) {
	        String requete = "SELECT * FROM Equipement WHERE typeDEquipement LIKE '%" + filtre + "%' OR "
	                + "detailAppart LIKE '%" + filtre + "%';";
	        ArrayList<Equipement> lesEquipements = new ArrayList<>();
	        try {
	            uneConnexion.seConnecter();
	            Statement unStat = uneConnexion.getMaConnexion().createStatement();
	            ResultSet lesResultats = unStat.executeQuery(requete);
	            while (lesResultats.next()) {
	                Equipement unEquipement = new Equipement(
	                        lesResultats.getInt("idEquipement"),
	                        lesResultats.getString("typeDEquipement"),
	                        lesResultats.getString("detailAppart"),
	                        lesResultats.getInt("idAppartement")
	                );
	                lesEquipements.add(unEquipement);
	            }
	            unStat.close();
	            uneConnexion.seDeconnecter();
	        } catch (SQLException exp) {
	            System.out.println("Erreur d'exécution de la requête : " + requete);
	        }
	        return lesEquipements;
	    }
	    
	    
		/**************************** Gestion des Locataire ********************/
	        public static void insertLocataire(Locataire unLocataire) {
	            String requete = "INSERT INTO Locataire (idLocataire, nomL, prenomL, adresse_Email_L, passwordL, telephoneL) VALUES ('"
	                    + unLocataire.getIdLocataire() + "', '"
	                    + unLocataire.getNomL() + "', '"
	                    + unLocataire.getPrenomL() + "', '"
	                    + unLocataire.getAdresseEmailL() + "', '"
	                    + unLocataire.getPasswordL() + "', '"
	                    + unLocataire.getTelephoneL() + "');";
	            executerRequete(requete);
	        }

	        public static void deleteLocataire(int idLocataire) {
	            String requete = "DELETE FROM Locataire WHERE idLocataire = '" + idLocataire + "';";
	            executerRequete(requete);
	        }

	        public static void updateLocataire(Locataire unLocataire) {
	            String requete = "UPDATE Locataire SET nomL = '" + unLocataire.getNomL()
	                    + "', prenomL = '" + unLocataire.getPrenomL()
	                    + "', adresse_Email_L = '" + unLocataire.getAdresseEmailL()
	                    + "', passwordL = '" + unLocataire.getPasswordL()
	                    + "', telephoneL = '" + unLocataire.getTelephoneL()
	                    + "' WHERE idLocataire = '" + unLocataire.getIdLocataire() + "';";
	            executerRequete(requete);
	        }

	        public static Locataire selectWhereLocataire(int idLocataire) {
	            String requete = "SELECT * FROM Locataire WHERE idLocataire = '" + idLocataire + "';";
	            Locataire unLocataire = null;
	            try {
	                uneConnexion.seConnecter();
	                Statement unStat = uneConnexion.getMaConnexion().createStatement();
	                ResultSet lesResultats = unStat.executeQuery(requete);
	                if (lesResultats.next()) {
	                    unLocataire = new Locataire(
	                            lesResultats.getInt("idLocataire"),
	                            lesResultats.getString("nomL"),
	                            lesResultats.getString("prenomL"),
	                            lesResultats.getString("adresse_Email_L"),
	                            lesResultats.getString("passwordL"),
	                            lesResultats.getString("telephoneL")
	                    );
	                }
	                unStat.close();
	                uneConnexion.seDeconnecter();
	            } catch (SQLException exp) {
	                System.out.println("Erreur d'exécution de la requête : " + requete);
	            }
	            return unLocataire;
	        }

	        public static ArrayList<Locataire> selectAllLocataires() {
	            String requete = "SELECT * FROM Locataire;";
	            ArrayList<Locataire> lesLocataires = new ArrayList<>();
	            try {
	                uneConnexion.seConnecter();
	                Statement unStat = uneConnexion.getMaConnexion().createStatement();
	                ResultSet lesResultats = unStat.executeQuery(requete);
	                while (lesResultats.next()) {
	                    Locataire unLocataire = new Locataire(
	                            lesResultats.getInt("idLocataire"),
	                            lesResultats.getString("nomL"),
	                            lesResultats.getString("prenomL"),
	                            lesResultats.getString("adresse_Email_L"),
	                            lesResultats.getString("passwordL"),
	                            lesResultats.getString("telephoneL")
	                    );
	                    lesLocataires.add(unLocataire);
	                }
	                unStat.close();
	                uneConnexion.seDeconnecter();
	            } catch (SQLException exp) {
	                System.out.println("Erreur d'exécution de la requête : " + requete);
	            }
	            return lesLocataires;
	        }

	        public static ArrayList<Locataire> selectLikeLocataires(String filtre) {
	            String requete = "SELECT * FROM Locataire WHERE nomL LIKE '%" + filtre + "%' OR "
	                    + "prenomL LIKE '%" + filtre + "%' OR "
	                    + "adresseEmailL LIKE '%" + filtre + "%';";
	            ArrayList<Locataire> lesLocataires = new ArrayList<>();
	            try {
	                uneConnexion.seConnecter();
	                Statement unStat = uneConnexion.getMaConnexion().createStatement();
	                ResultSet lesResultats = unStat.executeQuery(requete);
	                while (lesResultats.next()) {
	                    Locataire unLocataire = new Locataire(
	                            lesResultats.getInt("idLocataire"),
	                            lesResultats.getString("nomL"),
	                            lesResultats.getString("prenomL"),
	                            lesResultats.getString("adresse_Email_L"),
	                            lesResultats.getString("passwordL"),
	                            lesResultats.getString("telephoneL")
	                    );
	                    lesLocataires.add(unLocataire);
	                }
	                unStat.close();
	                uneConnexion.seDeconnecter();
	            } catch (SQLException exp) {
	                System.out.println("Erreur d'exécution de la requête : " + requete);
	            }
	            return lesLocataires;
	        }



		/**************************** Gestion des Photo ********************/
	        public static void insertPhoto(Photo unePhoto) {
	            String requete = "INSERT INTO Photo (idPhoto, chemin, descriptionP, idAppartement) VALUES ('"
	                    + unePhoto.getIdPhoto() + "', '"
	                    + unePhoto.getChemin() + "', '"
	                    + unePhoto.getDescriptionP() + "', '"
	                    + unePhoto.getIdAppartement() + "');";
	            executerRequete(requete);
	        }

	        public static void deletePhoto(int idPhoto) {
	            String requete = "DELETE FROM Photo WHERE idPhoto = '" + idPhoto + "';";
	            executerRequete(requete);
	        }

	        public static void updatePhoto(Photo unePhoto) {
	            String requete = "UPDATE Photo SET chemin = '" + unePhoto.getChemin()
	                    + "', descriptionP = '" + unePhoto.getDescriptionP()
	                    + "', idAppartement = '" + unePhoto.getIdAppartement()
	                    + "' WHERE idPhoto = '" + unePhoto.getIdPhoto() + "';";
	            executerRequete(requete);
	        }

	        public static Photo selectWherePhoto(int idPhoto) {
	            String requete = "SELECT * FROM Photo WHERE idPhoto = '" + idPhoto + "';";
	            Photo unePhoto = null;
	            try {
	                uneConnexion.seConnecter();
	                Statement unStat = uneConnexion.getMaConnexion().createStatement();
	                ResultSet lesResultats = unStat.executeQuery(requete);
	                if (lesResultats.next()) {
	                    unePhoto = new Photo(
	                            lesResultats.getInt("idPhoto"),
	                            lesResultats.getString("chemin"),
	                            lesResultats.getString("descriptionP"),
	                            lesResultats.getInt("idAppartement")
	                    );
	                }
	                unStat.close();
	                uneConnexion.seDeconnecter();
	            } catch (SQLException exp) {
	                System.out.println("Erreur d'exécution de la requête : " + requete);
	            }
	            return unePhoto;
	        }

	        public static ArrayList<Photo> selectAllPhotos() {
	            String requete = "SELECT * FROM Photo;";
	            ArrayList<Photo> lesPhotos = new ArrayList<>();
	            try {
	                uneConnexion.seConnecter();
	                Statement unStat = uneConnexion.getMaConnexion().createStatement();
	                ResultSet lesResultats = unStat.executeQuery(requete);
	                while (lesResultats.next()) {
	                    Photo unePhoto = new Photo(
	                            lesResultats.getInt("idPhoto"),
	                            lesResultats.getString("chemin"),
	                            lesResultats.getString("descriptionP"),
	                            lesResultats.getInt("idAppartement")
	                    );
	                    lesPhotos.add(unePhoto);
	                }
	                unStat.close();
	                uneConnexion.seDeconnecter();
	            } catch (SQLException exp) {
	                System.out.println("Erreur d'exécution de la requête : " + requete);
	            }
	            return lesPhotos;
	        }

	        public static ArrayList<Photo> selectLikePhotos(String filtre) {
	            String requete = "SELECT * FROM Photo WHERE chemin LIKE '%" + filtre + "%' OR "
	                    + "descriptionP LIKE '%" + filtre + "%';";
	            ArrayList<Photo> lesPhotos = new ArrayList<>();
	            try {
	                uneConnexion.seConnecter();
	                Statement unStat = uneConnexion.getMaConnexion().createStatement();
	                ResultSet lesResultats = unStat.executeQuery(requete);
	                while (lesResultats.next()) {
	                    Photo unePhoto = new Photo(
	                            lesResultats.getInt("idPhoto"),
	                            lesResultats.getString("chemin"),
	                            lesResultats.getString("descriptionP"),
	                            lesResultats.getInt("idAppartement")
	                    );
	                    lesPhotos.add(unePhoto);
	                }
	                unStat.close();
	                uneConnexion.seDeconnecter();
	            } catch (SQLException exp) {
	                System.out.println("Erreur d'exécution de la requête : " + requete);
	            }
	            return lesPhotos;
	        }
	        
	    
		/**************************** Gestion des Region ********************/
	        public static void insertRegion(Region uneRegion) {
	            String requete = "INSERT INTO Region (idRegion, nomR, departementR) VALUES ('"
	                    + uneRegion.getIdRegion() + "', '"
	                    + uneRegion.getNomR() + "', '"
	                    + uneRegion.getDepartementR() + "');";
	            executerRequete(requete);
	        }

	        public static void deleteRegion(int idRegion) {
	            String requete = "DELETE FROM Region WHERE idRegion = '" + idRegion + "';";
	            executerRequete(requete);
	        }

	        public static void updateRegion(Region uneRegion) {
	            String requete = "UPDATE Region SET nomR = '" + uneRegion.getNomR()
	                    + "', departementR = '" + uneRegion.getDepartementR()
	                    + "' WHERE idRegion = '" + uneRegion.getIdRegion() + "';";
	            executerRequete(requete);
	        }

	        public static Region selectWhereRegion(int idRegion) {
	            String requete = "SELECT * FROM Region WHERE idRegion = '" + idRegion + "';";
	            Region uneRegion = null;
	            try {
	                uneConnexion.seConnecter();
	                Statement unStat = uneConnexion.getMaConnexion().createStatement();
	                ResultSet lesResultats = unStat.executeQuery(requete);
	                if (lesResultats.next()) {
	                    uneRegion = new Region(
	                            lesResultats.getInt("idRegion"),
	                            lesResultats.getString("nomR"),
	                            lesResultats.getString("departementR")
	                    );
	                }
	                unStat.close();
	                uneConnexion.seDeconnecter();
	            } catch (SQLException exp) {
	                System.out.println("Erreur d'exécution de la requête : " + requete);
	            }
	            return uneRegion;
	        }

	        public static ArrayList<Region> selectAllRegions() {
	            String requete = "SELECT * FROM Region;";
	            ArrayList<Region> lesRegions = new ArrayList<>();
	            try {
	                uneConnexion.seConnecter();
	                Statement unStat = uneConnexion.getMaConnexion().createStatement();
	                ResultSet lesResultats = unStat.executeQuery(requete);
	                while (lesResultats.next()) {
	                    Region uneRegion = new Region(
	                            lesResultats.getInt("idRegion"),
	                            lesResultats.getString("nomR"),
	                            lesResultats.getString("departementR")
	                    );
	                    lesRegions.add(uneRegion);
	                }
	                unStat.close();
	                uneConnexion.seDeconnecter();
	            } catch (SQLException exp) {
	                System.out.println("Erreur d'exécution de la requête : " + requete);
	            }
	            return lesRegions;
	        }

	        public static ArrayList<Region> selectLikeRegions(String filtre) {
	            String requete = "SELECT * FROM Region WHERE nomR LIKE '%" + filtre + "%' OR "
	                    + "departementR LIKE '%" + filtre + "%';";
	            ArrayList<Region> lesRegions = new ArrayList<>();
	            try {
	                uneConnexion.seConnecter();
	                Statement unStat = uneConnexion.getMaConnexion().createStatement();
	                ResultSet lesResultats = unStat.executeQuery(requete);
	                while (lesResultats.next()) {
	                    Region uneRegion = new Region(
	                            lesResultats.getInt("idRegion"),
	                            lesResultats.getString("nomR"),
	                            lesResultats.getString("departementR")
	                    );
	                    lesRegions.add(uneRegion);
	                }
	                unStat.close();
	                uneConnexion.seDeconnecter();
	            } catch (SQLException exp) {
	                System.out.println("Erreur d'exécution de la requête : " + requete);
	            }
	            return lesRegions;
	        }

	        
	   
		/**************************** Gestion des Reservation ********************/
	        public static void insertReservation(Reservation uneReservation) {
	            String requete = "INSERT INTO Reservation (idReservation, dateDeDebutR, dateDeFinR, statutR, idAppartement, idLocataire) VALUES ('"
	                    + uneReservation.getIdReservation() + "', '"
	                    + uneReservation.getDateDeDebutR() + "', '"
	                    + uneReservation.getDateDeFinR() + "', '"
	                    + uneReservation.getStatutR() + "', '"
	                    + uneReservation.getIdAppartement() + "', '"
	                    + uneReservation.getIdLocataire() + "');";
	            executerRequete(requete);
	        }

	        public static void deleteReservation(int idReservation) {
	            String requete = "DELETE FROM Reservation WHERE idReservation = '" + idReservation + "';";
	            executerRequete(requete);
	        }

	        public static void updateReservation(Reservation uneReservation) {
	            String requete = "UPDATE Reservation SET dateDeDebutR = '" + uneReservation.getDateDeDebutR()
	                    + "', dateDeFinR = '" + uneReservation.getDateDeFinR()
	                    + "', statutR = '" + uneReservation.getStatutR()
	                    + "', idAppartement = '" + uneReservation.getIdAppartement()
	                    + "', idLocataire = '" + uneReservation.getIdLocataire()
	                    + "' WHERE idReservation = '" + uneReservation.getIdReservation() + "';";
	            executerRequete(requete);
	        }

	        public static Reservation selectWhereReservation(int idReservation) {
	            String requete = "SELECT * FROM Reservation WHERE idReservation = '" + idReservation + "';";
	            Reservation uneReservation = null;
	            try {
	                uneConnexion.seConnecter();
	                Statement unStat = uneConnexion.getMaConnexion().createStatement();
	                ResultSet lesResultats = unStat.executeQuery(requete);
	                if (lesResultats.next()) {
	                    uneReservation = new Reservation(
	                            lesResultats.getInt("idReservation"),
	                            lesResultats.getDate("dateDeDebutR"),
	                            lesResultats.getDate("dateDeFinR"),
	                            lesResultats.getString("statutR"),
	                            lesResultats.getInt("idAppartement"),
	                            lesResultats.getInt("idLocataire")
	                    );
	                }
	                unStat.close();
	                uneConnexion.seDeconnecter();
	            } catch (SQLException exp) {
	                System.out.println("Erreur d'exécution de la requête : " + requete);
	            }
	            return uneReservation;
	        }

	        public static ArrayList<Reservation> selectAllReservations() {
	            String requete = "SELECT * FROM Reservation;";
	            ArrayList<Reservation> lesReservations = new ArrayList<>();
	            try {
	                uneConnexion.seConnecter();
	                Statement unStat = uneConnexion.getMaConnexion().createStatement();
	                ResultSet lesResultats = unStat.executeQuery(requete);
	                while (lesResultats.next()) {
	                    Reservation uneReservation = new Reservation(
	                            lesResultats.getInt("idReservation"),
	                            lesResultats.getDate("dateDeDebutR"),
	                            lesResultats.getDate("dateDeFinR"),
	                            lesResultats.getString("statutR"),
	                            lesResultats.getInt("idAppartement"),
	                            lesResultats.getInt("idLocataire")
	                    );
	                    lesReservations.add(uneReservation);
	                }
	                unStat.close();
	                uneConnexion.seDeconnecter();
	            } catch (SQLException exp) {
	                System.out.println("Erreur d'exécution de la requête : " + requete);
	            }
	            return lesReservations;
	        }

	        public static ArrayList<Reservation> selectLikeReservations(String filtre) {
	            String requete = "SELECT * FROM Reservation WHERE statutR LIKE '%" + filtre + "%' OR "
	                    + "idAppartement LIKE '%" + filtre + "%' OR idLocataire LIKE '%" + filtre + "%';";
	            ArrayList<Reservation> lesReservations = new ArrayList<>();
	            try {
	                uneConnexion.seConnecter();
	                Statement unStat = uneConnexion.getMaConnexion().createStatement();
	                ResultSet lesResultats = unStat.executeQuery(requete);
	                while (lesResultats.next()) {
	                    Reservation uneReservation = new Reservation(
	                            lesResultats.getInt("idReservation"),
	                            lesResultats.getDate("dateDeDebutR"),
	                            lesResultats.getDate("dateDeFinR"),
	                            lesResultats.getString("statutR"),
	                            lesResultats.getInt("idAppartement"),
	                            lesResultats.getInt("idLocataire")
	                    );
	                    lesReservations.add(uneReservation);
	                }
	                unStat.close();
	                uneConnexion.seDeconnecter();
	            } catch (SQLException exp) {
	                System.out.println("Erreur d'exécution de la requête : " + requete);
	            }
	            return lesReservations;
	        }
	       
	        
		
	     /**************************** Gestion des Station ********************/
	        public static void insertStation(Station uneStation) {
	            String requete = "INSERT INTO Station (idStation, nomS, idRegion) VALUES ('"
	                    + uneStation.getIdStation() + "', '"
	                    + uneStation.getNomS() + "', '"
	                    + uneStation.getIdRegion() + "');";
	            executerRequete(requete);
	        }

	        public static void deleteStation(int idStation) {
	            String requete = "DELETE FROM Station WHERE idStation = '" + idStation + "';";
	            executerRequete(requete);
	        }

	        public static void updateStation(Station uneStation) {
	            String requete = "UPDATE Station SET nomS = '" + uneStation.getNomS()
	                    + "', idRegion = '" + uneStation.getIdRegion()
	                    + "' WHERE idStation = '" + uneStation.getIdStation() + "';";
	            executerRequete(requete);
	        }

	        public static Station selectWhereStation(int idStation) {
	            String requete = "SELECT * FROM Station WHERE idStation = '" + idStation + "';";
	            Station uneStation = null;
	            try {
	                uneConnexion.seConnecter();
	                Statement unStat = uneConnexion.getMaConnexion().createStatement();
	                ResultSet lesResultats = unStat.executeQuery(requete);
	                if (lesResultats.next()) {
	                    uneStation = new Station(
	                            lesResultats.getInt("idStation"),
	                            lesResultats.getString("nomS"),
	                            lesResultats.getInt("idRegion")
	                    );
	                }
	                unStat.close();
	                uneConnexion.seDeconnecter();
	            } catch (SQLException exp) {
	                System.out.println("Erreur d'exécution de la requête : " + requete);
	            }
	            return uneStation;
	        }

	        public static ArrayList<Station> selectAllStations() {
	            String requete = "SELECT * FROM Station;";
	            ArrayList<Station> lesStations = new ArrayList<>();
	            try {
	                uneConnexion.seConnecter();
	                Statement unStat = uneConnexion.getMaConnexion().createStatement();
	                ResultSet lesResultats = unStat.executeQuery(requete);
	                while (lesResultats.next()) {
	                    Station uneStation = new Station(
	                            lesResultats.getInt("idStation"),
	                            lesResultats.getString("nomS"),
	                            lesResultats.getInt("idRegion")
	                    );
	                    lesStations.add(uneStation);
	                }
	                unStat.close();
	                uneConnexion.seDeconnecter();
	            } catch (SQLException exp) {
	                System.out.println("Erreur d'exécution de la requête : " + requete);
	            }
	            return lesStations;
	        }

	        public static ArrayList<Station> selectLikeStations(String filtre) {
	            String requete = "SELECT * FROM Station WHERE nomS LIKE '%" + filtre + "%' OR idRegion LIKE '%" + filtre + "%';";
	            ArrayList<Station> lesStations = new ArrayList<>();
	            try {
	                uneConnexion.seConnecter();
	                Statement unStat = uneConnexion.getMaConnexion().createStatement();
	                ResultSet lesResultats = unStat.executeQuery(requete);
	                while (lesResultats.next()) {
	                    Station uneStation = new Station(
	                            lesResultats.getInt("idStation"),
	                            lesResultats.getString("nomS"),
	                            lesResultats.getInt("idRegion")
	                    );
	                    lesStations.add(uneStation);
	                }
	                unStat.close();
	                uneConnexion.seDeconnecter();
	            } catch (SQLException exp) {
	                System.out.println("Erreur d'exécution de la requête : " + requete);
	            }
	            return lesStations;
	        }
	        

	        
	 /**************************** Gestion des Administrateur ********************/
	        public static void insertAdministrateur(Administrateur unAdministrateur) {
	            String requete = "INSERT INTO Administrateur (idAdministrateur, nomA, prenomA, username, passwordA) VALUES ('"
	                    + unAdministrateur.getIdAdministrateur() + "', '"
	                    + unAdministrateur.getNomA() + "', '"
	                    + unAdministrateur.getPrenomA() + "', '"
	                    + unAdministrateur.getUsername() + "', '"
	                    + unAdministrateur.getPasswordA() + "');";
	            executerRequete(requete);
	        }

	        public static void deleteAdministrateur(int idAdministrateur) {
	            String requete = "DELETE FROM Administrateur WHERE idAdministrateur = '" + idAdministrateur + "';";
	            executerRequete(requete);
	        }

	        public static void updateAdministrateur(Administrateur unAdministrateur) {
	            String requete = "UPDATE Administrateur SET nomA = '" + unAdministrateur.getNomA()
	                    + "', prenomA = '" + unAdministrateur.getPrenomA()
	                    + "', username = '" + unAdministrateur.getUsername()
	                    + "', passwordA = '" + unAdministrateur.getPasswordA()
	                    + "' WHERE idAdministrateur = '" + unAdministrateur.getIdAdministrateur() + "';";
	            executerRequete(requete);
	        }

	        public static Administrateur selectWhereAdministrateur(int idAdministrateur) {
	            String requete = "SELECT * FROM Administrateur WHERE idAdministrateur = '" + idAdministrateur + "';";
	            Administrateur unAdministrateur = null;
	            try {
	                uneConnexion.seConnecter();
	                Statement unStat = uneConnexion.getMaConnexion().createStatement();
	                ResultSet lesResultats = unStat.executeQuery(requete);
	                if (lesResultats.next()) {
	                    unAdministrateur = new Administrateur(
	                            lesResultats.getInt("idAdministrateur"),
	                            lesResultats.getString("nomA"),
	                            lesResultats.getString("prenomA"),
	                            lesResultats.getString("usernameA"),
	                            lesResultats.getString("passwordA")
	                    );
	                }
	                unStat.close();
	                uneConnexion.seDeconnecter();
	            } catch (SQLException exp) {
	                System.out.println("Erreur d'exécution de la requête : " + requete);
	            }
	            return unAdministrateur;
	        }
//
	        public static Administrateur selectWhereAdministrateur(String usernameA, String passwordA) {
	            String requete = "SELECT * FROM Administrateur WHERE usernameA = '" + usernameA + "' and passwordA='"+passwordA+"';";
	            Administrateur unAdministrateur =null;
	            try {
	                uneConnexion.seConnecter();
	                Statement unStat = uneConnexion.getMaConnexion().createStatement();
	                ResultSet lesResultats = unStat.executeQuery(requete);
	                if (lesResultats.next()) {
	                    unAdministrateur = new Administrateur(
	                            lesResultats.getInt("idAdministrateur"),
	                            lesResultats.getString("nomA"),
	                            lesResultats.getString("prenomA"),
	                            lesResultats.getString("usernameA"),
	                            lesResultats.getString("passwordA")
	                    );
	                }
	                unStat.close();
	                uneConnexion.seDeconnecter();
	            } catch (SQLException exp) {
	                System.out.println("Erreur d'exécution de la requête : " + requete);
	            }
	            return unAdministrateur;
	        }
	        //
	        public static ArrayList<Administrateur> selectAllAdministrateurs() {
	            String requete = "SELECT * FROM Administrateur;";
	            ArrayList<Administrateur> lesAdministrateurs = new ArrayList<>();
	            try {
	                uneConnexion.seConnecter();
	                Statement unStat = uneConnexion.getMaConnexion().createStatement();
	                ResultSet lesResultats = unStat.executeQuery(requete);
	                while (lesResultats.next()) {
	                    Administrateur unAdministrateur = new Administrateur(
	                            lesResultats.getInt("idAdministrateur"),
	                            lesResultats.getString("nomA"),
	                            lesResultats.getString("prenomA"),
	                            lesResultats.getString("usernameA"),
	                            lesResultats.getString("passwordA")
	                    );
	                    lesAdministrateurs.add(unAdministrateur);
	                }
	                unStat.close();
	                uneConnexion.seDeconnecter();
	            } catch (SQLException exp) {
	                System.out.println("Erreur d'exécution de la requête : " + requete);
	            }
	            return lesAdministrateurs;
	        }


public static ArrayList<Administrateur> selectLikeAdministrateur(String filtre) {
    String requete = "SELECT * FROM Administrateur WHERE idAdministrateur LIKE '%" + filtre + "%' OR " +
                     "nomA LIKE '%" + filtre + "%' OR " +
                     "prenomA LIKE '%" + filtre + "%' OR " +
                     "usernameA LIKE '%" + filtre + "%' OR " +
                     "passwordA LIKE '%" + filtre + "%';";
    ArrayList<Administrateur> lesAdministrateurs = new ArrayList<>();
    try {
        uneConnexion.seConnecter();
        Statement unStat = uneConnexion.getMaConnexion().createStatement();
        ResultSet lesResultats = unStat.executeQuery(requete);
        while (lesResultats.next()) {
            Administrateur unAdministrateur = new Administrateur(
                    lesResultats.getInt("idAdministrateur"),
                    lesResultats.getString("nomA"),
                    lesResultats.getString("prenomA"),
                    lesResultats.getString("usernameA"),
                    lesResultats.getString("passwordA")
            );
            lesAdministrateurs.add(unAdministrateur);
        }
        unStat.close();
        uneConnexion.seDeconnecter();
    } catch (SQLException exp) {
        System.out.println("Erreur d'exécution de la requête : " + requete);
    }
    return lesAdministrateurs;
}
	
	/******************** Autres méthodes ***************************/
	public static void executerRequete(String requete) {
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneConnexion.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
	}

}