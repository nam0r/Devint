
package t2s.son;

import java.io.*;
import t2s.exception.*;
import t2s.prosodie.*;
import t2s.traitement.*;
import t2s.util.*;

/**
 * Classe LecteurTexte
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class LecteurTexte{

    // le fichier pour ecrire les wav ou pho
    private final static String FICHIER_PHO_WAV = ConfigFile.rechercher("FICHIER_PHO_WAV");
    private final static String REPERTOIRE_PHO_WAV = ConfigFile.rechercher("REPERTOIRE_PHO_WAV");
    
    private String outFile;
    private String voix;
    private Arbre arbre;
    private Pretraitement pt;
    private boolean vide;
    private BufferedReader cin;

    private SynthetiseurMbrola sb;

    /** Constructeur complet de Lecteur de Texte 
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param s le texte a lire
     * @param of le fichier ou l'on ecrit les phonemes a prononcer
     * @param v la voix a utiliser pour lire ces phonemes
     * @param isFile Lit - on depuis un fichier ou depuis l'entree standard ?
     */
    public LecteurTexte(String s,  String of, String v, boolean isFile)
    {
    	outFile = of;
		vide= false;
		voix = v;
		sb = null;
		boolean stdin;
		if("-".equals(s))
		{
		    stdin = true;
		    cin = new BufferedReader(new InputStreamReader(System.in));
		}
		else
		{
		    stdin = false;
		    String texte = (isFile)? (new LecteurFichier(s)).toutLire() : s;
		    pt =  new Pretraitement(texte);
		}
		try {
			arbre = new Arbre(""); 
		} catch (AnalyseException a) {
		    System.out.println(a);
		}
		if(stdin)
		    interactifMode();
    }

    /** 
     * Methode d'affichage standard
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return une chaine de caractere ad'hoc
     */
    public String toString()
    {
    	String s = "";
    	s = "  Sortie :=" + outFile +"\n";
    	return s;
    }
    
    /** 
     * Constructeur allege utilisant des valeurs par defaut
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param s le texte a lire
     * @param isFile Lit-on depuis un fichier ?
     */
    public LecteurTexte(String s, boolean isFile)
    {
    	this(s,REPERTOIRE_PHO_WAV+"/"+FICHIER_PHO_WAV,SynthetiseurMbrola.VOIX1,isFile);
    }
    
    public LecteurTexte(File f, String out)
    {
    	this(f.toString(),out,SynthetiseurMbrola.VOIX1,true);
    }
    
    /** 
     * Constructeur completement allege
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param s le texte a lire
     */
    public LecteurTexte(String s)
    {
    	this(s,false);
    }
    
    /** 
     * Constructeur vide
     * @author Ecole Polytechnique de Sophia Antipolis
     */
    public LecteurTexte()
    {
    	this("",false);
    }
    
    /**
     * Pour lire du texte en mode interactif.
     * @author Ecole Polytechnique de Sophia Antipolis
     */
    private void interactifMode()
    {
        try {
            String text = "";
	    String ligne;
            while (true) {
                ligne = cin.readLine();
                if(ligne != null) {
                    if (ligne.equals("<QUIT>")) {
                        System.exit(0);
                    } else if(!ligne.equals("<END>")) {
                        text = text + ligne;
                    } else {
                        pt = new Pretraitement(text);
                        this.muet();
			System.out.println(outFile + ".wav");
			vide = false;
			text = "";
                    }
                } else {
                    try {
			System.out.println("sleeping...");
                        Thread.sleep(200);
                    } catch(InterruptedException e) {
			System.out.println("InterruptedException in interactifMode()");
		    }
                }
            }
        } catch(IOException e) {
            System.out.println("<IOException>");
        }
    }
	
	
    /** 
     * Pour mettre a jour la voix utilise par le synthetiseur
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param v le numero de la voix (parmi 1,2, 3, 5, 6 ou 7 (defaut))
     */
    public void setVoix(int v)
    {
		int nbvoix=Integer.parseInt(ConfigFile.rechercher("NBVOIX"));
		v=(v>nbvoix)?nbvoix:v;
    	switch (v) {
		case 1: 
		    voix = SynthetiseurMbrola.VOIX1;
		    break;
		case 2: 
		    voix = SynthetiseurMbrola.VOIX2;
		    break;
		case 3: 
		    voix = SynthetiseurMbrola.VOIX3;
		    break;
		case 4: 
		    voix = SynthetiseurMbrola.VOIX4;
		    break;
		case 5: 
		    voix = SynthetiseurMbrola.VOIX5;
		    break;
		case 6: 
		    voix = SynthetiseurMbrola.VOIX6;
		    break;
		case 7: 
		    voix = SynthetiseurMbrola.VOIX7;
		    break;
		default:
		    voix = SynthetiseurMbrola.VOIX1;
		    break;
		}
    }

    public String getVoix()
    {
    	return voix;
    }
   
    /** 
     * Pour savoir si le texte est vide
     * @author Ecole Polytechnique de Sophia Antipolis
     */
    public boolean vide()
    {
    	return vide;
    }

    /** 
     * Pour changer le texte a lire
     * @author Ecole Polytechnique de Sophia Antipolis
     */
    public void setTexte(String s)
    {
    	if(s==null) throw new IllegalArgumentException( "texte vide" );
    	pt = new Pretraitement(s);
    	vide = false;
    }

    /** 
     * Pour recharger les fichiers de regles (reconstruction de l'arbre)
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param pathRegle l'emplacement du repertoire contenant les regles
     * @param pathPrepo l'emplacement contenant les prepositions (DEPRECATED)
     */
    public void reloadArbre() throws AnalyseException
    {
    	arbre = new Arbre("");
    }
    
    /** 
     * Pour lire le texte phrase par phrase avec Mbrola
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return la chaine de caractere represenant la liste de phonemes a prononcer.
     */
    public String play()
    {
        ListePhonemes l = new ListePhonemes();
        try {
            Phrase p = pt.nouvellePhrase();
            if (p != null) {
            l = new ListePhonemes(arbre.trouverPhoneme(p.getPhrase()), p.getProsodie());
            System.out.println(outFile);
		    l.ecrirePhonemes(outFile +".pho");
            sb = new SynthetiseurMbrola(voix, REPERTOIRE_PHO_WAV+"/", FICHIER_PHO_WAV);
            sb.play();
            try {
                Thread.sleep(l.dureePhonemes()+ 100);
            } catch ( InterruptedException e ) {}
            }
        } catch (PlusDePhraseException e) {
            vide = true;
        }
        return l.toString();
    }

    /** 
     * Pour lire la totalite d'un texte avec Mbrola
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return la chaine de caracteres representant la liste de phonemes a prononcer.
     */
    public String playAll()
    {
		ListePhonemes l = new ListePhonemes();
		try {
		    Phrase p = pt.nouvellePhrase();
		    while (true) { 
		    	l.ajouterPhonemes(arbre.trouverPhoneme(p.getPhrase()), p.getProsodie());
		    	p = pt.nouvellePhrase();
		    }
		}
		catch (PlusDePhraseException e) {
		    vide = true;
		    l.ecrirePhonemes(outFile + ".pho");
		    sb = new SynthetiseurMbrola(voix, REPERTOIRE_PHO_WAV+"/", FICHIER_PHO_WAV);
		    sb.play();
		    try {
		    	Thread.sleep(l.dureePhonemes()+ 100);
		    } catch (Exception ee ) {};
		}
		return l.toString();
    }
    
  
    /** 
     * Pour generer un fichier de phoneme en mode silencieux (ne rien prononcer)
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param mb l'emplacement de MBROLA.
     */
    public String muet()
    {
    	ListePhonemes l = new ListePhonemes();
    	try {
    		Phrase p = pt.nouvellePhrase();
    		while (true)
    		{
    			l.ajouterPhonemes(arbre.trouverPhoneme(p.getPhrase()),
    			p.getProsodie());
    			p = pt.nouvellePhrase();
    			l.ecrirePhonemes(outFile+".pho");
    		}
    	} catch (PlusDePhraseException e) {
    		vide = true;
    		sb = new SynthetiseurMbrola(voix,REPERTOIRE_PHO_WAV+"/",FICHIER_PHO_WAV);
    		sb.muet();
    	}
    	return l.toString();
    }

    /** probleme pour stopper la lecture, il est probable que sb n'est jamais null
	par contre, on sait stopper le jukebox dans SynthetiseurMbrola
    */
    public void stop()
    {
    	if (sb!=null)
    	{
    		sb.stop();
    	}
    }
}
