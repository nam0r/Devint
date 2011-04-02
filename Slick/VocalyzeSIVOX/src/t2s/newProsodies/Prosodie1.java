package t2s.newProsodies;

import java.util.Vector;

import t2s.prosodie.ListePhonemes;
import t2s.prosodie.Phoneme;
import t2s.traitement.Phrase;

public class Prosodie1 {

	private Vector<Phrase> listePhrase;
	
	public Prosodie1 (Vector<Phrase> l) {
		listePhrase = l;
	}

	public Vector<Phoneme> prosodier() {
		Vector<Phoneme> v = new Vector<Phoneme>();
		ListePhonemes l = new ListePhonemes();
		
		for (Phrase p : listePhrase)
			l.ajouterPhonemes(p.getPhrase(), p.getProsodie());
		
		for (int i = 0; i < l.getPhonemes().size(); ++i)
			v.add(l.getPhonemes().get(i));
		
		return v;
	}
}
