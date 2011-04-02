package t2s.ihm.courbe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Iterator;
import java.util.Vector;
import t2s.newProsodies.courbe.*;

/**
 * Classe Tableau
 * @author Ecole Polytechnique de Sophia Antipolis
 */
public class Tableau {

	private int mArrowLength, mTickSize, padding, current = 0;
	public static Dimension	dim;
	private Vector<Point2D> listePointsCles, listePoints;	
	private boolean	visible = true;
	
	/**
	 * Constructeur par defaut de tableau
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param d La dimension du tableau
	 */
	public Tableau(Dimension d){
		listePointsCles = new Vector<Point2D>();
		listePoints = new Vector<Point2D>();
		dim = d;
		mArrowLength = 4;
		mTickSize = 4;
		padding = 50;
	}
	
	/**
	 * Methode qui retourne la dimension du tableau
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return La dimension du tableau
	 */
	public Dimension getDimension() { return dim; }
	
	/**
	 * Methode qui retourne la liste des points cles
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return La liste des points cles
	 */
	public Vector<Point2D> getListePointsCles() { return listePointsCles; }
	
	/**
	 * Methode qui retourne la liste des points
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return La liste des points
	 */
	public Vector<Point2D> getListePoints() { return listePoints; }
	
	/**
	 * Methode qui retourne l element courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return L'element courant
	 */
	public int getCurrent() { return current; }
	
	/**
	 * Methode qui modifie la liste des points cles
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param lpc
	 */
	public void setListePointsCles (Vector<Point2D> lpc)
	{
		listePointsCles.clear(); listePointsCles.addAll(lpc);
	}
	
	/**
	 * Methode qui modifie la liste des points
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param lp La nouvelle liste des points
	 */
	public void setListePoints (Vector<Point2D> lp)
	{
		listePoints.clear(); listePoints.addAll(lp);
	}
	
	/**
	 * Methode qui modifie la dimension
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param d la nouvelle dimension
	 */
	public void setDimension(Dimension d)
	{
		dim = d;
	}
	
	/**
	 * Methode qui indique la visibilite
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return true si visible et false sinon
	 */
	public boolean isVisible()
	{
		return visible;
	}
	
	/**
	 * Methode qui vide la liste de points cles et la liste de points
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void clear()
	{
		listePointsCles.clear();
		listePoints.clear();
	}
	
	/**
	 * Méthode qui vérifie si un point de coordonnées (x,y) appartient à la liste de points cles
	 * @param x La coordonnee x du point a tester
	 * @param y La coordonne y du point a tester
	 * @return True si le point appartient a la liste des points cle et false sinon
	 */
	public boolean contains(double x, double y)
	{
		for (int i = 0; i < listePointsCles.size(); ++i)
			if (listePointsCles.get(i).contains(x, y))
			{
				listePointsCles.get(current).setColor(Color.BLUE);
				current = i;
				listePointsCles.get(current).setColor(Color.PINK);
				return true;
			}
		return false;
	}
	
	/**
	 * Methode de convertion graphique
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param point Le point 2D
	 * @return le pointd2D double
	 */
	public java.awt.geom.Point2D.Double convertGraphique(Point2D point)
	{
		int epsilon = 20;
		int coefY = (Tableau.dim.height - 60 - 70) / (2 * (int)CalculCourbe.amplitude + 20);
		float coefX = 720.0f / CalculCourbe.dureeTotale; 
		java.awt.geom.Point2D.Double p = new java.awt.geom.Point2D.Double(50 + (point.getX() * coefX), dim.height - 60 - epsilon - (point.getY() * coefY));
		return p;
	}
	
	/**
	 * Methode qui converti
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param vpoint Le vecteur de Point2D d'entree
	 * @return Un vecteur de Point2D
	 */
	public Vector<Point2D> convertCoordPho(Vector<Point2D> vpoint)
	{
		int epsilon = 20;
		int coefY = (Tableau.dim.height - 60 - 70) / (2 * (int)CalculCourbe.amplitude + 20);
		float coefX = 720.0f / CalculCourbe.dureeTotale; 
		Vector<Point2D> v = new Vector<Point2D>();
		for (int i = 0; i < vpoint.size(); i++)
		{
			Point2D p = new Point2D((vpoint.get(i).getX() - 50) / coefX, 
					(vpoint.get(i).getY() - dim.height + 60 + epsilon) / (-coefY) + (int)CalculCourbe.frequence - (CalculCourbe.amplitude + 10));
			v.add(p);
		}
		return v;
	}
	
