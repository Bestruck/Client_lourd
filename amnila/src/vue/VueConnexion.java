package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Administrateur;
import controleur.Amnila;
import controleur.Controleur;

public class VueConnexion extends JFrame implements ActionListener {
    private JButton btSeConnecter = new JButton("Se Connecter");
    private JButton btAnnuler = new JButton("Annuler");
    private JTextField txtUsername = new JTextField();
    private JPasswordField txtMdp = new JPasswordField();
    
    private JPanel panelForm = new JPanel();

    public VueConnexion() {
        this.setTitle("Application CL 2025 Amnila");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(200, 200, 900, 400);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.lightGray);
        
        // ajout de l'image logo
        ImageIcon uneImage = new ImageIcon("src/images/logo_n&s.png");
        JLabel unLogo = new JLabel(uneImage);
        unLogo.setBounds(40, 40, 400, 300);
        this.add(unLogo);
        
        // construction du panel formulaire
        this.panelForm.setBounds(490, 40, 310, 250);
        this.panelForm.setBackground(Color.lightGray);
        this.panelForm.setLayout(new GridLayout(3, 2));
        this.panelForm.add(new JLabel("Username"));
        this.panelForm.add(this.txtUsername);
        
        this.panelForm.add(new JLabel("MDP"));
        this.panelForm.add(this.txtMdp);
        
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btSeConnecter);
        
        this.add(this.panelForm);

        this.btAnnuler.addActionListener(this);
        this.btSeConnecter.addActionListener(this);
        
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtUsername.setText("");
            this.txtMdp.setText("");
        } else if (e.getSource() == this.btSeConnecter) {
            String username = this.txtUsername.getText(); 
            String passwordA = new String(this.txtMdp.getPassword()); 
            
            // on vérifie la présence de l'Administrateur dans la BDD 
            Administrateur unAdministrateur = Controleur.selectWhereAdministrateur(username, passwordA); 
            if (unAdministrateur == null) {
                JOptionPane.showMessageDialog(this, "Veuillez vérifier vos identifiants !", 
                        "Erreur de Connexion", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Bienvenue Nom : " + unAdministrateur.getNomA()
                        + " Prénom : " + unAdministrateur.getPrenomA(), 
                        "Connexion à Amnila Application", JOptionPane.INFORMATION_MESSAGE);
                
                Amnila.rendreVisible(false); // fermeture de la connexion
                Amnila.creerVueGenerale(true); // ouverture du logiciel 
            }
        }
    }
}
