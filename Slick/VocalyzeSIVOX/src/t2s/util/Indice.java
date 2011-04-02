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

package t2s.util;


/** Une classe permettant la gestion des indices dans les chaines de caracteres
 */
public class Indice {
    
    private int indice;

    /** Construit une instance d'Indice suivant le parametre fourni
     * @param i le numero de l'indice voulu
     */
    public Indice(int i)
    {
    	indice = i;
    }

    /** Construit une instance d'indice initialise a 0
     */
    public Indice()
    {
    	this(0);
    }

    /** Incremente l'indice d'un facteur donne
     * @param i le facteur en question
     */
    public void inc(int i)
    {
    	indice+=i;
    }

    /** Incremente l'indice d'un facteur 1
     */
    public void inc()
    {
    	indice++;
    }

    /** Pour avoir la valeur courante de l'indice
     * @return la valeur courante stocke dans l'instance
     */
    public int val()
    {
    	return indice;
    }
    
    /** Pour mettre e jour la valeur stockee dans l'Indice
     * @param i la nouvelle valeur
     */
    public void val(int i)
    {
    	indice = i;
    }

    /** Teste l'egalite entre l'indice courant et le parametre
     * @param i la valeur a tester
     * @return true si egalite, false sinon
     */
    public boolean egal(int i)
    {
    	return indice == i;
    }

    /** Teste la superiorite stricte pour l'indice courant et le parametre
     * @param i le parametree a tester
     * @return true si <code>courant > i</code>, false sinon
     */
    public boolean plusGrand(int i)
    {
    	return indice > i;
    }

    /** Teste la superiorite au sens large pour l'indice courant et le parametre
     * @param i le parametree a tester
     * @return true si <code>courant >= i</code>, false sinon
     */
    public boolean grandOuEgal(int i)
    {
    	return indice >= i;
    }

    /** Pour afficher une instance d'indice
     * @return une chaine de caracteres representant l'indice
     */
    public String toString()
    {
    	return " " + indice + " ";
    }
}
