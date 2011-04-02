/*  
 * SI VOX Copyright (C) 2004 - 2005
 *
 * Author :
 *   ESSI2 school project (2004) : Affouard, Lemonnier, Fournols ,Lizzul
 *   Tutor                (2004) : H�l�ne Collavizza   [ helen@essi.fr    ] 
 *                                 Jean-Paul Stromboni [ strombon@essi.fr ]
 *
 * Contributor :
 *   (2004) : Louis Parisot [ parisot@essi.fr ]
 *   (2005) : S�bastien Mosser  [ mosser@essi.fr ]
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

import t2s.exception.AnalyseException;
import t2s.util.*;


/** Un arbre <i>(prefixe)</i> des regles pour retrouver les phonemes correspondant a des groupes de lettres. */

public class Arbre {

    private Arbre frere;
    private Arbre fils;
    private String lettre;
    private ListeRegles regles;

    /** Constructeur d'arbre a partir d'un repertoire contenant des fichiers de regles.
     * <p> <b>Organisation du repertoire de regles</b> : </p>
     * <p>
     *   <ul>
     *     <li> fichier <code>preposition.txt</code> contenant les prepositions (pour couper les phrases)</li>
     *     <li> fichier <code>regle.txt</code> contenant les regles de prononciation </li>
     *     <li> fichier <code>exception.txt</code> contenant les exceptions de prononciations </li>
     *     <li> fichier <code>acronymes.txt</code> contenant les acronymes de la langue francaise </li>
     *   </ul>
     * </p>
     * @param path le chemin d'acces au fichiers de regles
     */
    public Arbre(String path) throws AnalyseException
    {
    	this();
    	GenerateurPreposition prop = new GenerateurPreposition(ConfigFile.rechercher("PREPOSITIONS"));
    	Regle p = prop.nouvellePreposition();
    	
    	while (!prop.vide())
    	{
    		this.ajouter(p);
    		p = prop.nouvellePreposition();
    	}
    	prop.close();
    	
    	creerLexique(ConfigFile.rechercher("REGLES"));
    	creerLexique(ConfigFile.rechercher("EXCEPTIONS"));
    	creerLexique(ConfigFile.rechercher("ACCRONYMES"));
    }
    
    /** Constructeur d'arbre vide.
     * <p><b>Definition</b> : Un arbre vide contient uniquement la lettre 'a'</p>
     */
    private Arbre()
    {
    	frere = null;
    	fils = null;
    	lettre = "a";
    	regles = new ListeRegles();
    }
    
    /** Construction d'arbre par copie
     * @param a l'arbre porefixe que l'on souhaite copier dans this.
     */
    private Arbre(Arbre a)
    {
    	this.frere = a.frere;
    	this.fils = a.fils;
    	this.lettre = a.lettre;
    	this.regles = a.regles;
    }
    
    /** Pour trouver les phonemes associe a une phrase
     * @param phrase la phrase que l'on veut transformer en phonemes
     * @return la liste des phonemes qui va bien ^_^.
     */
    public String trouverPhoneme(String phrase)
    {
    	Indice i = new Indice();
    	String res = "";
    	String tmp = "";
    	while (i.val() < phrase.length())
    	{
    		tmp =  trouverPhoneme(phrase, i);
    		if (!vide(tmp))
    		{
    			res += tmp;
    		}
    		else
    		{
    			i.inc();
    		}
    	}
    	return res;
    }
    
    /** Methode d'affichage standart (affichage en largeur).
     * <p><b>Precondition</b> : L'arbre n'est pas vide.</p>
     * @return une chaine de caractere representant l'arbre
     */ 
    public String toString()
    {
    	FileArbre f = new FileArbre();
    	f.ajouter(this);
    	String s = "";
    	while (!f.vide())
    	{
    		Arbre a = f.retirer();
    		s += a.getRegles().toString() + "\n";
    		Arbre cour = a.getFrere();
    		while (cour != null)
    		{
    			f.ajouter(cour);
    			cour = cour.getFrere();
    		}
    		if (a.getFils()!=null) f.ajouter(a.getFils());
    	}
    	return s;
    }
    
