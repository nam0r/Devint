/**
 * Cette classe doit etre modifiee.
 * 
 * 
 * Cette classe est le panel principal du jeu.
 * C'est ici qu'il faut implementer l'interface 
 * graphique du jeu proprement dit.
 * 
 */


package jeu;


import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import classesMeres.Settings;



public class JeuPanel extends JPanel  {

		/*
		 * Attributs
		 */
		private static final long serialVersionUID = 1L;

		/*
		 * Constructeurs
		 */
		public JeuPanel(){
			configPanel();
			interfaceJeu();
		}
	
		
		public void configPanel(){
			setBorder(new LineBorder(Settings.couleurTexte,2));
			repaint();
		}		
		
		/*
		 * Rafraichit les donnees d'affichage
		 */
		public void repaint(){
			setBackground(Settings.couleurFond);
		}
		
		/*
		 * 
		 * Lorsqu'on lance l'application, tous les composants graphiques sont affichés.
		 * En particulier, la méthode ci-dessous implémente le contenu du cadre vert, qui est un JPanel.
		 * 
		 * Implémentez votre interface graphique ci-dessous, mais ne touchez pas aux méthodes précédentes
		 *  
		 */
		public void interfaceJeu(){
			
			setLayout(new BorderLayout());
			// TODO : Changer le Layout éventuellement, et coder votre interface graphique ici.
			
		}
		
		
		
}
