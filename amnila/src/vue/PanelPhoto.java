package vue;

import controleur.Controleur;
import controleur.Photo;
import controleur.Tableau;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.ArrayList;

public class PanelPhoto extends PanelPrincipal implements ActionListener {
    private JPanel panelForm = new JPanel();
    private JTextField txtChemin = new JTextField();
    private JTextField txtDescription = new JTextField();
    private JTextField txtIdAppartement = new JTextField();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JTable uneTable;
    private Tableau unTableau;

    public PanelPhoto() {
        super("Gestion des Photos");

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
        this.panelForm.add(new JLabel("Chemin de la Photo :"));
        this.panelForm.add(this.txtChemin);
        this.panelForm.add(new JLabel("Description de la Photo :"));
        this.panelForm.add(this.txtDescription);
        this.panelForm.add(new JLabel("ID Appartement :"));
        this.panelForm.add(this.txtIdAppartement);
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        this.add(this.panelForm);

        // Ajout des écouteurs
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        // Tableau
        String entetes[] = {"ID", "Chemin", "Description", "ID Appartement"};
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
                    txtChemin.setText(unTableau.getValueAt(numLigne, 1).toString());
                    txtDescription.setText(unTableau.getValueAt(numLigne, 2).toString());
                    txtIdAppartement.setText(unTableau.getValueAt(numLigne, 3).toString());
                    btSupprimer.setVisible(true);
                    btValider.setText("Modifier");
                }
            }
        });
    }

    public Object[][] obtenirDonnees() {
        ArrayList<Photo> lesPhotos = Controleur.selectAllPhotos();
        Object[][] matrice = new Object[lesPhotos.size()][4];

        int i = 0;
        for (Photo unePhoto : lesPhotos) {
            matrice[i][0] = unePhoto.getIdPhoto();
            matrice[i][1] = unePhoto.getChemin();
            matrice[i][2] = unePhoto.getDescriptionP();
            matrice[i][3] = unePhoto.getIdAppartement();
            i++;
        }
        return matrice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtChemin.setText("");
            this.txtDescription.setText("");
            this.txtIdAppartement.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
        else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
            // Récupérer les champs saisis
            String chemin = this.txtChemin.getText();
            String description = this.txtDescription.getText();
            int idAppartement = Integer.parseInt(this.txtIdAppartement.getText());

            // Instancier la classe Photo
            Photo unePhoto = new Photo(0, chemin, description, idAppartement);

            // Insérer la photo dans la BDD
            Controleur.insertPhoto(unePhoto);

            // Afficher un message d'insertion réussie
            JOptionPane.showMessageDialog(this, "Insertion réussie de la photo.");
            
            // Actualiser l'affichage du tableau
            this.unTableau.setDonnees(this.obtenirDonnees());
            
            // Vider les champs
            this.txtChemin.setText("");
            this.txtDescription.setText("");
            this.txtIdAppartement.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
        else if (e.getSource() == this.btSupprimer) {
            // Récupérer l'ID de la photo à supprimer
            int numLigne, idPhoto;
            numLigne = this.uneTable.getSelectedRow();
            idPhoto = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());

            int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer cette photo ?", 
                    "Suppression de la photo", JOptionPane.YES_NO_OPTION);
            
            if (retour == 0) {
                // Supprimer de la base de données
                Controleur.deletePhoto(idPhoto);
                
                // Actualiser l'affichage
                this.unTableau.setDonnees(this.obtenirDonnees());
                JOptionPane.showMessageDialog(this, "Suppression réussie de la photo.");
                
                // Vider les champs
                this.txtChemin.setText("");
                this.txtDescription.setText("");
                this.txtIdAppartement.setText("");
                btSupprimer.setVisible(false);
                btValider.setText("Valider");
            }
        }
    }
}
