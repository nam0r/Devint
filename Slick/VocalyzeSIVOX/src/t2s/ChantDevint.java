package t2s;

import t2s.son.*;
import t2s.util.*;
import t2s.chant.*;
import t2s.exception.*;

/**
 * Classe ChantDevint utilisee pour chanter
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class ChantDevint {
	
	private JukeBox jk;
	private LecteurTexte lt=new LecteurTexte();
	private Chant c = new Chant();
	static private SynthetiseurMbrola s;
	static public boolean sjoue;
	
	/**
	 * Constructeur par defaut de ChantDevint
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public ChantDevint()
	{
		jk = null;
		sjoue=false;
		lt.setVoix(1);
	}
	
	/**
	 * Constructeur par parametre de voix de ChantDevint
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param voix : la voix choisie (entier entre 1 et 7)
	 */
	public ChantDevint(int voix) {
		int vox;
		jk = null;
		sjoue=false;
		vox=(voix>7)?7:voix;
		vox=(voix<1)?1:voix;
		lt.setVoix(vox);
	}
	
	/**
	 * Methode qui fixe le numero de voix
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param voix Le numero de voix (entre 1 et 7)
	 */
	public void setVoix(int voix)
	{
		int vox;
		vox =(voix<1)?1:voix;
		vox =(voix>7)?7:voix;
		lt.setVoix(vox);
	}
	
	/**
	 * Methode qui affecte le chant en fonction du fichier SVC
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param filename Un fichier de chant au format SVC
	 */
	public void setChant(String filename) throws SIVOXException
	 {
		 try {
			 c = new Chant(filename);
		 } catch (SIVOXException e) {
			 throw e;
		 }
	}
	
	/**
	 * Methode qui fixe le texte a chanter
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param text Le texte a chanter
	 */
	public void setTexteToSing(String text) {
		c.setTexte(text);
	}
	
	/**
	 * Methode qui fixe la melodie du chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param melody La melodie a chanter
	 */
	public void setMelody(String melody) {
		try {
			c.setMelodie(melody);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Methode qui retourne le texte du chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le texte du chant
	 */
	public String getTextetoSing()
	{
		return c.texteToString();
	}
	
	/**
	 * Methode qui retourne la melodie du chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return La melodie du chant
	 */
	public String getMelody()
	{
		return c.melodieToString();
	}
	
	/**
	 * Methode qui ajoute une note en fin de chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param text Le texte de la note chant�e
	 * @param note La note a ajouter
	 */
	public void addNote(String text, String note)
	{
		try {
			c.addNote(text, note);
		} catch (Exception e) {
			//erreur ajout
		}
	}
	
	/**
	 * Methode qui ajoute le silence en fin de chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param silence Le silence a ajouter
	 */
	public void addSilence(String silence) {
		 try {
			c.addSilence(silence);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	/**
	 * Methode qui fixe le tempo du chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param tempo Le tempo a appliquer au chant
	 */
	public void setTempo(int tempo) {
		try {
			c.setTempo(tempo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode qui chante le chant si celui ci est valide
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void sing()
	{
		sing(false);
	}
	
	/**
	 * Methode qui chante le chant (en boucle eventuellement)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param flagloop True si le chant doit etre chante en boucle et false sinon
	 */
	public void sing(boolean flagloop) {
		if(c.isValide()) {
			try {
				c.sauvegarderPHO(ConfigFile.rechercher("REPERTOIRE_PHO_WAV")+"/"+ConfigFile.rechercher("FICHIER_PHO_WAV")+".pho");
			} catch (Exception e) {
				e.printStackTrace();
			}
			s = new SynthetiseurMbrola(lt.getVoix(),ConfigFile.rechercher("REPERTOIRE_PHO_WAV")+"/",ConfigFile.rechercher("FICHIER_PHO_WAV"));
			if (flagloop) s.loop(); else s.play();
			sjoue=true;
		}
	}
	
	/**
	 * Methode qui chante un chant d'un fichier de chant (format .svc)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param fichier Le fichier de chant a chanter
	 * @throws SIVOXException
	 */
	public void sing(String fichier) throws SIVOXException
	{
		try {
			sing(fichier, false);
		} catch (SIVOXException e) {
			throw e;
		}
	}

	/**
	 * Methode qui chante un chant d'un fichier de chant (eventuellement en boucle)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param fichier Le fichier de chant a chanter
	 * @param flagloop True si on doit chanter en boucle et false sinon
	 * @throws SIVOXException
	 */
	public void sing(String fichier, boolean flagloop) throws SIVOXException
	{
		try {
			c = new Chant(fichier);
			sing(flagloop);
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui sauvegarde le chant dans un fichier
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param filename Le nom du fichier a enregistrer
	 */
	public void saveSingFile(String filename)
	{
		if(filename.substring(filename.length()-4, filename.length()).equals(".pho")) {
			try {
				c.sauvegarderPHO(filename);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(filename.substring(filename.length()-4, filename.length()).equals(".svc")) {
			try {
				c.sauvegarderSVC(filename);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
			try {
				c.sauvegarderSVC(filename+".svc");
				c.sauvegarderPHO(filename+".pho");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * Methode qui stoppe la lecture du chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void stop()
	{
		if (jk!=null) jk.stop();
		if (lt!= null) lt.stop();
		if (sjoue==true) {
			sjoue=false;
			s.stop();
		}
	}
	
	/**
	 * main utilise pour les projet devint
	 * @param s la chaine de parametre
	 * @throws SIVOXException
	 */
    public static void main (String[] s) throws SIVOXException
    {
    	try {
    		ChantDevint c = new ChantDevint();
    		c.sing("clairLune.svc");
    	} catch (SIVOXException e) {
    		throw e;
    	}
    }
}

