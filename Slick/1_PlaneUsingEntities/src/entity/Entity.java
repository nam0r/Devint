package entity;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public abstract class Entity {
	
	protected Image image;
	
	protected float x;
	protected float y;
	protected float scale;
	
	/* ************ *
	 * Constructors *
	 * ************ */
	public Entity(String path) {
		
		try {
			this.image = new Image(path);
		} catch (SlickException e) {
			System.err.println("Couldn't load image");
		}
		
		this.x = 0;
		this.y = 0;
		
		scale = 1.0f;
		
	}
	
	public Entity(String path, float x, float y) {
		this(path);
		this.x = x;
		this.y = y;
	}
	
	public Entity(String path, float x, float y, float scale) {
		this(path,x,y);
		this.scale = scale;
	}
	
	/* **************** *
	 * Public Functions *
	 * **************** */
	public void draw() {
		image.draw(x, y, scale);
	}

}
