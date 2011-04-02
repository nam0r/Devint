package t2s.ihm.courbe;

import java.awt.*;

/**
 * Cette classe redéfinit un Point2D
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */
public class Point2D {

	public static final int WIDTH = 8;
	public static final int	HEIGHT = 8;
	java.awt.geom.Point2D.Double coord;
	private Rectangle rect;
	private boolean	visible = true;
	private Color color = Color.BLUE;

	
	/**
	 * Constructeur par defaut
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public Point2D(){
		coord = new java.awt.geom.Point2D.Double();
		rect = new Rectangle(0, 0, WIDTH, HEIGHT);
	}

	/**
	 * Constructeur qui prend un point en paramètre
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param p Le point 2d
	 */
	public Point2D(Point2D p){
		coord = new java.awt.geom.Point2D.Double(p.getX(), p.getY());
		rect = new Rectangle(0, 0, WIDTH, HEIGHT);
		rect.setLocation((int)p.getX() - 4, (int)p.getY() - 4);
	}

	/**
	 * Constructeur qui prend les coordonnées d'un point en paramètre
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param x La coordonnee x
	 * @param y La coordonnee y
	 */
	public Point2D(double x, double y){
		coord = new java.awt.geom.Point2D.Double(x, y);
		rect = new Rectangle(0, 0, WIDTH, HEIGHT);
		rect.setLocation((int)x - 4, (int)y - 4);
	}
	
	/**
	 * Methode qui retourne la couleur du point
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return La couleur du point
	 */
	public Color getColor() { return color; }
	
	/**
	 * Methode qui retourne la coordonnee x du point
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return La coordonnee x du point
	 */
	public double getX() { return coord.getX(); }
	
	/**
	 * Methode qui retourne la coordonnee y du point
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return
	 */
	public double getY() { return coord.getY(); }

	/**
	 * Methode qui change l'etat visible du point
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param b true si le point doit etre visible et false sinon
	 */
	public void setVisible(boolean b) { visible = b; }
	
	/**
	 * Methode qui change la couleur du point
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param c La nouvelle couleur du point
	 */
	public void setColor(Color c) { color = c; }
	
	/**
	 * Methode qui change la location du point
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param x La coordonnee x
	 * @param y La coordonnee y
	 */
	public void setLocation(double x, double y){
		coord.setLocation(x, y);
		rect.setLocation((int)x - 4, (int)y - 4);
	}
	
	/**
	 * Methode qui modifie la location
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param x La coordonnee x
	 * @param y La coordonnee y
	 * @param dim La dimension
	 */
	public void setLocation(double x, double y, Dimension dim)
	{
		if (x < WIDTH / 2)
			x = WIDTH / 2;
		else if (x > dim.width - WIDTH / 2)
			x = dim.width  - WIDTH / 2;
		if (y < HEIGHT / 2)
			y = HEIGHT / 2;
		else if (y > dim.height - HEIGHT / 2)
			y = dim.height - HEIGHT / 2;
		setLocation(x, y);
	}
	
	/**
	 * Methode qui indique si le point est visible ou pas
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si le point est visible et false sinon
	 */
	public boolean isVisible() { return visible; }

	/**
	 * Vérifie si les coordonnées appartiennent au point
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param x La coordonnee x
	 * @param y La coordonnee y
	 * @return true si les coordonnee appartienent au point et false sinon
	 */
	public boolean contains(double x, double y){
		if (x > getX() - WIDTH / 2  && x < getX() + WIDTH / 2)
			if (y > getY() - HEIGHT / 2  && y < getY() + HEIGHT / 2)
				return true;
		return false;
	}

	/**
	 * Méthode qui dessine les points
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param g Le graphics2D dessinateur
	 */
	public void draw(Graphics2D g){
		if (!visible)
			return;
		g.setPaint(color);
		g.fill(this.rect);
	}

	/**
	 * Transforme un point de cette classe en un Point2D de java.awt.geom
	 * @return le point 2D de java.awt.geom
	 */
	public java.awt.geom.Point2D.Double toAwt(){
		java.awt.geom.Point2D.Double point = new java.awt.geom.Point2D.Double(getX(), getY());
		return point;
	}

}
