package t2s.exception;

import java.io.*;

/**
 * Classe de ChargementProjetException heritant de SIVOXException
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class ChargementProjetException extends SIVOXException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * @author Ecole Polytechnique de Sophia Antipolis 
	 */
	public ChargementProjetException()
	{
		super();
	}
	
	/**
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param message Le message contenu dans l'exception
	 * @param messageSonore Le message sonore contenu dans l'exception
	 */
	public ChargementProjetException(String message, String messageSonore)
	{
		super(message, messageSonore);
	}
	
	/**
	 * Methode PrintStackStrace
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace()
	 */
	public void printStackTrace()
	{
		System.out.println("Erreur SIVOX Chargement de projet : "+messageErreur);
	}
	
	/**
	 * Methode printStackTrace(PrintStream)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace(java.io.PrintStream)
	 */
	public void printStackTrace(PrintStream s)
	{
		s.println("Erreur SIVOX Chargement de projet : "+messageErreur);
	}
	
	/**
	 * Methode printStackTrace(PrintWriter)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace(java.io.PrintWriter)
	 */
	public void printStackTrace(PrintWriter w)
	{
		w.println("Erreur SIVOX Chargement de projet : "+messageErreur);
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
		return(new ChargementProjetException(messageErreur, messageSonoreErreur));
	}
}