	/**
	 * Méthode qui dessine la courbe, et les points cles
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void draw(Graphics2D g)
	{
		if (listePointsCles == null)
			return;
		if (!visible || listePointsCles.size() <= (2 - 1))
			return;
		drawAxes(g);
		Iterator<Point2D> it = null; 
		Line2D.Double line = new Line2D.Double();
		Point2D p1, p2;
		it = listePoints.iterator(); 
		p2 = it.next();
		while (it.hasNext()) {
			p1 = p2;
			p2 = it.next();
			if (p1 != null && p2 != null){
				line.setLine(new java.awt.geom.Point2D.Double(p1.getX(), p1.getY()), new java.awt.geom.Point2D.Double(p2.getX(), p2.getY()));
				g.draw(line);
			}
		}
		for (it = listePointsCles.iterator(); it.hasNext();) {
			it.next().draw(g);
		}
	}

	/**
	 * Méthode qui dessine le repere
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param g2 Le dessinateur Graphics3D
	 */
	void drawAxes(Graphics2D g2)
	{
		g2.setPaint(Color.WHITE);
		g2.setStroke(new BasicStroke(2.0f));
		g2.setFont(new Font("Courier",Font.BOLD,14));																						// legende
		g2.drawLine(padding, dim.height - padding, dim.width - padding, dim.height - padding);												// axe horizontal
		g2.drawLine(padding, padding, padding, dim.height - padding);																		// axe vertical
		g2.drawLine(padding, padding, padding - mArrowLength, padding + mArrowLength);														// flèche axe vertical
		g2.drawLine(padding, padding, padding + mArrowLength, padding + mArrowLength);
		g2.drawLine(dim.width - padding, dim.height - padding, dim.width - padding - mArrowLength, dim.height - padding - mArrowLength);	// flèche axe horizontal
		g2.drawLine(dim.width - padding, dim.height - padding, dim.width - padding - mArrowLength, dim.height - padding + mArrowLength);	
		
		int lgrAxeX = dim.width - 170;
		int dureeTotale = CalculCourbe.dureeTotale, lgrGraduation = 0, dureePhoConcat = 0;
		double dureePho = 0;
		for (int i = 0; i < CalculCourbe.listePhoSymboles.size(); i++) {
			dureePho = CalculCourbe.dureePhonemes.get(i);
			dureePhoConcat += dureePho;
			if (CalculCourbe.listePhoSymboles.size() > 25) {
				if ((i % 2) == 0)
					g2.drawString("[" + CalculCourbe.listePhoSymboles.get(i) + "]", 50 + lgrGraduation - 10, 40);							// phoneme
				else
					g2.drawString("[" + CalculCourbe.listePhoSymboles.get(i) + "]", 50 + lgrGraduation - 10, 55);							// phoneme
			}
			else
				g2.drawString("[" + CalculCourbe.listePhoSymboles.get(i) + "]", 50 + lgrGraduation - 10, 40);								// phoneme
			lgrGraduation = (dureePhoConcat * lgrAxeX) / dureeTotale;
			g2.drawLine(50 + lgrGraduation, dim.height - 50 - mTickSize, 50 + lgrGraduation, dim.height - 50 + mTickSize);					// graduations horizontales
			if (CalculCourbe.listePhoSymboles.size() > 25) {
				if ((i % 2) == 0)
					g2.drawString((int)dureePhoConcat + "", 50 + lgrGraduation - 10, dim.height - 25);										// duree phoneme
				else
					g2.drawString((int)dureePhoConcat + "", 50 + lgrGraduation - 10, dim.height - 10);										// duree phoneme
			}
			else
				g2.drawString((int)dureePhoConcat + "", 50 + lgrGraduation - 10, dim.height - 20);											// duree phoneme
		}
		
		g2.drawLine(padding - mTickSize, padding + 20, padding + mTickSize, padding + 20);													// graduations verticales
		g2.drawLine(padding - mTickSize, dim.height - 60, padding + mTickSize, dim.height - 60);
		g2.drawLine(padding - mTickSize, ((dim.height - 60 - (padding + 20)) / 2) + padding + 20, padding + mTickSize, ((dim.height - 60 - (padding + 20)) / 2) + padding + 20);
		g2.drawString("fréquence (Hz)", 15, 20);	
		g2.drawString("durée", dim.width - 60, dim.height - 25);
		g2.drawString("(ms)", dim.width - 55, dim.height - 10);
		g2.drawString("0", padding, dim.height - 20);																						// origine
		g2.drawString((int)CalculCourbe.frequence + "", 15, ((dim.height - 60 - (padding + 20)) / 2) + padding + 20 + 3);					// frequence
		g2.drawString((int)CalculCourbe.frequence + (int)CalculCourbe.amplitude + 10 + "", 15, padding + 20 + 3);							// frequence + amplitude
		g2.drawString((int)(CalculCourbe.frequence - (int)CalculCourbe.amplitude - 10) + "", 15, dim.height - 60 + 3);						// frequence - amplitude
    }
}
