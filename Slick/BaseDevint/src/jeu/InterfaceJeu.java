/**
 * 
 * Cette classe doit etre modifiee(implementation des actions propres au jeu).
 * Le texte des touches specifiques doit egalement etre modifiee.
 * 
 * 
 * Cette classe est un panel qui va contenir les 2 panels du jeu.
 * C'est dans celle-ci que l'on va implementer les actions des touches (comme ctrlPerformed()).
 */


package jeu;

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;


import classesMeres.ClasseAbstraite;
import classesMeres.MainFrame;
import menuGraphique.MenuPanel;

public class InterfaceJeu extends ClasseAbstraite{

	/*
	 * Attributs
	 */
	private static final long serialVersionUID = 1L;
	private TouchesPanel panelTouches;
	private JeuPanel panelJeu;

	private boolean menuTouchesVisible = true;
	private MenuPanel menu;
	
	
	/**
	 * String a modifier ! !
	 */ 
    private String texteTouchesSpecifiques = 
	"   Entree - ...\n" +
	"   Espace - ...\n" +
	"   Haut - ...\n" +
	"   Bas - ...\n" +
	"   Gauche - ...\n" + 
	"   Droite - ...\n";
	
	/*
	 * Constructeur
	 */
	public InterfaceJeu(MenuPanel p,MainFrame mainFrame)
	{
		
		super("Jeu",mainFrame);
		mainFrame.voix.stop();
		mainFrame.voix.playText("Bienvenue dans le jeu");
		menu = p;
		panelJeu = new JeuPanel();
		//Appelle la méthode qui va créer le contenu de la fenêtre
		rafraichirFrame();
	}
	
	public void stopPerformed() {
		mainFrame.voix.playText("Voulez-vous revenir au menu ? Entrée pour confirmer, Echapp si non.");
		int confirm = JOptionPane.showConfirmDialog(this, 
				"Etes-vous sûr de vouloir revenir au menu?",
				"Quitter",
				JOptionPane.OK_CANCEL_OPTION);
		if(confirm == JOptionPane.OK_OPTION){
			mainFrame.voix.stop();
			mainFrame.voix.playText("Menuu");
			menu.activeListener();
			mainFrame.switchPanel(menu);
		}
	}
	
	
	/**
	 * 
	 * Méthode pour rafraichir l'affichage de la fenetre une fois qu'on cache le menu.
	 *
	 */
	private void rafraichirFrame(){
		this.removeAll();
		panelTouches = new TouchesPanel();
		modifierTexteTouchesSpecifiques();
		panelJeu.repaint();
		// Choisi un type d'affichage du contentPane
		this.setLayout(new BorderLayout());
		this.setBorder(new EtchedBorder());
		
		// Touches à gauche (eventuellement) et plateau de jeu à droite
		if (menuTouchesVisible) {
			this.add(panelTouches, BorderLayout.WEST);
		}
		this.add(panelJeu, BorderLayout.CENTER);
  
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
	
	public void miseAJour(){
		rafraichirFrame();
	}
	
	public void couperSon(){
		mainFrame.voix.stop();
		mainFrame.voix1.stop();
		mainFrame.voix2.stop();
	}
	
	/*
	 * Methode pour modifier le texte afficher dans le panel de rappel des touches.
	 * Ceci uniquement pour la partie des touches specifiques au jeu.
	 */
	public void modifierTexteTouchesSpecifiques(){
		panelTouches.setTexteTouchesSpecifiques(texteTouchesSpecifiques);
	}
		
}
