package t2s.traitement;

import t2s.util.Indice;

/** Regle de la langue francaise et des exceptions sur les phonemes.
 * <p> Une instance de Regle est composee  : <p>
 * <ul>
 *  <li> D'un prefixe (expression reguliere) </li>
 *  <li> D'un suffixe (expression reguliere) </li>
 *  <li> D'une racine </li>
 *  <li> D'une chaine courante contenant les phonemes de la regle </li>
 * </ul
 * <p> A toute regle, on associe automatiquement une priorite, calcule par le poid de chacune des expressions regulieres presente en suffixe et en prefixe.</p>
 */
public class Regle{

    private String prefix;
    private String suffix;
    private String racine;
    private String phoneme;
    private int priorite;

    /** Pour construire une Regle vide.
     * <p> Tous les parametres sont initialise a la chaine vide.
     */
    public Regle()
    {
    	this("","","","");
    }

    /** Pour construire une Regle sans suffixe ni prefixe.
     * <p> <b> Remarque </b> : utilise pour les regles sur les prepositions </p>
     * @param r la racine de la regle
     * @param ph la chaine contenant les phonemes
     */
    public Regle(String r,String ph)
    {
    	this("",r, "", ph);
    }

    /** Pour construire une Regle complete.
     * @param p le prefixe
     * @param r la racine
     * @param s le suffixe
     * @param ph la chaine contenant les phonemes
     */
    public Regle(String p, String r, String s, String ph)
    {
    	prefix = p;
    	suffix = s;
    	racine = r;
    	phoneme = ph;
    	priorite = poids(p) + poids(s);
    }

    /** Pour recuperer le prefixe de la regle
     * @return l'expression reguliere de l'element prefix.
     */
    public String getPrefix()
    {
    	return prefix;
    }

    /** Pour recuperer le suffixe de la regle
     * @return l'expression reguliere de l'element suffix.
     */
    public String getSuffix()
    {
    	return suffix;
    }

    /** Pour recuperer la racine de la regle
     * @return  la racine de la regle.
     */
    public String getRacine()
    {
    	return racine;
    }

    /** Pour recuperer le phoneme de la regle
     * @return le phoneme associe a l'element courant.
     */
    public String getPhoneme()
    {
    	return phoneme;
    }

    /** Pour recuperer la priorite de la regle.
     * <p> <b>Remarque</b> : La priorite est la somme du poid de chacune des E.R. presente dans la regle </p>
     * <p> <center> { <code>priorite <-- poid(prefixe) + poid(suffixe)</code> } </center> </p>
     * <p> <b> Calcul du poid </b> : Il s'agit de la longueur maximale de la chaine definie par l'E.R. <br>En cas de choix (<code>'|'</code>),
     *     on prend la longueur de la plus grande chaine.</p>
     * @return la priorite associee a l'element courant.
     */
    public int priorite()
    {
    	return priorite;
    }

    /** Pour modifier la chaine de phonemes
     * @param ph la nouvelle chaine a mettre dans la Regle
     */
    public void setPhoneme(String ph)
    {
    	phoneme = ph;
    }
    
    private static int poids(String s)
    {
    	return poids(s,new Indice());
    }

    /** Renvoie le poids de l'expression reguliere s
     * Definition : 
     *       poids = longueur max de la chaine definie par l'expression reguliere s.
     *               { Quand il  y a un choix '|', on prend la longueur de la plus grande chaine. }
     * Precondition : 
     *       1. s est une expression reguliere syntaxiquement correcte 
     *       2. On a calcule le poids jusqu'a l'indice ind
     */
    private static int poids(String s, Indice ind)
    {
    	if (fin(s,ind))
    		return 0;
    	else
    	{
    		int max = poidsTerme(s,ind);
    		while (!fin(s,ind))
    		{
    			char cour = s.charAt(ind.val());
    			ind.inc();
    			if (cour == '|')
    			{
    				int lgCour = poidsTerme(s,ind);
    				if (lgCour > max)
    					max = lgCour;
    			}
    		}	
    		return max ;
    	}
    }

    /** Renvoie le poids d'un sous-terme.
     * Definition : 
     *       les sous-termes sont separes pas des '|'
     * Precondition : 
     *       1. s est une expression reguliere syntaxiquement correcte 
     *       2. on a calcule le poids jusqu'a l'indice ind
     * Postcondition : 
     *       1. ind designe  '|', ')' ou la fin de s
     */
    private static int poidsTerme(String s, Indice ind)
    {
    	if (finTerme(s,ind))
    		return 0;
    	else
    	{
    		char cour = s.charAt(ind.val());
    		if (cour == '[')
    		{
    			ind.val(s.indexOf("]", ind.val()));
    			ind.inc();
    			return 1 + poidsTerme(s,ind);
    		}
    		else
    		{
    			if (cour == '(')
    			{
    				ind.inc();
    				int lg = poids(s,ind);
    				ind.inc();
    				return lg + poidsTerme(s,ind);
    			}
    			else
    			{
    				ind.inc();
    				return 1 + poidsTerme(s,ind);
    			}
    		}
    	}
    }    
    
    /** Determine si s[ind] est la fin d'un terme de s
     * Definition : 
     *       fin d'un terme = vide ou egal a ')' ou '|'
     */ 
    private static boolean finTerme(String s, Indice ind)
    {
    	if(ind.egal(s.length()))
    		return true;
    	else
    	{
    		char c = s.charAt(ind.val());
    		return (c==')') || (c=='|');
    	}
    }
    
    /** Determine si ind est la fin de s
     * Definition : 
     *       fin de la chaine = s[ind] est vide ou egal a ')'
     */
    private static boolean fin(String s, Indice ind)
    {
    	if( ind.egal(s.length())) 
    		return true;
    	else
    	{
    		char c = s.charAt(ind.val());
    		return (c==')');
    	}
    }
    
    /**Pour afficher une Regle de maniere lisible
     * @return la chaine de caracteres qui va bien ^_^.
     */
    public String toString()
    {
    	return ("pref : " + prefix + " racine : " + racine 
		+ " suff :  " + suffix + " pho :  " + phoneme + "\n");
    }

    /** Une methode executable pour faire des tests
     */
    public static void main(String[] s)
    {
    	System.out.println(poids("(a|[ab][ef])(a|b)|ab[cbfe]a|a[ab](de|abc)dfe", new Indice(0)));
    	System.out.println(poids("a",new Indice(0)));
    }
}
