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

import java.util.regex.*;


/** Une implementation d'une liste simplement chainee pour des instances de <code>Regle</code>.
 * <p><b>Remarque</b> : les regles sont classees par taille de <code>(suffixe + prefixe)</code>.On applique ainsi la r�gle la plus grande possible en premier</p>
 */
public class ListeRegles {

    /** Le tag correpsondant a la situation ou il n'y a pas de regles */
    public static final String PAS_DE_REGLE = "vide";

    ListeRegles suivant;
    Regle tete;

    /** Constructeur de liste vide.
     * <p><b>Remarque</b> : Une liste de regles vide n'a ni suivant ni tete (mis a <code>null</code>).</p>
     */
    public ListeRegles()
    {
    	suivant = null;
    	tete = null;
    }

    /** Construit une liste de Regle.
     * @param regle la regle a rajouter en tete dans la liste
     * @param suivant la liste de regles qui suivra <code>regle</code>
     */
    private ListeRegles(Regle regle, ListeRegles suivant)
    {
    	this.suivant = suivant;
    	this.tete = regle;
    }

    /** Pour recuperer l'element suivant dans la liste (une liste de regles, prive de la tete de <code>this<code>)
     * @return l'element suivant.
     */
    public ListeRegles getListeSuivante()
    {
    	return suivant;
    }

    /** Pour recuperer la regle presente en tete de liste
     * @return l'element present en tete.
     */
    public Regle getRegle()
    {
    	return  tete;
    }

    /** Pour ajouter une regle a la liste.
     * <p><b>Remarque</b> : On respecte un ordre decroissant sur la taille du suffixe et du prefixe</p>
     * @param t la regle que l'on veut ajouter dans la liste courante
     */
    public void ajouter(Regle t)
    {
    	if (estVide()||t.priorite() > tete.priorite())
    	{
    		suivant = new ListeRegles(tete,suivant);
    		tete = t;
    	}
    	else 
    		suivant.ajouter(t);
    }

    /** Pour savoir si la liste est vide.
     * @return true si c'est le cas, false sinon.
     */
    public boolean estVide()
    {
    	return tete == null;
    }

    /** Pour trouver les phonemes associe a un mot.
     * <p><b>Remarque</b> : On applique la premiere regle qui s'unifie a la sous-chaine du <code>mot</code> se terminant sur <code>indice</code></p>
     * @param mot le mot que l'on veut transformer
     * @param indice l'entier representant l'indice sur lequel on finit l'analyse.
     * @return  le phoneme correspondant a la partie du mot unifiee
     */
    public String trouverPhoneme(String mot, int indice)
    {
    	if (estVide()) 
    		return PAS_DE_REGLE;
    	else
    	{
    		String corps = getRegle().getRacine();
    		int debutRacine = indice - corps.length();
    		String prefixe = getRegle().getPrefix();
    		String suffixe = getRegle().getSuffix();
    		
    		Pattern p = Pattern.compile(corps + suffixe);
    		Matcher m = p.matcher(mot);
    		if (m.find(debutRacine) && m.start() == debutRacine )
    		{
    			p = Pattern.compile(prefixe + corps);
    			m = p.matcher(mot);
    			boolean prefixeOK = false;
    			while (!prefixeOK && m.find())
    			{
    				if (m.end() == indice) 
    					prefixeOK =true;
    			}
    			if (prefixeOK)
    			{
    				return getRegle().getPhoneme();
    			}
    		}
    		return getListeSuivante().trouverPhoneme(mot,indice);
    	}
    }

    /** Methode d'affichage standart d'une liste de regles.
     * @return la chaine de caracteres qui va bien ^_^.
     */
    public String toString()
    {
    	if (estVide()) 
    		return "";
    	else 
    		return getRegle().toString() + getListeSuivante().toString();
    }
}
