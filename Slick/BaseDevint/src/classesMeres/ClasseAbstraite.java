/**
 * 
 * Cette classe doit etre modifie(ajout des nouvelles actions).
 * 
 * 
 * Cette classe est la classe mere de tous les panels.
 * Vous devez implementer par defaut les actions rajoutees 
 * dans la classe CommandeInterface ici (un corps vide suffit).
 */



package classesMeres;

import java.awt.Color;
import javax.swing.JPanel;



public class ClasseAbstraite extends JPanel implements CommandeInterface {

	/*
	 * Attributs 
	 */
	private static final long serialVersionUID = 2136228559804770708L;
	
	//La Frame parente
	protected MainFrame mainFrame;
	protected String titre;
	protected Color couleurTexte;
	protected Color couleurFond;
	protected Color couleurJ1;
	protected Color couleurJ2;
	protected Color couleurObjet;
	protected int taillePolice;
	protected double rapidite;
	protected int typeVoix1;
	protected int typeVoix2;
	protected int typeVoix;
	
	
	/*
	 * Constructeur
	 */
	public ClasseAbstraite(String title,MainFrame mainFrame){
		this.mainFrame = mainFrame;
		titre = title;
		setVisible(true);
		requestFocus();
		
	}
	
	/*
	 * Les attributs generaux sont recuperer de la classe Settings.
	 */
	public void miseAJour(){
		couleurFond = Settings.couleurFond;
		couleurJ1 = Settings.couleurJ1;
		couleurJ2 = Settings.couleurJ2;
		couleurObjet = Settings.couleurObjet;
		couleurTexte = Settings.couleurTexte;
		taillePolice = Settings.taillePolice;
		typeVoix1 = Settings.typeVoix1;
		typeVoix2 = Settings.typeVoix2;
		typeVoix = Settings.typeVoix;
		rapidite = Settings.rapidite;
	}

	public void enterPerformed() {
		// TODO Auto-generated method stub
		
	}

	public void backPerformed() {
		// TODO Auto-generated method stub
		
	}

	public void upPerformed() {
		// TODO Auto-generated method stub
		
	}
	
	public void downPerformed() {
		// TODO Auto-generated method stub
		
	}

	public void spacePerformed() {
		// TODO Auto-generated method stub
		
	}

	public void stopPerformed() {
		// TODO Auto-generated method stub
		mainFrame.stopPerformed();
	}


	public void helpPerformed() {
		// TODO Auto-generated method stub
		mainFrame.voix.playText("F 2 règles, F 3 scores, F 4 touches, F 5 info");
	}

	public void reglesPerformed() {
		// TODO Auto-generated method stub
		
	}

	public void scorePerformed() {
		// TODO Auto-generated method stub
		
	}

	public void touchesPerformed() {
		// TODO Auto-generated method stub
		
	}

	public void infoPerformed() {
		// TODO Auto-generated method stub
		
	}

	public void ctrlPerformed() {
		// TODO Auto-generated method stub
		
	}

	public void touche1Performed() {
		// TODO Auto-generated method stub
		
	}

	public void touche2Performed() {
		// TODO Auto-generated method stub
		
	}

	public void touche3Performed() {
		// TODO Auto-generated method stub
		
	}

	public void touche4Performed() {
		// TODO Auto-generated method stub
		
	}
}
