
package t2s.prosodie;

import java.util.*;
import t2s.traitement.*;
import java.io.*;

/**
 * lasse representant les phonemes d'une phrase
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */
public class ListePhonemes {

	public static final String PAUSE = "_";
	private List<Phoneme> phonemes; 
	
	/**
	 * Constructeur par defaut de ListePhonemes
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public ListePhonemes()
	{
		phonemes = new ArrayList<Phoneme>();
	}

	/** 
	 * Constructeur de liste de phoneme pour une phrase donnees
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param ph la phrase en question
	 * @param ponct la ponctuation de cette phrase
	 */
	public ListePhonemes(String ph, int ponct)
	{
		phonemes = genererPhonemes(ph, ponct);
	}

	/** 
	 * Pour ajouter les phonemes d'une phrase 
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param ph la phrase que l'on veut rajouter
	 * @param ponct la ponctuation de cette phrase
	 */
	public void ajouterPhonemes(String ph, int ponct)
	{
		phonemes.addAll(genererPhonemes(ph,ponct));
	}

	/** 
	 * Pour creer le vecteur des phonemes de la phrase avec la prosodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param ph la phrase en question
	 * @param ponct la ponctuation de cette phrase.
	 * @return un Vector contenant les phonemes de la phrase
	 */ 
	private static List<Phoneme> genererPhonemes(String ph, int ponct)
	{
		Syntagme s  = new Syntagme();
		List<Phoneme> result = new ArrayList<Phoneme>();
		List<Phoneme> tempo = new ArrayList<Phoneme>();
		int coupure = ph.indexOf("%");
		while (coupure != -1)
		{
			if (coupure != 1)
			{
				s = Syntagme.type(ph,coupure);
				tempo = genererLesPhonemes(ph,coupure-1,s);
				appliquerProsodie(tempo,s); 
				result.addAll(tempo);
			}
			ph = elaguer(ph,coupure);
			coupure = ph.indexOf("%");
		}
		s = Syntagme.typeFin(ponct);
		tempo = genererLesPhonemes(ph,ph.length(), s);
		appliquerProsodie(tempo,s); 
		result.addAll(tempo);
		return result;
	}

	/** 
	 * Pour creer le vecteur des phonemes de la phrase ph jusqu'a l'indice fin avec seulement les durees
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param ph la chaine contenant les phonemes
	 * @param fin : l'indice de fin de la chaine a considerer
	 * @param synt : type de la phrase
	 * @return : le vecteur contenant les phonemes de ph[0:fin]
	 */ 
	private static List<Phoneme> genererLesPhonemes(String ph,  int fin, Syntagme synt)
	{
		List<Phoneme> result = new ArrayList<Phoneme>();
		StringTokenizer st = new StringTokenizer(ph.substring(0,fin));
		while (st.hasMoreTokens())
		{
			String s = st.nextToken();
			result.add(new Phoneme(s));
		}
		((Phoneme)result.get( 0 )).allonge(synt);
		((Phoneme)result.get(result.size()-1)).allonge(synt);
		result.add(new Phoneme(PAUSE,synt.dureePause()));
		return result;
	}


	/** 
	 * Pour supprimer '%' ou '%%' du debut d'une phrase
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param ph la phrase en question
	 * @param coupure l'endroit de <code>ph</code> que l'on souhaite analyser
	 */
	private static String elaguer(String ph, int coupure)
	{
		if (ph.charAt(coupure+1) == '%') 
			return ph.substring(coupure+2);
		else 
			return  ph.substring(coupure+1);
	}	

	/** 
	 * Pour appliquer un schema de prosodie en fonction du type de Syntagme
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * <p><b>Postcondition</b> : Le schema de prosodie ad'hoc est applique a <code>pho</code> par <i>side-effect</i></p>
	 * @param pho : le vecteur des phonemes avec les duree
	 * @param synt : le Syntagme associe
	 */ 
	public static void appliquerProsodie(List pho, Syntagme synt)
	{
		int length = pho.size();
		int i=0;
		Courbe c = new Courbe(synt,length);
		while (i<length)
		{
			Phoneme p = (Phoneme)pho.get(i);
			if (!p.estPause()) p.setProsodie(c.nextValue());
			i++;
		}
	}


	/** 
	 * Pour obtenir la duree de la prononciation de la liste complete des phonemes
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return la duree ad'hoc
	 */
	public int dureePhonemes()
	{
		int n=0;
		int max = nombrePhonemes();
		for( int i=0;i<max;i++ )
			n+=((Phoneme)phonemes.get(i)).getLongueur();
		return n;
	}

	/** 
	 * Pour obtenir le nombre de phonemes present dans cette liste
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return le nombre de phonemes en questions
	 */
	public int nombrePhonemes()
	{
		return phonemes.size();
	}
	
	/** 
	 * Pour ecrire les phonemes dans un fichier
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param name le nom du fichier danslequel on souhaite ecrire ces phonemes
	 */
	public void ecrirePhonemes(String name)
	{
		try {
			FileWriter fw = new FileWriter(name);
			fw.write(toString());
			fw.close();
		} catch (Exception e) {
			System.out.println("SI_VOX WARNING [ListePhonemes.ecrirePhonemes] : Erreur lors de l'ecriture du fichier .pho");
		}
	}

	/** 
	 * Pour afficher la liste de phoneme au format MBROLA
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		int max=nombrePhonemes();
		for (int i = 0; i < max; i++)
		{
			buffer.append(((Phoneme)phonemes.get(i)).toString());
			buffer.append("\n");
		}
		return buffer.toString();
	}

	public List<Phoneme> getPhonemes()
	{
		return phonemes;
	}
	
	/** 
	 * Une methode executable de test
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public static final void main(String[] s)
	{
		ListePhonemes p = new ListePhonemes("b o~ Z u R % i l",Phrase.VIRGULE);
		System.out.println("main :\n" + p);
		p.ecrirePhonemes("bidon");
	}
}
