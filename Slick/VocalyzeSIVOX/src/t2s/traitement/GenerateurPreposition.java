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

import java.io.*;
import java.util.*;

import t2s.exception.AnalyseException;
import t2s.util.ConfigFile;

/** Classe d'analyse du fichier qui contient la liste des prepositions.
 * <p> <b> Remarque </b>: selon la preposition, on fera une pause longue ou courte. </p>
 */
public class GenerateurPreposition {

    /** Une pause courte */
    public static final int COURT = 1;
    /** Une pause longue */
    public static final int LONG = 0;
    /** Absence de pause */
    public static final int VIDE = -1;
    
    private BufferedReader br;
    private  int noLigne ; 
    private int duree;
    private boolean vide;


    /** Construit un generateur de regle a partir d'un fichier de preposition (encodage <code>ISO-8859-1</code>)
     * @param path le chemin d'acces au fichier de prepositions
     */
    public GenerateurPreposition(String path)
    {
    	try {
    		FileInputStream fos = new FileInputStream(path);
    		br = new BufferedReader(new InputStreamReader(fos,ConfigFile.rechercher("ENCODAGE_FICHIER"))); 
    		noLigne = 0;
    		vide = false;
    		duree = VIDE;
    	} catch (FileNotFoundException e) {
    		System.out.println("SI_VOX WARNING [GenerateurPreposition] : Erreur lors du chargement du fichier de regles"+ path);
    		System.out.println(e);
    	} catch (UnsupportedEncodingException e) {
    		System.out.println("SI_VOX WARNING [GenerateurPreposition] : Encodage inconnu");
    		System.out.println(e);
    	}
    }

    /** Pour savoir s'il reste encore des regles a lire
     * @return false s'il reste encore des regles a lire, true sinon.
     */
    public boolean vide()
    {
    	return vide;
    }

    /** Pour fermer en lecture le fichier de preposition specifie dans le constructeur. */
    public void close()
    {
    	try {
    		br.close();
    	} catch (Exception e) {
    		System.out.println("SI_VOX WARNING [GenerateurPreposition.close] : Erreur lors de la fermeture du fichier de regles");	    
    	}
    }

    /** Pour lire une ligne a notre maniere dans un lecteur bufferise
     * @param br le lecteur bufferise en question
     * @return une instance de <code>StringTokenizer</code>, reference par les caracteres <code>'\t', '\n'</code> et <code>'\r'</code>
     */
    public StringTokenizer tokensLine(BufferedReader br) throws AnalyseException
    {
    	String linein = "";
    	try {
    		while (linein!=null && (linein.equals("")|| comment(linein)))
    		{
    			linein = br.readLine();
    			noLigne++;
    		}
    	} catch (IOException e) {
    		erreur(1);
    	}
    	if(linein == null) 
    		erreur(4);
    	return new StringTokenizer(linein," \t\n\r");
    }
    
    /** Pour analyser une ligne du fichier, et en produire une instance de Regle.
     * <p> <b>Definition</b> : une preposition est de la forme suivante </p>
     * <p> <center> <code>preposition -> phoneme </code></center> </p>
     * <p>
     * <ul> 
     *   <li> <code>'preposition'</code> et <code>'phoneme'</code> sont des chaines de caracteres.</li>
     *   <li> <code>'preposition'</code> est en minuscules et les <code>' '</code> ont ete remplaces par <code>'_'</code>.</li>
     * </ul>
     * </p>
     * @return une nouvelle Regle sur une preposition
     * @throws  AnalyseException
     */
    public Regle nouvellePreposition() throws AnalyseException
    {
    	StringTokenizer line = tokensLine(br);
    	if (!line.hasMoreTokens()) 
    		erreur(4);
    	String courant = line.nextToken();
    	if(fin(courant))
    	{
    		vide = true; 
    		return null;
    	}
    	else
    	{
    		if(pause(courant))
    		{
    			line = tokensLine(br);
    			if (!line.hasMoreTokens()) 
    				erreur(6);
    			courant = line.nextToken();
    			if (pauseLongue(courant)) 
    				duree = LONG;
    			else 
    				duree = COURT;
    		}
    		else if (duree == VIDE) 
    			erreur(2);
    		if (!minuscules(courant)) 
    			erreur(3);
    		String preposition = courant;
    		if (!line.hasMoreTokens()) 
    			erreur(5);
    		courant = line.nextToken();
    		if (!fleche(courant)) 
    			erreur(5);
    		String phoneme = analysePhoneme(line);
    		if (phoneme.equals("")) 
    			erreur(7);
    		preposition = "_".concat(preposition);
    		if (!preposition.endsWith("qu'")) 
    			preposition = preposition.concat("_");
    		String debut =  (duree == COURT) ? " % " : " %% ";
    		phoneme = debut.concat(phoneme);
    		return new Regle(preposition,phoneme);
    	}
    }
    
    
    /** Analyse et renvoie le phoneme associe a la ligne courante
     * Precondition : 
     *        on vient de lire la chaine '->'
     * Postcondition : 
     *        Si la chaine est correcte, line est vide
     * @param line le StringTokenizer de la ligne courante
     * @return une String contenant la liste des phoneme de la preposition
     */
    private String analysePhoneme(StringTokenizer line) throws AnalyseException
    {
    	String pho = "";
    	String courant ="";
    	while (line.hasMoreTokens() && ! comment(courant ))
    	{
    		courant = line.nextToken();
    		if (!comment(courant)) 
    			pho = pho + " " + courant;
    	}
    	return pho;
    }
    
