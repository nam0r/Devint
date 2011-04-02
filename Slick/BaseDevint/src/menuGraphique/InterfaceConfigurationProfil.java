/**
 * Cette classe ne doit pas etre modifiee.
 * 
 * Cette classe permet de gerer l'affichage de l'interface de configuration.
 * 
 */

package menuGraphique;


import java.awt.BorderLayout;

import javax.swing.border.EtchedBorder;

import jeu.InterfaceJeu;
import classesMeres.ClasseAbstraite;
import classesMeres.MainFrame;
import classesMeres.Settings;

public class InterfaceConfigurationProfil extends ClasseAbstraite {

	/**
	 * Attributs
	 */
	private static final long serialVersionUID = 1L;
	private MenuPanel menuPanel;
	private ConfigPanel configPanel;
	
	private int frameHeight = 600;
	private ClasseAbstraite currentPanel;
	private InterfaceJeu game; 
	
	
	/**
	 * constructeur
	 */
	public InterfaceConfigurationProfil(MenuPanel p,InterfaceJeu jeu,MainFrame mainFrame)
	{
		super(p.nomJeu,mainFrame);
		int panelTouchesWidth = 500;
		
		configPanel = new ConfigPanel(panelTouchesWidth,frameHeight,mainFrame,p);
		menuPanel = p;
		menuPanel.removeListener();
		game = jeu;
		currentPanel = menuPanel;
		mainFrame.voix.playText("interface de configuration");
		

		//Appelle la méthode qui va créer le contenu de la fenêtre
		rafraichirFrame();
	}
	
	
	/**
	 * 
	 * Méthode qui raffraichit l'affichage de la fenêtre
	 *
	 */
	private void rafraichirFrame(){
		removeAll(); //On vide le panel
				
		// Choisi un type d'affichage du contentPane
		setLayout(new BorderLayout());
		setBorder(new EtchedBorder());
		
		// Touches à gauche (eventuellement) et plateau de jeu à droite
		this.add(configPanel, BorderLayout.WEST);
		//panneau.add(demoPanel, BorderLayout.CENTER);
		this.add(currentPanel);
		mainFrame.pack();
	}

	public void actionPerformed() {
	}


	public void downPerformed() {
		if (configPanel.tailleDePolice > 8){
			configPanel.tailleDePolice--;
			configPanel.appliquer();	
			configPanel.peindre(currentPanel);
			configPanel.progressBar.setValue(configPanel.tailleDePolice);
		}
	}
	
	public void upPerformed() {
		if (configPanel.tailleDePolice < 108){
			configPanel.tailleDePolice++;
			configPanel.appliquer();	
			configPanel.peindre(currentPanel);
			configPanel.progressBar.setValue(configPanel.tailleDePolice);
		}
	}


	public void helpPerformed() {
		mainFrame.voix.playText("Vous pouvez configurer la voix, les couleurs et la taille des polices.");
	}
	
	public void touchesPerformed(){
		mainFrame.voix.playText("Espace pour valider, bakkspaysse pour annuler, controle pour changer la vue, touche 1 2 3 4 profils par défault");
	}
	
	public void infoPerformed(){
		mainFrame.voix.playText("Vous êtes dans l'interface de configuration");
	}


	public void stopPerformed() {
		// TODO Auto-generated method stub
		menuPanel.activeListener();
		backPerformed();
		try{
			configPanel.jf.dispose();
		}
		catch(Exception e){
			//cas par défaut
		}
		mainFrame.voix.playText("menuu");
		mainFrame.switchPanel(menuPanel);
	}
	
	public void spacePerformed(){
		System.out.println(Settings.taillePolice);
		configPanel.appliquer();
		configPanel.peindre(currentPanel);
	}

	
	public void backPerformed() {
		// TODO Auto-generated method stub
		configPanel.defaire();
		menuPanel.miseAJour();
		menuPanel.repaint();
		configPanel.miseAJourVoiceInterface();
	}


	public void ctrlPerformed(){
		if (currentPanel == menuPanel){
			currentPanel = game;
			game.miseAJour();
		}
		else {
			currentPanel = menuPanel;
			menuPanel.miseAJour();
		}
		rafraichirFrame();
	}
	
	/*
	  * Action associee a la touche 1
	  */
	 public void touche1Performed(){
		 configPanel.chargerProfil("NoirJaune");
	 }
	 
	 /*
	  * Action associee a la touche 2
	  */
	 public void touche2Performed(){
		 configPanel.chargerProfil("JauneNoirPetit");
	 }
	 
	 /*
	  * Action associee a la touche 3
	  */
	 public void touche3Performed(){
		 configPanel.chargerProfil("JauneBleu");
	 }
	 
	 /*
	  * Action associee a la touche 4
	  */
	 public void touche4Performed(){
		 configPanel.chargerProfil("BleuJaune");
	 }


}