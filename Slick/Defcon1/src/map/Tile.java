package map;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * Une tuile representee par une image et une forme
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
