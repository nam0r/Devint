package t2s.exception;

import java.io.*;

/**
 * Classe generale des Exception de l'application SIVOX
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class SIVOXException extends Exception {

	private static final long serialVersionUID = 1L;
	protected String messageErreur;
	protected String messageSonoreErreur;
	
	/**
	 * Constructeur par defaut de SIVOXException
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public SIVOXException()
	{
		messageErreur = "Erreur inconnue";
		messageSonoreErreur = "erreur inconnu";
	}
	
	/**
	 * Constructeur par parametre de SIVOXException
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param message Le message contenu dans l'exception
	 * @param messageSonore Le message en version sonore
	 */
	public SIVOXException(String message, String messageSonore)
	{
		messageErreur = message;
		messageSonoreErreur = messageSonore;
	}
	
	/**
	 * Methode de modification du message ecrit
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param message Le nouveau message de l'exception
	 */
	public void setMessage(String message)
	{
		messageErreur = message;
	}
	
	/**
	 * Methode de modification du message sonore
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param messageSonore Le nouveau message sonore
	 */
	public void setMessageSonore(String messageSonore)
	{
		messageSonoreErreur = messageSonore;
	}
	
	/**
	 * Methode de récuperation du message ecrit
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 * @return Le message contenu dans l'exception
	 */
	public String getMessage()
	{
		return(messageErreur);
	}
	
	/**
	 * Methode de récuperation du message sonore
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le message sonore contenu dans l'exception
	 */
	public String getMessageSonore()
	{
		return(messageSonoreErreur);
	}
	
	/**
	 * Methode toString
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 * @return Le message contenu dans l'exception
	 */
	public String toString()
	{
		return(messageErreur);
	}
	
	/**
	 * Methode PrintStackStrace
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace()
	 */
	public void printStackTrace()
	{
		System.out.println("Erreur SIVOX : "+messageErreur);
	}
	
	/**
	 * Methode printStackTrace(PrintStream)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace(java.io.PrintStream)
	 */
	public void printStackTrace(PrintStream s)
	{
		s.println("Erreur SIVOX : "+messageErreur);
	}
	
	/**
	 * Methode printStackTrace(PrintWriter)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace(java.io.PrintWriter)
	 */
	public void printStackTrace(PrintWriter w)
	{
		w.println("Erreur SIVOX : "+messageErreur);
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
		return(new SIVOXException(messageErreur, messageSonoreErreur));
	}
}
