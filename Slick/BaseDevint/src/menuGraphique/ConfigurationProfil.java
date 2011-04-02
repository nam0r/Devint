/**
 * 
 * Cette classe ne doit pas etre modifie.
 * 
 * Cette classe permet d'enregistrer/charger les profils serialises.
 * Elle contient egalement les profils par defaut.
 *
 */


package menuGraphique;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.Color;
import java.lang.Double;
import java.io.*;

import classesMeres.Settings;

public class ConfigurationProfil {
	
	/**
	 * Attributs
	 */
	private int  couleurTexte, couleurFond, couleurJoueur1, couleurJoueur2, couleurObjet, taillePolice, typeVoix, typeVoix1, typeVoix2;
	private double rapidite;
	@SuppressWarnings("unused")
	private String nomProfil;
	
	// Le fichier de profil général
	private final String directory = "c:/DevintProfil/";
	private final String file="c:/DevintProfil/profils.txt";
	
	private final String fileJeu="../ressources/fileJeu.txt";
	
	// Les profils par defaut
	private final String profil1 = "NoirJaune;-16777216;-256;-65536;-16751104;-16737793;64;1;4;6;0.8;";
	private final String profil2 = "JauneNoirPetit;-256;-16777216;-65536;-16751104;-16737793;24;1;4;6;0.8;";
	private final String profil3 = "JauneBleu;-256;-16776961;-65536;-16751104;-16737793;64;1;4;6;0.8;";
	private final String profil4 = "BleuJaune;-16776961;-256;-65536;-16751104;-16737793;64;1;4;6;0.8;";
	
	/**
	 * constructeurs
	 */
	public ConfigurationProfil(){
		File dir = new File(directory);
		File fichier = new File(file);
		File fichierJeu = new File(fileJeu);
		try {
			dir.mkdir();
			fichier.createNewFile();
			fichierJeu.createNewFile();
			ecrireDansFichier(profil1);
			ecrireDansFichier(profil2);
			ecrireDansFichier(profil3);
			ecrireDansFichier(profil4);
		} catch (IOException e) {
			e.getMessage();
		}
	}

	/*
	 * 
	 * Setters
	 * 
	 */


	private void setCouleurFond(int cf) {
		couleurFond = cf;
	}

	private void setCouleurJoueur1(int cj1) {
		couleurJoueur1 = cj1;
	}

	private void setCouleurJoueur2(int cj2) {
		couleurJoueur2 = cj2;
	}

	private void setCouleurObjet(int co) {
		couleurObjet = co;
	}

	private void setCouleurTexte(int ct) {
		couleurTexte = ct;
	}

	private void setNomProfil(String nom) {
		nomProfil = nom;
	}

	private void setTypeVoix(int voix) {
		typeVoix = voix;
	}
	
	private void setTypeVoix1(int voix) {
		typeVoix1 = voix;
	}
	
	private void setTypeVoix2(int voix) {
		typeVoix2 = voix;
	}
	
	private void setRapidite(double speed) {
		rapidite = speed;
		//ConfigFile.changer("RAPIDITE",""+speed);
	}
	
	private void setTaillePolice(int taille) {
		taillePolice = taille;
	}
	
	private Color convertir(int couleur){
		return new Color(couleur);
	}

	private void miseAJour(){
		Settings.couleurFond = convertir(couleurFond);
		Settings.couleurJ1 = convertir(couleurJoueur1);
		Settings.couleurJ2 = convertir(couleurJoueur2);
		Settings.couleurObjet = convertir(couleurObjet);
		Settings.couleurTexte = convertir(couleurTexte);
		Settings.taillePolice = taillePolice;
		Settings.typeVoix = typeVoix;
		Settings.typeVoix1 = typeVoix1;
		Settings.typeVoix2 = typeVoix2;
		Settings.rapidite = rapidite;
	}
	
	private void miseAJourOld(){
		Settings.oldCouleurFond = convertir(couleurFond);
		Settings.oldCouleurJ1 = convertir(couleurJoueur1);
		Settings.oldCouleurJ2 = convertir(couleurJoueur2);
		Settings.oldCouleurObjet = convertir(couleurObjet);
		Settings.oldCouleurTexte = convertir(couleurTexte);
		Settings.oldTaillePolice = taillePolice;
		Settings.oldTypeVoix = typeVoix;
		Settings.oldTypeVoix1 = typeVoix1;
		Settings.oldTypeVoix2 = typeVoix2;
		Settings.oldRapidite = rapidite;
	}
	
