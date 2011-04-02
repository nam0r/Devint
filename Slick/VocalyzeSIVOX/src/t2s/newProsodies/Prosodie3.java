package t2s.newProsodies;

import java.util.Vector;

import t2s.newProsodies.courbe.CalculCourbe;
import t2s.newProsodies.courbe.InterpolationLagrange;
import t2s.prosodie.CoupleProsodie;
import t2s.prosodie.Phoneme;
import t2s.traitement.Phrase;
import t2s.util.ConfigFile;

public class Prosodie3 {

	private Vector<Phrase> listePhrases;
	private boolean pausePreposition = false;
	
	public static int AMPLITUDE = 60;
	private static int FREQUENCE = (int)CalculCourbe.frequence;
	private static int TEMPS_CONSONNE = new Integer(ConfigFile.rechercher("ANALYSER_TEMPS_CONSONNE"));
	private static int TEMPS_VOYELLE = new Integer(ConfigFile.rechercher("ANALYSER_TEMPS_VOYELLE"));
	private static int TEMPS_LONGUE = new Integer(ConfigFile.rechercher("ANALYSER_TEMPS_LONGUE"));
	private static int NOMBRE_COUPLES = new Integer(ConfigFile.rechercher("ANALYSER_NOMBRE_COUPLES"));
	
	
	public Prosodie3 (Vector<Phrase> l) {
		listePhrases = l;
	}
	
