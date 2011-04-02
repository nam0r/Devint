
package t2s.prosodie;

import t2s.util.*;

/**
 * Classe decrivant les courbes
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class Courbe {

    private Syntagme synt;
    private int frequenceInit;
    private int nbPoint;
    private int hauteurNiveau;
    private double coeffk;
    private int xn;

    /** Constructeur par defaut
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param s le syntagme associe a la courbe
     * @param f la frequence initiale de la courbe
     * @param n le nombre de points utilise par la courbe
     * @param h la hauteur entre les 4 niveaux des courbes
     */
    public Courbe(Syntagme s, int f, int n, int h)
    {
    	synt =  s;
    	frequenceInit = f;
    	nbPoint = n;      
    	hauteurNiveau = h;
    	if (s.mineur()) 
    		coeffk = - (Integer.parseInt(ConfigFile.rechercher("COEFF_K_MINEUR")) * h)/Math.pow(1-n,2) ;
    	else 
    		if (s.majeur()) 
    			coeffk = - (Integer.parseInt(ConfigFile.rechercher("COEFF_K_MAJEUR"))*h)/Math.pow(1-n,2);
    		else 
    			coeffk = 0;
    	xn = 0;
    }

    /** 
     * Constructeur de courbe plus facile d'acces (valeur par defaut)
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param s le type de syntagme
     * @param n le nombre de points de la courbe
     */
    public Courbe(Syntagme s,  int n)
    {
    	this(s,Integer.parseInt(ConfigFile.rechercher("FREQUENCE_INIT")),n,Integer.parseInt(ConfigFile.rechercher("HAUTEUR_PALIER")));
    }

    /**
     * Pour construire une courbe constante
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param s le syntagme associe
     */
    public Courbe(Syntagme s)
    {
    	this(s,Integer.parseInt(ConfigFile.rechercher("FREQUENCE_INIT")),-1,Integer.parseInt(ConfigFile.rechercher("HAUTEUR_PALIER")));
    }
    
    /**
     * Pour obtenir la prochaine valeur de la courbe (iterateur)
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return l'entier correspondant
     */
    public int nextValue()
    {
    	if (xn == -1)
    	{
    		if (synt.finExclam()) 
    			return frequenceInit + Integer.parseInt(ConfigFile.rechercher("COEFF_EXCLAMATION"))  * hauteurNiveau;
    		else
    			return frequenceInit + hauteurNiveau;
    	}
    	else
    	{
    		xn++;
    		if (synt.mineur()) 
    			return valueA();
    		if (synt.majeur()) 
    			return valueB();
    		if (synt.finInterro())
    			return valueC();
    		if (synt.finExclam()) 
    			return valueE();
    		return valueD();
    	}
    }

    /** 
     * Methode permettant de savoir s'il reste des points a calculer sur la courbe
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return true s'il reste des points, false sinon
     */
    public boolean hasMoreValue()
    {
    	return xn < nbPoint;
    }

    /** 
     * Calcul du point suivant, pour une courbe de type A
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return la valeur ad'hoc
     */
    private int valueA()
    {
    	double d = (double)(xn-nbPoint);
    	return (int)(frequenceInit + Integer.parseInt(ConfigFile.rechercher("COEFF_HAUTEUR_A")) * hauteurNiveau + 
		     coeffk * Math.pow(d,Integer.parseInt(ConfigFile.rechercher("PUISSANCE_A"))));
    }
    
    /** 
     * Calcul du point suivant, pour une courbe de type B
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return la valeur ad'hoc
     */
    private int valueB()
    {
    	double d = (double)(xn-nbPoint);
    	return (int)(frequenceInit + Integer.parseInt(ConfigFile.rechercher("COEFF_HAUTEUR_B")) * hauteurNiveau + 
		     coeffk * Math.pow(d,Integer.parseInt(ConfigFile.rechercher("PUISSANCE_B"))));
    }


    /** 
     * Calcul du point suivant, pour une courbe de type C
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return la valeur ad'hoc
     */
    private int valueC()
    {
    	double r = Math.pow((double)(xn-1)/(nbPoint-1),Integer.parseInt(ConfigFile.rechercher("PUISSANCE_C")));
    	return (int)(frequenceInit + Integer.parseInt(ConfigFile.rechercher("COEFF_HAUTEUR_C"))*hauteurNiveau - 
		     Integer.parseInt(ConfigFile.rechercher("COEFF_H_SQRT_C")) * hauteurNiveau * Math.sqrt(1 -r));
    }


    /** 
     * Calcul du point suivant, pour une courbe de type D
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return la valeur ad'hoc
     */
    private int valueD()
    {
    	return (int) (frequenceInit + Integer.parseInt(ConfigFile.rechercher("COEFF_HAUTEUR_D")) * hauteurNiveau -
		      Integer.parseInt(ConfigFile.rechercher("COEFF_H_N-1_D")) * hauteurNiveau*(xn-1)/(nbPoint-1));
    }


    /** 
     * Calcul du point suivant, pour une courbe de type E
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return la valeur ad'hoc
     */
    private int valueE()
    {
    	return (int) (frequenceInit + Integer.parseInt(ConfigFile.rechercher("COEFF_HAUTEUR_E"))*hauteurNiveau - 
		      Integer.parseInt(ConfigFile.rechercher("COEFF_H_N-1_E")) * hauteurNiveau*(xn-1)/(nbPoint-1));
    }


    /** 
     * Une methode de test executable
     * @author Ecole Polytechnique de Sophia Antipolis
     */
    public static void main(String[] s)
    {
    	Courbe c = new Courbe(new Syntagme(Syntagme.MINEUR, Syntagme.PAUSE_COURTE),40);
    	System.out.println(c.coeffk);
    	while (c.hasMoreValue()) 
    		System.out.println(c.nextValue());
    }
}