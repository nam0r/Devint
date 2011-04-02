package t2s.chant;

import t2s.exception.*;

/**
 * Classe representant une note
 * @author Ecole Polytechnique de Sophia Antipolis
 */

public class Note {
	
	private String note;
	
	/**
	 * Constructeur par defaut de Note
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public Note()
	{
		note="";
	}
	
	/**
	 * Constructeur par parametre de Note
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param note La chaine de la note 
	 */
	public Note(String note)
	{
		this.note = note;
	}
	
	/**
	 * Methode qui retourne la note
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return la chaine de caractres correspondant  la note.
	 */
	public String getNote()
	{
		return note;
	}
	
	/**
	 * Methode qui affecte une nouvelle note
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param note La nouvelle note
	 */
	public void setNote(String note)
	{
		this.note = note;
	}
	
	/**
	 * Methode qui retourne la duree de la note
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param tempo La valeur du tempo utilise
	 * @return La dure de la note
	 * @throws NoteException
	 */
	public double getDuree(int tempo) throws NoteException
	{
		if(isValide())
		{
			String rythme = note.substring(4, 7);
			double tempoDouble = (double) tempo;
			double result = 60000.0/tempoDouble;
			if(rythme.equals("DCR"))
				return result/4.0;
			else if(rythme.equals("DCP"))
				return 3.0*result/8.0;
			else if(rythme.equals("CRO"))
				return result/2.0;
			else if(rythme.equals("CRP"))
				return 3.0*result/4.0;
			else if(rythme.equals("NOI"))
				return result;
			else if(rythme.equals("NOP"))
				return 3.0*result/2.0;
			else if(rythme.equals("BLA"))
				return 2.0*result;
			else if(rythme.equals("BLP"))
				return 3.0*result;
			else if(rythme.equals("RON"))
				return 4.0*result;
			else if(rythme.equals("ROP"))
				return 6.0*result;
		}
		throw new NoteException("La note n'est pas valide","la note nai pa valide");
	}
	
	/**
	 * Methode qui teste si la note est valide
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si la note est valide ou false sinon
	 */
	public boolean isValide() {
		if(note.length() != 7)
			return false;
		if(!note.substring(0, 2).equals("DO") &&
				!note.substring(0, 2).equals("RE") &&
				!note.substring(0, 2).equals("MI") &&
				!note.substring(0, 2).equals("FA") &&
				!note.substring(0, 2).equals("SO") &&
				!note.substring(0, 2).equals("LA") &&
				!note.substring(0, 2).equals("SI") &&
				!note.substring(0, 2).equals("SH"))
			return false;
		if(!note.substring(2, 3).equals("0") &&
				!note.substring(2, 3).equals("1") &&
				!note.substring(2, 3).equals("2") &&
				!note.substring(2, 3).equals("3") &&
				!note.substring(2, 3).equals("4") &&
				!note.substring(2, 3).equals("5") &&
				!note.substring(2, 3).equals("6") &&
				!note.substring(2, 3).equals("7") &&
				!note.substring(2, 3).equals("8") &&
				!note.substring(2, 3).equals("9"))
			return false;
		if(!note.substring(3, 4).equals("n") &&
				!note.substring(3, 4).equals("b") &&
				!note.substring(3, 4).equals("d"))
			return false;
		if(!note.substring(4, 7).equals("DCR") &&
				!note.substring(4, 7).equals("DCP") &&
				!note.substring(4, 7).equals("CRO") &&
				!note.substring(4, 7).equals("CRP") &&
				!note.substring(4, 7).equals("NOI") &&
				!note.substring(4, 7).equals("NOP") &&
				!note.substring(4, 7).equals("BLA") &&
				!note.substring(4, 7).equals("BLP") &&
				!note.substring(4, 7).equals("RON") &&
				!note.substring(4, 7).equals("ROP"))
			return false;
		return true;
	}
	
	/**
	 * Methode qui retourne la frequence de la note
	 * @return La frequence de la note
	 * @throws NoteException
	 */
	public double getFrequence() throws NoteException
	{
		if(isValide())
		{
			if(note.substring(0,2).equals("SH"))
			{
				return 0.0;
			}
			else
			{
				double result = 0;
				if(note.substring(0,2).equals("DO"))
					result = 66;
				else if(note.substring(0,2).equals("RE"))
					result = 74.25;
				else if(note.substring(0,2).equals("MI"))
					result = 82.5;
				else if(note.substring(0,2).equals("FA"))
					result = 88;
				else if(note.substring(0,2).equals("SO"))
					result = 99;
				else if(note.substring(0,2).equals("LA"))
					result = 110;
				else if(note.substring(0,2).equals("SI"))
					result = 123.75;
				int octave = Integer.valueOf(note.substring(2,3)).intValue();
				result *= Math.pow(2,octave-1);
				if(note.substring(3,4).equals("d"))
				{
					result *= 16.0/15.0;
				}
				else if(note.substring(3,4).equals("b"))
				{
					result *= 15.0/16.0;
				}
				return result;	
			}
		}
		else
		{
			throw new NoteException("La note n'est pas valide","la note nai pa valide");
		}
	}
	