	/**
	 * Prosodier
	 * @param p
	 * @param nbSyllabes
	 * @return
	 */
	public Vector<Phoneme> prosodier() {
		Vector<Phoneme> v = new Vector<Phoneme>();
		
		for (Phrase p : listePhrases) {
			String phrase = p.getPhrase();
			String[] tableauPhonemes = phrase.split(" ");
			int nbPhonemes = tableauPhonemes.length;
			int NOMBRE_COUPLES = 3;
			int pourcentage = 0;
			
			
			int limiteInf = FREQUENCE - 10; //La fréquence en dessous de laquelle l'interpolation doit être corrigée.
			int limiteSup = FREQUENCE + 50; //La fréquence au dessus de laquelle l'interpolation doit être corrigée.
			int plancher = FREQUENCE - 20;  //La fréquence en dessous de laquelle il ne faut pas descendre.
			int plafond = FREQUENCE + 60;   //La frequence au dessus de laquelle il ne faut pas monter.
						
			
			//On gère la vitesse d'enonciation. Plus ou moins rapide suivant la longueur de la phrase en phonèmes.
			//resultat += gererVitesse(nbPhonemes);
			
			//On a fixe des points pour l'interpolation.
			InterpolationLagrange il = fixerPoints(p.getProsodie(), tableauPhonemes);
			
			
			int derniereFreq = FREQUENCE; //La dernière fréquence calculée.
			Vector<Phoneme> phoNegatifs= new Vector<Phoneme>(); //Pour garder temporairement les phonemes en dessous de limiteInf
			boolean erreur = false; //Erreur est true si on vient de traiter un phoneme en dessous de limiteInf
			Vector<Phoneme> phoPositifs= new Vector<Phoneme>(); //Pour garder temporairement les phonemes au dessus de la limiteSup.
			boolean erreur2 = false; //Erreur2 est true si on vient de traiter un phoneme au dessus de la limiteSup.
			
			for(int i=0; i < nbPhonemes; i++)  //Pour tous les phonèmes de la phrase
			{
				//On créé le phonème avec sa durée.
				Phoneme phoneme = new Phoneme(tableauPhonemes[i], duree(tableauPhonemes[i])); 
				//On alonge les phonèmes de la fin.
				phoneme = alongerFin(phoneme, i, nbPhonemes);
				
				for(int j = 0; j < NOMBRE_COUPLES; j++)  //On va ajouter tous les couples (%, frequence) au  phonème.
				{
					double j2 = j; //On cast j en double pour pourvoir diviser j2 et obtenir un double
					if (!"_".equals(phoneme.getPho())) //Seulement si ce n'est pas une pause
					{
						pourcentage = j * 100 / NOMBRE_COUPLES; //Calcule du pourcentage actuel.
						int freq = (int) il.valeurFonctionInterpolee((double) i + j2/NOMBRE_COUPLES); //Valeur interpolée
						
						if (freq < limiteInf && i < nbPhonemes - 1) { //Si la fréquence obtenue est trop basse.
																	  //Et que ce n'est pas le dernier phonème.
							phoNegatifs.add(phoneme); //On met le phonème de coté pour recalculer sa fréquence plus tard
							erreur = true; //Pour se rapeller que des phonèmes de fréquence trop basse ont été mis de coté.
							phoneme.getProsodie().removeAllElements(); //On enlève les couples déjà placés pour CE phonème
							j = NOMBRE_COUPLES; // On veut traiter le phonème suivant, donc sortir de la boucle.
						}
						else 
							if (freq > limiteSup && i < nbPhonemes - 1) { //Si la fréquence obtenue est trop haute
																		  //Et que ce n'est pas le dernier phonème.
							phoPositifs.add(phoneme); //On met le phonème de coté pour recalculer sa fréquence plus tard
							erreur2 = true; //Pour se rapeller que des phonèmes de fréquence trop grande ont été mis de coté.
							phoneme.getProsodie().removeAllElements(); //On enlève les couples déjà placés pour CE phonème
							j = NOMBRE_COUPLES; // On veut traiter le phonème suivant, donc sortir de la boucle.
							}
							else { //On a maintenant une fréquence correcte (ni trop basse, ni trop haute)
								if (erreur || (erreur && i == nbPhonemes - 1)){ //Si on avait des phonèmes de fréquence en dessous de limiteInf
																				//Et que le phonème est eventuellement le dernier de la phrase .
							
									
									//On va traiter les phonèmes de fréquence trop basse que l'on a mis de coté, les corriger et les ajouter à résultat
									v.addAll(recupererPhoneme(true, phoNegatifs, NOMBRE_COUPLES, derniereFreq, plancher,
										plafond, i, il, nbPhonemes, phoneme, freq));
									erreur = false;
								}
								if (erreur2 || (erreur2 && i == nbPhonemes - 1)){ //Si on avait des phonèmes de fréquence au dessus de limitSup
																				  //Et que le phonème est eventuellement le dernier de la phrase .	
									//On va traiter les phonèmes de fréquence trop haute que l'on a mis de coté, les corriger et les ajouter à résultat
									v.addAll(recupererPhoneme(false, phoPositifs, NOMBRE_COUPLES, derniereFreq, plancher,
											plafond, i, il, nbPhonemes, phoneme, freq));
									erreur2 = false;
								}
								//On a traité tous les phonèmes qui étaient mis de coté si il y en avait.
								//On va maintenant traiter le phonème courant.
								if (i < nbPhonemes - 1) { //On peut maintenant fair confiance à la frequence donnée par l'interpolation
									phoneme.getProsodie().add(new CoupleProsodie(pourcentage, freq));
									derniereFreq = freq;
								}
								else { //On ne donne qu'une fréquence pour le dernier phonème car c'est le dernier point de l'interpolation.
									phoneme.getProsodie().add(new CoupleProsodie(pourcentage, freq));
									derniereFreq = freq;
									j = NOMBRE_COUPLES; //Pour sortir de la boucle
								}
							}
					}	
				}
				if (! erreur && ! erreur2)
					v.add(phoneme);
			}
			if (p.getProsodie() == 3) //On ne veut faire une pause qu'une seule préposition sur deux.
				if(pausePreposition) {
					pausePreposition = false;
					v.add(ajouterPause(p.getProsodie()));
				}
				else {
					pausePreposition = true;
				}
			v.add(ajouterPause(p.getProsodie()));
		}
		
		return v;
	}
	