	private void chargerSettings(){
		couleurFond = Settings.couleurFond.getRGB();
		couleurJoueur1 = Settings.couleurJ1.getRGB();
		couleurJoueur2 = Settings.couleurJ2.getRGB();
		couleurObjet = Settings.couleurObjet.getRGB();
		couleurTexte = Settings.couleurTexte.getRGB();
		taillePolice = Settings.taillePolice;
		typeVoix = Settings.typeVoix;
		typeVoix1 = Settings.typeVoix1;
		typeVoix2 = Settings.typeVoix2;
		rapidite = Settings.rapidite;
	}
	
	
	/*
	 * Sérialiser un profil dans une String, et le déserialiser en objets String et int(s).
	 */
	

	/**
	 * Méthode qui met dans une String tous les attributs de la classe courante séparés par des ";"
	 * @return
	 */
	private String serialize(String nomDuProfil){
		chargerSettings();
		String serializedProfil = "" + nomDuProfil + ";" + couleurTexte + ";" 
							+ couleurFond + ";" + couleurJoueur1 + ";" + couleurJoueur2 + ";" 
							+ couleurObjet + ";" + taillePolice + ";" + typeVoix + ";"
							+ typeVoix1  + ";" + typeVoix2  + ";" + rapidite + ";";
		return serializedProfil;
	}

	
	/**
	 * Méthode qui met dans une ArrayList tous les éléments d'une String séparés par des ";".
	 * 
	 * @param serializedProfil
	 * @return
	 */
	private ArrayList<String> deserialize(String serializedProfil){
		
		StringTokenizer st = new StringTokenizer(serializedProfil,";");
		ArrayList<String> elements = new ArrayList<String>();
		while(st.hasMoreTokens()) {
			elements.add( ((String)st.nextToken()));
		}
		return elements;
	}	

	
	/*
	 * Affecter les données d'une Arraylist bien formée à la classe
	 */
	
		
	/**
	 * Méthode qui affecte les données de l'ArrayList aux attributs de la Classe
	 * 
	 * @param elements
	 */
	private void setProfil(ArrayList<String> elements){
		// ici, element contient normalement : nomProfil / c1 / c2 / c3 / c4 / taillePolice (en String)/voix/rapidite
		// test : est-ce qu'on a bien 8 éléments dans "elements" ?
		if (elements.size() == 11 ) {
			setNomProfil(elements.get(0));
			setCouleurTexte(Integer.parseInt(elements.get(1)));
			setCouleurFond(Integer.parseInt(elements.get(2)));
			setCouleurJoueur1(Integer.parseInt(elements.get(3)));
			setCouleurJoueur2(Integer.parseInt(elements.get(4)));
			setCouleurObjet(Integer.parseInt(elements.get(5)));
			setTaillePolice(Integer.parseInt(elements.get(6)));
			setTypeVoix(Integer.parseInt(elements.get(7)));
			setTypeVoix1(Integer.parseInt(elements.get(8)));
			setTypeVoix2(Integer.parseInt(elements.get(9)));
			setRapidite(Double.parseDouble(elements.get(10)));
			//System.out.println("parseDouble: "+Double.parseDouble(elements.get(10)));
		} else {
			System.out.println("Erreur : mauvais format de profil.");
		}	
	}

	
	/*
	 * 
	 * Ecrire et Lire dans un fichier
	 * 
	 */
	
	
	/**
	 * Méthode qui écrit une string dans un fichier, avec un retour à la ligne à la fin
	 * Remarque : il faut que la string n'existe pas déjà.
	 * 
	 * @param serializedProfil
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private boolean ecrireDansFichier(String serializedProfil)throws FileNotFoundException, IOException{
		
		/*
		 * On va d'abord lire le fichier pour vérifier que le profil n'existe pas déjà
		 */
		// On extrait le nomDuProfil du serializedProfil
		String nomProfilExtrait = "";
		StringTokenizer stsp = new StringTokenizer(serializedProfil,";");
		nomProfilExtrait = stsp.nextToken(); // Le 1er token est le nom du profil
		// On lit ensuite le fichier, si le profil existe déjà, on renvoie faux. 
		BufferedReader bufr = new BufferedReader(new FileReader(file));
		String ligne;
		while((ligne = bufr.readLine())!=null) {
			StringTokenizer st = new StringTokenizer(ligne,";");
			if(st.nextToken().equals(nomProfilExtrait)) {
				return false;
			}
		}
		// Si on n'a pas trouvé le nom dans le fichier, alors on écrit le profil dans le fichier general.
		BufferedWriter bufw = new BufferedWriter(new FileWriter(file,true));
		bufw.write(serializedProfil);
		bufw.newLine();
		bufw.close();
		// Puis on met a jour le fichierJeu
		ecraserFichierJeu(serializedProfil);
		return true;
	}

	/**
	 * Méthode qui recherche une string donnée en paramètre dans le fichier profils.txt.
	 * 
	 * @param nomProfil
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private String lireDansFichier(String nomDuProfil) throws FileNotFoundException, IOException{
		BufferedReader bufr = new BufferedReader(new FileReader(file));
		String ligne;
		while((ligne = bufr.readLine())!=null) {
			StringTokenizer st = new StringTokenizer(ligne,";");
			if(st.nextToken().equals(nomDuProfil)) {
				return ligne; // on retourne la ligne entière concernant le profil
			}
		}
		return "Profil inconnu"; // si profil non trouvé
	}

	
	/*
	 * 
	 * Charger et Enregistrer un profil
	 * 
	 */

	/**
	 * Méthode qui charge un profil à partir du fichier
	 * 
	 * @param nom
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void chargerUnProfilAPartirDuFicher(String nomDuProfil) throws FileNotFoundException, IOException{
		String profilSerialize = lireDansFichier(nomDuProfil);
		ecraserFichierJeu(profilSerialize); // On met a jour le fichierJeu
		setProfil(deserialize(profilSerialize));
		miseAJour();
		// Les attributs sont à la bonne couleur
		// TODO : mais il faut rafraichir la frame 
	}

		
		
	/**
	 * Méthode qui enregistre un profil dans un fichier
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public boolean enregistrerLeProfilCourantDansLeFicher(String nomDuProfil) throws FileNotFoundException, IOException{
		String serializedProfil = serialize(nomDuProfil);
		if (ecrireDansFichier(serializedProfil) == false){
			// Ici, le profil existe déjà dans le fichier. 
			// TODO : Dire au gonze qu'il foute un autre nom de profil, celui la existe déjà.
			System.out.println("J'ai déjà ce nom de profil enregistré.");
			return false;
		}
		else{
			// Ici, le profil a bien été enregistré, (donc écrit dans le fichier)
			// TODO : il faut maintenant fermer la fenetre de configuration profil,
			// TODO : et sauvegarder les modifs pour le menu en cours :)
			return true;
		}
	}
	
	/**
	 * Méthode qui supprime un profil du fichier
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void supprimerProfil(String nomDuProfil){
		BufferedReader bufr;
		BufferedWriter bufw;
		String ligne;
		String tmp = directory + "tmp.txt";
		File fic;
		File tmpfic;
		try {
			fic = new File(file);
			tmpfic = new File(tmp);
			bufr = new BufferedReader(new FileReader(fic));
			bufw = new BufferedWriter(new FileWriter(tmpfic));

			while ((ligne=bufr.readLine())!=null){
				StringTokenizer st = new StringTokenizer(ligne,";");
				if(!(st.nextToken().equals(nomDuProfil))){
					bufw.write(ligne);
					bufw.newLine();
				}
			}
			bufw.flush();
			bufw.close();
			bufr.close();

			fic.delete();
			tmpfic.renameTo(fic);
						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		
	}

	
	/**
	 * Méthode qui liste les profils du fichier
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ArrayList<String> listerProfils(){
		BufferedReader bufr;
		ArrayList<String> al= new ArrayList<String>();
		try {
			bufr = new BufferedReader(new FileReader(file));
			String ligne;
			while((ligne = bufr.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(ligne,";");
				al.add(st.nextToken()); 
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		return al;
	}
	
	/**
	 * Méthode qui écrit dans le fichier de profilJeu une string en écrasant
	 * systématiquement la seule ligne du fichier.
	 * 
	 * @param serializedProfil
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void ecraserFichierJeu(String serializedProfil)throws FileNotFoundException, IOException{
		
		// On extrait le nomDuProfil du serializedProfil
		BufferedWriter bufw = new BufferedWriter(new FileWriter(fileJeu));
		bufw.write(serializedProfil);
		bufw.close();
	}
	
	/**
	 * Méthode qui charge le denrier profil enregistre
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void chargerDernierProfil() throws FileNotFoundException, IOException{
		BufferedReader bufr = new BufferedReader(new FileReader(fileJeu));
		String ligne = bufr.readLine();
		if(ligne == null)
			chargerUnProfilAPartirDuFicher("BleuJaune");
		else{
			setProfil(deserialize(ligne));
			miseAJour();
			miseAJourOld();
		}
	}

}