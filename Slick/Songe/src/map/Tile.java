package map;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * A tile represented by an image and a shape
 * 
 * @author Kevin Glass
 */
public class Tile {
	private Shape shape;
	private Image image;
	
	public Tile(Image image, Shape shape) {
		this.shape = shape;
		this.image = image;
	}
	
	public Image getImage() {
		return image;
	}
	
	public Shape getShape() {
		return shape;
	}
}