	/**
     * Calculer la durée d'un phonème
     * @param c
     * @return
     */
    private int duree (String c) {
    	int temps = new java.util.Random().nextInt(9) + TEMPS_CONSONNE;
		if(estSonVoyelle(c))
			temps = new java.util.Random().nextInt(9) + TEMPS_VOYELLE;	
		if ("~".endsWith(c))
			temps += TEMPS_LONGUE;
		return temps;
    }
   
    
    /**
     * Permet à partir de l'intonnation d'une phrase (? ! , ect) et du nombre de phonèmes, de fixer des points pour l'interpolation de Lagrange.
     * @param prosodie est l'intonnation de la phrase, tableauPhonème est le tableau de tous les phonèmes qui composent la phrase.
     * @return une InterpolationLagrange qui permet de calculer des points d'une fonction interpolée.
     */
    private InterpolationLagrange fixerPoints(int prosodie, String[] tableauPhonemes) {
    	InterpolationLagrange il = new InterpolationLagrange();
    	
    	boolean voyelle1 = false; //On a pas encore traité la première voyelle
    	boolean voyelle2 = false; //On a pas encore traité la deuxième voyelle
    	boolean consonne = false; //On a pas encore traité la première consonne
    	
    	for (int i=0; i < tableauPhonemes.length; i++) // On parcourt tous les phonèmes de la phrase.
		{
			if (i == (tableauPhonemes.length - 1)) {//Si c'est le dernier alors on fixe un point pour la ponctuation.
			
				if (prosodie== Phrase.VIRGULE)
					il.addPoint(i, FREQUENCE + 10);
				else if (prosodie== Phrase.INTERROGATION)
					il.addPoint(i, FREQUENCE + 40);
				else if (prosodie== Phrase.EXCLAMATION)
					il.addPoint(i, FREQUENCE - 15);
				else if (prosodie== 3) //Pour les prepositions
					il.addPoint(i, FREQUENCE + 10);
				else
					il.addPoint(i, FREQUENCE - 10);
			}
			else
				if(i == 0) //C'est le premier phonème
					il.addPoint(i, FREQUENCE);
				else //Pour les autres phonèmes, on fixe que certain nombre de points pour l'interpolation.
					if (tableauPhonemes.length < 6) { //Si on à une petite phrase, on monte un peu à la première voyelle
						if (!voyelle1 && estSonVoyelle(tableauPhonemes[i])) {
							il.addPoint(i, FREQUENCE + 20 + new java.util.Random().nextInt(10));
							voyelle1 = true;
						}
					}
					else	//Pour les phrases inferieures à 12 phonèmes, on monte sur une voyelle en début de phrase
						if (tableauPhonemes.length < 12) {
							if (i > 2 && !voyelle1 && estSonVoyelle(tableauPhonemes[i])) {
								il.addPoint(i, FREQUENCE + 35 + new java.util.Random().nextInt(10));
								voyelle1 = true;
							}
						}
						else //Pour toutes les autres phrases
							//On monte sur un son voyelle en début de phrase.
							if (i > 2 && i < tableauPhonemes.length / 3 && !voyelle1 && estSonVoyelle(tableauPhonemes[i])) {
								il.addPoint(i, FREQUENCE + 35 + new java.util.Random().nextInt(10));
								voyelle1 = true;
							}
							else
								//On descend sur un son qui n'est pas voyelle en milieu de phrase
								if (tableauPhonemes.length / 3 < i && i < 2 * tableauPhonemes.length / 3 && !consonne && !estSonVoyelle(tableauPhonemes[i])) {
									il.addPoint(i, FREQUENCE + 5 + new java.util.Random().nextInt(10));
									consonne = true;
								}
								else 
									//Et on remonte un peu sur une voyelle en fin de phrase.
									if (2 * tableauPhonemes.length / 3 < i && i <  3 * tableauPhonemes.length / 3 && !voyelle2 && estSonVoyelle(tableauPhonemes[i])) {
										il.addPoint(i, FREQUENCE + 15 + new java.util.Random().nextInt(10));
										voyelle2 = true;
									}
					
					 
					 
		}
    	return il;
    }
    
    
	/**
     * Permet de trouver la vitesse à laquelle prononcer la phrase.
     * Une phrase longue sera énoncée plus rapidement qu'une phrase courte.
     * @param nbPhoneme est le nombre de phonèmes de la phrase.
     * @return une chaine de caractères à ajouter au fichier .pho pour modifier la vitesse.
     */
    private String gererVitesse(int nbPhonemes) {
    	// Gestion de la vitesse.
		if (nbPhonemes > 20) //Si on a une phrase supérieur à 20 phonèmes, alors on la lit plus vite.
			return ";;T=0.90\n";	
		else if(nbPhonemes > 12) //Pour les phrases supérieures à 12 phonèmes.
			return ";;T=0.95\n";
		else 
			return ";;T=1.0\n";
    }
    
    
    /**
     * Permet d'allonger les phonemes de la fin d'un phrase
     * @param phoneme est le phonème à alonger, numeroPhoneme est sa position dans la phrase, et nbPhonemes est le nombre de phonèmes que contient la phrase.
     * @return une chaine de caractères à ajouter au fichier .pho pour modifier la vitesse.
     */
    private Phoneme alongerFin(Phoneme phoneme, int numeroPhoneme, int nbPhonemes) {
    	if (numeroPhoneme == nbPhonemes -3) //Si c'est l'avant avant dernier phonème
			phoneme.setLongueur(phoneme.getLongueur() + 10);
		else
			if (numeroPhoneme == nbPhonemes -2) //Si c'est l'avant dernier phonème
				phoneme.setLongueur(phoneme.getLongueur() + 20);
			else
				if (numeroPhoneme == nbPhonemes -1) //Si c'est le dernier phonème
					phoneme.setLongueur(phoneme.getLongueur() + 30);
    	return phoneme;
    }
	
