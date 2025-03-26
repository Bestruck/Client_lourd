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
import controleur.Proprietaire;
import controleur.Tableau;

public class PanelProprietaire extends PanelPrincipal implements ActionListener {
    private JPanel panelForm = new JPanel();
    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JPasswordField txtMdp = new JPasswordField();
    private JTextField txtTelephone = new JTextField();
    private JTextField txtRib = new JTextField();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JTable uneTable;
    private Tableau unTableau;

    public PanelProprietaire() {
        super("Gestion des Propriétaires");

        // Bouton Supprimer
        this.btSupprimer.setBounds(40, 370, 300, 40);
        this.add(this.btSupprimer);
        this.btSupprimer.setVisible(false);
        this.btSupprimer.setBackground(Color.RED);
        this.btSupprimer.addActionListener(this);

        // Formulaire
        this.panelForm.setBackground(Color.LIGHT_GRAY);
        this.panelForm.setBounds(40, 80, 300, 280);
        this.panelForm.setLayout(new GridLayout(8, 2));
        this.panelForm.add(new JLabel("Nom Propriétaire :"));
        this.panelForm.add(this.txtNom);
        this.panelForm.add(new JLabel("Prénom Propriétaire :"));
        this.panelForm.add(this.txtPrenom);
        this.panelForm.add(new JLabel("Email :"));
        this.panelForm.add(this.txtEmail);
        this.panelForm.add(new JLabel("Mot de passe :"));
        this.panelForm.add(this.txtMdp);
        this.panelForm.add(new JLabel("Téléphone :"));
        this.panelForm.add(this.txtTelephone);
        this.panelForm.add(new JLabel("RIB :"));
        this.panelForm.add(this.txtRib);
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        this.add(this.panelForm);

        // Ajout des écouteurs
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        // Tableau
        String entetes[] = {"ID", "Nom", "Prénom", "Email", "Mot de passe", "Téléphone", "RIB"};
        this.unTableau = new Tableau(this.obtenirDonnees(), entetes);
        this.uneTable = new JTable(this.unTableau);
        JScrollPane uneScroll = new JScrollPane(this.uneTable);
        uneScroll.setBounds(400, 80, 720, 440);
        this.add(uneScroll);

        // Clic sur le tableau
        this.uneTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = uneTable.getSelectedRow();
                if (numLigne != -1) {
                    txtNom.setText(unTableau.getValueAt(numLigne, 1).toString());
                    txtPrenom.setText(unTableau.getValueAt(numLigne, 2).toString());
                    txtEmail.setText(unTableau.getValueAt(numLigne, 3).toString());
                    txtMdp.setText(unTableau.getValueAt(numLigne, 4).toString());
                    txtTelephone.setText(unTableau.getValueAt(numLigne, 5).toString());
                    txtRib.setText(unTableau.getValueAt(numLigne, 6).toString());
                    btSupprimer.setVisible(true);
                    btValider.setText("Modifier");
                }
            }
        });
    }

    public Object[][] obtenirDonnees() {
        ArrayList<Proprietaire> lesProprietaires = Controleur.selectAllProprietaires();
        Object[][] matrice = new Object[lesProprietaires.size()][7];

        int i = 0;
        for (Proprietaire unProprietaire : lesProprietaires) {
            matrice[i][0] = unProprietaire.getIdProprietaire();
            matrice[i][1] = unProprietaire.getNomP();
            matrice[i][2] = unProprietaire.getPrenomP();
            matrice[i][3] = unProprietaire.getAdresseEmailP();
            matrice[i][4] = unProprietaire.getPasswordP();
            matrice[i][5] = unProprietaire.getTelephoneP();
            matrice[i][6] = unProprietaire.getRib();
            i++;
        }
        return matrice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtNom.setText("");
            this.txtPrenom.setText(""); 
            this.txtEmail.setText("");
            this.txtMdp.setText("");
            this.txtTelephone.setText("");
            this.txtRib.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
        else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
            // Récupérer les champs saisis
            String nom = this.txtNom.getText(); 
            String prenom = this.txtPrenom.getText();
            String email = this.txtEmail.getText();
            String mdp = new String(this.txtMdp.getPassword());
            String telephone = this.txtTelephone.getText();
            String rib = this.txtRib.getText();
            
            // Instancier la classe Proprietaire
            Proprietaire unProprietaire = new Proprietaire(nom, prenom, email, mdp, rib, telephone);
            
            // Insérer le propriétaire dans la BDD
            Controleur.insertProprietaire(unProprietaire);
            
            // Afficher un message d'insertion réussie
            JOptionPane.showMessageDialog(this, "Insertion réussie du Propriétaire.");
            
            // Actualiser l'affichage du tableau
            this.unTableau.setDonnees(this.obtenirDonnees());
            this.uneTable.repaint();  // Ajouter cette ligne pour actualiser le tableau
            
            // Vider les champs
            this.txtNom.setText("");
            this.txtPrenom.setText("");
            this.txtEmail.setText("");
            this.txtMdp.setText("");
            this.txtTelephone.setText("");
            this.txtRib.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
        else if (e.getSource() == this.btSupprimer) {
            // Récupérer l'ID du propriétaire à supprimer
            int numLigne, idProprietaire;
            numLigne = this.uneTable.getSelectedRow();
            idProprietaire = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());

            int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer le Propriétaire ?", 
                    "Suppression du Propriétaire", JOptionPane.YES_NO_OPTION);
            
            if (retour == 0) {
                // Supprimer de la base de données
                Controleur.deleteProprietaire(idProprietaire);
                
                // Actualiser l'affichage
                this.unTableau.setDonnees(this.obtenirDonnees());
                this.uneTable.repaint();  // Ajouter cette ligne pour actualiser le tableau
                JOptionPane.showMessageDialog(this, "Suppression réussie du propriétaire.");
                
                // Vider les champs
                this.txtNom.setText("");
                this.txtPrenom.setText("");
                this.txtEmail.setText("");
                this.txtMdp.setText("");
                this.txtTelephone.setText("");
                this.txtRib.setText("");
                btSupprimer.setVisible(false);
                btValider.setText("Valider");
            }
        }
        else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
            // Récupérer les données y compris l'ID
            int numLigne, idProprietaire;
            numLigne = this.uneTable.getSelectedRow();
            idProprietaire = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());

            String nom = this.txtNom.getText();
            String prenom = this.txtPrenom.getText();
            String email = this.txtEmail.getText();
            String mdp = new String(this.txtMdp.getPassword());
            String telephone = this.txtTelephone.getText();
            String rib = this.txtRib.getText();
           

            // Modifier dans la BDD
            Proprietaire unProprietaire = new Proprietaire(idProprietaire, nom, prenom, email, mdp, telephone, rib);
            Controleur.updateProprietaire(unProprietaire);

            // Actualiser l'affichage du tableau
            this.unTableau.setDonnees(this.obtenirDonnees());
            this.uneTable.repaint();  // Ajouter cette ligne pour actualiser le tableau
            JOptionPane.showMessageDialog(this, "Modification réussie du propriétaire.");
            
            // Vider les champs
            this.txtNom.setText("");
            this.txtPrenom.setText("");
            this.txtEmail.setText("");
            this.txtMdp.setText("");
            this.txtTelephone.setText("");
            this.txtRib.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
    }
}
