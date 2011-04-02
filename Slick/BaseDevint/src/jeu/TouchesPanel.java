/**
 * Cette classe n'a pas besoin d'etre modifiee.
 * 
 * 
 * Cette classe est un panel de rappel des touches.
 * On peut le cacher pendant le jeu grace a l'action ctrlPerformed().
 */

package jeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import classesMeres.Settings;


public class TouchesPanel extends JPanel {

	/*
	 * Attributs
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea touchesClassiques;
	private JTextArea touchesSpecifiquesAuJeu;
	
	/* 
	 * Ces 2 strings seront affichees dans le panel de rappel des touches durant le jeu.
	 */
	private String texteTouchesClassiques = 
								"   Echap - Quitter \n" +
								"   F1 - Aide \n" +
								"   F2 - Règles \n" +
								"   F3 - Scores \n" +
								"   F4 - Touches \n" +
								"   F5 - Info \n" +
								"   Control - Cacher \n";
	
	private String texteTouchesSpecifiques =
								"   Entree - ...\n" +
								"   Espace - ...\n" +
								"   Haut - ...\n" +
								"   Bas - ...\n" +
								"   Gauche - ...\n" + 
								"   Droite - ...\n";
	
	/*
	 * Constructeurs
	 */
	
	public TouchesPanel(){
		configPanel();
	}

	
	public void configPanel(){
		touchesClassiques = new JTextArea("Touches classiques pas encore ecrites");
		touchesClassiques.setFont(new Font(Settings.police,1,Settings.taillePolice));
		touchesClassiques.setForeground(Settings.couleurTexte);
		touchesClassiques.setBackground(Settings.couleurFond);
		touchesSpecifiquesAuJeu = new JTextArea("Touches spécifiques pas encore ecrites");
		touchesSpecifiquesAuJeu.setFont(new Font(Settings.police,1,Settings.taillePolice));
		touchesSpecifiquesAuJeu.setForeground(Settings.couleurTexte);
		touchesSpecifiquesAuJeu.setBackground(Settings.couleurFond);
		setLayout(new GridLayout(2,1));
		setBackground(Settings.couleurFond);
		setBorder(new LineBorder(Settings.couleurObjet,2));
		remplirTouchesClassiques();
		remplirTouchesSpecifiquesAuJeu();
		
		
		// panel touchesClassiques
		JPanel explicationsPanel = new JPanel();
		explicationsPanel.setLayout(new BorderLayout());
		
		touchesClassiques.setEditable(false);
		explicationsPanel.setOpaque(false);
		explicationsPanel.setBorder(BorderFactory.createTitledBorder(
			         BorderFactory.createEtchedBorder(javax.swing.border.
			                  EtchedBorder.LOWERED),"Touches classiques",2,0,new Font("Georgia",Font.BOLD,22),Color.RED));
		explicationsPanel.add(touchesClassiques);	
		add(explicationsPanel);
			

		// panel touchesClassiques
		JPanel explicationsPanel1 = new JPanel();
		explicationsPanel1.setLayout(new BorderLayout());
		
		touchesSpecifiquesAuJeu.setEditable(false);
		explicationsPanel1.setOpaque(false);
		explicationsPanel1.setBorder(BorderFactory.createTitledBorder(
			         BorderFactory.createEtchedBorder(javax.swing.border.
			                  EtchedBorder.LOWERED),"Touches specifiques",2,0,new Font("Georgia",Font.BOLD,22),Color.RED));
		explicationsPanel1.add(touchesSpecifiquesAuJeu);	
		add(explicationsPanel1);

		
	}
	
	
	/*
	 * Cette méthode renvoie les touches générales, écrites dans la classe Settings.
	 */
	public void remplirTouchesClassiques(){		
		touchesClassiques.setText(texteTouchesClassiques);
	}
	
	/*
	 * Cette méthode renvoie les touches de votre jeu, écrites dans la classe Settings.
	 */
	public void remplirTouchesSpecifiquesAuJeu(){		
		touchesSpecifiquesAuJeu.setText(texteTouchesSpecifiques);
	}


	/*
	 * Cette methode sera appele par chaque panel qui utilise le panel de rappel des touches.
	 */
	public void setTexteTouchesSpecifiques(String texteTouchesSpecifiques) {
		this.texteTouchesSpecifiques = texteTouchesSpecifiques;
	}
}