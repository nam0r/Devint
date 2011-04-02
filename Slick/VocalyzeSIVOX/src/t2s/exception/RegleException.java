package t2s.exception;

import java.io.*;

/**
 * Classe RegleException heritant de AnalyseException
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class RegleException extends AnalyseException {
    
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur par defaut de RegleException
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param ligne La ligne ou l'exception a ete levee
	 */
	public RegleException(int ligne)
    {
    	super(ligne);
    }
	
	/**
	 * Constructeur par parametre de RegleException
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param message Le message contenu dans l'exception
	 * @param messageSonore Le message sonore contenu dans l'exception
	 * @param ligne La ligne ou l'exceptiona ete levee
	 */
    public RegleException(String message, String messageSonore, int ligne)
    {
    	super(message, messageSonore, ligne);
    }
    
    /**
	 * Methode PrintStackStrace
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace()
	 */
	public void printStackTrace()
	{
		System.out.println("Erreur SIVOX Regle : "+messageErreur+" a la ligne "+ligne);
	}
	
	/**
	 * Methode printStackTrace(PrintStream)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace(java.io.PrintStream)
	 */
	public void printStackTrace(PrintStream s)
	{
		s.println("Erreur SIVOX Regle : "+messageErreur+" a la ligne "+ligne);
	}
	
	/**
	 * Methode printStackTrace(PrintWriter)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace(java.io.PrintWriter)
	 */
	public void printStackTrace(PrintWriter w)
	{
		w.println("Erreur SIVOX Regle : "+messageErreur+" a la ligne "+ligne);
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
		return(new RegleException(messageErreur, messageSonoreErreur, ligne));
	}
}
