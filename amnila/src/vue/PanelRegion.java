package vue;

import controleur.Controleur;
import controleur.Region;
import controleur.Tableau;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.ArrayList;

public class PanelRegion extends PanelPrincipal implements ActionListener {
    private JPanel panelForm = new JPanel();
    private JTextField txtNom = new JTextField();
    private JTextField txtDepartement = new JTextField();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JTable uneTable;
    private Tableau unTableau;

    public PanelRegion() {
        super("Gestion des Régions");

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
        this.panelForm.add(new JLabel("Nom de la Région :"));
        this.panelForm.add(this.txtNom);
        this.panelForm.add(new JLabel("Département de la Région :"));
        this.panelForm.add(this.txtDepartement);
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        this.add(this.panelForm);

        // Ajout des écouteurs
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        // Tableau
        String entetes[] = {"ID", "Nom", "Département"};
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
                    txtDepartement.setText(unTableau.getValueAt(numLigne, 2).toString());
                    btSupprimer.setVisible(true);
                    btValider.setText("Modifier");
                }
            }
        });
    }

    public Object[][] obtenirDonnees() {
        ArrayList<Region> lesRegions = Controleur.selectAllRegions();
        Object[][] matrice = new Object[lesRegions.size()][3];

        int i = 0;
        for (Region uneRegion : lesRegions) {
            matrice[i][0] = uneRegion.getIdRegion();
            matrice[i][1] = uneRegion.getNomR();
            matrice[i][2] = uneRegion.getDepartementR();
            i++;
        }
        return matrice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtNom.setText("");
            this.txtDepartement.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
        else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
            // Récupérer les champs saisis
            String nom = this.txtNom.getText();
            String departement = this.txtDepartement.getText();

            // Instancier la classe Region
            Region uneRegion = new Region(0, nom, departement);

            // Insérer la région dans la BDD
            Controleur.insertRegion(uneRegion);

            // Afficher un message d'insertion réussie
            JOptionPane.showMessageDialog(this, "Insertion réussie de la région.");
            
            // Actualiser l'affichage du tableau
            this.unTableau.setDonnees(this.obtenirDonnees());
            
            // Vider les champs
            this.txtNom.setText("");
            this.txtDepartement.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
        else if (e.getSource() == this.btSupprimer) {
            // Récupérer l'ID de la région à supprimer
            int numLigne, idRegion;
            numLigne = this.uneTable.getSelectedRow();
            idRegion = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());

            int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer cette région ?", 
                    "Suppression de la région", JOptionPane.YES_NO_OPTION);
            
            if (retour == 0) {
                // Supprimer de la base de données
                Controleur.deleteRegion(idRegion);
                
                // Actualiser l'affichage
                this.unTableau.setDonnees(this.obtenirDonnees());
                JOptionPane.showMessageDialog(this, "Suppression réussie de la région.");
                
                // Vider les champs
                this.txtNom.setText("");
                this.txtDepartement.setText("");
                btSupprimer.setVisible(false);
                btValider.setText("Valider");
            }
        }
    }
}
