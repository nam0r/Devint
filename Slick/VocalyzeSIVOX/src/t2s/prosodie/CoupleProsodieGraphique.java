package t2s.prosodie;

/**
 * Classe representant un couple prosodie graphique heritant de coupleProsodie
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */
public class CoupleProsodieGraphique extends CoupleProsodie {

	private int x;
	private int y;
	
	/**
	 * Constructeur par defaut de CoupleProsodieGraphique
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public CoupleProsodieGraphique()
	{
		super();
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Constructeur par parametre de CoupleProsodieGraphique
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param p Le pourcentage du couple
	 * @param f La frequence du couple
	 * @param x La position x du couple
	 * @param y La position y du couple
	 */
	public CoupleProsodieGraphique(int p, int f, int x, int y)
	{
		super(p, f);
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Methode qui retourne la position x du coupleprosodieGraphique
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return La position x
	 */
	public int getX()
	{
		return(x);
	}
	
	/**
	 * Methode qui retourne la position y du coupleProsodieGraphique
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return La position y
	 */
	public int getY()
	{
		return(y);
	}
	
	/**
	 * Methode qui modifie la position x du coupleProsodieGraphique
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param x La nouvelle position x
	 */
	public void setX(int x)
	{
		if(x>=0)
			this.x = x;
	}
	
	/**
	 * Methode qui modifie la position y du coupleProsodieGraphique
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param y
	 */
	public void setY(int y)
	{
		if(y>=0)
			this.y = y;
	}
}
