package t2s.newProsodies.courbe;


import java.util.Vector;

import t2s.ihm.courbe.Point2D;


/**
 * Permet d'obtenir les ordonnees d'une fonction construite par interpolation
 * a l'aide d'une liste de points
 */
public class InterpolationLagrange {
	
	private Vector<Point2D> points; //les points utilises pour faire l'interpolation
	
	
	/**
	 * Constructeur de la classe
	 *
	 */
	public InterpolationLagrange(){
		points = new Vector<Point2D>();
	}
	
	public InterpolationLagrange(Vector<Point2D> l) {
		points = l;
	}
	
	/**
	 * Renvoie la valeur au point d'abscisse x de la fonction polynome
	 * (resultat de l'interpollation)	  
	 * @param x: abscisse
	 * @return: ordonnee au point d'abscisse x
	 */
	public double valeurFonctionInterpolee(double x){
		double y=0;
		for(int i=0; i < points.size(); i++){
			y = y + new Double((Double)(points.get(i).getY())).doubleValue() * coeffLagrange(x,i);
		}
		return y;
	}
	
	
	/**
	 * Calcule le coefficient de Lagrange Li
	 * @param x: abscisse
	 * @param i: indice
	 * @return
	 */
	private double coeffLagrange(double x, int i){
		double num=1, denom=1;
		for(int j=0; j < points.size(); j++){
			if(i!=j){
				num = num * (x - new Double((Double)(points.get(j).getX())).doubleValue());
				denom = denom * (new Double((Double)(points.get(i).getX())).doubleValue() 
						- new Double((Double)(points.get(j).getX())).doubleValue());
			}
		}
		return num/denom;
	}
	
	
	/**
	 * Ajoute un point d'interpolation
	 * @param x: abscisse
	 * @param y: ordonnee
	 */
	public void addPoint(double x, double y){
		Point2D point = new Point2D(x,y);
		points.add(point);
	}
	
	
	/**
	 * Ajoute une liste de points
	 * @param listePoints: Liste de points (formee d'une liste contenant l'abscisse puis l'ordonnee)
	 */
	public void addPoints(Vector<Point2D> listePoints){
		for(int i=0; i<listePoints.size(); i++){
			points.add(listePoints.get(i));
		}			
	}
	
	
	/**
	 * Supprime le point d'abscisse x de la liste des points d'interpolation
	 * @param x
	 */
	public void removePoint(double x){
		int i=0;
		while(x!=(new Double((Double)points.get(i).getX()).doubleValue()) && i<points.size())
			i++;
		if(i!=points.size())
			points.remove(i);	
	}
	
	
	/**
	 * Supprime le point d'indice "indice" de la liste des points d'interpolation
	 * @param indice
	 */
	public void removePointAtIndice(int indice){
		if(indice<points.size())
			points.remove(indice);
	}
	
	public Vector<Point2D> getPointsCle(){
		return points;
	}
}
