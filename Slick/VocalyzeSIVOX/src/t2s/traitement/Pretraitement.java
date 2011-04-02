/*  
 * SI VOX Copyright (C) 2004 - 2005
 *
 * Author :
 *   ESSI2 school project (2004) : Affouard, Lemonnier, Fournols ,Lizzul
 *   Tutor                (2004) : Hï¿½lï¿½ne Collavizza   [ helen@essi.fr    ] 
 *                                 Jean-Paul Stromboni [ strombon@essi.fr ]
 *
 * Contributor :
 *   (2004) : Louis Parisot [ parisot@essi.fr ]
 *   (2005) : Sï¿½bastien Mosser  [ mosser@essi.fr ]
 *
 * Institute : 
 *    Polytechnich school, University of Nice - Sophia Antipolis (FRANCE)
 *
 * This program is free software. It uses mbrola speech synthesizers system.
 * 
 * You can redistribute it and/or modify it under the terms of the MBROLA 
 * Licenses  { http://tcts.fpms.ac.be/synthesis/mbrola.html }.
 *
 */

package t2s.traitement;


import t2s.exception.PlusDePhraseException;
import t2s.util.*;


public class Pretraitement {

    // constantes pour les nombres
    private static final int MILLIARD = 1000000000;
    private static final int MILLION = 1000000;
    private static final int MILLE = 1000;
    private static final int CENT = 100;
    private static final String[] nombre = {
	"", "un", "deux", "trois",
	"quatre", "cinq", "six", "sept", "huit", "neuf",
	"dix", "onze", "douze", "treize", "quatorze",
	"quinze", "seize", "dix_sept", "dix_huit",
	"dix_neuf"};
    private static final String[] dizaine = {
	"", "", "vingt", "trente", "quarante", "cinquante",
	"soixante", "", "quatre_vingt",
	""};

    private String texte;

    /** Constructeur par defaut de pre-traitement.
     * <p> <b>Remarque</b> : Si la chaine <code>txt</code> ne finit pas par un caractere de coupure (<i>ponctuation</i>), on rajoute un <code>'.'</code>
     * de chaine.</p>
     * @param txt la chaine de caracteres a pre - traiter
     */
    public Pretraitement(String txt)
    {
    	this.texte = txt;
    	if(!"".equals(texte) && !estCoupure(texte.charAt(texte.length()-1)))
    		texte+=".";
    }

    /** Pour prendre une nouvelle <code>Phrase</code> dans le texte present dans <code>this</code>
     * <p><b>Remarque</b> : Par effet de bord, on avance (sans espoir de retour) dans le texte pre-traite.</p>
     * @return une instance de <code>Phrase</code> contenant les informations lue dans le texte.
     * @throws PlusDePhraseException
     */
    public Phrase nouvellePhrase() throws PlusDePhraseException
    {
    	if ("".equals(texte))
    		throw new PlusDePhraseException("Il n'y a plus de phrase", "il nia plu de fraze");
    	Phrase p = traiter();
    	if (p == null)
    		throw new PlusDePhraseException("Il n'y a plus de phrase", "il nia plu de fraze");
    	return p;
    }

    /** Pour pre-traiter le texte present dans this.
     * <p><b>Pre-traitement</b> : il faut rajouter le traitement des symboles bizarres...
     * <p><ul>
     * j'aime ubuntu
     *   <li> on enleve les majuscules </li>
     *   <li> on transforme les nombres</li>
     *   <li> On transforme les <code>' '</code> en <code>'_'</code>.
     * </ul></p>
     *  @return   Une instance de <code>Phrase</code> relative au texte pre-traite (texte + type de prosodie a appliquer), 
     *            ou <code>null</code> si on ne peut rien faire.
     */
    private Phrase traiter()
    {
    	int i = 0;
    	StringBuffer buffer = new StringBuffer();
    	char tete = texte.charAt(i);
    	while (i < texte.length() && !estCoupure(tete))
    	{
    		if (Character.isUpperCase(tete))
    		{
    			Indice in = new Indice(i);
    			String a = abbrev(texte,in);
    			if (a.length()>0)
    			{
    				buffer.append(a);
    				i = in.val();
    			} 
    			else
    			{
    				buffer.append(Character.toLowerCase(tete));
    				i++;
    			}
    		}
    		else if((tete == ' ') || (tete == '\n'))
    		{
    			if (buffer.length()!=0 && buffer.charAt(buffer.length()-1) != '_')
    				buffer.append('_');
    			i++;
    		}
    		else if (tete == '-')
    		{
    			buffer.append('_');
    			i++;
    		}
    		else if (estSpecial(tete))
    		{
    			buffer.append(special(tete));
    			i++;
    		}
    		else if (estChiffre(tete))
    		{
    			Indice ind = new Indice(i);
    			buffer.append(traiterLesChiffres(texte,ind));
    			i = ind.val();
    			if (i < texte.length()) 
    				tete = texte.charAt(i);
    		}
    		else if (estLettre(tete))
    		{
    			buffer.append(tete);
    			i++;
    		}
    		else
    		{
    			buffer.append('_');
    			System.out.println("SI_VOX WARNING [Pretraitement.traiter] : le caractere '" + tete + "' a ete ignore !");
    			i++;
    		}
    		if (i < texte.length())
    			tete = texte.charAt(i);
    	}
    	if (estCoupure(tete))
    	{
    		while(i < texte.length() && estCoupure(texte.charAt(i)))
    			i++;
    		if (buffer.length()!=0)
    		{
    			if (buffer.charAt(0) != '_')
    				buffer.insert(0,"_");
    			if (buffer.charAt(buffer.length() - 1) != '_')
    				buffer.append("_"); 
    			if (i < texte.length())
    				texte = texte.substring(i);
    			else
    				texte = "";
    			return new Phrase(buffer.toString(), prosodie(tete));
    		}
    		if (i < texte.length())
    			texte = texte.substring(i);
    		else
    			texte = "";
    		return new Phrase("_", prosodie(tete));
    	}
    	return null;
    }

