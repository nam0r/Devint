package map;

import java.util.HashMap;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

/**
 * Definit les formes des tuiles.
 */
public class TileShapes {
	
	public static final HashMap<String, Shape> shapes = new HashMap<String, Shape>();

	public static final Polygon BLOCK;
	public static final Polygon LEFTSLOPE;
	public static final Polygon RIGHTSLOPE;
	
	static {
		BLOCK = new Polygon();
		BLOCK.addPoint(0,0);
		BLOCK.addPoint(1,0);
		BLOCK.addPoint(1,1);
		BLOCK.addPoint(0,1);
		shapes.put("BLOCK", BLOCK);
		
		LEFTSLOPE = new Polygon();
		LEFTSLOPE.addPoint(1,0);
		LEFTSLOPE.addPoint(1,1);
		LEFTSLOPE.addPoint(0,1);
		shapes.put("LEFTSLOPE", LEFTSLOPE);
		
		RIGHTSLOPE = new Polygon();
		RIGHTSLOPE.addPoint(0,0);
		RIGHTSLOPE.addPoint(1,1);
		RIGHTSLOPE.addPoint(0,1);
		shapes.put("RIGHTSLOPE", RIGHTSLOPE);
	}
	
	/**
	 * Renvoie la forme correspondant au type de tuile passe en parametre
	 * 
	 * @param name Le nom de la forme dont on veut obtenir les proprietes
	 * @return La forme correspondant au type de tuile passe en parametre
	 * @throws SlickException Si aucune forme ne correspond au nom passe en parametre
	 */
	public static Shape getShapeByName(String name) throws SlickException {
		Shape shape = shapes.get(name);
		if (shape == null) {
			throw new SlickException("Unrecognised shape: "+name);
		}
		
		return shape;
	}
}
