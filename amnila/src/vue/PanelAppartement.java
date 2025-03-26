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
import controleur.Appartement;
import controleur.Tableau;
public class PanelAppartement extends PanelPrincipal implements ActionListener {
    private JPanel panelForm = new JPanel();
    private JTextField txtTypeAppartement = new JTextField();
    private JTextField txtRue = new JTextField();
    private JTextField txtCodePostal = new JTextField();
    private JTextField txtVille = new JTextField();
    private JTextField txtImmeuble = new JTextField();
    private JTextField txtSurface = new JTextField();
    private JTextField txtPrixJournalier = new JTextField();
    private JTextField txtIdProprietaire = new JTextField();
    private JTextField txtIdRegion = new JTextField();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JTable uneTable;
    private Tableau unTableau;

    public PanelAppartement() {
        super("Gestion des Appartements");

        // Bouton Supprimer
        this.btSupprimer.setBounds(40, 370, 300, 40);
        this.add(this.btSupprimer);
        this.btSupprimer.setVisible(false);
        this.btSupprimer.setBackground(Color.RED);
        this.btSupprimer.addActionListener(this);

        // Formulaire
        this.panelForm.setBackground(Color.LIGHT_GRAY);
        this.panelForm.setBounds(40, 80, 300, 280);
        this.panelForm.setLayout(new GridLayout(10, 2, 10, 10)); // On rajoute un espacement entre les composants

        // Ajout des labels et des champs de texte dans le formulaire
        this.panelForm.add(new JLabel("Type Appartement :"));
        this.panelForm.add(this.txtTypeAppartement);
        
        this.panelForm.add(new JLabel("Rue :"));
        this.panelForm.add(this.txtRue);
        
        this.panelForm.add(new JLabel("Code Postal :"));
        this.panelForm.add(this.txtCodePostal);
        
        this.panelForm.add(new JLabel("Ville :"));
        this.panelForm.add(this.txtVille);
        
        this.panelForm.add(new JLabel("N° Immeuble :"));
        this.panelForm.add(this.txtImmeuble);
        
        this.panelForm.add(new JLabel("Surface :"));
        this.panelForm.add(this.txtSurface);
        
        this.panelForm.add(new JLabel("Prix Journalier :"));
        this.panelForm.add(this.txtPrixJournalier);
        
        this.panelForm.add(new JLabel("ID Propriétaire :"));
        this.panelForm.add(this.txtIdProprietaire);
        
        this.panelForm.add(new JLabel("ID Région :"));
        this.panelForm.add(this.txtIdRegion);

        // Ajouter les boutons annuler et valider à la fin du formulaire
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        
        this.add(this.panelForm);

        // Ajout des écouteurs
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        // Tableau
        String entetes[] = {"ID", "Type", "Rue", "Code Postal", "Ville", "Immeuble", "Surface", "Prix Journalier", "ID Propriétaire", "ID Région"};
        this.unTableau = new Tableau(this.obtenirDonnees(), entetes);
        this.uneTable = new JTable(this.unTableau);
        JScrollPane uneScroll = new JScrollPane(this.uneTable);
        uneScroll.setBounds(400, 80, 700, 440);
        this.add(uneScroll);

        // Clic sur le tableau
        this.uneTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = uneTable.getSelectedRow();
                if (numLigne != -1) {
                    txtTypeAppartement.setText(unTableau.getValueAt(numLigne, 1).toString());
                    txtRue.setText(unTableau.getValueAt(numLigne, 2).toString());
                    txtCodePostal.setText(unTableau.getValueAt(numLigne, 3).toString());
                    txtVille.setText(unTableau.getValueAt(numLigne, 4).toString());
                    txtImmeuble.setText(unTableau.getValueAt(numLigne, 5).toString());
                    txtSurface.setText(unTableau.getValueAt(numLigne, 6).toString());
                    txtPrixJournalier.setText(unTableau.getValueAt(numLigne, 7).toString());
                    txtIdProprietaire.setText(unTableau.getValueAt(numLigne, 8).toString());
                    txtIdRegion.setText(unTableau.getValueAt(numLigne, 9).toString());
                    btSupprimer.setVisible(true);
                    btValider.setText("Modifier");
                }
            }
        });
    }

    public Object[][] obtenirDonnees() {
        ArrayList<Appartement> lesAppartements = Controleur.selectAllAppartements();
        Object[][] matrice = new Object[lesAppartements.size()][10];

        int i = 0;
        for (Appartement unAppartement : lesAppartements) {
            matrice[i][0] = unAppartement.getIdAppartement();
            matrice[i][1] = unAppartement.getTypeDappartementA();
            matrice[i][2] = unAppartement.getRueA();
            matrice[i][3] = unAppartement.getCodePostalA();
            matrice[i][4] = unAppartement.getVilleA();
            matrice[i][5] = unAppartement.getNImmeubleA();
            matrice[i][6] = unAppartement.getSurfaceA();
            matrice[i][7] = unAppartement.getPrixJournalier();
            matrice[i][8] = unAppartement.getIdProprietaire();
            matrice[i][9] = unAppartement.getIdRegion();
            i++;
        }
        return matrice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtTypeAppartement.setText("");
            this.txtRue.setText("");
            this.txtCodePostal.setText("");
            this.txtVille.setText("");
            this.txtImmeuble.setText("");
            this.txtSurface.setText("");
            this.txtPrixJournalier.setText("");
            this.txtIdProprietaire.setText("");
            this.txtIdRegion.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
        else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
            // Récupérer les champs saisis
            String typeAppartement = this.txtTypeAppartement.getText();
            String rue = this.txtRue.getText();
            String codePostal = this.txtCodePostal.getText();
            String ville = this.txtVille.getText();
            String immeuble = this.txtImmeuble.getText();
            String surface = this.txtSurface.getText();
            double prixJournalier = Double.parseDouble(this.txtPrixJournalier.getText());
            int idProprietaire = Integer.parseInt(this.txtIdProprietaire.getText());
            int idRegion = Integer.parseInt(this.txtIdRegion.getText());

            // Instancier la classe Appartement
            Appartement unAppartement = new Appartement(typeAppartement, rue, codePostal, ville, immeuble, surface, prixJournalier, idProprietaire, idRegion);

            // Insérer l'appartement dans la BDD
            Controleur.insertAppartement(unAppartement);

            // Afficher un message d'insertion réussie
            JOptionPane.showMessageDialog(this, "Insertion réussie de l'Appartement.");

            // Actualiser l'affichage du tableau
            this.unTableau.setDonnees(this.obtenirDonnees());

            // Vider les champs
            this.txtTypeAppartement.setText("");
            this.txtRue.setText("");
            this.txtCodePostal.setText("");
            this.txtVille.setText("");
            this.txtImmeuble.setText("");
            this.txtSurface.setText("");
            this.txtPrixJournalier.setText("");
            this.txtIdProprietaire.setText("");
            this.txtIdRegion.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
        else if (e.getSource() == this.btSupprimer) {
            // Récupérer l'ID de l'appartement à supprimer
            int numLigne, idAppartement;
            numLigne = this.uneTable.getSelectedRow();
            idAppartement = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());

            int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer l'Appartement ?", 
                    "Suppression de l'Appartement", JOptionPane.YES_NO_OPTION);

            if (retour == 0) {
                // Supprimer de la base de données
                Controleur.deleteAppartement(idAppartement);

                // Actualiser l'affichage
                this.unTableau.setDonnees(this.obtenirDonnees());
                JOptionPane.showMessageDialog(this, "Suppression réussie de l'appartement.");

                // Vider les champs
                this.txtTypeAppartement.setText("");
                this.txtRue.setText("");
                this.txtCodePostal.setText("");
                this.txtVille.setText("");
                this.txtImmeuble.setText("");
                this.txtSurface.setText("");
                this.txtPrixJournalier.setText("");
                this.txtIdProprietaire.setText("");
                this.txtIdRegion.setText("");
                btSupprimer.setVisible(false);
                btValider.setText("Valider");
            }
        }
        else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
            // Récupérer les données y compris l'ID
            int numLigne, idAppartement;
            numLigne = this.uneTable.getSelectedRow();
            idAppartement = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());

            String typeAppartement = this.txtTypeAppartement.getText();
            String rue = this.txtRue.getText();
            String codePostal = this.txtCodePostal.getText();
            String ville = this.txtVille.getText();
            String immeuble = this.txtImmeuble.getText();
            String surface = this.txtSurface.getText();
            double prixJournalier = Double.parseDouble(this.txtPrixJournalier.getText());
            int idProprietaire = Integer.parseInt(this.txtIdProprietaire.getText());
            int idRegion = Integer.parseInt(this.txtIdRegion.getText());

            // Modifier dans la BDD
            Appartement unAppartement = new Appartement(idAppartement, typeAppartement, rue, codePostal, ville, immeuble, surface, prixJournalier, idProprietaire, idRegion);
            Controleur.updateAppartement(unAppartement);

            // Actualiser l'affichage du tableau
            this.unTableau.setDonnees(this.obtenirDonnees());
            JOptionPane.showMessageDialog(this, "Modification réussie de l'appartement.");

            // Vider les champs
            this.txtTypeAppartement.setText("");
            this.txtRue.setText("");
            this.txtCodePostal.setText("");
            this.txtVille.setText("");
            this.txtImmeuble.setText("");
            this.txtSurface.setText("");
            this.txtPrixJournalier.setText("");
            this.txtIdProprietaire.setText("");
            this.txtIdRegion.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
    }
}
