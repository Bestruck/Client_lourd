package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Locataire;
import controleur.Controleur;
import controleur.Tableau;

public class PanelLocataire extends PanelPrincipal implements ActionListener {
    
    private JPanel panelForm = new JPanel();
    private JPanel panelListe = new JPanel();
    
    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtAdresseEmail = new JTextField();
    private JTextField txtPassword = new JTextField();
    private JTextField txtTel = new JTextField();
    
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    
    private JTable uneTable;
    private Tableau unTableau;

    public PanelLocataire() {
        super("Gestion des Locataires");
        
        // Installation du bouton Supprimer
        this.btSupprimer.setBounds(40, 340, 300, 40);
        this.add(this.btSupprimer);
        this.btSupprimer.setVisible(false);
        this.btSupprimer.setBackground(Color.red);
        this.btSupprimer.addActionListener(this);

        // Installation du panel formulaire
        this.panelForm.setBackground(Color.lightGray);
        this.panelForm.setBounds(40, 80, 300, 220);
        this.panelForm.setLayout(new GridLayout(6, 2));
        
        this.panelForm.add(new JLabel("Nom Locataire :"));
        this.panelForm.add(this.txtNom);
        
        this.panelForm.add(new JLabel("Prénom Locataire :"));
        this.panelForm.add(this.txtPrenom);
        
        this.panelForm.add(new JLabel("Adresse Email :"));
        this.panelForm.add(this.txtAdresseEmail);
        
        this.panelForm.add(new JLabel("MDP :"));
        this.panelForm.add(this.txtPassword);
        
        this.panelForm.add(new JLabel("Téléphone :"));
        this.panelForm.add(this.txtTel);
        
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);

        // On ajoute le formulaire dans la vue
        this.add(this.panelForm);

        // Rendre les boutons écoutables
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);
        
        // Installation de la JTable
        String entetes[] = {"ID Locataire", "Nom", "Prénom", "Email", "Téléphone"};
        this.unTableau = new Tableau(this.obtenirDonnees(), entetes);
        this.uneTable = new JTable(this.unTableau);
        JScrollPane uneScroll = new JScrollPane(this.uneTable);
        uneScroll.setBounds(400, 80, 500, 240);
        this.add(uneScroll);
        
        // Implémentation du click sur une ligne de la table
        this.uneTable.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = uneTable.getSelectedRow();

                if (e.getClickCount() >= 1) {
                    txtNom.setText(unTableau.getValueAt(numLigne, 1).toString());
                    txtPrenom.setText(unTableau.getValueAt(numLigne, 2).toString());
                    txtAdresseEmail.setText(unTableau.getValueAt(numLigne, 3).toString());
                    txtTel.setText(unTableau.getValueAt(numLigne, 4).toString());

                    btSupprimer.setVisible(true);
                    btValider.setText("Modifier");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}

        });
    }

    		public Object[][] obtenirDonnees() {
        // Récupérer les locataires de la base de données
        ArrayList<Locataire> lesLocataires = Controleur.selectAllLocataires();
        // Création d'une matrice de données
        Object[][] matrice = new Object[lesLocataires.size()][5];
        int i = 0;
        for (Locataire unLocataire : lesLocataires) {
            matrice[i][0] = unLocataire.getIdLocataire();
            matrice[i][1] = unLocataire.getNomL();
            matrice[i][2] = unLocataire.getPrenomL();
            matrice[i][3] = unLocataire.getAdresseEmailL();
            matrice[i][4] = unLocataire.getTelephoneL();
            i++;
        }
        return matrice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtNom.setText("");
            this.txtPrenom.setText("");
            this.txtAdresseEmail.setText("");
            this.txtPassword.setText("");
            this.txtTel.setText("");

            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        } else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
            // Récupérer les champs saisis
            String nom = this.txtNom.getText();
            String prenom = this.txtPrenom.getText();
            String adresseEmail = this.txtAdresseEmail.getText();
            String password = this.txtPassword.getText();
            String tel = this.txtTel.getText();

            // Instancier la classe Locataire
            Locataire unLocataire = new Locataire(nom, prenom, adresseEmail, password, tel);

            // Insérer le locataire dans la BDD
            Controleur.insertLocataire(unLocataire);

            // Message d'insertion réussie
            JOptionPane.showMessageDialog(this, "Insertion réussie du locataire");

            // Actualiser l'affichage du tableau
            this.unTableau.setDonnees(this.obtenirDonnees());

            // Vider les champs
            this.txtNom.setText("");
            this.txtPrenom.setText("");
            this.txtAdresseEmail.setText("");
            this.txtPassword.setText("");
            this.txtTel.setText("");

            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        } else if (e.getSource() == this.btSupprimer) {
            // Récupérer l'id du locataire à supprimer
            int numLigne, idLocataire;
            numLigne = this.uneTable.getSelectedRow();
            idLocataire = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());

            int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer le locataire ?", "Suppression du locataire", JOptionPane.YES_NO_CANCEL_OPTION);
            if (retour == 0) {
                // Supprimer le locataire de la base de données
                Controleur.deleteLocataire(idLocataire);

                // Actualiser l'affichage
                this.unTableau.setDonnees(this.obtenirDonnees());
                JOptionPane.showConfirmDialog(this, "Suppression du locataire réussie");

                // Vider les champs
                this.txtNom.setText("");
                this.txtPrenom.setText("");
                this.txtAdresseEmail.setText("");
                this.txtPassword.setText("");
                this.txtTel.setText("");
                btSupprimer.setVisible(false);
                btValider.setText("Valider");
            }
        } else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
            // Récupérer les données y compris l'id du locataire
            int numLigne, idLocataire;
            numLigne = this.uneTable.getSelectedRow();
            idLocataire = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());

            String nom = this.txtNom.getText();
            String prenom = this.txtPrenom.getText();
            String adresseEmail = this.txtAdresseEmail.getText();
            String password = this.txtPassword.getText();
            String tel = this.txtTel.getText();

            // Modifier dans la base de données
            Locataire unLocataire = new Locataire(idLocataire, nom, prenom, adresseEmail, password, tel);
            Controleur.updateLocataire(unLocataire);

            // Actualiser l'affichage du tableau
            this.unTableau.setDonnees(this.obtenirDonnees());
            JOptionPane.showMessageDialog(this, "Modification réussie du locataire");

            // Message de confirmation et vider les champs
            this.txtNom.setText("");
            this.txtPrenom.setText("");
            this.txtAdresseEmail.setText("");
            this.txtPassword.setText("");
            this.txtTel.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
    }
}