    /** Lit une suite de chiffres eventuellement separes par une virgule
     * Precondition :
     *        texte[i.val()] est un chiffre
     * Postcondition : 
     *        texte[i.val()] n'est pas un chiffre 
     */
    private String traiterLesChiffres(String texte,  Indice ind)
    {
    	int lg = texte.length();
    	String nbr = lireNombre(texte,ind);
    	StringBuffer buffer = traiterNombre(nbr, false);
    	String ordinal = ordinal(texte, ind);
    	if (ordinal.length() > 0) 
    		if (ordinal.charAt(1) == 'p') 
    			buffer=new StringBuffer(ordinal);
    		else buffer.append(ordinal);
    	else
    	{
    		if (ind.val() < lg)
    		{
    			char tete = texte.charAt(ind.val());
    			if (estVirgule(tete)|| estHeure(tete))
    			{
    				int iSuiv = ind.val() + 1;
    				if (iSuiv < lg && estChiffre(texte.charAt(iSuiv)))
    				{
    					buffer.append(estVirgule(tete) ? "_virgule_" : "_heure_");
    					ind.inc();
    					nbr = lireNombre(texte,ind);
    					buffer.append(traiterNombre(nbr, true));
    				}
    			}
    		}
    	}
    	return buffer.toString();
    }

    /** Lit tous les caracteres du nombre jusqu'a la virgule ou un autre caractere
     * Prï¿½condition :
     *        texte[i.val()] est un chiffre
     * Postcondition : 
     *        texte[i.val()] n'est pas un chiffre
     */
    private String lireNombre(String texte,  Indice i)
    {
    	StringBuffer buffer = new StringBuffer();
    	int lg = texte.length();
    	char tete = texte.charAt(i.val());
    	while (i.val() < lg && estChiffre(tete))
    	{
    		buffer.append(tete);
    		i.inc();
    		if (i.val() < lg)
    			tete = texte.charAt(i.val());
        }
    	return buffer.toString();
    }
    
    /**Pour traiter un nombre.
     * <p><center><code>12 |--> douze</code></center></p>
     * <p><b>Remarque</b>: On ne gere pas le feminin (et puis quoi encore ??) </p>
     * @param nbr une chaine de caractere representant un nombre
     * @param decimal un booleen pour savoir si l'on doit prononcer les '0'.
     * @return une chaine de caractere contenant le nombre sous sa forme textuelle.
     */
    private StringBuffer traiterNombre(String nbr, boolean decimal)
    {
    	StringBuffer buffer = new StringBuffer();
    	if (decimal)
    	{
    		while (nbr.charAt(0) == '0')
    		{
    			buffer.append( "zï¿½ro_" );
    			nbr = nbr.substring(1);
    		}
    	}
    	try {
    		int nombre = (new Integer(nbr)).intValue();
    		if (nombre == 0)
    			return new StringBuffer("zï¿½ro");
    		int milliard = nombre / MILLIARD;
    		nombre = nombre % MILLIARD;
    		int million = nombre / MILLION;
    		nombre = nombre % MILLION;
    		int mille = nombre / MILLE;
    		nombre = nombre % MILLE; //le reste
    		
    		if (milliard != 0)
    			buffer.append(traiterNombre(milliard)).append("_milliard_");
    		if (million != 0)
    			buffer.append(traiterNombre(million)).append("_million_");
    		if (mille != 0)
    		{
    			if (mille != 1)
    				buffer.append(traiterNombre(mille)).append("_mille_");
    			else
    				buffer.append( "_mille_");
    		}
    		buffer.append(traiterNombre(nombre));
    		return buffer;
    	} catch (Exception e) {
    		System.out.println("SI_VOX WARNING [Pretraitement.traiterNombre] : Erreur !");
    		System.out.println(e);
    	}
    	return buffer.append(pasTraiterNombre(nbr));
    }

