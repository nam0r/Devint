package t2s.chant;

/**
 * Classe NoteGraphique heritant de Note
 * @author EcolePolytechnique de Sophia Antipolis
 */

public class NoteGraphique extends Note {

	int departX;
	String text = "      Aucun      ";
	boolean isSync = false;
	
	/**
	 * Constructeur par defaut de Notegraphique
	 * @author EcolePolytechnique de Sophia Antipolis
	 */
	public NoteGraphique()
	{
		super();
		departX = 0;
	}
	
	/**
	 * Constructeur par parametre de NoteGraphique
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @param note La chaine de la note
	 * @param dpX le point de depart de la note (en X)
	 */
	public NoteGraphique(String note, int dpX)
	{
		super(note);
		departX = dpX;
	}
	
	/**
	 * Methode qui modifie la position de depart de la note graphique
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @param x La position X de depart
	 */
	public void setDepartX(int x)
	{
		departX = x;
	}
	
	/**
	 * Methode qui retourne la position de depart de la note graphique
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @return Le position X de depart
	 */
	public int getDepartX()
	{
		return(departX);
	}
	
	/**
	 * Methode qui modifie le texte associe a la note
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @param t
	 */
	public void setText(String t)
	{
		text = t;
	}
	
	/**
	 * Methode qui retourne le texte associe a la note
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @return Le texte associe a la note
	 */
	public String getText()
	{
		return(text);
	}
	
	/**
	 * Methode qui retourne le booleen de synchronisation
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @return True si la note est synchronisee et false sinon
	 */
	public boolean getSync()
	{
		return(isSync);
	}
	
	/**
	 * Methode qui modifie le booleen de synchronisation
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @param value La valeur a appliquer (true = synchro et false = non synchro)
	 */
	public void setSync(boolean value)
	{
		isSync = value;
	}
}