	/**
     * Permet de corriger un vecteur de fréquences trop basses ou trop haute.
     * @return un String à ajouter au fichier .pho
     */
	private Vector<Phoneme> recupererPhoneme(boolean correcBas, Vector<Phoneme> nonTraites, int nbCouples, int derniereFreq, int plancher, 
								int plafond, int indice, InterpolationLagrange il, int nbPhonemes, Phoneme phoneme, int freq) {
		
		Vector<Phoneme> v = new Vector<Phoneme>();
		int pourcentage = 0;
		int nbPoints = nonTraites.size() * NOMBRE_COUPLES; //Le nombre  de fréquence à calculer
		int nbPhonemesNeg = nonTraites.size(); //Le nombre de phonèmes que l'on a mis de coté.
		
		int pasGauche = 0;
		int pasDroite = 0;
		if (correcBas) {
			pasGauche = 0 - (derniereFreq - plancher) / (nbPoints/2); //La différence qu'il y aura entre
																	//deux frequences pour la descente.
			if (indice == nbPhonemes - 1) { //Si c'est le dernier phonème alors on est obligé de revenir en positif.

				pasDroite = (((int) il.valeurFonctionInterpolee((double) nbPhonemes - 1) - plancher) 
							/ (nbPoints/2));
				nonTraites.add(phoneme);	
			}
			else
				pasDroite = (freq - plancher) / (nbPoints/2);
		}
		else {
			 pasGauche = (plafond - derniereFreq) / (nbPoints/2); //La différence qu'il y aura entre
			//deux frequences pour la descente.
			if (indice == nbPhonemes - 1) { //Si c'est le dernier phonème alors on est obligé de revenir en positif.	
				pasDroite = 0 - (plafond - (int) il.valeurFonctionInterpolee((double) nbPhonemes - 1) 
						/ (nbPoints/2));
				nonTraites.add(phoneme);
			}
			else
				pasDroite = 0 - (plafond - freq) / (nbPoints/2);
		}
		//if (correcBas) 
		    //System.out.println("Bas pasGauche : " + pasGauche + " pasDroite" + pasDroite);
		//else
		    //System.out.println("Haut pasGauche : " + pasGauche + " pasDroite" + pasDroite);
		int tmp = 0;
		for (int k = 0; k < nbPhonemesNeg; k++) { //On va traiter tous les phonèmes mis de coté.
			Phoneme pho = (Phoneme) nonTraites.get(0);
			for (int l = 0; l < NOMBRE_COUPLES; l++) {
			    //System.out.println("k : " + k + ", l : " + l);
				pourcentage = l * 100 / NOMBRE_COUPLES;
				if (tmp <= nbPoints/2)  //On est dans la descente
					derniereFreq += pasGauche;
				else if (tmp > nbPoints/2)  //On est dans la montée
					derniereFreq += pasDroite;
				//System.out.println("derniereFreq : " + derniereFreq);
				pho.getProsodie().add(new CoupleProsodie(pourcentage, derniereFreq)); //On ajoute le nouveau couple au phonème
				tmp ++; //On passe au point suivant
			}
			v.add(pho); //Un nouveau phonème mis de coté à été traité
			nonTraites.remove(0); //on l'enlève.
		}
		
		return v;
	}
	
	
	

