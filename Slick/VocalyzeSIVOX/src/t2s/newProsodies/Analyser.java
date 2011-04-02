package t2s.newProsodies;

import java.util.Vector;
import java.io.*;

//import t2s.prosodie.CoupleProsodie;
import t2s.exception.*;
import t2s.prosodie.Phoneme;
//import t2s.traitement.AnalyseException;
import t2s.traitement.Arbre;
import t2s.traitement.Phrase;
//import t2s.traitement.PlusDePhraseException;
import t2s.traitement.Pretraitement;
import t2s.util.ConfigFile;

public class Analyser {

	private String texte;
	private int prosodie;
//    private Prosodie1 p1;
//    private Prosodie2 p2;
//    private Prosodie3 p3;
	
	public Analyser (String t, int p) {
		texte = t;
		prosodie = p;
	}
	
	private static String CHEMIN_REGLES = ConfigFile.rechercher("CHEMIN_REGLES");
	
	public Vector<Phoneme> analyserGroupes () {
	    Vector<Phrase> phrases = new Vector<Phrase>();
	    String chainePhonemes;
	    Pretraitement pt = new Pretraitement(texte);
        
	    try {
		Arbre a = new Arbre(CHEMIN_REGLES);
		try {
		    Phrase p = pt.nouvellePhrase();
		    while (!"".equals(p.getPhrase())) {
			String s[] = a.trouverPhoneme(p.getPhrase()).trim().replaceAll("  ", " ").split("%");
			for (int i = 0; i < s.length; ++i) {
			    if (!"".equals(s[i]))
				if (i == s.length-1)
				    phrases.add(new Phrase(s[i].trim(), p.getProsodie()));
				else
				    phrases.add(new Phrase(s[i].trim(), 3));
 			}
			if (prosodie == 1)
			    chainePhonemes= this.afficher(new Prosodie1(phrases).prosodier());
			else if (prosodie == 2)
			    chainePhonemes=this.afficher(new Prosodie2(phrases).prosodier());
			else
			    chainePhonemes=this.afficher(new Prosodie3(phrases).prosodier());
			//System.out.println(chainePhonemes);
			try {
			    FileWriter fw = new FileWriter(ConfigFile.rechercher("REPERTOIRE_PHO_WAV")+ConfigFile.rechercher("FICHIER_PHO_WAV")+".pho");
			    fw.write(chainePhonemes);
			    fw.close();
			}
			catch (Exception e) {
			    System.out.println("SI_VOX WARNING: Erreur avec le fichier .pho");
			    e.printStackTrace();
			}
			p = pt.nouvellePhrase();
		    }

		} catch (PlusDePhraseException e) { }
	    } catch (AnalyseException aa) {
		System.err.println(aa);
	    }
	    if (prosodie == 1)
		return new Prosodie1(phrases).prosodier();
	    else if (prosodie == 2)
		return new Prosodie2(phrases).prosodier();
	    else
		return new Prosodie3(phrases).prosodier();
	}
	
	public String afficher (Vector<Phoneme> l) {
		String s = new String();
		for (int i = 0; i < l.size(); ++i)
			s += l.get(i).toString() + "\n";
		return s;
	}
		
}
