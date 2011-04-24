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
 *   (2006) : Frdric Dlchamp  [ fredisfree@users.sourceforge.net ]
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

import java.io.*;
import java.util.Properties;

import org.newdawn.slick.util.ResourceLoader;

/** Une classe pour utiliser un fichier de configuration aisement.
 * <p><b>Remarque</b> : Le fichier de configuration utilise la syntaxe suivante (pas d'espace !) : </p>
 * <p><center><code> MOT_CLE=VALEUR</code></center></p>
 */

public class ConfigFile {
	
    // Constantes
    private static String DEFAULT_FILE;

    // Variables privees : 
    private File f;
    
    /**
     * l'instance encapsulant toutes les donnees de configuration 
     */
    private Properties proprietes = new Properties();

    // Le constructeur
    private ConfigFile()
    {
    	try {
    		//if under jnlp
    		if(System.getProperty("javawebstart.version") != null)
    			DEFAULT_FILE = System.getProperty("user.home")+"/Songe/ressources/si_vox_ihm.conf";
    		//if under CD devint
    		else
    			DEFAULT_FILE = "../ressources/si_vox_ihm.conf";
    		f = new File(DEFAULT_FILE);
			proprietes.load(ResourceLoader.getResourceAsStream(DEFAULT_FILE));
			proprietes.setProperty("REPERTOIRE_PHO_WAV", System.getProperty("java.io.tmpdir"));
			System.out.println("temp : "+System.getProperty("java.io.tmpdir"));
		    String os = System.getProperty("os.name").toLowerCase();
		    //linux or unix
	        if (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0) {
	            proprietes.setProperty("REPERTOIRE_PHO_WAV", System.getProperty("java.io.tmpdir") + "/");
	        }
	        setVarsIfInJNLP();
		} catch( FileNotFoundException e ) {
			System.err.println( "fichier " +DEFAULT_FILE +" non trouve" );
			e.printStackTrace();
		} catch( IOException e ) {
			e.printStackTrace();
		}
    }
    
    /**
     * Sets properties if under JNLP
     */
    private void setVarsIfInJNLP(){
    	if (System.getProperty("javawebstart.version") != null) {
    		proprietes.setProperty("EXE_LINUX", System.getProperty("user.home")+"/Songe/ressources/donneesMbrola/Mbrola/LinuxMbrolaExe/mbrola-linux-i386");
    		proprietes.setProperty("EXE_WINDOWS", System.getProperty("user.home")+"/Songe/ressources/donneesMbrola/Mbrola/WindowsMbrolaExe/mbrola.exe");
    		proprietes.setProperty("EXE_MAC", System.getProperty("user.home")+"/Songe/ressources/donneesMbrola/Mbrola/MacMbrolaExe/MacMbrola");
    		proprietes.setProperty("REPERTOIRE_PHO_WAV", System.getProperty("user.home")+"/Songe/ressources/donneesMbrola/pho_wav/");
    		proprietes.setProperty("PREPOSITIONS", System.getProperty("user.home")+"/Songe/ressources/donneesMbrola/rules/preposition.txt");
    		proprietes.setProperty("REGLES", System.getProperty("user.home")+"/Songe/ressources/donneesMbrola/rules/regle.txt");
    		proprietes.setProperty("EXCEPTIONS", System.getProperty("user.home")+"/Songe/ressources/donneesMbrola/rules/exception.txt");
    		proprietes.setProperty("ACCRONYMES", System.getProperty("user.home")+"/Songe/ressources/donneesMbrola/rules/accronymes.txt");
    		proprietes.setProperty("IMG_PATH", System.getProperty("user.home")+"/Songe/ressources/img/");
    		proprietes.setProperty("AIDE_PATH", System.getProperty("user.home")+"/Songe/ressources/aide/");
    		proprietes.setProperty("VOIX_1", System.getProperty("user.home")+"/Songe/ressources/donneesMbrola/Mbrola/fr1/fr1");
    		proprietes.setProperty("VOIX_2", System.getProperty("user.home")+"/Songe/ressources/donneesMbrola/Mbrola/fr2/fr2");
    		proprietes.setProperty("VOIX_3", System.getProperty("user.home")+"/Songe/ressources/donneesMbrola/Mbrola/fr3/fr3");
    		proprietes.setProperty("VOIX_4", System.getProperty("user.home")+"/Songe/ressources/donneesMbrola/Mbrola/fr4/fr4");
    		proprietes.setProperty("VOIX_5", System.getProperty("user.home")+"/Songe/ressources/donneesMbrola/Mbrola/fr5/fr5");
    		proprietes.setProperty("VOIX_6", System.getProperty("user.home")+"/Songe/ressources/donneesMbrola/Mbrola/fr6/fr6");
    		proprietes.setProperty("VOIX_7", System.getProperty("user.home")+"/Songe/ressources/donneesMbrola/Mbrola/fr7/fr7");
    	}
    }

    // Pour effectuer une recherche
    private String search(String keyword)
    {
    	return proprietes.getProperty( keyword );
    }
    
    private void change(String keyword,String value)
    {
    	proprietes.setProperty( keyword, value );
    }

    // Pour analyser une chaine de caractere, et la separer en fonction du signe egal
    private void analyser(String line)
    {
    	int  size = line.length();
    	int i = 0;
    	while (i < size && line.charAt(i) != '=')
    		i++;
    	if (i == size)
    		return;
    	String keyword = line.substring(0,i);
    	String value = line.substring(i+1);
    	String result = "| " + keyword ;
    	while (result.length() < 21)
    		result += " ";
    	result += " | \"" + value+"\"";
    	while(result.length() < 78)
    		result += " ";
    	System.out.println(result+" |");
    	afficherLigne();
    }

    // Pour lister le contenu du fichier de configuration : 
    private void toutLister()
    {
    	try{
    		System.out.println("Chargement du fichier de configuration ...");
    		System.out.println("  " + f.getAbsoluteFile());
    		System.out.println();
    		BufferedReader in = new BufferedReader(new FileReader(f));
    		String line = "";
    		while((line = in.readLine()) != null)
    		{
				try {
					if (!line.equals("") && line.charAt(1) == '^')
					{
						System.out.println();
						System.out.println("   " + line.substring(1));
						System.out.println();
						afficherLigne();
					}
					else
						analyser(line);
				} catch (Exception exc) {}
    		}
    		System.out.println();
    	} catch(IOException ioe) {
    		System.out.println("Erreur d'entree sortie pour le fichier de configuration !");
    	}
    }

    
    /** Pour lister le contenu du fichier de configuration
     */
    public static void lister()
    {
    	cf.toutLister();
    }
    
    /** Pour afficher n fois le caractere '*'
     */
    private static void afficherLigne()
    {
    	System.out.print("+");
    	for (int i = 0; i < 21; i++)
    		System.out.print("-");
    	System.out.print("+");
    	for (int i = 22; i < 78; i++)
    		System.out.print("-");
    	System.out.println("+");
    }

    /**
     * le fichier de configuration de SI_VOX
     */
	private static ConfigFile cf = new ConfigFile();
	
    /** Pour effectuer une recherche dans le fichier de configuration
     * @param key la cle recherchee
     * @return <code> null </code> si on n'a rien trouve, la chaine de caractere contenue dans le fichier de configuration sinon.
     */
    public static String rechercher(String key)
    {
    	return cf.search(key);
    }
    public static void changer(String key, String value)
    {
    	cf.change(key,value);
    }

    /** Une methode de test */
    public static void main(String[] args)
    {
    	System.out.println(rechercher(args[0]));
    }
}
