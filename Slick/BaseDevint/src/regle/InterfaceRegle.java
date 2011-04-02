/**
 * 
 * Cette classe doit etre modifiee.
 * Le texte des touches specifiques doit etre modifiee.
 * 
 * 
 * Cette classe est un panel qui va contenir les 2 panels des regles.
 * C'est dans celle-ci que l'on va decrire les regles du jeu
 */


package regle;

import java.awt.BorderLayout;
import javax.swing.border.EtchedBorder;

import jeu.TouchesPanel;

import classesMeres.ClasseAbstraite;
import classesMeres.MainFrame;
import menuGraphique.MenuPanel;

public class InterfaceRegle extends ClasseAbstraite{

	/*
	 * Attributs
	 */
	private static final long serialVersionUID = 1L;
	private TouchesPanel panelTouches;
	private ReglePanel panelRegle;

	private boolean menuTouchesVisible = true;
	private MenuPanel menu;
	String regles = "Ecrivez ici vos règles"; // Vous devez definir vos regles.
	
	
	/**
	 * String a modifier ! !
	 */ 
	private String texteTouchesSpecifiques = "   Entree - ...\n" +
											"   Espace - ...\n" +
											"   Haut - ...\n" +
											"   Bas - ...\n" +
											"   Gauche - ...\n" + 
											"   Droite - ...\n";
	
	/*
	 * Constructeur
	 */
	public InterfaceRegle(MenuPanel p,MainFrame mainFrame)
	{	
		super("Regle",mainFrame);
		mainFrame.voix.stop();
		mainFrame.voix.playText("Rèègles");
		menu = p;
	        
		
		panelRegle = new ReglePanel();
		
		//Appelle la méthode qui va créer le contenu de la fenêtre
		rafraichirFrame();
	}
	
	
	/**
	 * 
	 * Méthode pour l'affichage de la fenêtre
	 *
	 */
	
	private void rafraichirFrame(){
		this.removeAll();
		
		// Choisi un type d'affichage du contentPane
		this.setLayout(new BorderLayout());
		this.setBorder(new EtchedBorder());
		
		panelTouches = new TouchesPanel();
		modifierTexteTouchesSpecifiques();
		
		// Touches à gauche (eventuellement) et plateau de jeu à droite
		if (menuTouchesVisible) {
			this.add(panelTouches, BorderLayout.WEST);
		}
		this.add(panelRegle, BorderLayout.CENTER);
  
		// Mettre le tout en place et l'afficher
		mainFrame.pack();
		this.setVisible(true);
	}

	private void menuTouches(){
		menuTouchesVisible = !menuTouchesVisible;
		rafraichirFrame();
	}

	/*
	 *  Permet de definir une action pour la touche Control dans le panel de jeu.
	 */
	public void ctrlPerformed() {
		menuTouches();		
	}
	
	public void stopPerformed() {
		mainFrame.voix.stop();
		mainFrame.voix.playText("Menuu");
		menu.activeListener();
		mainFrame.switchPanel(menu);
	}
	
	public void infoPerformed(){
		mainFrame.voix.playText("vous êtes dans le menu règles");
	}
	
	public void reglesPerformed(){
		mainFrame.voix.playText(regles);
	}
	
	/*
	 * Methode pour modifier le texte afficher dans le panel de rappel des touches.
	 * Ceci uniquement pour la partie des touches specifiques au jeu.
	 */
	public void modifierTexteTouchesSpecifiques(){
		panelTouches.setTexteTouchesSpecifiques(texteTouchesSpecifiques);
	}

}