	/**
     * Creer une pause plus ou moins longue suivant la prodosie.
     * @param prosodie est l'intonnation d'une phrase ( interrogation, exclamation, virgule...)
     * @return Un phoneme correspondant à une bonne pause.
     */
    private Phoneme ajouterPause(int prosodie) {
    	int dureePause = 0;
    	
    	switch(prosodie) {
			case Phrase.INTERROGATION :  	
			case Phrase.EXCLAMATION :
			case Phrase.POINT :
				dureePause = 400;
			break;
	
			case Phrase.VIRGULE :
			case Phrase.DEUXPOINTS:
			case Phrase.POINTVIRGULE:
				dureePause = 200;
			break;
	
			case 3 :
				dureePause = 50;
			break;
			
			default:
				dureePause = 0;
			break;
		}
		
		return new Phoneme("_", dureePause);
	}
	
	
	/**
	 * Teste si un caractère est une voyelle
	 * @param c
	 * @return vrai si 'c' est un char
	 */
    private boolean estVoyelles(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y';
	}
    
    /**
     * Teste si la chaine (phoneme) est un son voyelle
     * @param c
     * @return vrai si la s est un son voyelle
     */
    private boolean estSonVoyelle(String s) {
    	//j'ai trouvé 18 sons voyelles dans SAMPA, et 18 consonnes
        return "i".equals(s)||"e".equals(s)||"E".equals(s)||"a".equals(s)||"A".equals(s)||"O".equals(s)||"o".equals(s)
        	||"u".equals(s)||"y".equals(s)||"2".equals(s)||"@".equals(s)||"9".equals(s)||"e~".equals(s) 
        	||"a~".equals(s)||"9~".equals(s)||"o~".equals(s)||"j".equals(s)||"w".equals(s);
    }
    

    /**
     * Genere un vecteur de phonemes a partir de la chaine de phonemes de la partie de droite 
     * @param phonemes
     * @return
     */
    public static Vector<Phoneme> genererPhonemes(String phonemes) 
{
    	Vector<Phoneme> v = new Vector<Phoneme>();
    	String[] ligne = phonemes.split("\n");
    	Phoneme phoneme;
    	
    	for (int i = 0; i < ligne.length; ++i) {
    		if(!ligne[i].startsWith(";")) {
    			String[] pho = ligne[i].split(" ");
    			phoneme = new Phoneme(pho[0], new Integer(pho[1]));
    			for (int j = 3; j < pho.length; j = j + 2)
    				if (pho.length > (j + 1))
    					phoneme.getProsodie().add(new CoupleProsodie(new Integer(pho[j]), new Integer(pho[j+1])));
    					v.add(phoneme);
    		}
    	}
    	return v;
    }
}
