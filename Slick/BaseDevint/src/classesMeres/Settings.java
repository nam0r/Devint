/**
 * 
 * Cette classe n'a pas besoin d'etre modifiee.
 * 
 * 
 * Cette classe recele tous les attributs statiques qui vont servir 
 * aux differents composants du jeu.
 * En effet, par exemple, aucune couleur ne doit etre codee en dur.
 * L'attribut police doit etre utilise partout.
 */


package classesMeres;

import java.awt.Color;

public class Settings {
	
	/* 
	 * Ces attributs par tous les composants du jeu.
	 */
	public static Color couleurTexte = Color.white;
	public static Color couleurFond = new Color(10,0,150);
	public static Color couleurJ1 = new Color(255,0,0);
	public static Color couleurJ2 = new Color(250,250,0);
	public static Color couleurObjet = new Color(250,250,250);
	public static int taillePolice = 64;
	public static double rapidite = 0.8;
	public static int typeVoix = 1;	// La voix generale
	public static int typeVoix1 = 4; // La voix 1
	public static int typeVoix2 = 6; // La voix 2
	// Les 2 dernieres voix peuvent ne pas etre utilisees.

	/* 
	 * Ces attributs sont utilises lors d'un Backspace ou d'un Echap
	 * dans l'interface de configuration pour revenir au dernier profil.
	 */
	public static Color oldCouleurTexte = Color.white;
	public static Color oldCouleurFond = new Color(10,0,150);
	public static Color oldCouleurJ1 = new Color(255,0,0);
	public static Color oldCouleurJ2 = new Color(250,250,0);
	public static Color oldCouleurObjet = new Color(250,250,250);
	public static int oldTaillePolice = 64;
	public static double oldRapidite = 0.8;
	public static int oldTypeVoix = 1;
	public static int oldTypeVoix1 = 2;
	public static int oldTypeVoix2 = 3;
	
	public static String police = "Arial"; // Police a utiliser
	
}