    /** Methode de remplissage de l'arbre
     * @param s le fichier a analyser
     */
    private void creerLexique(String s) throws AnalyseException
    {
    	GenerateurRegle ana = new GenerateurRegle(s);
    	Regle a = ana.nouvelleRegle();
    	while (!ana.vide())
    	{
    		this.ajouter(a);
    		a = ana.nouvelleRegle();
    	}
    	ana.close();
    }

    /** Pour obtenir le frere de l'arbre
     * @return l'arbre frere
     */
    private Arbre getFrere()
    {
    	return this.frere;
    }

    /** Pour obtenir le fils de l'arbre
     * @return l'arbre fils
     */
    private Arbre getFils()
    {
    	return this.fils;
    }

    /** Pour obtenir la lettre presente a la racine
     * @return la lettre en question
     */
    private String getLettre()
    {
    	return this.lettre;
    }

    /** Pour obtenir la liste de regles de l'arbre
     * @return une instance de <code>ListeRegles</code> ad'hoc.
     */
    private ListeRegles getRegles()
    {
    	return this.regles;
    }

    /** Pour modifier le frere d'un arbre
     * @param l la lettre presente a la racine de nouveau frere
     */
    private void ajouterFrere(String l)
    {
    	Arbre ar = new Arbre();
    	ar.lettre = l;
    	this.frere = ar;
    }

    /** Pour modifier le fils d'un arbre
     * @param l la lettre presente a la racine de nouveau fils
     */
    private void ajouterFils(String l)
    {
    	Arbre ar = new Arbre();
    	ar.lettre = l;
    	this.fils = ar;
    }

    /** Pour ajouter en tete de l'arbre
     * @param l la 
     */
    private void ajouterDebut(String l)
    {
    	Arbre f = new Arbre(this);
    	this.frere = f;
    	this.fils = null;
    	this.lettre = l;
    	this.regles = new ListeRegles();
    }
    
    /** Ajouter une regle a la liste de regles de <code>this</code>.
     * @param regle la regle que l'on veut ajouter
     */
    private void ajouter(Regle regle)
    {
    	ajouter(regle.getRacine(), regle);
    }

    /** ajoute une regle a la liste de regles, par rapport a une chaine de caractere.
     * @param mot la chaine de caractere 'racine'.
     * @param regle la regle a ajouter.
     */
    private void ajouter(String mot, Regle regle)
    {
    	String lettre = mot.substring(0, 1);
    	String fin = mot.substring(1);
    	if (lettre.equals(getLettre()))
    	{
    		if (fin.length() == 0)
    		{
    			regles.ajouter(regle);
    		}
    		else
    		{
    			if (getFils() == null) 
    				ajouterFils(fin.substring(0, 1));
    			getFils().ajouter(fin, regle);
    		}
    	}
    	else if (lettre.compareTo(getLettre()) > 0)
    	{
    		if (getFrere() == null) 
    			ajouterFrere(lettre);
    		getFrere().ajouter(mot, regle);
    	}
    	else
    	{
    		ajouterDebut(lettre);
    		ajouter(mot, regle);
    	}
    }  