    /** Teste si une chaine est  acceptee comme preposition
     * Definition : 
     *       Chaine acceptee = contient des lettres minuscules, ' ou _
     * @return true si c'est bon, false sinon
     */
    private static boolean minuscules(String s)
    {
    	for (int i = 0; i < s.length();i++)
    	{
    		char c = s.charAt(i);
    		if (c != '\'' && c !='_' && c != '~' && c != 'æ')
    			if (!Character.isLowerCase(c))
		    return false;
    	}
    	return true;
    }
    
    /** Teste si une chaine est un commentaire
     * Definition : 
     *       commentaire = ligne commencant par #
     * @param s la chaine de caractere representant la ligne a analyser
     */
    private boolean comment(String s)
    {
    	if (s.length() != 0)
    	{
    		int i;
    		for (i=0; i < s.length() && s.charAt(i)==' '; i++) {}
    		if (i < s.length())
    			return s.charAt(i)=='#';
    	}
    	return false;
    }

    /** Teste si une chaine est le tag de fin de fichier
     * Definition : 
     *       tag de fin de fichier = mots cle END
     * @return true si on est la fin, false sinon
     */
    private boolean fin(String s)
    {
    	return s.equals("END");
    }

 
    /** Teste si une chaine est le tag de transition.
     * Definition : 
     *       tag de transition : La fleche '->'
     * @param s la chaine a analyser
     * @return true si c'est le bon tag, false sinon
     */
    private boolean fleche(String s)
    {
    	return s.equals("->");
    }


    /** Teste si une chaine est le tag de pause courte.
     * Definition : 
     *       tag de pause courte = mot cle "PAUSE_COURTE"
     * @param s la chaine a analyser
     * @return true si c'est le bon tag, false sinon
     */
    private boolean pauseCourte(String s)
    {
    	return s.equals("PAUSE_COURTE");
    }

    /** Teste si une chaine est le tag de pause longue
     * Definition : 
     *       tag de pause longue = mot cle "PAUSE_LONGUE"
     * @param s la chaine a analyser
     * @return true si c'est le bon tag, false sinon
     */
    private boolean pauseLongue(String s)
    {
    	return s.equals("PAUSE_LONGUE");
    }

    /** teste si une chaine est un tag de pause (courte ou longue)
     * @param s la chaine a analyser
     * @return true si c'est un tag de pause, false sinon
     */
    private boolean pause(String s)
    {
    	return pauseCourte(s) || pauseLongue(s);
    }

    /** Methode pour transmettre les exceptions
     */
    private void erreur(int i) throws AnalyseException
    {
    	switch (i) {
    	case 1 : 
    		throw new AnalyseException("Fin fichier de regles","fin du fichier de raigle",noLigne);
    	case 2 : 
    		throw new AnalyseException("Manque mot clef PAUSE_COURTE ou PAUSE_LONGUE","il manque le mo clai pause courte ou pause longue", noLigne);
    	case 3 : 
    		throw new AnalyseException("Les prepositions doivent etre en minuscules, les espaces doivent etre remplaces par des _","", noLigne);
    	case 4 : 
    		throw new AnalyseException("tag END attendu","tague de fin attendu",noLigne);
    	case 5 : 
    		throw new AnalyseException("tag '->' attendu","tague flaiche attendu",noLigne);
    	case 6 : 
    		throw new AnalyseException("Preposition attendue","praipozission attendu",noLigne);
    	case 7 : 
    		throw new AnalyseException("Phonemes de la preposition attendue","fonaime de la praipozission attendu",noLigne);
    	}
    }
}
