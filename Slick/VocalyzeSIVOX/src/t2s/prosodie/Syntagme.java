
package t2s.prosodie;

import t2s.traitement.*;
import t2s.util.*;

/**
 * Classe des Syntagme
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class Syntagme {

    /** Syntagme mineur : debut ou milieu de phrase mineure */
    protected static final int MINEUR = 1;
    /** Syntagme majeur : debut ou milieu de phrase majeure*/
    protected static final int MAJEUR = 2;
    /** Syntagme interogatif : fin de phrase interrogative */
    protected static final int FIN_INTERRO = 3;
    /** Syntagme final : fin de phrase */
    protected static final int FIN = 4;
    /** Syntagme exclamatif : fin de phrase exclamative */
    protected static final int FIN_EXCLAM = 5;
    protected static final int CDD= 6;
    protected static final int CMM = 7;
    protected static final int CD = 8;
    protected static final int CM = 9;
    
    /** Une pause courte : conjonction de coordination */
    protected static final int PAUSE_COURTE = Integer.parseInt(ConfigFile.rechercher("PAUSE_COURTE")); 
    /** Une pause longue : conjonction de subordination */
    protected static final int PAUSE_LONGUE = Integer.parseInt(ConfigFile.rechercher("PAUSE_LONGUE"));
    /** La pause finale : pour le <i>'point final'</i> */
    protected static final int PAUSE_FIN = Integer.parseInt(ConfigFile.rechercher("PAUSE_FIN"));
    
    private int synt;
    private int pause;

    /** Constructeur de syntagme
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param s le type de syntagme que l'on souhaite creer (a choisir parmis les constantes)
     * @param d la duree de la pause associee (a choisir parmis les constantes)
     */
    public Syntagme(int s, int d)
    {
    	synt =  s;
    	pause = d;
    }

    /** 
     * Constructeur de Syntagme par defaut
     * @author Ecole Polytechnique de Sophia Antipolis
     */
    protected Syntagme()
    {
    	this(0,0);
    }

    /** 
     * Pour recuperer le code representant la duree de la pause
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return l'entier ad'hoc
     */
    protected int dureePause()
    {
    	return pause;
    }

    /** Pour determiner le type de syntagme final d'une phrase
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param ponctuation la ponctuation de cette phrase
     * @return le Syntagme
     */
    protected static Syntagme typeFin(int ponctuation)
    {
    	Syntagme synt = new Syntagme();
    	switch (ponctuation)
    	{
    		case (Phrase.VIRGULE ) : 
    			if (Random.negatif()) 
    				synt =  new Syntagme(MINEUR, PAUSE_COURTE);
    			else  
    				synt =  new Syntagme(MAJEUR, PAUSE_COURTE); 
    		break;
    		case (Phrase.POINT): 
    			synt = new Syntagme(FIN, PAUSE_FIN);
    		break;
    		case (Phrase.DEFAUT): 
    			synt = new Syntagme(FIN, PAUSE_FIN);
    		break;
    		case (Phrase.INTERROGATION):
    			synt = new Syntagme(FIN_INTERRO, PAUSE_FIN);
    		break;
    		case (Phrase.EXCLAMATION):
    			synt = new Syntagme(FIN_EXCLAM, PAUSE_FIN);
    		break;
    	}
    	return synt;
    }

    /** Pour determiner le type de syntagme de coupure en fonction du type de coupure % ou %%.
     * <p><b>Precondition</b> : ph contient au moins un % </p>
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param ph : une phrase 
     * @param coupure : l'indice dans ph du premier %
     * @return le Syntagme qui va bien ^_^.
     */
    protected static Syntagme type(String ph, int coupure)
    {
    	if (ph.charAt(coupure+1) == '%') 
    		return new Syntagme(MINEUR,PAUSE_COURTE) ;
    	else 
    		return new Syntagme(MAJEUR,PAUSE_LONGUE);
    }
        
    /** 
     * Est-ce un syntagme final ?
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return true si c'est le cas, false sinon
     */
    protected boolean fin()
    {
    	return (FIN_INTERRO <= synt ) && (synt <= FIN_EXCLAM);
    }

    /** 
     * Est-ce un syntagme de fin d'exclamation ?
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return true si c'est le cas, false sinon
     */
    protected boolean finExclam()
    {
    	return  (synt == FIN_EXCLAM);
    }

    /** 
     * Est-ce un syntagme de fin d'interrogation ?
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return true si c'est le cas, false sinon
     */
    protected boolean finInterro()
    {
    	return  (synt == FIN_INTERRO);
    }

    /** 
     * Est-ce un syntagme de pause longue?
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return true si c'est le cas, false sinon
     */
    protected boolean longb()
    {
    	return (pause == PAUSE_LONGUE);
    }

    /** 
     * Est-ce un syntagme de pause courte ?
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return true si c'est le cas, false sinon
     */
    protected boolean court()
    {
    	return (pause == PAUSE_COURTE);
    }

    /** 
     * Est-ce un syntagme mineur ?
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return true si c'est le cas, false sinon
     */
    protected boolean mineur()
    {
    	return (synt == MINEUR);
    }

    /** 
     * Est-ce un syntagme majeur ?
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return true si c'est le cas, false sinon
     */
    protected boolean majeur()
    {
    	return (synt == MAJEUR);
    }
    
    protected boolean CDD()
    {
    	return (synt == CDD);
    }
    
    protected boolean CMM()
    {
    	return (synt == CMM);
    }
    
    protected boolean CD()
    {
    	return (synt == CD);
    }
    
    protected boolean CM()
    {
    	return (synt == CM);
    }
}