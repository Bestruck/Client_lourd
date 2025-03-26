package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import controleur.Administrateur;
import controleur.Controleur;
import controleur.Tableau;

public class PanelAdministrateur extends PanelPrincipal implements ActionListener {
    
    private JPanel panelForm = new JPanel(); 
    private JPanel panelListe = new JPanel();
    
    private JTextField txtNom = new JTextField(); 
    private JTextField txtPrenom = new JTextField();
    private JTextField txtUsername = new JTextField();
    private JTextField txtPassword = new JTextField();
    
    private JButton btAnnuler = new JButton("Annuler"); 
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    
    private JTable uneTable;
    private Tableau unTableau;

    public PanelAdministrateur() {
        super("Gestion des Administrateurs");
        
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
        
        this.panelForm.add(new JLabel("Nom Administrateur :"));
        this.panelForm.add(this.txtNom);
        
        this.panelForm.add(new JLabel("Prénom Administrateur :"));
        this.panelForm.add(this.txtPrenom);
        
        this.panelForm.add(new JLabel("Username :"));
        this.panelForm.add(this.txtUsername);
        
        this.panelForm.add(new JLabel("Password :"));
        this.panelForm.add(this.txtPassword);
        
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);

        // On ajoute le formulaire dans la vue
        this.add(this.panelForm);

        // Rendre les boutons écoutables
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);
        
        // Installation de la JTable
        String entetes[] = {"ID Administrateur", "Nom", "Prénom", "Username"};
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
                    txtUsername.setText(unTableau.getValueAt(numLigne, 3).toString());

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
        // Récupérer les administrateurs de la base de données
        ArrayList<Administrateur> lesAdministrateurs = Controleur.selectAllAdministrateurs();
        // Création d'une matrice de données
        Object[][] matrice = new Object[lesAdministrateurs.size()][4];
        int i = 0;
        for (Administrateur unAdministrateur : lesAdministrateurs) {
            matrice[i][0] = unAdministrateur.getIdAdministrateur();
            matrice[i][1] = unAdministrateur.getNomA();
            matrice[i][2] = unAdministrateur.getPrenomA();
            matrice[i][3] = unAdministrateur.getUsername();
            i++;
        }
        return matrice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtNom.setText("");
            this.txtPrenom.setText("");
            this.txtUsername.setText("");
            this.txtPassword.setText("");
            
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        } else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
            // Récupérer les champs saisis
            String nom = this.txtNom.getText();
            String prenom = this.txtPrenom.getText();
            String username = this.txtUsername.getText();
            String password = this.txtPassword.getText();

            // Instancier la classe Administrateur
            Administrateur unAdministrateur = new Administrateur(0, nom, prenom, username, password);

            // Insérer l'administrateur dans la BDD
            Controleur.insertAdministrateur(unAdministrateur);

            // Message d'insertion réussie
            JOptionPane.showMessageDialog(this, "Insertion réussie de l'administrateur");

            // Actualiser l'affichage du tableau
            this.unTableau.setDonnees(this.obtenirDonnees());

            // Vider les champs
            this.txtNom.setText("");
            this.txtPrenom.setText("");
            this.txtUsername.setText("");
            this.txtPassword.setText("");
            
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        } else if (e.getSource() == this.btSupprimer) {
            // Récupérer l'id de l'administrateur à supprimer
            int numLigne, idAdministrateur;
            numLigne = this.uneTable.getSelectedRow();
            idAdministrateur = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());

            int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer l'administrateur ?", "Suppression de l'administrateur", JOptionPane.YES_NO_CANCEL_OPTION);
            if (retour == 0) {
                // Supprimer l'administrateur de la base de données
                Controleur.deleteAdministrateur(idAdministrateur);

                // Actualiser l'affichage
                this.unTableau.setDonnees(this.obtenirDonnees());
                JOptionPane.showConfirmDialog(this, "Suppression de l'administrateur réussie");

                // Vider les champs
                this.txtNom.setText("");
                this.txtPrenom.setText("");
                this.txtUsername.setText("");
                this.txtPassword.setText("");
                btSupprimer.setVisible(false);
                btValider.setText("Valider");
            }
        } else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
            // Récupérer les données y compris l'id de l'administrateur
            int numLigne, idAdministrateur;
            numLigne = this.uneTable.getSelectedRow();
            idAdministrateur = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());

            String nom = this.txtNom.getText();
            String prenom = this.txtPrenom.getText();
            String username = this.txtUsername.getText();
            String password = this.txtPassword.getText();

            // Modifier dans la base de données
            Administrateur unAdministrateur = new Administrateur(idAdministrateur, nom, prenom, username, password);
            Controleur.updateAdministrateur(unAdministrateur);

            // Actualiser l'affichage du tableau
            this.unTableau.setDonnees(this.obtenirDonnees());
            JOptionPane.showMessageDialog(this, "Modification réussie de l'administrateur");

            // Message de confirmation et vider les champs
            this.txtNom.setText("");
            this.txtPrenom.setText("");
            this.txtUsername.setText("");
            this.txtPassword.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
    }
}
