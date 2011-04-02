package t2s.exception;

import java.io.*;

/**
 * Classe Analyse Exception heritant de SIVOXException
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class AnalyseException extends SIVOXException {
    
	private static final long serialVersionUID = 1L;
	protected int ligne;
    
	/**
	 * Constructeur par defaut de AnalyseException
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param l La ligne ou l'on a levee l'exception
     */
    public AnalyseException(int l)
    {
    	super();
    	ligne = l;
    }
    
    /** 
     * Constructeur par parametre de AnalyseException
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param message Le message contenu dans l'exception AnalyseException
     * @param messageSonore Le message sonore contenu dans l'exception
     * @param l La ligne ou l'on a leve l'exception
     */
    public AnalyseException(String message, String messageSonore, int l)
    {
    	super(message, messageSonore);
    	ligne = l;
    }
    
    /**
     * Methode toString
     * @author Ecole Polytechnique de Sophia Antipolis
     * (non-Javadoc)
     * @see t2s.exception.SIVOXException#toString()
     * @return Le message contenu dans l'exception
     */
    public String toString()
    {
    	return(super.toString()+" a la ligne "+ligne);
    }
    
    /**
	 * Methode PrintStackStrace
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace()
	 */
	public void printStackTrace()
	{
		System.out.println("Erreur SIVOX Analyse : "+messageErreur+" a la ligne "+ligne);
	}
	
	/**
	 * Methode printStackTrace(PrintStream)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace(java.io.PrintStream)
	 */
	public void printStackTrace(PrintStream s)
	{
		s.println("Erreur SIVOX Analyse : "+messageErreur+" a la ligne "+ligne);
	}
	
	/**
	 * Methode printStackTrace(PrintWriter)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace(java.io.PrintWriter)
	 */
	public void printStackTrace(PrintWriter w)
	{
		w.print("Erreur SIVOX Analyse : "+messageErreur+" a la ligne "+ligne);
	}
	
    /**
     * Methode qui change la ligne de l'exception
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param l Le ligne ou l'on a levee l'exception
     */
    public void setLigne(int l)
    {
    	ligne = l;
    }
    
    /**
     * Methode qui retourne le numero de la ligne ou l'on a levee l'exception
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return Le numero de ligne
     */
    public int getLigne()
    {
    	return(ligne);
    }
    
    /**
	 * Methode clone
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 * @return Une copie de l'exception courante
	 */
	public Object clone()
	{
		return(new AnalyseException(messageErreur, messageSonoreErreur, ligne));
	}
}
