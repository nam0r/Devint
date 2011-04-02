package t2s.chant;

import java.io.*;
import java.util.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import t2s.exception.*;
import t2s.son.*;
import t2s.util.*;

/**
 * Classe de chant permettant de charger des chants et de les trnasformer en phonemes
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class Chant {

	private ArrayList<String> texte;
	private ArrayList<Note> melodie;
	private int tempo;
	private String voix;
	private SynthetiseurMbrola sb = null;

    /**
     * Constructeur par defaut
     * @author Ecole Polytechnique de Sophia Antipolis
     */
	public Chant()
	{
		texte = new ArrayList<String>();
		melodie = new ArrayList<Note>();
		tempo = 120;
		setVoix(1);
	}
	
	/**
	 * Constructeur avec le paramtrage de voix
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param nbVoix
	 */
	public Chant(int nbVoix)
	{
		texte = new ArrayList<String>();
		melodie = new ArrayList<Note>();
		tempo = 120;
		if((nbVoix >= 1) && (nbVoix <= 7))
		{
			setVoix(nbVoix);
		}
		else
		{
			setVoix(1);
		}
	}

    /**
     * Contructeur d'un chant a partir d'un fichier de chant (.SVC)
     * @param fichierSvc fichier .svc pour creer un chant correspondant
     * @throws SIVOXException
     */
	public Chant(String fichierSvc) throws SIVOXException
	{
		try {
			chargerSVC(fichierSvc);
		} catch (SIVOXException e) {
			throw e;
		}
	}

    /**
     * Methode qui affecte une melodie au chant
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param melodie La melodie a appliquer
     * @throws NotValidException
     */
	public void setMelodie(String melodie) throws ChantException
	{
		this.melodie = new ArrayList<Note>();
		String[] melodieSplit = melodie.split(" ");
		for(String s : melodieSplit)
		{
			Note n = new Note(s);
			if(n.isValide())
			{
				this.melodie.add(new Note(s));
			}
			else
			{
				throw new ChantException("Erreur : Une note n'est pas valide", "une note nai pa valid");
			}
		}
	}

    /**
     * Methode qui affecte un texte au chant
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param texte Le texte correspondant
     */
	public void setTexte(String texte)
	{
		this.texte = new ArrayList<String>();
		String[] texteSplit = texte.split("/");
		for(String s : texteSplit)
			this.texte.add(s);
	}

    /**
     * Methode qui retourne le tempo du chant
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return La valeur numerique du tempo
     */
	public int getTempo()
	{
		return tempo;
	}

    /**
     * Methode qui affecte un tempo au chant
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param tempo Le tempo a affecter
     * @throws ChantException
     */
	public void setTempo(int tempo) throws ChantException
	{
		if(tempo > 0)
			this.tempo = tempo;
		else
			throw new ChantException("Le tempo doit etre positif", "le taim po doi tetre positif");
	}

    /**
     * Methode qui retourne la duree du chant
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return La valeur numerique de la duree
     * @throws SIVOXException
     */
	public double getDuree() throws SIVOXException
	{
		double result = 0;
		for(Note n : melodie)
		{
			try {
				result += n.getDuree(tempo);
			} catch (SIVOXException e) {
				throw e;
			}
		}
		return result;
	}

    /**
     * Methode qui affecte une voix au chant
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param v La voix a affecter au chant
     */
	public void setVoix(int v)
	{
		switch (v) {
		case 1 :
			voix = ConfigFile.rechercher("VOIX_1");
		break;
		
		case 2 :
			voix = ConfigFile.rechercher("VOIX_2");
		break;
		
		case 3 :
			voix = ConfigFile.rechercher("VOIX_3");
		break;
		
		case 4 :
			voix = ConfigFile.rechercher("VOIX_4");
		break;
		
		case 5 :
			voix = ConfigFile.rechercher("VOIX_5");
		break;
		
		case 6 :
			voix = ConfigFile.rechercher("VOIX_6");
		break;
		
		case 7 :
			voix = ConfigFile.rechercher("VOIX_7");
		break;
		
		default:
			voix = ConfigFile.rechercher("VOIX_1");
		break;
		}
	}

    /**
     * Methode qui retourne la voix du chant
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return La chaine de caracteres correspondante a la voix du chant.
     */
	public String getVoix()
	{
		return voix;
	}
	
	/**
     * Methode qui retourne le numero de la voix du chant
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return Le numero de la voix (1 a 7)
     */
	public int getNbVoix()
	{
		if(voix.contains("fr1"))
		{
			return(1);
		}
		else if(voix.contains("fr2"))
		{
			return(2);
		}
		else if(voix.contains("fr3"))
		{
			return(3);
		}
		else if(voix.contains("fr4"))
		{
			return(4);
		}
		else if(voix.contains("fr5"))
		{
			return(5);
		}
		else if(voix.contains("fr6"))
		{
			return(6);
		}
		else
		{
			return(7);
		}
	}
	
	/**
	 * Methode qui retourne le coefficient multiplicateur de la voix pour vocalyse sivox
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le coefficient multiplicateur de la voix
	 */
	public double getCoefFrequence()
	{
		double result = 1.0;
		if(voix.contains("fr1"))
			result = Double.parseDouble(ConfigFile.rechercher("FR1"));
		else if(voix.contains("fr2"))
			result = Double.parseDouble(ConfigFile.rechercher("FR2"));
		else if(voix.contains("fr3"))
			result = Double.parseDouble(ConfigFile.rechercher("FR3"));	
		else if(voix.contains("fr4"))
			result = Double.parseDouble(ConfigFile.rechercher("FR4"));	
		else if(voix.contains("fr5"))
			result = Double.parseDouble(ConfigFile.rechercher("FR5"));
		else if(voix.contains("fr6"))
			result = Double.parseDouble(ConfigFile.rechercher("FR6"));
		else
			result = Double.parseDouble(ConfigFile.rechercher("FR7"));
		return(result);
	}

    /**
     * Methode qui ajoute une note au chant
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param texte Le texte associe a la note ajoutee
     * @param note La note ajoutee
     * @throws ChantException
     */
	public void addNote(String texte, String note) throws ChantException
	{
		Note n = new Note(note);
		if(n.isValide()) {
			melodie.add(n);
			if(!n.isSilence())
				if(!texte.contains("/"))
					this.texte.add(texte);
				else
					throw new ChantException("Le texte contient le caractere separateur '/'", "le texte contien le caractaire saiparateur slache");
		}
		else
			throw new ChantException("La note n'est pas valide","la note nai pa valide");
	}

    /**
     * Methode qui ajoute un silence au chant
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param silence La chaine de caractere representant le silence
     * @throws NotValidException
     */
	public void addSilence(String silence) throws ChantException
	{
		Note s = new Note(silence);
		if(s.isValide())
			if(s.isSilence())
				melodie.add(s);
			else
				throw new ChantException("La note n'est pas un silence","la note nai pa un silence");
		else
			throw new ChantException("La note n'est pas valide","la note nai pa valide");
	}

    /**
     * Methode qui recupere le nombre de morceaux de textes chantes du chant
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return le nombre de morceaux de textes chantes du chant
     */
	private int getNoOfTextes()
	{
		return texte.size();
	}

    /**
     * Methode qui recupere le nombre de notes chantees du chant
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return le nombre de notes chantes du chant
     */
	private int getNoOfNotes()
	{
		int tmp = 0;
		for(Note n : melodie)
			if((n.isValide()) && (!n.isSilence()))
				++tmp;
		return tmp;
	}

    /**
     * Methode qui teste si le chant est valide
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return True Si le chant est valide ou false sinon
     */
	public boolean isValide()
	{
		return (getNoOfTextes() == getNoOfNotes());
	}

    /**
     * Methode qui sauve le chant dans un svc
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param nomFichierSVC Le nom du fichier (.SVC)
     * @throws ChantException
     */
	public void sauvegarderSVC(String nomFichierSVC) throws ChantException {
		if(isValide()) {
			try {
				Element elementRacine = new Element("Chant");
				Document document = new Document(elementRacine);
				Element elementTempo = new Element("Tempo");
				elementTempo.setText(""+tempo);
				elementRacine.addContent(elementTempo);
				Element elementVoix = new Element("Voix");
				elementVoix.setText(""+getNbVoix());
				elementRacine.addContent(elementVoix);
				Element elementTexte = new Element("Texte");
				for(Iterator<String> it = texte.iterator() ; it.hasNext() ; )
				{
					Element elementSyllabe = new Element("Syllabe");
					elementSyllabe.setText(it.next());
					elementTexte.addContent(elementSyllabe);
				}
				elementRacine.addContent(elementTexte);
				Element elementMelodie = new Element("Melodie");
				for(Iterator<Note> it = melodie.iterator() ; it.hasNext() ; )
				{
					Element elementNote = new Element("Note");
					elementNote.setText(it.next().getNote());
					elementMelodie.addContent(elementNote);
				}
				elementRacine.addContent(elementMelodie);
				XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
				sortie.output(document, new FileOutputStream(nomFichierSVC));
			} catch (Exception e) {
				throw new ChantException("Le chant est mal synchronise ou invalide - sauvegarde annulee","le chan nai pa valide");
			}
		}
		else
			throw new ChantException("Le chant est mal synchronise ou invalide - sauvegarde annulee","le chan nai pa valide");
	}

    /**
     * Methode qui charge un chant a partir d un fichier chant (.SVC)
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param nomFichierSVC Le nom du fichier (.SVC)
     */
	private void chargerSVC(String nomFichierSVC) throws SIVOXException
	{
		texte = new ArrayList<String>();
		melodie = new ArrayList<Note>();
		try {
			SAXBuilder sxb = new SAXBuilder();
			Document document = null;
			document = sxb.build(new File(nomFichierSVC));
			Element elementRacine = document.getRootElement();
			Element elementTempo = elementRacine.getChild("Tempo");
			try {
				tempo = Integer.parseInt(elementTempo.getText());
			} catch (Exception e) {
				tempo = 120;
			}
			Element elementVoix = elementRacine.getChild("Voix");
			try {
				setVoix(Integer.parseInt(elementVoix.getText()));
			} catch (Exception e) {
				setVoix(1);
			}
			Element elementTexte = elementRacine.getChild("Texte");
			java.util.List listeSyllabe = (java.util.List)elementTexte.getChildren("Syllabe");
			Iterator j = listeSyllabe.iterator();
			while(j.hasNext())
			{
				Element elementSyllabe = (Element)j.next();
				texte.add(elementSyllabe.getText());
			}
			Element elementMelodie = elementRacine.getChild("Melodie");
			java.util.List listeNote = (java.util.List)elementMelodie.getChildren("Note");
			Iterator k = listeNote.iterator();
			while(k.hasNext())
			{
				Element elementNote = (Element)k.next();
				melodie.add(new Note(elementNote.getText()));
			}
		} catch (Exception e) {
			throw new SIVOXException("Erreur : impossible de charger le chant (fichier corrompu ?)","impossible de charger le chan");
		}
	}

    /**
     * Methode qui teste si le phoneme est une voyelle
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param phoneme Le phoneme a tester
     * @return True si le phoneme est une voyelle ou false sinon
     */
	static private boolean isVoyelle(String phoneme)
	{
		return (phoneme.equals("i") ||
				phoneme.equals("e") ||
				phoneme.equals("E") ||
				phoneme.equals("a") ||
				phoneme.equals("A") ||
				phoneme.equals("O") ||
				phoneme.equals("o") ||
				phoneme.equals("u") ||
				phoneme.equals("y") ||
				phoneme.equals("2") ||
				phoneme.equals("9") ||
				phoneme.equals("@") ||
				phoneme.equals("e~") ||
				phoneme.equals("a~") ||
				phoneme.equals("o~") ||
				phoneme.equals("9~") ||
				phoneme.equals("i"));
	}

    /**
     * Methode qui ecrit tous les phonemes du chant dans un String
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return la chaine de caracteres correspondante.
     * @throws ChantException , NoteException
     */
	public String toStringPho() throws ChantException , NoteException
	{
		if(isValide())
		{
			LecteurTexte lt = new LecteurTexte();
			String tmp = "; Genere automatiquement par SVC\n;;F = 0.5\n";
			int i = 0;
			for(Note n : melodie)
			{
				if(n.isSilence())
				{
					tmp += "_ " + n.getDuree(tempo) + " \n";
				}
				else
				{
					lt.setTexte(texte.get(i));
					String[] phonemes = lt.muet().split("\n");
					double dureePrincipale = 0;
					double dureeMax = 0;
					int phonemeMax = 0;
					double dureeTotale = 0;
					int phonemePrincipal = -1;
					for(int j=0;j<phonemes.length-1;++j)
					{
						String[] phonemeSplit = phonemes[j].split(" ");
						double duree = new Double(phonemeSplit[1]);
						if(duree >= dureeMax)
						{
							dureeMax = duree;
							phonemeMax = j;
						}
						dureeTotale += duree;
						if(isVoyelle(phonemeSplit[0]))
						{
							phonemePrincipal = j;
							dureePrincipale = duree;
						}
					}
					if(phonemePrincipal == -1)
					{
						dureePrincipale = dureeMax;
						phonemePrincipal = phonemeMax;
					}
					dureePrincipale += n.getDuree(tempo) - dureeTotale;
					if(dureePrincipale < 1.0)
					{
						dureePrincipale = 1.0;
					}
					for(int j=0;j<phonemes.length-1;++j)
					{
						String[] phonemeSplit = phonemes[j].split(" ");
						if(j == phonemePrincipal)
						{
							tmp += phonemeSplit[0] + " " + dureePrincipale + " 0 " + n.getFrequence() + " 100 " + n.getFrequence() + "\n";
						}
						else
						{
							tmp += phonemeSplit[0] + " " + phonemeSplit[1] + " 0 " + n.getFrequence() + " 100 " + n.getFrequence() + "\n";
						}
					}
					++i;
				}
			}
			return tmp;
		}
		else
		{
			throw new ChantException("Le chant n'est pas valide","le chan nai pa valide");
		}
	}

    /**
     * Methode qui ecrit tous les phonemes du chant dans un vecteur de String
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return Le vecteur de String correspondant
     * @throws ChantException , NoteException
     */
	public Vector<String> toPho() throws ChantException , NoteException
	{
		if(isValide())
		{
			double coef = getCoefFrequence();
			LecteurTexte lt = new LecteurTexte();
			Vector<String> tmp = new Vector<String>();
			int i = 0;
			for(Note n : melodie)
			{
				if(n.isSilence())
				{
					tmp.add("_ " + n.getDuree(tempo));
				}
				else
				{
					lt.setTexte(texte.get(i));
					String[] phonemes = lt.muet().split("\n");
					double dureePrincipale = 0;
					double dureeMax = 0;
					int phonemeMax = 0;
					double dureeTotale = 0;
					int phonemePrincipal = -1;
					for(int j=0;j<phonemes.length-1;++j)
					{
						String[] phonemeSplit = phonemes[j].split(" ");
						double duree = new Double(phonemeSplit[1]);
						if(duree >= dureeMax)
						{
							dureeMax = duree;
							phonemeMax = j;
						}
						dureeTotale += duree;
						if(isVoyelle(phonemeSplit[0]))
						{
							phonemePrincipal = j;
							dureePrincipale = duree;
						}
					}
					if(phonemePrincipal == -1)
					{
						dureePrincipale = dureeMax;
						phonemePrincipal = phonemeMax;
					}
					dureePrincipale += n.getDuree(tempo) - dureeTotale;
					if(dureePrincipale < 1.0)
					{
						dureePrincipale = 1.0;
					}
					for(int j=0;j<phonemes.length-1;++j)
					{
						String[] phonemeSplit = phonemes[j].split(" ");
						if(j == phonemePrincipal)
						{
							tmp.add(phonemeSplit[0] + " " + dureePrincipale + " 0 " + (n.getFrequence()/coef) + " 100 " + (n.getFrequence()/coef));
						}
						else
						{
							tmp.add(phonemeSplit[0] + " " + phonemeSplit[1] + " 0 " + (n.getFrequence()/coef) + " 100 " + (n.getFrequence()/coef));
						}
					}
					++i;
				}
			}
			return tmp;
		}
		else
		{
			throw new ChantException("Le chant n'est pas valide","le chan nai pa valide");
		}
	}

    /**
     * Pour ecrire tous les phonemes du chant dans un vecteur INT de chaines de caracteres
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return Le vecteur correspondant
     * @throws ChantException , NoteException
     */
	public Vector<String> toPhoInt() throws ChantException , NoteException {
		if(isValide()) {
			double coef = getCoefFrequence();
			LecteurTexte lt = new LecteurTexte();
			Vector<String> tmp = new Vector<String>();
			int i = 0;
			for(Note n : melodie)
			{
				if(n.isSilence())
				{
					tmp.add("_ " + n.getDuree(tempo));
				}
				else
				{
					lt.setTexte(texte.get(i));
					String[] phonemes = lt.muet().split("\n");
					double dureePrincipale = 0;
					double dureeMax = 0;
					int phonemeMax = 0;
					double dureeTotale = 0;
					int phonemePrincipal = -1;
					for(int j=0;j<phonemes.length-1;++j)
					{
						String[] phonemeSplit = phonemes[j].split(" ");
						double duree = new Double(phonemeSplit[1]);
						if(duree >= dureeMax)
						{
							dureeMax = duree;
							phonemeMax = j;
						}
						dureeTotale += duree;
						if(isVoyelle(phonemeSplit[0]))
						{
							phonemePrincipal = j;
							dureePrincipale = duree;
						}
					}
					if(phonemePrincipal == -1)
					{
						dureePrincipale = dureeMax;
						phonemePrincipal = phonemeMax;
					}
					dureePrincipale += n.getDuree(tempo) - dureeTotale;
					if(dureePrincipale < 1.0)
						dureePrincipale = 1.0;
					for(int j=0;j<phonemes.length-1;++j)
					{
						String[] phonemeSplit = phonemes[j].split(" ");
						if(j == phonemePrincipal)
						{
							tmp.add(phonemeSplit[0] + " " + (int)dureePrincipale + " 0 " + (int)(n.getFrequence()/coef) + " 100 " + (int)(n.getFrequence()/coef));
						}
						else
						{
							tmp.add(phonemeSplit[0] + " " + (int)Double.parseDouble(phonemeSplit[1]) + " 0 " + (int)(n.getFrequence()/coef) + " 100 " + (int)(n.getFrequence()/coef));
						}
					}
					++i;
				}
			}
			return tmp;
		}
		else
		{
			throw new ChantException("Le chant n'est pas valide","le chan nai pa valide");
		}
	}
	
    /**
     * Methode qui sauve le chant dans un fichier de phoneme (.PHO)
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param nomFichierPHO Le nom du fichier .pho.
     * @throws ChantException , NoteException
     */
	public void sauvegarderPHO(String nomFichierPHO) throws ChantException , NoteException
	{
		try {
			FileWriter fw = new FileWriter(nomFichierPHO);
			fw.write(toStringPho());
			fw.close();
		} catch (IOException e) {
			throw new ChantException("Erreur : impossible de sauver le fichier de phoneme","impossible de sauver le fichier de fonaime");
		} catch (ChantException e) {
			throw new ChantException("Le chant n'est pas valide - sauvegarde annulee","le chan nai pa valide");
		}
	}

    /**
     * Methode qui transforme le texte en chaine de caracteres
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return la chaine de caracteres correspondante.
     */
	public String texteToString()
	{
		String tmp = "";
		int i = 0;
		for(Note n : melodie)
		{
			if(!n.isSilence())
			{
				tmp += texte.get(i) + "/";
				++i;
			}
		}
		return tmp.substring(0, tmp.length()-1);
	}

    /**
     * Methode qui transforme la melodie en chaine de caractere
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return la chaine de caracteres correspondante
     */
	public String melodieToString()
	{
		String tmp = "";
		for(Note n : melodie)
			tmp += n + " ";
		return tmp.substring(0, tmp.length()-1);
	}

    /**
     * Methode qui chante le chant
     * @author Ecole Polytechnique de Sophia Antipolis
     */
	public void chanter() throws ChantException , NoteException
	{
		try {
			sauvegarderPHO(ConfigFile.rechercher("REPERTOIRE_PHO_WAV") + ConfigFile.rechercher("FICHIER_PHO_CHANT") + ".pho");
			sb = new SynthetiseurMbrola(voix,ConfigFile.rechercher("REPERTOIRE_PHO_WAV") + ConfigFile.rechercher("FICHIER_PHO_CHANT"), "");
			sb.play();
		} catch (ChantException c) {
			throw c;
		} catch (NoteException n) {
			throw n;
		} catch (Exception e) {
			throw new ChantException("Erreur chant : impossible de chanter le chant","impossible de chanter le chan");
		}
	}
	
	/**
	 * Methode qui stop la lecture du chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void stopperChant()
	{
		if(sb != null)
		{
			sb.stop();
		}
	}
}
