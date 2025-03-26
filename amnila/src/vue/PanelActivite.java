package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

import controleur.Controleur;
import controleur.Activite;
import controleur.Tableau;

public class PanelActivite extends PanelPrincipal implements ActionListener {
    private JPanel panelForm = new JPanel();
    private JTextField txtIDStation = new JTextField();
    private JTextField txtNomActivite = new JTextField();
    private JTextField txtPrix = new JTextField();
    private JTextField txtDescription = new JTextField();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JTable uneTable;
    private Tableau unTableau;

    public PanelActivite() {
        super("Gestion des Activités");

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
        this.panelForm.add(new JLabel("ID Station :"));
        this.panelForm.add(this.txtIDStation);
        this.panelForm.add(new JLabel("Nom Activité :"));
        this.panelForm.add(this.txtNomActivite);
        this.panelForm.add(new JLabel("Prix :"));
        this.panelForm.add(this.txtPrix);
        this.panelForm.add(new JLabel("Description :"));
        this.panelForm.add(this.txtDescription);
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        this.add(this.panelForm);

        // Ajout des écouteurs
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        // Tableau
        String entetes[] = {"ID Activité", "ID Station", "Nom", "Prix", "Description"};
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
                    txtIDStation.setText(unTableau.getValueAt(numLigne, 1).toString());
                    txtNomActivite.setText(unTableau.getValueAt(numLigne, 2).toString());
                    txtPrix.setText(unTableau.getValueAt(numLigne, 3).toString());
                    txtDescription.setText(unTableau.getValueAt(numLigne, 4).toString());
                    btSupprimer.setVisible(true);
                    btValider.setText("Modifier");
                }
            }
        });
    }

    public Object[][] obtenirDonnees() {
        ArrayList<Activite> lesActivites = Controleur.selectAllActivites();
        Object[][] matrice = new Object[lesActivites.size()][5];

        int i = 0;
        for (Activite uneActivite : lesActivites) {
            matrice[i][0] = uneActivite.getIdActivite();
            matrice[i][1] = uneActivite.getIdStation();
            matrice[i][2] = uneActivite.getNomA();
            matrice[i][3] = uneActivite.getPrixA();
            matrice[i][4] = uneActivite.getDescriptionA();
            i++;
        }
        return matrice;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtIDStation.setText("");
            this.txtNomActivite.setText("");
            this.txtPrix.setText("");
            this.txtDescription.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
        else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
            int idStation = Integer.parseInt(this.txtIDStation.getText());
            String nomActivite = this.txtNomActivite.getText();
            double prix = Double.parseDouble(this.txtPrix.getText());
            String description = this.txtDescription.getText();

            Activite uneActivite = new Activite(idStation, nomActivite, prix, description);
            Controleur.insertActivite(uneActivite);
            JOptionPane.showMessageDialog(this, "Insertion réussie de l'Activité.");
            this.unTableau.setDonnees(this.obtenirDonnees());
            this.txtIDStation.setText("");
            this.txtNomActivite.setText("");
            this.txtPrix.setText("");
            this.txtDescription.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
        else if (e.getSource() == this.btSupprimer) {
            int numLigne = this.uneTable.getSelectedRow();
            int idActivite = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
            int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer l'Activité ?", "Suppression de l'Activité", JOptionPane.YES_NO_OPTION);

            if (retour == 0) {
                Controleur.deleteActivite(idActivite);
                this.unTableau.setDonnees(this.obtenirDonnees());
                JOptionPane.showMessageDialog(this, "Suppression réussie de l'activité.");
                this.txtIDStation.setText("");
                this.txtNomActivite.setText("");
                this.txtPrix.setText("");
                this.txtDescription.setText("");
                btSupprimer.setVisible(false);
                btValider.setText("Valider");
            }
        }
        else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
            int numLigne = this.uneTable.getSelectedRow();
            int idActivite = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
            int idStation = Integer.parseInt(this.txtIDStation.getText());
            String nomActivite = this.txtNomActivite.getText();
            double prix = Double.parseDouble(this.txtPrix.getText());
            String description = this.txtDescription.getText();

            Activite uneActivite = new Activite(idActivite, idStation, nomActivite, prix, description);
            Controleur.updateActivite(uneActivite);
            this.unTableau.setDonnees(this.obtenirDonnees());
            JOptionPane.showMessageDialog(this, "Modification réussie de l'activité.");
            this.txtIDStation.setText("");
            this.txtNomActivite.setText("");
            this.txtPrix.setText("");
            this.txtDescription.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
    }
}
