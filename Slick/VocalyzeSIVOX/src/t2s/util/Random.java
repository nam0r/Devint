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

/** Une classe pour generer des nombres aleatoires a notre maniere
 */

public class Random{

    /** Pour decider aleatoirement si un nombre est negatif
     * @return true dans 50% des cas, false sinon
     */
    public static boolean negatif()
    {
    	return Math.random() > 0.5;
    }

    /** Pour obtenir des nombres flottants aleatoires non signes
     * @param n la borne superieure de l'intervalle
     * @return un double positif compris entre 0 et n 
     */
    public static double unsignedRandom(int n)
    {
    	return Math.random() * n;
    }
    
    /** Pour obtenir des nombres aleatoires signes
     * @param n la borne inferieure et superieure de l'intervalle
     * @return un double compris entre -n et n 
     */
    public static double random(int n)
    {
    	double d = unsignedRandom(n);
    	return (negatif()) ? -1 * d : d;
    }
    
    /** Pour augmenter d'un delta une valeur
     * @param i la valeur en question
     * @param n le maximum possible pour le delta
     * @return un int egal a i + delta avec 0 < delta < n
     */
    public static int unsignedDelta(int i, int n)
    {
    	return (int)(i + unsignedRandom(n));
    }

    /** Pour augmenter ou diminuer d'un delta une valeur
     * @param i la valeur en question
     * @param n le maximum possible pour le delta
     * @return un int egal a i + delta avec -n < delta < n
     */
    public static int delta(int i, int n)
    {
    	return (int) (i + random(n));
    }
}
