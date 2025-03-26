package vue;

import controleur.Controleur;
import controleur.Tableau;
import controleur.Contrat;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

public class PanelContrat extends PanelPrincipal implements ActionListener {
    private JPanel panelForm = new JPanel();
    private JTextField txtDateSignature = new JTextField();
    private JTextField txtDateDebut = new JTextField();
    private JTextField txtDateFin = new JTextField();
    private JTextField txtArrhes = new JTextField();
    private JTextField txtSolde = new JTextField();
    private JTextField txtCaution = new JTextField();
    private JTextField txtIdAppartement = new JTextField();
    private JTextField txtIdReservation = new JTextField();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JTable uneTable;
    private Tableau unTableau;

    public PanelContrat() {
        super("Gestion des Contrats");

        // Bouton Supprimer
        this.btSupprimer.setBounds(40, 370, 300, 40);
        this.add(this.btSupprimer);
        this.btSupprimer.setVisible(false);
        this.btSupprimer.setBackground(Color.RED);
        this.btSupprimer.addActionListener(this);

        // Formulaire
        this.panelForm.setBackground(Color.LIGHT_GRAY);
        this.panelForm.setBounds(40, 80, 300, 320);
        this.panelForm.setLayout(new GridLayout(9, 2));
        this.panelForm.add(new JLabel("Date de Signature :"));
        this.panelForm.add(this.txtDateSignature);
        this.panelForm.add(new JLabel("Date de Début :"));
        this.panelForm.add(this.txtDateDebut);
        this.panelForm.add(new JLabel("Date de Fin :"));
        this.panelForm.add(this.txtDateFin);
        this.panelForm.add(new JLabel("Arrhes Payées :"));
        this.panelForm.add(this.txtArrhes);
        this.panelForm.add(new JLabel("Solde Payé :"));
        this.panelForm.add(this.txtSolde);
        this.panelForm.add(new JLabel("Caution Versée :"));
        this.panelForm.add(this.txtCaution);
        this.panelForm.add(new JLabel("ID Appartement :"));
        this.panelForm.add(this.txtIdAppartement);
        this.panelForm.add(new JLabel("ID Réservation :"));
        this.panelForm.add(this.txtIdReservation);
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        this.add(this.panelForm);

        // Ajout des écouteurs
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        // Tableau
        String entetes[] = {"ID", "Date de Signature", "Date de Début", "Date de Fin", "Arrhes Payées", "Solde Payé", "Caution Versée", "ID Appartement", "ID Réservation"};
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
                    txtDateSignature.setText(unTableau.getValueAt(numLigne, 1).toString());
                    txtDateDebut.setText(unTableau.getValueAt(numLigne, 2).toString());
                    txtDateFin.setText(unTableau.getValueAt(numLigne, 3).toString());
                    txtArrhes.setText(unTableau.getValueAt(numLigne, 4).toString());
                    txtSolde.setText(unTableau.getValueAt(numLigne, 5).toString());
                    txtCaution.setText(unTableau.getValueAt(numLigne, 6).toString());
                    txtIdAppartement.setText(unTableau.getValueAt(numLigne, 7).toString());
                    txtIdReservation.setText(unTableau.getValueAt(numLigne, 8).toString());
                    btSupprimer.setVisible(true);
                    btValider.setText("Modifier");
                }
            }
        });
    }

    public Object[][] obtenirDonnees() {
        ArrayList<Contrat> lesContrats = Controleur.selectAllContrats();
        Object[][] matrice = new Object[lesContrats.size()][9];

        int i = 0;
        for (Contrat unContrat : lesContrats) {
            matrice[i][0] = unContrat.getIdContrat();
            matrice[i][1] = unContrat.getDateDeSignatureC();
            matrice[i][2] = unContrat.getDateDeDebutC();
            matrice[i][3] = unContrat.getDateDeFinC();
            matrice[i][4] = unContrat.getArrhesPayees();
            matrice[i][5] = unContrat.getSoldePayee();
            matrice[i][6] = unContrat.getCautionVersee();
            matrice[i][7] = unContrat.getIdAppartement();
            matrice[i][8] = unContrat.getIdReservation();
            i++;
        }
        return matrice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtDateSignature.setText("");
            this.txtDateDebut.setText(""); 
            this.txtDateFin.setText("");
            this.txtArrhes.setText("");
            this.txtSolde.setText("");
            this.txtCaution.setText("");
            this.txtIdAppartement.setText("");
            this.txtIdReservation.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
        else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
            // Récupérer les champs saisis
            String dateSignatureStr = this.txtDateSignature.getText();
            String dateDebutStr = this.txtDateDebut.getText();
            String dateFinStr = this.txtDateFin.getText();
            double arrhes = Double.parseDouble(this.txtArrhes.getText());
            double solde = Double.parseDouble(this.txtSolde.getText());
            double caution = Double.parseDouble(this.txtCaution.getText());
            int idAppartement = Integer.parseInt(this.txtIdAppartement.getText());
            int idReservation = Integer.parseInt(this.txtIdReservation.getText());

            // Convertir les dates
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date dateSignature = null;
            Date dateDebut = null;
            Date dateFin = null;
            try {
                dateSignature = format.parse(dateSignatureStr);
                dateDebut = format.parse(dateDebutStr);
                dateFin = format.parse(dateFinStr);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Format de date invalide. Utilisez le format dd/MM/yyyy.");
                return;
            }
            
            // Instancier la classe Contrat
            Contrat unContrat = new Contrat(0, dateSignature, dateDebut, dateFin, arrhes, solde, caution, idAppartement, idReservation);

            // Insérer le contrat dans la BDD
            Controleur.insertContrat(unContrat);

            // Afficher un message d'insertion réussie
            JOptionPane.showMessageDialog(this, "Insertion réussie du Contrat.");
            
            // Actualiser l'affichage du tableau
            this.unTableau.setDonnees(this.obtenirDonnees());
            
            // Vider les champs
            this.txtDateSignature.setText("");
            this.txtDateDebut.setText("");
            this.txtDateFin.setText("");
            this.txtArrhes.setText("");
            this.txtSolde.setText("");
            this.txtCaution.setText("");
            this.txtIdAppartement.setText("");
            this.txtIdReservation.setText("");
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
        else if (e.getSource() == this.btSupprimer) {
            // Récupérer l'ID du contrat à supprimer
            int numLigne, idContrat;
            numLigne = this.uneTable.getSelectedRow();
            idContrat = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());

            int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer le Contrat ?", 
                    "Suppression du Contrat", JOptionPane.YES_NO_OPTION);
            
            if (retour == 0) {
                // Supprimer de la base de données
                Controleur.deleteContrat(idContrat);
                
                // Actualiser l'affichage
                this.unTableau.setDonnees(this.obtenirDonnees());
                JOptionPane.showMessageDialog(this, "Suppression réussie du contrat.");
                
                // Vider les champs
                this.txtDateSignature.setText("");
                this.txtDateDebut.setText("");
                this.txtDateFin.setText("");
                this.txtArrhes.setText("");
                this.txtSolde.setText("");
                this.txtCaution.setText("");
                this.txtIdAppartement.setText("");
                this.txtIdReservation.setText("");
                btSupprimer.setVisible(false);
                btValider.setText("Valider");
            }
        }
    }
}
