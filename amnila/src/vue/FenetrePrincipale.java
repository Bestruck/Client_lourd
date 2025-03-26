package vue;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import controleur.AppartementControleur;

public class FenetrePrincipale extends JFrame {
    private JTextArea textArea;

    public FenetrePrincipale() {
        setTitle("Liste des Appartements");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton btnAfficher = new JButton("Afficher Appartements");
        btnAfficher.addActionListener(e -> afficherAppartements());
        add(btnAfficher, BorderLayout.SOUTH);
    }

    private void afficherAppartements() {
        List<String> appartements = AppartementControleur.obtenirAppartements();
        textArea.setText(""); // On vide la zone avant d'afficher les nouvelles données
        for (String app : appartements) {
            textArea.append(app + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FenetrePrincipale().setVisible(true);
        });
    }
}
