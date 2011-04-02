
package t2s.prosodie;

import java.util.Vector;

import t2s.util.*;

/**
 * Classe de Phoneme
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */
public class Phoneme {
	
	private String pho;
	private Vector<CoupleProsodie> prosodie;
	private int longueur;
	
	/** 
	 * Pour creer un phoneme a partir d'une chaine de caractere
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * <p><b>Remarque</b> : la suite de couple de prosodie est vide </p>
	 * @param pho la chaine de caractere representant le phoneme
	 */
	public Phoneme(String pho)
	{
		this.pho = pho;
		this.prosodie = new Vector<CoupleProsodie>();
		longueur = duree(pho);
	}
	
	/** 
	 * Pour creer un phoneme a partir d'une chaine de caractere, en specifiant sa duree
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * <p><b>Remarque</b> : la suite de couple de prosodie est vide </p>
	 * @param pho la chaine de caractere representant le phoneme.
	 * @param l la duree du phoneme
	 */
	public Phoneme(String pho, int l)
	{
		this.pho = pho;
		this.prosodie = new Vector<CoupleProsodie>();
		longueur=l;
	}
	
	/** 
	 * Pour retrouver la chaine de caractere du phoneme
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return la chaine de caracteres representant le phoneme
	 */
	public String getPho()
	{
		return pho;
	}
	
	/** 
	 * Pour recuperer la suite de couples de prosodies du phoneme
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return le <code>Vector</code>
	 */
	public Vector<CoupleProsodie> getProsodie()
	{
		return prosodie;
	}
	
	/** 
	 * Pour recuperer la longueur (duree) du phoneme
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return la duree du phoneme
	 */
	public int getLongueur()
	{
		return longueur;
	}
	
	/** 
	 * Pour modifier la chaine de caracteres du phoneme
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param pho la nouvelle chaine de caractere
	 */
	public void setPho(String pho)
	{
		this.pho = pho;
	}
	
	/** 
	 * Pour modifier la duree du phoneme
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param longueur la nouvelle duree
	 */
	public void setLongueur(int longueur)
	{
		this.longueur = longueur;
	}
	
	/** 
	 * Pour allonger un Phoneme en fonction du type de syntagme
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param s le syntagme dont fait partie le phoneme
	 */
	public void allonge(Syntagme s)
	{
		int l = (s.court()) ? 50 : 100;
		this.longueur = longueur + l;
	}
	
	/** 
	 * Pour savoir si le phoneme est une pause
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return true si c'est le cas, false sinon
	 */
	public boolean estPause()
	{
		return("_".equals(pho));
	}
	
	/** 
	 * Pour savoir s'il s'agit d'une d'une occlusive voisee
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * <p><b>Besoin d'aide ?</b> : <i>cf.</i> <a href="http://fr.wikipedia.org/wiki/Occlusive"><code>WikiPedia</code></a></p>
	 * @return true si c'est le cas, false sinon
	 */
	public boolean occlusiveVoisee()
	{
		return(pho == "b" || pho == "d" || pho == "g");
	}
	
	
	/** 
	 * Pour Calculer automatiquement la duree d'un phoneme
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param s une String representant un phoneme
	 * @return int : la duree associee
	 * <p>Fonction qui determine la duree a appliquer au phoneme s.</p>
	 */
	private int duree(String s)
	{
		String[][] groupe = {{"R", "l", "H"},
				{"d", "n", "j", "w"},
				{"b", "v", "Z", "m", "N", "i", "y"},
				{"t", "k", "z", "e", "a", "o", "u"},
				{"p", "O", "E"},
				{"f", "S", "s", "2"}};
		
		int sign = (Math.random() > 0.5) ? 1 : -1;
		int facteur = (int) (sign * 4 * Math.random()) ;
		
		if (s.endsWith("~"))  
			return 120+facteur;
		
		for (int i = 0; i < groupe.length; i++)
			for (int j = 0; j < groupe[i].length; j++)
				if (s.equals(groupe[i][j]))
				{
					return 65 + 10 * i + facteur;
				}
		
		return 70 + facteur;
	}
	
	/** 
	 * Pour affecter une prosodie (suite de couple de Prosodie) au phoneme
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param f la frequence autour de laquelle on fait varier les phonemes longs
	 */
	protected void setProsodie(int f)
	{
		int l = longueur;
		int nbVariations = (l<95) ? 1: Random.unsignedDelta(1,Integer.parseInt(ConfigFile.rechercher("NB_VARIATIONS_PITCH")));
		SuiteCroissante pourcentage = new SuiteCroissante(nbVariations);
		Vector<CoupleProsodie> v = new Vector<CoupleProsodie>();
		if (occlusiveVoisee()) 
			f -= 10;
		for (int i = 0; i <= nbVariations; i++)
		{
			CoupleProsodie cp = new CoupleProsodie(pourcentage.next(),Random.delta(f,4));
			v.add(cp);
		}
		prosodie = new Vector<CoupleProsodie>(v);
	}
	
	
	/** 
	 * Methode standart d'affichage d'un phoneme (conformite au format MBROLA)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return la representation du phoneme au format MBROLA
	 */
	public String toString()
	{
		String s = getPho() + " " + getLongueur() + " ";
		for (int i = 0; i < prosodie.size(); i++)
		{
			CoupleProsodie couple = (CoupleProsodie) prosodie.get(i);
			s += " " + couple.getPourcentage() + " " + couple.getFrequence();
		}
		return s;
	}
}
