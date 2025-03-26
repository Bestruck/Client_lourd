package vue;

import controleur.Controleur;
import controleur.Station;
import controleur.Tableau;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.ArrayList;

public class PanelStation extends PanelPrincipal implements ActionListener {
    private JPanel panelForm = new JPanel();
    private JTextField txtNom = new JTextField();
    private JTextField txtRegion = new JTextField(); // Champs pour la région
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JTable uneTable;
    private Tableau unTableau;

    public PanelStation() {
        super("Gestion des Stations");

        // Bouton Supprimer
        this.btSupprimer.setBounds(40, 370, 300, 40);
        this.add(this.btSupprimer);
        this.btSupprimer.setVisible(false);
        this.btSupprimer.setBackground(Color.RED);
        this.btSupprimer.addActionListener(this);

        // Formulaire
        this.panelForm.setBackground(Color.LIGHT_GRAY);
        this.panelForm.setBounds(40, 80, 300, 200);
        this.panelForm.setLayout(new GridLayout(5, 2));
        this.panelForm.add(new JLabel("Nom de la Station :"));
        this.panelForm.add(this.txtNom);
        this.panelForm.add(new JLabel("ID de la Région :"));
        this.panelForm.add(this.txtRegion);
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        this.add(this.panelForm);

        // Ajout des écouteurs
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        // Tableau
        String entetes[] = {"ID", "Nom", "Région"};
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
                    txtNom.setText(unTableau.getValueAt(numLigne, 1).toString());
                    txtRegion.setText(unTableau.getValueAt(numLigne, 2).toString());
                    btSupprimer.setVisible(true);
                    btValider.setText("Modifier");
                }
            }
        });
    }

    public Object[][] obtenirDonnees() {
        ArrayList<Station> lesStations = Controleur.selectAllStations();
        Object[][] matrice = new Object[lesStations.size()][3];

        int i = 0;
        for (Station uneStation : lesStations) {
            matrice[i][0] = uneStation.getIdStation();
            matrice[i][1] = uneStation.getNomS();
            matrice[i][2] = uneStation.getIdRegion();
            i++;
        }
        return matrice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtNom.setText("");
            this.txtRegion.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
        else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
            // Récupérer les champs saisis
            String nom = this.txtNom.getText();
            int regionId = Integer.parseInt(this.txtRegion.getText());

            // Instancier la classe Station
            Station uneStation = new Station(0, nom, regionId);

            // Insérer la station dans la BDD
            Controleur.insertStation(uneStation);

            // Afficher un message d'insertion réussie
            JOptionPane.showMessageDialog(this, "Insertion réussie de la station.");
            
            // Actualiser l'affichage du tableau
            this.unTableau.setDonnees(this.obtenirDonnees());
            
            // Vider les champs
            this.txtNom.setText("");
            this.txtRegion.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
        else if (e.getSource() == this.btSupprimer) {
            // Récupérer l'ID de la station à supprimer
            int numLigne, idStation;
            numLigne = this.uneTable.getSelectedRow();
            idStation = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());

            int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer cette station ?", 
                    "Suppression de la station", JOptionPane.YES_NO_OPTION);
            
            if (retour == 0) {
                // Supprimer de la base de données
                Controleur.deleteStation(idStation);
                
                // Actualiser l'affichage
                this.unTableau.setDonnees(this.obtenirDonnees());
                JOptionPane.showMessageDialog(this, "Suppression réussie de la station.");
                
                // Vider les champs
                this.txtNom.setText("");
                this.txtRegion.setText("");
                btSupprimer.setVisible(false);
                btValider.setText("Valider");
            }
        }
        else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
            // Récupérer l'ID de la station à modifier
            int numLigne = this.uneTable.getSelectedRow();
            int idStation = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
            String nom = this.txtNom.getText();
            int regionId = Integer.parseInt(this.txtRegion.getText());

            // Modifier la station
            Station uneStation = new Station(idStation, nom, regionId);

            // Mettre à jour la station dans la BDD
            Controleur.updateStation(uneStation);

            // Actualiser l'affichage
            this.unTableau.setDonnees(this.obtenirDonnees());
            JOptionPane.showMessageDialog(this, "Modification réussie de la station.");

            // Vider les champs
            this.txtNom.setText("");
            this.txtRegion.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
    }
}
