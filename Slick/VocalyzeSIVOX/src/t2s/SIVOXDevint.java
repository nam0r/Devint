
package t2s;

import t2s.newProsodies.Analyser;
import t2s.prosodie.Phoneme;
import t2s.son.*;
import t2s.util.*;

import java.io.*;
import java.util.Vector;

/** Une A.P.I. pour utiliser la synthèse vocale dans les projets DeViNT
 * @author Ecole Polytechnique Universitaire Nice Sophia Antipolis
 */
public class SIVOXDevint {
    private JukeBox jk; // pour jouer les wav
    static private LecteurTexte lt = new LecteurTexte(); // pour choisir une voix
    static private SynthetiseurMbrola s;
    static private Analyser an;
    static public boolean sjoue;
    static private  boolean interruptible=true;
    
    
    private boolean on; // true/false pour valider/invalider la synthèse SIVOX
    private int prosodie; // code la prosodie utilisée, de 1 à 3 (3 par défaut)
    
    /**
     * Constructeur par défaut : voix de Thierry
     * prosodie = 3, la plus performante
     */
    public SIVOXDevint() {
    	jk = null;
    	sjoue = false;
    	on = true;
    	lt.setVoix(1);
    	prosodie = 3;
    }

    /**
     * Constructeur pour fixer la voix
     * @param voix, de 1 à 7 pour fr1, fr2, ... fr7
     */
    public SIVOXDevint(int voix) {
    	int vox;
    	int nbvoix= Integer.parseInt(ConfigFile.rechercher("NBVOIX")); // nombre de voix disponibles
    	System.out.println(nbvoix);
    	jk = null;
    	sjoue = false;
    	on = true;
    	prosodie = 3; // la meilleure prosodie
    	vox =(voix > nbvoix) ? nbvoix : voix;
    	vox =(voix < 1) ? 1 : voix;
        lt.setVoix(vox);
    }

    /**
     * Pour lire un texte long à voix haute en boucle
     * @param text, chaîne à lire à voix haute
     */
    public void loopText(String text) {
    	play(text, true);
    }
    
    /**
     * Pour lire le son d'un fichier .wav en boucle
     * @param fichier nom du fichier (wave) à lire
     */
    public void loopWav(String fichier) {
		jk = new JukeBox(fichier);
		jk.loopSound();
    }
    
    /**
     * Pour lire un texte court (sans ponctuation) à voix haute
     * @param text, chaîne de caractères à lire à voix haute
     */

    public void playShortText(String text) {
    	if ( !on ) return;
	an = new Analyser(text, prosodie);
	Vector<Phoneme> listePhonemes = an.analyserGroupes();
		s = new SynthetiseurMbrola(lt.getVoix(), 
			ConfigFile.rechercher("REPERTOIRE_PHO_WAV"), 
			ConfigFile.rechercher("FICHIER_PHO_WAV"));
	s.play();
	sjoue=true;
    }
    
    /**
     * Pour lire un texte long à voix haute
     * @param text, chaîne à lire à voix haute
     */
    public void playText(String text) {
	play(text, false);
    }
    
    
    /**
     * Pour lire le son d'un fichier .wav
     * @param fichier wave à lire
     */
    public void playWav(String fichier) {
    	jk = new JukeBox(fichier);
		jk.playSound();
    }
  
    /**
     * Pour fixer la prosodie utilisée
     * @param p, entier de 1 à 3
     */
    public void setProsodie(int p) {
    	int pro;
    	pro = (p<1)?1:p;
    	pro = (p>3)?3:p;
    	prosodie = pro;
    }
    
    public int getProsodie() {
    	return prosodie;
    }
    
    /**
     * Pour fixer la voix utilisée si la synthèse parle
     * @param voix, de 1 à 7
     */
    public void setVoix(int voix) {
    	int vox;
    	int nbvoix= Integer.parseInt(ConfigFile.rechercher("NBVOIX")); // nombre de voix disponibles dans ressources
    	System.out.println(nbvoix); 
    	vox =(voix > nbvoix) ? nbvoix : voix;
    	vox =(voix < 1) ? 1 : voix;
		lt.setVoix(vox);
    }
    
    /**
     * Pour stopper la synthèse vocale et donc arrêter le son en cours de lecture
     * on stoppe le jukebox jk, qui lit les sons wave, le lecteur texte lt, et la synthèse s
     */
    public void stop() {
    	if (!interruptible) return;
    	if (jk!=null) jk.stop();
    	if (lt!= null) lt.stop();
    	if (sjoue==true) {
    		sjoue=false;
    		s.stop();
    	}
    }
    
    // Pour basculer entre voix on / voix off
    public void toggle() {
		on = !on;
    }
    /**
     * renvoie l'état du toggle voix on/voix off
     * @return
     */
    public boolean getToggle(){
    	return on;
    }
    
    //pour créer des fichiers wave en silence
    public void muet(String text, String out) {
	//if ( !on ) return;
	an = new Analyser(text, prosodie);
	Vector<Phoneme> listePhonemes = an.analyserGroupes();
	String chainePho=an.afficher(listePhonemes);
	try {
	    FileWriter fw= new FileWriter(out+".pho");
	    fw.write(chainePho);
	    fw.close();
	} catch (Exception e) {
	    System.out.println("erreur création fichier phonème.");}
		s = new SynthetiseurMbrola(lt.getVoix(),out,"");
	s.muet();
    }

    //appelé par loopText et playText avec valeur flagloop différente
    public void play(String text, boolean flagloop) {
	if ( !on ) return;
	an = new Analyser(text, prosodie);
	Vector<Phoneme> listePhonemes = an.analyserGroupes();
	s = new SynthetiseurMbrola(lt.getVoix(),
				   ConfigFile.rechercher("REPERTOIRE_PHO_WAV"),
				   ConfigFile.rechercher("FICHIER_PHO_WAV"));
	//System.out.println("RAPIDITE: "+ConfigFile.rechercher("RAPIDITE"));
	if (flagloop) s.loop(); else s.play();
	sjoue=true;
    }
}
