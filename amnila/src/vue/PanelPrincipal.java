package vue;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel 
{
	public PanelPrincipal(String titre) {
		this.setBounds(20,80,1150, 570 );
		this.setLayout(null);
		this.setBackground(Color.lightGray);
		
		JLabel lbTitre = new JLabel(titre); 
		lbTitre.setBounds(350, 20, 200,20);
		this.add(lbTitre);
		
		this.setVisible(false);
	}
}