	/**
	 * Methode qui teste si la note est un silence
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si la note est un silence ou false sinon
	 */
	public boolean isSilence()
	{
		return (isValide() && (note.substring(0, 2).equalsIgnoreCase("SH")));
	}
	
	/**
	 * Methode qui modifie la note (SILENCE)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setSilence()
	{
		note = "SH" + note.substring(2, 7);
	}

	/**
	 * Methode toString
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return la note
	 */
	public String toString()
	{
		if(isValide())
		{
			return note;
		}
		else
		{
			return "note_non_valide";
		}
	}
	
	/**
	 * Methode qui indique si la note est un Do
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si c'est un DO et false sinon
	 */
	public boolean isDo()
	{
		return(isValide() && note.substring(0, 2).equalsIgnoreCase("DO"));
	}
	
	/**
	 * Methode qui modifie la note (DO)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setDo()
	{
		note = "DO" + note.substring(2, 7);
	}
	
	/**
	 * Methode qui indique si la note est un Re
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si c'est un Re et false sinon
	 */
	public boolean isRe()
	{
		return(isValide() && note.substring(0, 2).equalsIgnoreCase("RE"));
	}
	
	/**
	 * Methode qui modifie la note (RE)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setRe()
	{
		note = "RE" + note.substring(2, 7);
	}
	
	/**
	 * Methode qui indique si la note est un Mi
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si c'est un MI et false sinon
	 */
	public boolean isMi()
	{
		return(isValide() && note.substring(0, 2).equalsIgnoreCase("MI"));
	}
	
	/**
	 * Methode qui modifie la note (MI)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setMi()
	{
		note = "MI" + note.substring(2, 7);
	}
	
	
	/**
	 * Methode qui indique si la note est un Fa
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si c'est un FA et false sinon
	 */
	public boolean isFa()
	{
		return(isValide() && note.substring(0, 2).equalsIgnoreCase("FA"));
	}
	
	/**
	 * Methode qui modifie la note (FA)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setFa()
	{
		note = "FA" + note.substring(2, 7);
	}
	
	/**
	 * Methode qui indique si la note est un Sol
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si c'est un SOL et false sinon
	 */
	public boolean isSol()
	{
		return(isValide() && note.substring(0, 2).equalsIgnoreCase("SO"));
	}
	
	/**
	 * Methode qui modifie la note (SOL)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setSol()
	{
		note = "SO" + note.substring(2, 7);
	}
	
	/**
	 * Methode qui indique si la note est un La
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si c'est un LA et false sinon
	 */
	public boolean isLa()
	{
		return(isValide() && note.substring(0, 2).equalsIgnoreCase("LA"));
	}
	
	/**
	 * Methode qui modifie la note (LA)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setLa()
	{
		note = "LA" + note.substring(2, 7);
	}
	
	/**
	 * Methode qui indique si la note est un Si
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si c'est un SI et false sinon
	 */
	public boolean isSi()
	{
		return(isValide() && note.substring(0, 2).equalsIgnoreCase("SI"));
	}
	
	/**
	 * Methode qui modifie la note (SI)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setSi()
	{
		note = "SI" + note.substring(2, 7);
	}
	
	/**
	 * Methode qui indique si la note est une noire
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si c'est une noire et false sinon
	 */
	public boolean isNoire()
	{
		return(isValide() && note.substring(4, 6).equalsIgnoreCase("NO"));
	}
	
	/**
	 * Methode qui met la longueur de la note a noire (en conservant la pointee)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setNoire()
	{
		if(note.substring(6, 7).equalsIgnoreCase("P"))
		{
			note = note.substring(0, 4) + "NOP";
		}
		else
		{
			note = note.substring(0, 4) + "NOI";
		}
	}
	
	/**
	 * Methode qui indique si la note est une blanche
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si c'est une blanche et false sinon
	 */
	public boolean isBlanche()
	{
		return(isValide() && note.substring(4, 6).equalsIgnoreCase("BL"));
	}
	
	/**
	 * Methode qui met la longueur de la note a blanche (en conservant la pointee)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setBlanche()
	{
		if(note.substring(6, 7).equalsIgnoreCase("P"))
		{
			note = note.substring(0, 4) + "BLP";
		}
		else
		{
			note = note.substring(0, 4) + "BLA";
		}
	}
	
	/**
	 * Methode qui indique si la note est une ronde
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si c'est une ronde et false sinon
	 */
	public boolean isRonde()
	{
		return(isValide() && note.substring(4, 6).equalsIgnoreCase("RO"));
	}
	
	/**
	 * Methode qui met la longueur de la note a ronde (en conservant la portee)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setRonde()
	{
		if(note.substring(6, 7).equalsIgnoreCase("P"))
		{
			note = note.substring(0, 4) + "ROP";
		}
		else
		{
			note = note.substring(0, 4) + "RON";
		}
	}
	
	/**
	 * Methode qui indique si la note est une croche
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si c'est une croche et false sinon
	 */
	public boolean isCroche()
	{
		return(isValide() && note.substring(4, 6).equalsIgnoreCase("CR"));
	}
	
