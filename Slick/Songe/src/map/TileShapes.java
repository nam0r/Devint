package map;

import java.util.HashMap;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

/**
 * Defines the tile's shape
 * 
 * @author Kevin Glass
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
	 * Returns the shape corresponding to the type of tile passed in param
	 * @param name The name of the shape we want to have
	 * @return The shape of the tile passed in parameter
	 * @throws SlickException if there is no shape corresponding to the name in parameter
	 */
	public static Shape getShapeByName(String name) throws SlickException {
		Shape shape = shapes.get(name);
		if (shape == null) {
			throw new SlickException("Unrecognised shape: "+name);
		}
		return shape;
	}
}
