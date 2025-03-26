package vue;

import controleur.Controleur;
import controleur.Equipement;
import controleur.Tableau;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

public class PanelEquipement extends PanelPrincipal implements ActionListener {
    private JPanel panelForm = new JPanel();
    private JTextField txtTypeEquipement = new JTextField();
    private JTextField txtDetailAppart = new JTextField();
    private JTextField txtIdAppartement = new JTextField();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JTable uneTable;
    private Tableau unTableau;

    public PanelEquipement() {
        super("Gestion des Equipements");

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
        this.panelForm.add(new JLabel("Type d'Equipement :"));
        this.panelForm.add(this.txtTypeEquipement);
        this.panelForm.add(new JLabel("Détail Appartement :"));
        this.panelForm.add(this.txtDetailAppart);
        this.panelForm.add(new JLabel("ID Appartement :"));
        this.panelForm.add(this.txtIdAppartement);
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        this.add(this.panelForm);

        // Ajout des écouteurs
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        // Tableau
        String entetes[] = {"ID", "Type d'Equipement", "Détail Appartement", "ID Appartement"};
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
                    txtTypeEquipement.setText(unTableau.getValueAt(numLigne, 1).toString());
                    txtDetailAppart.setText(unTableau.getValueAt(numLigne, 2).toString());
                    txtIdAppartement.setText(unTableau.getValueAt(numLigne, 3).toString());
                    btSupprimer.setVisible(true);
                    btValider.setText("Modifier");
                }
            }
        });
    }

    public Object[][] obtenirDonnees() {
        ArrayList<Equipement> lesEquipements = Controleur.selectAllEquipements();
        Object[][] matrice = new Object[lesEquipements.size()][4];

        int i = 0;
        for (Equipement unEquipement : lesEquipements) {
            matrice[i][0] = unEquipement.getIdEquipement();
            matrice[i][1] = unEquipement.getTypeDEquipement();
            matrice[i][2] = unEquipement.getDetailAppart();
            matrice[i][3] = unEquipement.getIdAppartement();
            i++;
        }
        return matrice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtTypeEquipement.setText("");
            this.txtDetailAppart.setText("");
            this.txtIdAppartement.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
        else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
            // Récupérer les champs saisis
            String typeEquipement = this.txtTypeEquipement.getText();
            String detailAppart = this.txtDetailAppart.getText();
            int idAppartement = Integer.parseInt(this.txtIdAppartement.getText());

            // Instancier la classe Equipement
            Equipement unEquipement = new Equipement(0, typeEquipement, detailAppart, idAppartement);

            // Insérer l'équipement dans la BDD
            Controleur.insertEquipement(unEquipement);

            // Afficher un message d'insertion réussie
            JOptionPane.showMessageDialog(this, "Insertion réussie de l'équipement.");
            
            // Actualiser l'affichage du tableau
            this.unTableau.setDonnees(this.obtenirDonnees());
            
            // Vider les champs
            this.txtTypeEquipement.setText("");
            this.txtDetailAppart.setText("");
            this.txtIdAppartement.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
        else if (e.getSource() == this.btSupprimer) {
            // Récupérer l'ID de l'équipement à supprimer
            int numLigne, idEquipement;
            numLigne = this.uneTable.getSelectedRow();
            idEquipement = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());

            int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer cet équipement ?", 
                    "Suppression de l'équipement", JOptionPane.YES_NO_OPTION);
            
            if (retour == 0) {
                // Supprimer de la base de données
                Controleur.deleteEquipement(idEquipement);
                
                // Actualiser l'affichage
                this.unTableau.setDonnees(this.obtenirDonnees());
                JOptionPane.showMessageDialog(this, "Suppression réussie de l'équipement.");
                
                // Vider les champs
                this.txtTypeEquipement.setText("");
                this.txtDetailAppart.setText("");
                this.txtIdAppartement.setText("");
                btSupprimer.setVisible(false);
                btValider.setText("Valider");
            }
        }
    }
}
