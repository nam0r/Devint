
package t2s.prosodie;

/**
 * Classe qui represente un couple pourcentage-frequence
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */
public class CoupleProsodie {
  
    private int pourcentage;
    private int freq;
    
    /**
     * Constructeur par defaut de CoupleProsodie
     * @author Ecole Polytechnique de Sophia Antipolis
     */
    public CoupleProsodie()
    {
    	pourcentage=0;
    	freq=0;
    }
    
    /**
     * Constructeur par parametre de CoupleProsodie
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param p Le pourcentage du couple
     * @param f La frequence du couple
     */
    public CoupleProsodie( int p, int f)
    {
    	pourcentage=p;
    	freq=f;
    }
    
    /**
     * Methode qui retourne le pourcentage
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return Le pourcentage du couple
     */
    public int getPourcentage()
    {
    	return pourcentage;
    }
    
    /**
     * Methode qui retourne la frequence
     * @author Ecole Polytechnique de Sophia Antipolis
     * @return La frequence du couple
     */
    public int getFrequence()
    {
    	return freq;
    }

    /**
     * Methode qui modifie le pourcentage du couple
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param p Le nouveau pourcentage
     */
    public void setPourcentage( int p)
    {
    	pourcentage=p;
    }
    
    /**
     * Methode qui modifie la frequence du couple
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param f La nouvelle frequence du couple
     */
    public void setFrequence(int f)
    {
    	freq = f;
    }
    
    /**
     * Methode qui retourne une chaine representant le couple
     * @author Ecole Polytechnique de Sophia Antipolis
     */
    public String toString()
    {
    	return getPourcentage()+" "+getFrequence() + " " ;
    }
}