    /** Pour ne pas traiter un nombre
     * @param nbr la chaine de caractere representant le nombre a ne pas traiter
     */

    private String pasTraiterNombre(String nbr)
    {
    	StringBuffer buffer= new StringBuffer();
    	int i=0;
    	int n;
    	while(i<nbr.length())
    	{
    		n=(new Integer(""+nbr.charAt(i))).intValue();
    		buffer.append(nombre[n]).append("_");
    		i++;
    	}
    	return buffer.toString();
    }

    /** Pour transformer un nombre en chaine de caractere.
     * @param n l'entier que l'on veut transformer
     * @return la chaine de caractere qui va bien.
     */
    private String traiterNombre(int n)
    {
    	StringBuffer buffer = new StringBuffer();
    	int cent = n / CENT;
    	if (cent != 0)
    	{
    		if (cent != 1)
    			buffer.append(nombre[cent]).append("_cent_");
    		else
    			buffer.append( "_cent_" );
    	}
    	n = n % CENT;
    	if (n >= 0 && n <= 19)
    		buffer.append( nombre[n]);
    	else
    	{
    		if ((n >= 70 && n <= 79) || (n >= 90 && n <= 99))
    		{
    			buffer.append( dizaine[n / 10 - 1]);
    			buffer.append( "_" );
    			buffer.append( nombre[n % 10 + 10]);
    		}
    		else
    		{
    			buffer.append( dizaine[n / 10]);
    			if (n % 10 != 0)
    				buffer.append( "_");
    			buffer.append( nombre[n % 10]);
    		}
    	}
    	return buffer.toString();
    }
    
    /** Pour traiter le cas des ordinaux 
     * Precondition :
     *       i est le 1er caractere de s qui n'est pas un chiffre
     * Remarque : 
     *       On effectue les tests sur une chaine en UTF-8 [ Transposition ISO-8859-1 --> UTF-8 ].
     * @param s la chaine a transformer
     * @param i un Indice
     * @return renvoi le texte '_premier', '_iaime', '_premiaire' ou la chaine vide
     */
    private String ordinal(String s, Indice i)
    {
    	String fin = encode(s.substring(i.val()),ConfigFile.rechercher("ENCODAGE_FICHIER"),"UTF-8");
    	if (fin.length() >= 2)
    	{
    		if (fin.substring(0,2).equals("er"))
    		{
    			i.inc(2);
    			return "_premier";
    		}
    		if (fin.substring(0,3).equals("ï¿½me"))
    		{
    			i.inc(3);
    			return "_iaime";
    		}
    		if (fin.substring(0,3).equals("ï¿½re"))
    		{
    			i.inc(3);
    			return "_premiaire";
    		}
    	}
    	return "";
    }
	
    /** Pour determiner si un caractere est la chaine vide
     * @param c le caractere a tester.
     * @return true si c est un c est un caractere de coupure {, . ; : ! ? parentheses}
     */
    private boolean estCoupure(char c)
    {
    	return c == ',' || c == '.' || c == ';' || c == ':' || c == '!' || c == '?' || estParenthese(c) || c == '/' || c == '|';
    }

    private boolean estPonctuation(char c)
    {
    	return c == '.' || c == '?' || c == ';' || c == '!'|| c == ','|| c == '/';
    }

    private boolean estFindePhrase(char c)
    {
    	return c == '.' || c == '?' || c == ';' || c == '!';
    }

    /** Pour determiner si un caractere est un caractere special
     * @param c le caractere a tester
     * @return true si c est un caractere special {% " @ a monnaie}
     */
    private boolean estSpecial(char c)
    {
    	return c == '%' || c == '&' ||  c == '"' || c == '@' || estMonnaie(c) ;
    }

    /** Pour determiner si un caractere est une parenthese 
     * @param c le caractere a tester
     * @return true si c est un caractere de parenthesage, ie (, ), {, }, [, ] ou "
     */
    private boolean estParenthese(char c)
    {
    	return c == '(' || c == ')' ||  c == '{' ||  c == '}' ||  c == '[' ||  c == ']' || c == '"';
    }

