package t2s.newProsodies;

import java.util.Vector;

import t2s.prosodie.CoupleProsodie;
import t2s.prosodie.Phoneme;
import t2s.traitement.Phrase;
import t2s.util.ConfigFile;

public class Prosodie2 {

	private Vector<Phrase> listePhrase;
	
	private static int AMPLITUDE = new Integer(ConfigFile.rechercher("ANALYSER_AMPLITUDE"));
	private static int FREQUENCE = new Integer(ConfigFile.rechercher("ANALYSER_FREQUENCE"));
	private static int TEMPS_CONSONNE = new Integer(ConfigFile.rechercher("ANALYSER_TEMPS_CONSONNE"));
	private static int TEMPS_VOYELLE = new Integer(ConfigFile.rechercher("ANALYSER_TEMPS_VOYELLE"));
	private static int TEMPS_LONGUE = new Integer(ConfigFile.rechercher("ANALYSER_TEMPS_LONGUE"));
	private static int NOMBRE_COUPLES = new Integer(ConfigFile.rechercher("ANALYSER_NOMBRE_COUPLES"));
	
	public Prosodie2 (Vector<Phrase> l) {
		listePhrase = l;
	}
	
	/**
     * Prosodier des phonemes
     * @param p phrase
     * @param nbSyllabes
     * @return chaine prosodiée
     */
    public Vector<Phoneme> prosodier() {
		Vector<Phoneme> v = new Vector<Phoneme>();
		
		for (Phrase p : listePhrase) {
		
			String phrase = p.getPhrase();
			String[] tableauPhonemes = phrase.split(" ");
			int nbPhonemes = tableauPhonemes.length;
			int pourcentage = 0;
			double t = 0;
			
			if (nbPhonemes > 0) {
				if(nbPhonemes > 3) {
					
					for(int i=0; i < nbPhonemes-3; i++) {
						
						Phoneme phoneme = new Phoneme(tableauPhonemes[i], duree(tableauPhonemes[i]));
						for(int j = 0; j < NOMBRE_COUPLES; j++) {
							// Pas de fréquence pour les silences
							if (!"_".equals(phoneme.getPho())) {
								pourcentage = j * 100 / NOMBRE_COUPLES;
								t = i + (double)pourcentage / 100;
								int freq = calculeFreq(t, nbPhonemes);
								phoneme.getProsodie().add(new CoupleProsodie(pourcentage , freq));
							}
						}
						v.add(phoneme);
					}
					
					int a = obtenirCoef(p.getProsodie());
					
					for(int i = 0; i < 3; i++) { //Les 3 derniers phonème d'une phrase.
						
						Phoneme phoneme;
						if (estSonVoyelle(tableauPhonemes[nbPhonemes-3+i]))
							phoneme = new Phoneme(tableauPhonemes[nbPhonemes-3+i], (duree(tableauPhonemes[nbPhonemes-3+i]) + 30));
						else
							phoneme = new Phoneme(tableauPhonemes[nbPhonemes-3+i], duree(tableauPhonemes[nbPhonemes-3+i]));
						
						for(int j = 0; j < NOMBRE_COUPLES; j++) {
							
							if (!"_".equals(phoneme.getPho())) {
								// Creation des couples de prosodie
								pourcentage = j * 100 / NOMBRE_COUPLES;
								t = (nbPhonemes-3) + i + (double)pourcentage/100;
								int freq = Math.abs((int)(a * t + calculeFreq((nbPhonemes-3), nbPhonemes) - a * (nbPhonemes-3)));					
								phoneme.getProsodie().add(new CoupleProsodie(pourcentage , freq));
							}
						}
						v.add(phoneme);			
					} //Fin du traitement des 3 derniers phonèmes.
				} 
				else { // nbPhonemes < 3
					for(int i=0; i<nbPhonemes; i++)  {
						
						Phoneme phoneme;
						if (estSonVoyelle(tableauPhonemes[i]))
							phoneme = new Phoneme(tableauPhonemes[i], (duree(tableauPhonemes[i]) + 30));
						else
							phoneme = new Phoneme(tableauPhonemes[i], duree(tableauPhonemes[i]));
						
						for(int j=0; j<NOMBRE_COUPLES; j++) {
							// Creation des couples de prosodie
							pourcentage = j * 100 / NOMBRE_COUPLES;
							phoneme.getProsodie().add(new CoupleProsodie(pourcentage , FREQUENCE));
						}
						v.add(phoneme);
					}
				}
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
     * Pour obtenir le coefficient directeur de la droite qui que suivra la fréquence
     * des phonème en fin de phrase
     * @param prosodie est l'intonnation à prendre en fin de phrase (exclamation, interrogation...)
     * @return un coefficient directeur pour une droite réprésentant la prosodie de fin de phrase.
     */
    private int obtenirCoef(int prosodie) {
    	int a = 0;
		switch (prosodie) {
			case Phrase.VIRGULE: a = 5; break; // ,
			case Phrase.INTERROGATION: a = 30; break;  // ?
			case 3: a = 10; break; // prep
			case Phrase.EXCLAMATION: a = 20; break; // !
			default: a = -10; break;
		}
		return a;
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
	
			default:
				dureePause = 0;
			break;
		}
		
		return new Phoneme("_", dureePause);
	}
	
    /**
     * Calcule la nouvelle fréquence
     * @param t
     * @param nbPhonemes
     * @return
     */
	private int calculeFreq(double t, int nbPhonemes) {
		if (nbPhonemes <= 15)
			return Math.abs((int)(AMPLITUDE * Math.cos( (2*Math.PI * 0.9 * t /(nbPhonemes - 3)) + 2.2) + FREQUENCE));	
		return Math.abs((int)(AMPLITUDE * Math.cos( (2*Math.PI * 1.9 * t /(nbPhonemes - 3)) + 2.2) + FREQUENCE));	
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
    public static Vector<Phoneme> genererPhonemes(String phonemes) {
    	Vector<Phoneme> v = new Vector<Phoneme>();
    	String[] ligne = phonemes.split("\n");
    	Phoneme phoneme;
    	
    	for (int i = 0; i < ligne.length; ++i) {
    		String[] pho = ligne[i].split(" ");
    		phoneme = new Phoneme(pho[0], new Integer(pho[1]));
    		for (int j = 3; j < pho.length; j = j + 2)
    			if (pho.length > (j + 1))
    				phoneme.getProsodie().add(new CoupleProsodie(new Integer(pho[j]), new Integer(pho[j+1])));
    		v.add(phoneme);
    	}
    	return v;
    }
}