    /** Pour trouver la liste de phoneme correspondant a un mot a partir d'un indice
     * <p><b>Remarque</b> : on choisit la regle qui permet d'unifier  la plus grande chaine commencant a l'indice i.</p> 
     * <p><b>Postcondition</b> : i designe la prochaine lettre a analyser</p>
     * @param mot le mot a analyser
     * @param i l'indice a partir duquel on analyse
     * @return la liste des phoneme qui va bien(a partir de l'indice i)
     */
    private String trouverPhoneme(String mot, Indice i)
    {
    	String res = ListeRegles.PAS_DE_REGLE;
    	String lettre = mot.substring(i.val(), i.val() + 1);
    	
    	if (lettre.equals(getLettre())) {
    		i.inc();
    		if (!i.egal(mot.length()) && getFils()!=null)
    		{
    			int saveInd = i.val();
    			String resFils = getFils().trouverPhoneme(mot, i);
    			if (!vide(resFils))
    			{
    				res = resFils;
    			}
    			else
    			{
    				i.val(saveInd);
    				res = getRegles().trouverPhoneme(mot, i.val());
    			}
    		}
    		else
    			res = getRegles().trouverPhoneme(mot, i.val());
	}
	else
	    if (lettre.compareTo(getLettre()) > 0)
	    {
	    	if (getFrere() != null)
	    	{
	    		int saveInd = i.val();
	    		String resFrere = getFrere().trouverPhoneme(mot, i);
	    		if (!vide(resFrere))
	    		{
	    			res = resFrere;
	    		}
	    		else
	    			i.val(saveInd);
	    	}
	    }
	    else
	    	res = ListeRegles.PAS_DE_REGLE;
	return res;
    }
    
    /** Pour savoir si une chaine de caractere est egale au tag "PAS DE REGLES".
     * @param s la chaine a tester
     * @return true si c'est le cas, false sinon
     */
    private static boolean vide(String s)
    {
    	return s.equals(ListeRegles.PAS_DE_REGLE);
    }

    /**
     * Classes privee, utilisee pour l'affichage de l'arbre (debuggage)
     */

    private class FileArbre
    {
    	private ChaineArbre entree;
    	private ChaineArbre sortie; 

    	public FileArbre()
    	{
    		entree = null;
    		sortie = null;
    	}
    	
    	private FileArbre(FileArbre f)
    	{
    		entree = f.entree;
    		sortie = f.sortie;
    	}
	
    	/**
    	 * Ajoute un nouvel objet dans la file.
    	 */
    	public void ajouter(Arbre objet)
    	{
    		if (vide())
    		{
    			entree = new ChaineArbre(objet);
    			sortie = entree;
    		}
    		else
    		{
    			if (!dejaMis(objet,new FileArbre(this)))
    			{
    				entree.suiv = new ChaineArbre(objet);
    				entree = entree.suiv;
    			}
    		}
    	}
    	
    	private boolean dejaMis(Arbre a,FileArbre f)
    	{
    		if (f.vide())
    			return false;
    		else
    		{
    			Arbre af = f.retirer();
    			if((af.getRegles().toString()).equals(a.getRegles().toString()))
    				return true; 
    			else
    				return dejaMis(a,f);
    		}
    	}
    	
    	/**
    	 * Retire un objet de la file et retourne cet objet.
    	 * Precondition : la file n'est pas vide.
    	 */
    	public Arbre retirer()
    	{
    		Arbre x = sortie.a;
    		sortie = sortie.suiv;
    		if (sortie == null)
    		{
    			entree = null;
    		}
    		return x;
    	}
    	
    	/**
    	 * Retourne l'objet situe en tete de la file.
    	 * Precondition : la file n'est pas vide.
    	 */
    	public Arbre suivant()
    	{
    		return sortie.a;
    	}
    	
    	/**
    	 * Teste si la file est vide.
    	 */
    	public boolean vide()
    	{
    		return entree == null;
    	}
    	
    	/**
    	 * Teste si la file est pleine.
    	 */
    	public boolean pleine()
    	{
    		return false;
    	}
    }
    
    private class ChaineArbre
    {
    	private Arbre a;
    	private ChaineArbre suiv; 
    	
    	public ChaineArbre(Arbre a)
    	{
    		this.a = a;
    	}

    	public ChaineArbre(Arbre a, ChaineArbre s)
    	{
    		this.a = a;
    		suiv = s;
    	}
    }
} 

