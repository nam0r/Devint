/**
 * 
 * Cette classe doit etre modifiee.
 * 
 * Cette classe est le panel principal des regles.
 * C'est ici qu'il faut implementer l'interface 
 * graphique affichant les regles. 
 * 
 */


package regle;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import classesMeres.Settings;



public class ReglePanel extends JPanel  {

		/**
		 * Attributs
		 */
		private static final long serialVersionUID = 1L;
		private JPanel but,regles; 
		/**
		 * Constructeurs
		 */
		public ReglePanel(){
			configPanel();
			interfaceRegle();
		}
	
		
		public void configPanel(){
			setBorder(new LineBorder(Settings.couleurTexte,2));
			setBackground(Settings.couleurFond);
			//Rappel: N'utiliser pas de couleurs en dur
		}		
		
		/**
		 * 
		 * Lorsqu'on lance l'application, tous les composants graphiques sont affichés.
		 * En particulier, la méthode ci-dessous implémente le contenu du cadre vert, qui est un JPanel.
		 * 
		 * Implémentez votre interface graphique ci-dessous, mais ne touchez pas aux méthodes précédentes
		 *  
		 */
		public void interfaceRegle(){
			
			// TODO : Changer le Layout éventuellement, et coder votre interface graphique ici.
			
			setLayout(new GridLayout(2,1));
			but = new JPanel();
			but.setBackground(Settings.couleurFond);
			but.setBorder(new LineBorder(Settings.couleurTexte,3));
			regles = new JPanel();
			regles.setBackground(Settings.couleurFond);
			regles.setBorder(new LineBorder(Settings.couleurTexte,3));
			add(but,BorderLayout.NORTH);
			add(regles,BorderLayout.SOUTH);
			
			
			//A effacer
			JTextArea aFaire = new JTextArea();
			aFaire.setOpaque(false);
			aFaire.setFocusable(false);
			aFaire.setForeground(Settings.couleurTexte);
			// Rappel: N'utilisez pas de couleurs en dur
			aFaire.setFont(new Font(Settings.police,1,Settings.taillePolice));
			aFaire.setText("Voici le but de mon jeu.\n");
	// 		aFaire.setText("  Décrivez ici le but \n      de votre jeu.");
			but.add(aFaire, BorderLayout.CENTER);
			
			
			//A effacer
			JTextArea aFaire2 = new JTextArea();
			aFaire2.setOpaque(false);
			aFaire2.setFocusable(false);
			aFaire2.setForeground(Settings.couleurTexte);
			// Rappel: N'utiliser pas de couleurs en dur
			aFaire2.setFont(new Font(Settings.police,1,Settings.taillePolice));
			aFaire2.setText("   Décrivez ici les règles \n           de votre jeu.");
			regles.add(aFaire2, BorderLayout.CENTER);
	
		}
}