    /** Pour determiner si un caractere est une virgule
     * @param c le charactere a tester
     * @return true si c est un caractere de 'virgule' {, .}
     */
    private boolean estVirgule(char c)
    {
    	return c == ',' || c == '.';
    }

    /** Pour determiner si on est sur le tag de representation usuel des heures ('h')
     * @param c le caractere a tester
     * @return true si <code>c == 'h'</code>, false sinon.
     */
    private boolean estHeure(char c)
    {
    	return c == 'h' ;
    }

    /** Pour transformer un caractere special en chaine de caractere
     * @param c le caractere a transformer
     * @return la chaine de caractere qui va bien ^_^
     */
    private String special(char c)
    {
    	if (estMonnaie(c)) return monnaie(c);
    	switch(c)
    	{
    		case '&': return "_et_";
    		case '%': return "_pour_cent_";
    		case '@': return "_arobase_";
    		case '©': return "_copirailt_";
    	}
    	return "";
    }
  
    /** Pour determiner si un caractere est un tag de monnaie
     * @param c le caractere a tester
     * @return true si c est un caractere de monnaie {$, ï¿½ ou \200} (\200 == euros)
     */
    private boolean estMonnaie(char c)
    {
    	return c == '$' || c == '€' || c == '£';
    }

    /** Pour prononcer un carcatere de monnaie
     * @param c le caractere a prononcer
     * @return la chaine de caractere correspondant au symbole de la monnaie.
     */
    private String monnaie(char c)
    {
    	switch(c)
    	{
    		case '$':return "_dollar_";
    		case '£':return "_livre_sterling_";
    		default:  return "";
    	}
    }

    /** Pour determiner si un caractere est une lettre
     * @param c le caractere a tester
     * @return true si c est une lettre au sens de <code>Character.isLetter(char)</code> ou si c == '
     */
    private boolean estLettre(char c)
    {
    	return (Character.isLetter(c) || c == '\'');
    }

    /** Renvoie une chaine contenant la traduction des abreviations qui commencent sur texte[i]
     * renvoie "" si pas d'abreviation
     * Precondition :  : texte[i] est une majuscule
     */
    private String abbrev(String texte, Indice i)
    {
    	int l = texte.length();
    	String res = "";
    	String s = "";
    	int ii = i.val();
    	if (l-ii>=2)
    	{
    		s = texte.substring(ii,ii+2);
    		if (s.equals("M.")) {res = "_monsieur_";i.inc(2);}
    	}
    	if (l-ii>=4)
    	{
    		s = texte.substring(ii,ii+4);
    		if (s.equals("Mme."))
    		{
    			res = "_madame_";i.inc(4);
    		}
    	}
    	if (l-i.val()>=5)
    	{
    		s = texte.substring(ii,ii+5);
    		if (s.equals("Mlle."))
    		{
    			res = "_mademoiselle_";i.inc(5);
    		}
    	}
    	return res;
    }
    
    /** Pour recuperer la prosodie a appliquer a cette phrase
     * @param c un caractere de ponctuation
     * @return l'entier correspondant, a la prosodie liee au caractere de coupure.
     */
    private int prosodie(char c)
    {
    	if (estParenthese(c)) 
    		return Phrase.VIRGULE; 
    	else
    		switch (c)
    		{
    			case ',':
    				return Phrase.VIRGULE;
    			case ';':
    				return Phrase.POINTVIRGULE;
    			case ':':
    				return Phrase.DEUXPOINTS;
    			case '.':
    				return Phrase.POINT;
    			case '/':
    				return Phrase.SLASH;
    			case '!':
    				return Phrase.EXCLAMATION;
    			case '?':
    				return Phrase.INTERROGATION;
    			default:
    				return Phrase.DEFAUT;
    		}
    }

    /** Pour savoir si un caractere est un chiffre
     * @param c le caractere a tester
     * @returntrue si c est un chiffre, false sinon
     */
    private boolean estChiffre(char c)
    {
    	return c >= 48 && c <= 57;
    }

    /** Transcodage d'une chaine de caractere d'un charset vers un autre
     * @param s la chaine de caractere a transcoder
     * @param cs1 le charset initial
     * @param cs2 le charset voulu
     * @return une chaine de caractere contenant <code>s<code> transcodee selon <code>cs2</code>
     */
    private static String encode(String s, String cs1, String cs2)
    {
    	String res = "";
    	try {
    		byte[] b = s.getBytes(cs1);
    		res = new String(b,cs2);
    	} catch (Exception e) { 
    		System.out.println("SI_VOX WARNING [Pretraitement.encode] : Erreur d'encodage pour la chaine \n" + s);
    	}
    	return res;
    }
}