	/**
	 * Methode qui met la longueur de la note a croche (en conservant la pointee)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setCroche()
	{
		if(note.substring(6, 7).equalsIgnoreCase("P"))
		{
			note = note.substring(0, 4) + "CRP";
		}
		else
		{
			note = note.substring(0, 4) + "CRO";
		}
	}
	
	/**
	 * Methode qui indique si la note est une double croche
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si c'est une double croche et false sinon
	 */
	public boolean isDoubleCroche()
	{
		return(isValide() && note.substring(4, 6).equalsIgnoreCase("DC"));
	}
	
	/**
	 * Methode qui met la longueur de la note a double croche (en conservant la pointee)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setDoubleCroche()
	{
		if(note.substring(6, 7).equalsIgnoreCase("P"))
		{
			note = note.substring(0, 4) + "DCP";
		}
		else
		{
			note = note.substring(0, 4) + "DCR";
		}
	}
	
	/**
	 * Methode qui teste si la note est en bemol
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si la note est en bemol et false sinon
	 */
	public boolean isBemol()
	{
		return(isValide() && note.substring(3, 4).equalsIgnoreCase("b"));
	}
	
	/**
	 * Methode qui met la note en bemol
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setBemol()
	{
		note = note.substring(0, 3) + "b" + note.substring(4, 7);
	}
	
	/**
	 * Methode qui teste si la note est en diese
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return true si la note est diese et false sinon
	 */
	public boolean isDiese()
	{
		return(isValide() && note.substring(3, 4).equalsIgnoreCase("d"));
	}
	
	/**
	 * Methode qui met la note en diese
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setDiese()
	{
		note = note.substring(0, 3) + "d" + note.substring(4, 7);
	}
	
	/**
	 * Methode qui met la note en normal
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setNormal()
	{
		note = note.substring(0, 3) + "n" + note.substring(4, 7);
	}
	
	/**
	 * Methode qui teste si la note est pointee
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si la note est pointee et false sinon
	 */
	public boolean isPointee()
	{
		return(isValide() && note.substring(6, 7).equalsIgnoreCase("P"));
	}
	
	/**
	 * Methode qui rend la note pointee
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setPointee()
	{
		note = note.substring(0, 6) + "P";
	}
	
	/**
	 * Methode qui rend la note non pointee
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setNonPointee()
	{
		if(note.substring(4, 7).equalsIgnoreCase("NOP"))
		{
			note = note.substring(0, 4) + "NOI";
		}
		else if(note.substring(4, 7).equalsIgnoreCase("BLP"))
		{
			note = note.substring(0, 4) + "BLA";
		}
		else if(note.substring(4, 7).equalsIgnoreCase("ROP"))
		{
			note = note.substring(0, 4) + "RON";
		}
		else if(note.substring(4, 7).equalsIgnoreCase("CRP"))
		{
			note = note.substring(0, 4) + "CRO";
		}
		else if(note.substring(4, 7).equalsIgnoreCase("DCP"))
		{
			note = note.substring(0, 4) + "DCR";
		}
	}
	
	/**
	 * Methode qui retourne l octave utilise pour la note
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return L'octave de la note
	 */
	public int getOctave()
	{
		if(isValide())
		{
			return(Integer.parseInt(note.substring(2, 3)));
		}
		else
		{
			return(0);
		}
	}
	
	public void setOctave(int octave)
	{
		if((octave >= 2) && (octave <= 5))
		{
			note = note.substring(0, 2) + String.valueOf(octave) + note.substring(3, 7);
		}
	}
	
	/**
	 * Methode qui retourne un numero correspondant a la note (0 a 6)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le numero de la note
	 */
	public int getNumeroNote()
	{
		if(isDo())
			return(0);
		else if(isRe())
			return(1);
		else if(isMi())
			return(2);
		else if(isFa())
			return(3);
		else if(isSol())
			return(4);
		else if(isLa())
			return(5);
		else if(isSi())
			return(6);
		else
			return(7);
	}
	
	/**
	 * Methode qui retourne le numero d alteration (0 a 2)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le numero d'alteration
	 */
	public int getNumeroAlteration()
	{
		if(isBemol())
			return(1);
		else if(isDiese())
			return(2);
		else
			return(0);
	}
	
	/**
	 * Methode qui retourne le numero de pointee (0 ou 1)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le numero de pointee
	 */
	public int getNumeroPointee()
	{
		if(isPointee())
			return(1);
		else
			return(0);
	}
	
	/**
	 * Methode qui retourne le numero de la longueur de la note (0 a 4)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le numero de la longueur
	 */
	public int getNumeroLongueur()
	{
		if(isNoire())
			return(0);
		else if(isBlanche())
			return(1);
		else if(isRonde())
			return(2);
		else if(isCroche())
			return(3);
		else
			return(4);
	}
}

