package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import controleur.Amnila;

public class VueGenerale extends JFrame implements ActionListener
{
	private JButton btActivite = new JButton("Activite");
	private JButton btAdministrateur = new JButton("Administrateur");
	private JButton btAppartement = new JButton("Appartement");
	private JButton btContrat = new JButton("Contrat");
	private JButton btEquipement = new JButton("Equipement");
	private JButton btLocataire = new JButton("Locataire");
	private JButton btPhoto = new JButton("Photo");
	private JButton btProprietaire = new JButton("Proprietaire");
	private JButton btRegion = new JButton("Region");
	private JButton btReservation = new JButton("Reservation");
	private JButton btStation = new JButton("Station");
	private JButton btQuitter = new JButton("Quitter");
	
	private JPanel panelMenu = new JPanel();
	private static PanelActivite unPanelActivite = new PanelActivite();
	private static PanelAdministrateur unPanelAdministrateur = new PanelAdministrateur();
	private static PanelAppartement unPanelAppartement = new PanelAppartement();
	private static PanelContrat unPanelContrat = new PanelContrat();
	private static PanelEquipement unPanelEquipement = new PanelEquipement();
	private static PanelLocataire unPanelLocataire = new PanelLocataire();
	private static PanelPhoto unPanelPhoto = new PanelPhoto();
	private static PanelProprietaire unPanelProprietaire = new PanelProprietaire();
	private static PanelRegion unPanelRegion = new PanelRegion();
	private static PanelReservation unPanelReservation = new PanelReservation();
	private static PanelStation unPanelStation = new PanelStation();
	
	public VueGenerale() {
		this.setTitle("Application Amnila 2025");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(100, 100, 1200, 700);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.darkGray);
		
		//construction du panel Menu
		this.panelMenu.setBackground(Color.darkGray);
		this.panelMenu.setBounds(0, 20, 1200, 50);
		this.panelMenu.setLayout(new GridLayout(2, 7));
		this.panelMenu.add(this.btActivite);
		this.panelMenu.add(this.btAdministrateur);
		this.panelMenu.add(this.btAppartement);
		this.panelMenu.add(this.btContrat);
		this.panelMenu.add(this.btEquipement);
		this.panelMenu.add(this.btLocataire);
		this.panelMenu.add(this.btPhoto);
		this.panelMenu.add(this.btProprietaire);
		this.panelMenu.add(this.btRegion);
		this.panelMenu.add(this.btReservation);
		this.panelMenu.add(this.btStation);
		this.panelMenu.add(this.btQuitter);
		this.add(this.panelMenu);
		
		//rendre les boutons écoutables
		this.btActivite.addActionListener(this);
		this.btAdministrateur.addActionListener(this);
		this.btAppartement.addActionListener(this);
		this.btContrat.addActionListener(this);
		this.btEquipement.addActionListener(this);
		this.btLocataire.addActionListener(this);
		this.btPhoto.addActionListener(this);
		this.btProprietaire.addActionListener(this);
		this.btRegion.addActionListener(this);
		this.btReservation.addActionListener(this);
		this.btStation.addActionListener(this);
		this.btQuitter.addActionListener(this);
		
		//ajouts des panels à la fenêtre
		this.add(this.unPanelActivite);
		this.add(this.unPanelAdministrateur);
		this.add(this.unPanelAppartement);
		this.add(this.unPanelContrat);
		this.add(this.unPanelEquipement);
		this.add(this.unPanelLocataire);
		this.add(this.unPanelPhoto);
		this.add(this.unPanelProprietaire);
		this.add(this.unPanelRegion);
		this.add(this.unPanelReservation);
		this.add(this.unPanelStation);
		
		this.setVisible(true);
	}
	public void afficherPanel(int choix) {
		this.unPanelActivite.setVisible(false);
		this.unPanelAdministrateur.setVisible(false);
		this.unPanelAppartement.setVisible(false);
		this.unPanelContrat.setVisible(false);
		this.unPanelEquipement.setVisible(false);
		this.unPanelLocataire.setVisible(false);
		this.unPanelPhoto.setVisible(false);
		this.unPanelProprietaire.setVisible(false);
		this.unPanelRegion.setVisible(false);
		this.unPanelReservation.setVisible(false);
		this.unPanelStation.setVisible(false);
		switch (choix) {
			case 1 : this.unPanelActivite.setVisible(true); break;
			case 2 : this.unPanelAdministrateur.setVisible(true); break;
			case 3 : this.unPanelAppartement.setVisible(true); break;
			case 4 : this.unPanelContrat.setVisible(true); break;
			case 5 : this.unPanelEquipement.setVisible(true); break;
			case 6 : this.unPanelLocataire.setVisible(true); break;
			case 7 : this.unPanelPhoto.setVisible(true); break;
			case 8 : this.unPanelProprietaire.setVisible(true); break;
			case 9 : this.unPanelRegion.setVisible(true); break;
			case 10 : this.unPanelReservation.setVisible(true); break;
			case 11 : this.unPanelStation.setVisible(true); break;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btQuitter) {
			int retour = JOptionPane.showConfirmDialog(this,
				"Voulez-vous quitter l'application ?",
				"Quitter l'application", JOptionPane.YES_NO_OPTION);
			if (retour == 0) {
				Amnila.rendreVisible(true);
				Amnila.creerVueGenerale(false);
			}
		} else if (e.getSource() == this.btActivite) {
			this.afficherPanel(1);
		} else if (e.getSource() == this.btAdministrateur) {
			this.afficherPanel(2);
		} else if (e.getSource() == this.btAppartement) {
			this.afficherPanel(3);
		} else if (e.getSource() == this.btContrat) {
			this.afficherPanel(4);
		} else if (e.getSource() == this.btEquipement) {
			this.afficherPanel(5);
		} else if (e.getSource() == this.btLocataire) {
			this.afficherPanel(6);
		} else if (e.getSource() == this.btPhoto) {
			this.afficherPanel(7);
		} else if (e.getSource() == this.btProprietaire) {
			this.afficherPanel(8);
		} else if (e.getSource() == this.btRegion) {
			this.afficherPanel(9);
		} else if (e.getSource() == this.btReservation) {
			this.afficherPanel(10);
		} else if (e.getSource() == this.btStation) {
			this.afficherPanel(11);
		}
	}
}
