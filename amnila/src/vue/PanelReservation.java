package vue;

import controleur.Controleur;
import controleur.Appartement;
import controleur.Locataire;
import controleur.Reservation;
import controleur.Tableau;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.ArrayList;


public class PanelReservation extends PanelPrincipal implements ActionListener {
    private JPanel panelForm = new JPanel();
    private JTextField txtDateDebut = new JTextField();
    private JTextField txtDateFin = new JTextField();
    private JTextField txtStatut = new JTextField();
    private JComboBox<String> cbAppartement = new JComboBox<>();
    private JComboBox<String> cbLocataire = new JComboBox<>();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JTable uneTable;
    private Tableau unTableau;

    public PanelReservation() {
        super("Gestion des Réservations");

        // Bouton Supprimer
        this.btSupprimer.setBounds(40, 370, 300, 40);
        this.add(this.btSupprimer);
        this.btSupprimer.setVisible(false);
        this.btSupprimer.setBackground(Color.RED);
        this.btSupprimer.addActionListener(this);

        // Formulaire
        this.panelForm.setBackground(Color.LIGHT_GRAY);
        this.panelForm.setBounds(40, 80, 300, 300);
        this.panelForm.setLayout(new GridLayout(6, 2));
        this.panelForm.add(new JLabel("Date de début :"));
        this.panelForm.add(this.txtDateDebut);
        this.panelForm.add(new JLabel("Date de fin :"));
        this.panelForm.add(this.txtDateFin);
        this.panelForm.add(new JLabel("Statut :"));
        this.panelForm.add(this.txtStatut);
        this.panelForm.add(new JLabel("Appartement :"));
        this.panelForm.add(this.cbAppartement);
        this.panelForm.add(new JLabel("Locataire :"));
        this.panelForm.add(this.cbLocataire);
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        this.add(this.panelForm);

        // Ajout des écouteurs
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        // Tableau
        String entetes[] = {"ID", "Date de début", "Date de fin", "Statut", "Appartement", "Locataire"};
        this.unTableau = new Tableau(this.obtenirDonnees(), entetes);
        this.uneTable = new JTable(this.unTableau);
        JScrollPane uneScroll = new JScrollPane(this.uneTable);
        uneScroll.setBounds(400, 80, 500, 240);
        this.add(uneScroll);

        // Clic sur le tableau
        this.uneTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = uneTable.getSelectedRow();
                if (numLigne != -1) {
                    txtDateDebut.setText(unTableau.getValueAt(numLigne, 1).toString());
                    txtDateFin.setText(unTableau.getValueAt(numLigne, 2).toString());
                    txtStatut.setText(unTableau.getValueAt(numLigne, 3).toString());
                    cbAppartement.setSelectedItem(unTableau.getValueAt(numLigne, 4).toString());
                    cbLocataire.setSelectedItem(unTableau.getValueAt(numLigne, 5).toString());
                    btSupprimer.setVisible(true);
                    btValider.setText("Modifier");
                }
            }
        });

        // Remplir les ComboBox avec les appartements et locataires
        remplirComboBox();
    }

    public Object[][] obtenirDonnees() {
        ArrayList<Reservation> lesReservations = Controleur.selectAllReservations();
        Object[][] matrice = new Object[lesReservations.size()][6];

        int i = 0;
        for (Reservation uneReservation : lesReservations) {
            matrice[i][0] = uneReservation.getIdReservation();
            matrice[i][1] = uneReservation.getDateDeDebutR();
            matrice[i][2] = uneReservation.getDateDeFinR();
            matrice[i][3] = uneReservation.getStatutR();
            matrice[i][4] = uneReservation.getIdAppartement(); // Afficher l'ID de l'appartement, ou un autre détail
            matrice[i][5] = uneReservation.getIdLocataire(); // Afficher l'ID du locataire, ou un autre détail
            i++;
        }
        return matrice;
    }

    public void remplirComboBox() {
        // Remplir la ComboBox des appartements
        ArrayList<Appartement> lesAppartements = Controleur.selectAllAppartements();
        for (Appartement appartement : lesAppartements) {
            cbAppartement.addItem(appartement.getIdAppartement() + " - " + appartement.getIdAppartement());
        }

        // Remplir la ComboBox des locataires
        ArrayList<Locataire> lesLocataires = Controleur.selectAllLocataires();
        for (Locataire locataire : lesLocataires) {
            cbLocataire.addItem(locataire.getIdLocataire() + " - " + locataire.getIdLocataire());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtDateDebut.setText("");
            this.txtDateFin.setText("");
            this.txtStatut.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
        else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
            // Récupérer les champs saisis
            String dateDebut = this.txtDateDebut.getText();
            String dateFin = this.txtDateFin.getText();
            String statut = this.txtStatut.getText();
            String appartement = (String) this.cbAppartement.getSelectedItem();
            String locataire = (String) this.cbLocataire.getSelectedItem();

            // Instancier la classe Reservation
            Reservation uneReservation = new Reservation(0, java.sql.Date.valueOf(dateDebut), java.sql.Date.valueOf(dateFin), statut, Integer.parseInt(appartement), Integer.parseInt(locataire));

            // Insérer la réservation dans la BDD
            Controleur.insertReservation(uneReservation);

            // Afficher un message d'insertion réussie
            JOptionPane.showMessageDialog(this, "Insertion réussie de la réservation.");
            
            // Actualiser l'affichage du tableau
            this.unTableau.setDonnees(this.obtenirDonnees());
            
            // Vider les champs
            this.txtDateDebut.setText("");
            this.txtDateFin.setText("");
            this.txtStatut.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
        else if (e.getSource() == this.btSupprimer) {
            // Récupérer l'ID de la réservation à supprimer
            int numLigne, idReservation;
            numLigne = this.uneTable.getSelectedRow();
            idReservation = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());

            int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer cette réservation ?", 
                    "Suppression de la réservation", JOptionPane.YES_NO_OPTION);
            
            if (retour == 0) {
                // Supprimer de la base de données
                Controleur.deleteReservation(idReservation);
                
                // Actualiser l'affichage
                this.unTableau.setDonnees(this.obtenirDonnees());
                JOptionPane.showMessageDialog(this, "Suppression réussie de la réservation.");
                
                // Vider les champs
                this.txtDateDebut.setText("");
                this.txtDateFin.setText("");
                this.txtStatut.setText("");
                btSupprimer.setVisible(false);
                btValider.setText("Valider");
            }
        }
    }
}
